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

    public ProcessadorDeArquivos(EscritorTXT escritorTXT, Enfileirador enfileirador, LeitorTXT leitorTXT) {
        this.escritorTXT = escritorTXT;
        this.enfileirador = enfileirador;
        this.leitorTXT = leitorTXT;
    }

    public void processaArquivo(String requisicao) throws NamingException, JMSException {
        String nomeArquivo = escritorTXT.escreve(requisicao, "entrada", DiretorioEntrada);
        String arquivoLido = leitorTXT.leia(nomeArquivo, DiretorioEntrada);
        enfileirador.enviaMensagem(arquivoLido, nomeArquivo, DiretorioEntrada, DiretorioProcessados);
    }
}
