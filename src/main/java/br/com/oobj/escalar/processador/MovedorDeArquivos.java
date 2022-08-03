package br.com.oobj.escalar.processador;

import br.com.oobj.escalar.io.EscritorTXT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class MovedorDeArquivos {

    private final Logger logger = LoggerFactory.getLogger(MovedorDeArquivos.class);

    public void moveArquivo(String nomeArquivo, String diretorioOrigem, String diretorioDestino) {
        try {
            logger.info("Movendo arquivo {} da pasta de entrada para pasta de processados...", nomeArquivo);
            Path pathSource = Paths.get(diretorioOrigem + nomeArquivo);
            Path pathTarget = Paths.get(diretorioDestino + nomeArquivo);
            Files.move(pathSource, pathTarget);
            logger.info("Arquivo {} movido da pasta de entrada para pasta de processados!", nomeArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
