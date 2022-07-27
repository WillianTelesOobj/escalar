package br.com.oobj.escalar.processador;

import br.com.oobj.escalar.io.EscritorTXT;
import br.com.oobj.escalar.jms.Enfileirador;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TratadorDeArquivos {
    private final List<String> msgRecebidas = new ArrayList<>();

    public void tratarArquivo(String mensagemRecebida) {
        int indexSubItinerario = mensagemRecebida.indexOf("SUB-ITINERÁRIO : ");
        int index22003 = mensagemRecebida.indexOf("22003");
        String numeroSubItinerario = mensagemRecebida.substring(indexSubItinerario + 17, index22003);

        int indexSeq = mensagemRecebida.indexOf("SEQ :");
        int index22008 = mensagemRecebida.indexOf("22008");
        String numeroSequenciamento = mensagemRecebida.substring(indexSeq + 5, index22008);

        String subItinerarioESequenciamento = numeroSubItinerario + "|" + numeroSequenciamento;
        String mensagemTratada = subItinerarioESequenciamento.replace(System.getProperty("line.separator"), "");

        msgRecebidas.add(mensagemTratada);
        SaideDeArquivos.enviaArquivo(msgRecebidas.size(), msgRecebidas);
    }
}
