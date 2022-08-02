package br.com.oobj.escalar.jms;

import br.com.oobj.escalar.processador.SaidaDeArquivos;
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
import java.util.TreeMap;

@Component
public class Receiver implements MessageListener {

    Integer totalMsgEnviadas = -1;
    private final Logger logger = LoggerFactory.getLogger(Enfileirador.class);
    private final TreeMap<Integer, String> treeMap = new TreeMap<>();
    private final TratadorDeArquivos tratadorDeArquivos;
    private final SaidaDeArquivos saidaDeArquivos;

    public Receiver(TratadorDeArquivos tratadorDeArquivos, SaidaDeArquivos saidaDeArquivos) {
        this.tratadorDeArquivos = tratadorDeArquivos;
        this.saidaDeArquivos = saidaDeArquivos;
    }

    @JmsListener(destination = "pre_impressao", concurrency = "5")
    @Override
    public void onMessage(Message message) {
        try {
            String mensagemRecebida = ((TextMessage) message).getText();
            Integer ordemMsg = message.getIntProperty("Ordem");
            treeMap.put(ordemMsg, mensagemRecebida);

            if (mensagemRecebida.contains("*****EOF*****")) {
                totalMsgEnviadas = message.getIntProperty("Ordem");
            }

            if (treeMap.containsValue("*****EOF*****") && treeMap.size() == totalMsgEnviadas) {
                String listaMensagens = treeMap.toString();
                String arquivoFinal = tratadorDeArquivos.tratarArquivo(listaMensagens);
                saidaDeArquivos.enviaArquivo(arquivoFinal);
                treeMap.clear();
                totalMsgEnviadas = -1;
            }
        } catch (JMSException e) {
            logger.error("Problema ao consumir mensagem da fila {}. {}", "pre_impressao", e.getMessage());
        } catch (IOException e) {
            logger.error("Erro ao salvar arquivo de saída. " + e.getMessage());
        }
    }
}
