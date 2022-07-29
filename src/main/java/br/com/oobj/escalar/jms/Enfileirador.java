package br.com.oobj.escalar.jms;

import br.com.oobj.escalar.processador.MovedorDeArquivo;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@Component
public class Enfileirador {

    private final MovedorDeArquivo movedorDeArquivo;

    public Enfileirador(MovedorDeArquivo movedorDeArquivo) {
        this.movedorDeArquivo = movedorDeArquivo;
    }

    public void enviaMensagem(String arquivo, String nomeArquivo, String diretorioEntrada, String diretorioProcessados)
            throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination fila = (Destination) context.lookup("fila");

        MessageProducer producer = session.createProducer(fila);
        String[] requisicaoArray = arquivo.split("IMPRESSORA;MANIFESTO");
        for (int i = 1; i < (requisicaoArray.length); i++) {
            Message message = session.createTextMessage("IMPRESSORA;MANIFESTO" + requisicaoArray[i]);
            message.setJMSMessageID(String.valueOf(i));
            producer.send(message);
            if (i == requisicaoArray.length - 1) {
                message = session.createTextMessage("FINAL");
                message.setJMSMessageID(String.valueOf(i+1));
                producer.send(message);
            }
        }

        session.close();
        connection.close();
        context.close();

        movedorDeArquivo.moveArquivo(nomeArquivo, diretorioEntrada, diretorioProcessados);
    }
}
