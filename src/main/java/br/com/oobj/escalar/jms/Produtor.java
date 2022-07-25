package br.com.oobj.escalar.jms;

import br.com.oobj.escalar.io.LeitorTXT;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        Message message = session.createTextMessage(requisicao);
        producer.send(message);

//        LeitorTXT leitorTXT = new LeitorTXT();
//        Set<File> arquivos = listFilesFrom(DIRETORIO);
//        for (File arquivo : arquivos) {
//            String mensagem = leitorTXT.leia(String.valueOf(arquivo), StandardCharsets.ISO_8859_1);
//            Message message = session.createTextMessage(mensagem);
//            producer.send(message);
//        }

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
