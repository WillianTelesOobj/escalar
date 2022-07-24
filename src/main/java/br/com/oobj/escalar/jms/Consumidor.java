package br.com.oobj.escalar.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class Consumidor {

    public static void main (String [] args) throws Exception {
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination fila = (Destination) context.lookup("fila");

        MessageConsumer consumer = session.createConsumer(fila);
        consumer.setMessageListener(message -> {
            TextMessage textMessage = (TextMessage)message;

            try {
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        });

        new Scanner(System.in).nextLine();
        session.close();
        connection.close();
        context.close();
    }
}
