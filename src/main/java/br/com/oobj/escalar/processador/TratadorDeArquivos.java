package br.com.oobj.escalar.processador;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TratadorDeArquivos {
    private final List<String> listaMensagens = new ArrayList<>();
    private final SaideDeArquivos saideDeArquivos;

    public TratadorDeArquivos(SaideDeArquivos saideDeArquivos) {
        this.saideDeArquivos = saideDeArquivos;
    }

    public void tratarArquivo(String mensagemRecebida) {
        if (!mensagemRecebida.equals("FINAL")) {
            int indexSubItinerario = mensagemRecebida.indexOf("SUB-ITINERÁRIO : ");
            int index22003 = mensagemRecebida.indexOf("22003");
            String numeroSubItinerario = mensagemRecebida.substring(indexSubItinerario + 17, index22003);

            int indexSeq = mensagemRecebida.indexOf("SEQ :");
            int index22008 = mensagemRecebida.indexOf("22008");
            String numeroSequenciamento = mensagemRecebida.substring(indexSeq + 5, index22008);

            String subItinerarioESequenciamento = numeroSubItinerario + "|" + numeroSequenciamento;
            String mensagemTratada = subItinerarioESequenciamento.replace(System.getProperty("line.separator"), "");

            listaMensagens.add(mensagemTratada);
        } else {
            Collections.sort(listaMensagens);
            saideDeArquivos.enviaArquivo(listaMensagens.toString().replace(", ", "\n")
                    .replace("[", "")
                    .replace("]", ""));
            listaMensagens.clear();
        }
    }
}
