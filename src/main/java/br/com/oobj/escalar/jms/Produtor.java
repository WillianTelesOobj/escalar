package br.com.oobj.escalar.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class Produtor {

    public static void main (String [] args) throws Exception {
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory)context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination fila = (Destination) context.lookup("fila");

        MessageProducer producer = session.createProducer(fila);
        for (int i = 0; i < 1000; i++) {
            Message message = session.createTextMessage("Mensagem número: " + i);
            producer.send(message);
        }

        session.close();
        connection.close();
        context.close();
    }
}
