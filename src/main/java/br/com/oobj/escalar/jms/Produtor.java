package br.com.oobj.escalar.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Produtor {

    public void enviaMensagem(String requisicao) throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination fila = (Destination) context.lookup("fila");

        MessageProducer producer = session.createProducer(fila);
        String[] requisicaoArray = requisicao.split("IMPRESSORA;MANIFESTO");
        for (int i = 1; i < (requisicaoArray.length); i++) {
            Message message = session.createTextMessage("IMPRESSORA;MANIFESTO" + requisicaoArray[i]);
            producer.send(message);
        }

        session.close();
        connection.close();
        context.close();
    }
}
