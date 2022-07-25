package br.com.oobj.escalar.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class Produtor {

    private static final String DIRETORIO = "src/main/resources/entrada";

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

    private static Set<File> listFilesFrom(String diretorio) {
        return Stream.of(requireNonNull(new File(diretorio).listFiles()))
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }
}
