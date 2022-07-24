package br.com.oobj.escalar.jms;

import javax.jms.*;
import javax.naming.InitialContext;

public class Consumidor {

    public static void main (String [] args) throws Exception {
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination fila = (Destination) context.lookup("fila");
        MessageConsumer consumer = session.createConsumer(fila);
        Message message = consumer.receive();
        System.out.println("Recebendo a mensagem: " + message);
        session.close();
        connection.close();
        context.close();
    }
}
