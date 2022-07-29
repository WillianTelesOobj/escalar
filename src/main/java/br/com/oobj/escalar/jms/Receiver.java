package br.com.oobj.escalar.jms;

import br.com.oobj.escalar.processador.SaideDeArquivos;
import br.com.oobj.escalar.processador.TratadorDeArquivos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;
import java.util.*;

@Component
public class Receiver implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(Enfileirador.class);
    private final TratadorDeArquivos tratadorDeArquivos;
    private final TreeMap<Integer, String> treeMap = new TreeMap<>();
    private final SaideDeArquivos saideDeArquivos;

    public Receiver(TratadorDeArquivos tratadorDeArquivos, SaideDeArquivos saideDeArquivos) {
        this.tratadorDeArquivos = tratadorDeArquivos;
        this.saideDeArquivos = saideDeArquivos;
    }

    @JmsListener(destination = "pre_impressao", concurrency = "5")
    @Override
    public void onMessage(Message message) {
        try {
            String mensagemRecebida = ((TextMessage) message).getText();

            String jmsMessageID = message.getJMSMessageID();
            int indexId = jmsMessageID.lastIndexOf("-");
            String numeroId = jmsMessageID.substring(indexId + 1).replace(":", "");

            treeMap.put(Integer.valueOf(numeroId), mensagemRecebida);
            if (treeMap.containsValue("*****EOF*****")) {
                String listaMensagens = treeMap.toString();
                String arquivoFinal = tratadorDeArquivos.tratarArquivo(listaMensagens);
                saideDeArquivos.enviaArquivo(arquivoFinal);
                treeMap.clear();
            }
        } catch (JMSException e) {
            logger.error("Problema ao consumir mensagem da fila {}. {}", "pre_impressao", e.getMessage());
        }
        catch (IOException e) {
            logger.error("Erro ao salvar arquivo de saída. " + e.getMessage());
        }
    }
}
