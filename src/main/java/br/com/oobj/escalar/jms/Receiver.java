package br.com.oobj.escalar.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "pre_impressao", concurrency = "4")
    public void onReceiverQueue(String mensagemRecebida) {
        int indexSubItinerario = mensagemRecebida.indexOf("SUB-ITINERÁRIO : ");
        int index22003 = mensagemRecebida.indexOf("22003");
        String numeroSubItinerario = mensagemRecebida.substring(indexSubItinerario + 17, index22003);

        int indexSeq = mensagemRecebida.indexOf("SEQ :");
        int index22008 = mensagemRecebida.indexOf("22008");
        String numeroSequenciamento = mensagemRecebida.substring(indexSeq + 5, index22008);

        String subItinerarioESequenciamento = numeroSubItinerario + "|" + numeroSequenciamento;
        String mensagemTratada = subItinerarioESequenciamento.replace(System.getProperty("line.separator"), "");

        System.out.println("Mensagem recebida e tratada: " + mensagemTratada);
    }
}
