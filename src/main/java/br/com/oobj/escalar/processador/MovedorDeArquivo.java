package br.com.oobj.escalar.processador;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class MovedorDeArquivo {

    public void moveArquivo(String nomeArquivo, String diretorioOrigem, String diretorioDestino) {
        try {
            Path pathSource = Paths.get(diretorioOrigem + nomeArquivo);
            Path pathTarget = Paths.get(diretorioDestino + nomeArquivo);
            Files.move(pathSource, pathTarget);
            System.out.println("Arquivo de entrada copiado para pasta de processados!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
