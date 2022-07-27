package br.com.oobj.escalar.processador;

import br.com.oobj.escalar.io.EscritorTXT;
import br.com.oobj.escalar.jms.Enfileirador;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.naming.NamingException;

@Component
public class ProcessadorDeArquivos {

    private static final String DiretorioEntrada = "src/main/resources/entrada/";
    private final EscritorTXT escritorTXT;
    private final Enfileirador enfileirador;

    public ProcessadorDeArquivos(EscritorTXT escritorTXT, Enfileirador enfileirador) {
        this.escritorTXT = escritorTXT;
        this.enfileirador = enfileirador;
    }

    public void processaArquivo(String requisicao) throws NamingException, JMSException {
        escritorTXT.escreve(requisicao, "entrada", DiretorioEntrada);
        enfileirador.enviaMensagem(requisicao);
    }
}
