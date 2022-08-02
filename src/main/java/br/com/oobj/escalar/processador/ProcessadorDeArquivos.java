package br.com.oobj.escalar.processador;

import br.com.oobj.escalar.io.EscritorTXT;
import br.com.oobj.escalar.io.LeitorTXT;
import br.com.oobj.escalar.jms.Enfileirador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.IOException;

@Component
public class ProcessadorDeArquivos {

    private final Logger logger = LoggerFactory.getLogger(ProcessadorDeArquivos.class);
    @Value("${escalar.diretorio.entrada}")
    private String DiretorioEntrada;
    @Value("${escalar.diretorio.processados}")
    private String DiretorioProcessados;
    private final EscritorTXT escritorTXT;
    private final Enfileirador enfileirador;
    private final LeitorTXT leitorTXT;
    private final MovedorDeArquivos movedorDeArquivos;

    public ProcessadorDeArquivos(EscritorTXT escritorTXT, Enfileirador enfileirador, LeitorTXT leitorTXT, MovedorDeArquivos movedorDeArquivos) {
        this.escritorTXT = escritorTXT;
        this.enfileirador = enfileirador;
        this.leitorTXT = leitorTXT;
        this.movedorDeArquivos = movedorDeArquivos;
    }

    public void processaArquivo(String requisicao) {
        try {
            String nomeArquivo = escritorTXT.escreve(requisicao, "entrada", DiretorioEntrada);
            if (nomeArquivo != null) {
                String arquivoLido = leitorTXT.leia(nomeArquivo, DiretorioEntrada);
                enfileirador.enviaMensagem(arquivoLido);
                movedorDeArquivos.moveArquivo(nomeArquivo, DiretorioEntrada, DiretorioProcessados);
            }
        } catch (IOException e) {
            logger.error("Erro ao escrever/ler arquivo. " + e.getMessage());
        } catch (NamingException e) {
            logger.error("Erro ao configurar fila. " + e.getMessage());
        } catch (JMSException e) {
            logger.error("Erro ao enviar arquivo para fila. " + e.getMessage());
        }
    }
}
