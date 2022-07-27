package br.com.oobj.escalar.processador;

import br.com.oobj.escalar.io.EscritorTXT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TratadorDeArquivos {
    private final List<String> mensagensRecebidas = new ArrayList<>();
    private final AtomicInteger i = new AtomicInteger(0);
    private final EscritorTXT escritorTXT = new EscritorTXT();

    public void tratarArquivo(String mensagemRecebida) {
        int indexSubItinerario = mensagemRecebida.indexOf("SUB-ITINERÁRIO : ");
        int index22003 = mensagemRecebida.indexOf("22003");
        String numeroSubItinerario = mensagemRecebida.substring(indexSubItinerario + 17, index22003);

        int indexSeq = mensagemRecebida.indexOf("SEQ :");
        int index22008 = mensagemRecebida.indexOf("22008");
        String numeroSequenciamento = mensagemRecebida.substring(indexSeq + 5, index22008);

        String subItinerarioESequenciamento = numeroSubItinerario + "|" + numeroSequenciamento;
        String mensagemTratada = subItinerarioESequenciamento.replace(System.getProperty("line.separator"), "");

        mensagensRecebidas.add(mensagemTratada);
        if(i.addAndGet(1) == 44) {
            String dadoDeSaida = null;
            Collections.sort(mensagensRecebidas);
            for (int i = 1; i < (mensagensRecebidas.size()); i++) {
                dadoDeSaida = mensagensRecebidas.toString().replace(", ", "\n")
                        .replace("[", "")
                        .replace("]", "");
            }
            escritorTXT.escreve(dadoDeSaida, "saida", "src/main/resources/saida/");
            AtomicInteger i = new AtomicInteger(0);
        }
    }
}
