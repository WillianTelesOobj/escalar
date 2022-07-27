package br.com.oobj.escalar.processador;

import br.com.oobj.escalar.io.EscritorTXT;

import java.util.Collections;
import java.util.List;

public class SaideDeArquivos {

    private static final EscritorTXT escritorTXT = new EscritorTXT();

    public static void enviaArquivo(int qtdMsgRecebidas, List<String> msgRecebidas) {
        if(qtdMsgRecebidas == 44) {
            String dadoDeSaida = null;
            Collections.sort(msgRecebidas);
            for (int i = 1; i < (msgRecebidas.size()); i++) {
                dadoDeSaida = msgRecebidas.toString().replace(", ", "\n")
                        .replace("[", "")
                        .replace("]", "");
            }
            escritorTXT.escreve(dadoDeSaida, "saida", "src/main/resources/saida/");
        }
    }
}
