package br.com.oobj.escalar.processador;

import br.com.oobj.escalar.io.EscritorTXT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SaideDeArquivos {

    @Value("${escalar.diretorio.saida}")
    private String DiretorioSaida;
    private final EscritorTXT escritorTXT;

    public SaideDeArquivos(EscritorTXT escritorTXT) {
        this.escritorTXT = escritorTXT;
    }

    public void enviaArquivo(String arquivoDeSaida) {
        escritorTXT.escreve(arquivoDeSaida, "saida", DiretorioSaida);
    }
}
