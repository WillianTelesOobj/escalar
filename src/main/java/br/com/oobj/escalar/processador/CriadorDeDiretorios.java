package br.com.oobj.escalar.processador;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class CriadorDeDiretorios {

    @Value("${escalar.diretorio.entrada}")
    private String DiretorioEntrada;
    @Value("${escalar.diretorio.processados}")
    private String DiretorioProcessados;
    @Value("${escalar.diretorio.saida}")
    private String DiretorioSaida;

    public void criarDiretorios () {
        List<String> pastas = new ArrayList<>();
        pastas.add(DiretorioEntrada);
        pastas.add(DiretorioProcessados);
        pastas.add(DiretorioSaida);

        for (String pasta : pastas) {
            File diretorio = new File(pasta);
            diretorio.mkdir();
        }
    }
}
