package br.com.oobj.escalar.processador;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TratadorDeArquivos {

    private final SaideDeArquivos saideDeArquivos;

    public TratadorDeArquivos(SaideDeArquivos saideDeArquivos) {
        this.saideDeArquivos = saideDeArquivos;
    }

    public void tratarArquivo(String listaMensagens) {
        String[] listaMensagensArray = listaMensagens.split("IMPRESSORA;MANIFESTO");
        StringBuilder mensagensTratadas = new StringBuilder();

        for (int i = 1; i < (listaMensagensArray.length); i++) {
            String mensagemRecebida = listaMensagensArray[i];

            int indexSubItinerario = mensagemRecebida.indexOf("SUB-ITINERÁRIO : ");
            int index22003 = mensagemRecebida.indexOf("22003");
            String numeroSubItinerario = mensagemRecebida.substring(indexSubItinerario + 17, index22003);

            int indexSeq = mensagemRecebida.indexOf("SEQ :");
            int index22008 = mensagemRecebida.indexOf("22008");
            String numeroSequenciamento = mensagemRecebida.substring(indexSeq + 5, index22008);

            String subItinerarioESequenciamento = numeroSubItinerario + "|" + numeroSequenciamento;
            String mensagemTratada = subItinerarioESequenciamento.replace(System.getProperty("line.separator"), "");

            if (i <= listaMensagensArray.length -2) {
                mensagensTratadas.append(mensagemTratada).append(" , ");
            } else if (i == listaMensagensArray.length - 1) {
                mensagensTratadas.append(mensagemTratada).append(" ");
            }
        }
        saideDeArquivos.enviaArquivo(mensagensTratadas.toString().replace(" , ", "\n"));
    }
}