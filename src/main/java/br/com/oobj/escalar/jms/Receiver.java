package br.com.oobj.escalar.jms;

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

    @JmsListener(destination = "pre_impressao", concurrency = "5")
    @Override
    public void onMessage(Message message) {
        try {
            String mensagemRecebida = ((TextMessage) message).getText();

            int indexNumeroMsg = mensagemRecebida.indexOf("MSG NUMERO: ");
            int indexImpressoraManifesto = mensagemRecebida.indexOf(" - IMPRESSORA;MANIFESTO");
            String numeroMsg = mensagemRecebida.substring(indexNumeroMsg + 12, indexImpressoraManifesto);

            int indexSubItinerario = mensagemRecebida.indexOf("SUB-ITINERÁRIO : ");
            int index22003 = mensagemRecebida.indexOf("22003");
            String numeroSubItinerario = mensagemRecebida.substring(indexSubItinerario + 17, index22003);

            int indexSeq = mensagemRecebida.indexOf("SEQ :");
            int index22008 = mensagemRecebida.indexOf("22008");
            String numeroSequenciamento = mensagemRecebida.substring(indexSeq + 5, index22008);

            String subItinerarioESequenciamento = numeroMsg + " - " + numeroSubItinerario + "|" + numeroSequenciamento;
            String mensagemTratada = subItinerarioESequenciamento.replace(System.getProperty("line.separator"), "");

            System.out.println("Mensagem recebida e tratada: " + mensagemTratada);
        } catch (JMSException e) {
            logger.error("Problema ao consumir mensagem da fila {}.", "pre_impressao");
            throw new RuntimeException(e.getMessage());
        }
    }
}
