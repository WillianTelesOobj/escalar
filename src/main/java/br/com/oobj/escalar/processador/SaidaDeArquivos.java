package br.com.oobj.escalar.processador;

import br.com.oobj.escalar.io.EscritorTXT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SaidaDeArquivos {

    @Value("${escalar.diretorio.saida}")
    private String DiretorioSaida;
    private final EscritorTXT escritorTXT;

    public SaidaDeArquivos(EscritorTXT escritorTXT) {
        this.escritorTXT = escritorTXT;
    }

    public void enviaArquivo(String arquivoDeSaida) throws IOException {
        escritorTXT.escreve(arquivoDeSaida, "saída", DiretorioSaida);
    }
}
