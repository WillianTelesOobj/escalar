package br.com.oobj.escalar.jms;

import br.com.oobj.escalar.processador.TratadorDeArquivos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class Receiver implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(Enfileirador.class);
    private final TratadorDeArquivos tratadorDeArquivos;

    public Receiver(TratadorDeArquivos tratadorDeArquivos) {
        this.tratadorDeArquivos = tratadorDeArquivos;
    }

    @JmsListener(destination = "pre_impressao", concurrency = "5")
    @Override
    public void onMessage(Message message) {
        try {
            String mensagemRecebida = ((TextMessage) message).getText();
            tratadorDeArquivos.tratarArquivo(mensagemRecebida);
        } catch (JMSException e) {
            logger.error("Problema ao consumir mensagem da fila {}.", "pre_impressao");
            throw new RuntimeException(e.getMessage());
        }
    }
}
