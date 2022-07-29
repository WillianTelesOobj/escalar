package br.com.oobj.escalar.jms;

import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Component
public class Enfileirador {

    public void enviaMensagem(String arquivo) throws NamingException, JMSException {
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
            message.setJMSPriority(9);
            producer.send(message);
            if (i == requisicaoArray.length - 1) {
                message = session.createTextMessage("*****EOF*****");
                message.setJMSMessageID(String.valueOf(i+1));
                message.setJMSPriority(0);
                producer.send(message);
            }
        }

        session.close();
        connection.close();
        context.close();
    }
}
