package br.com.oobj.escalar.processador;

import br.com.oobj.escalar.io.EscritorTXT;
import br.com.oobj.escalar.jms.Consumidor;
import br.com.oobj.escalar.jms.Produtor;

import javax.jms.JMSException;
import javax.naming.NamingException;

public class ProcessadorDeArquivos {

    private static final String DiretorioEntrada = "src/main/resources/entrada/";
    private final EscritorTXT escritorTXT = new EscritorTXT();
    private final Produtor produtor = new Produtor();
    private final Consumidor consumidor = new Consumidor();

    public void processaArquivo(String requisicao) throws NamingException, JMSException {
        escritorTXT.escreve(requisicao, "entrada", DiretorioEntrada);
        produtor.enviaMensagem(requisicao);
        consumidor.recebeMensagem();
    }
}
