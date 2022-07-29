package br.com.oobj.escalar.processador;

import br.com.oobj.escalar.io.EscritorTXT;
import br.com.oobj.escalar.io.LeitorTXT;
import br.com.oobj.escalar.jms.Enfileirador;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.IOException;

@Component
public class ProcessadorDeArquivos {

    @Value("${escalar.diretorio.entrada}")
    private String DiretorioEntrada;
    @Value("${escalar.diretorio.processados}")
    private String DiretorioProcessados;
    private final EscritorTXT escritorTXT;
    private final Enfileirador enfileirador;
    private final LeitorTXT leitorTXT;
    private final MovedorDeArquivo movedorDeArquivo;

    public ProcessadorDeArquivos(EscritorTXT escritorTXT, Enfileirador enfileirador, LeitorTXT leitorTXT, MovedorDeArquivo movedorDeArquivo) {
        this.escritorTXT = escritorTXT;
        this.enfileirador = enfileirador;
        this.leitorTXT = leitorTXT;
        this.movedorDeArquivo = movedorDeArquivo;
    }

    public void processaArquivo(String requisicao) {
        try {
            String nomeArquivo = escritorTXT.escreve(requisicao, "entrada", DiretorioEntrada);
            if (nomeArquivo != null) {
                String arquivoLido = leitorTXT.leia(nomeArquivo, DiretorioEntrada);
                enfileirador.enviaMensagem(arquivoLido);
                movedorDeArquivo.moveArquivo(nomeArquivo, DiretorioEntrada, DiretorioProcessados);
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever/ler arquivo. " + e.getMessage());
        } catch (NamingException e) {
            System.out.println("Erro ao configurar fila. " + e.getMessage());
        } catch (JMSException e) {
            System.out.println("Erro ao enviar arquivo para fila. " + e.getMessage());
        }
    }
}
