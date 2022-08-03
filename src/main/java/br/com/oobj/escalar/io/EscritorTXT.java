package br.com.oobj.escalar.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EscritorTXT {

    private final Logger logger = LoggerFactory.getLogger(EscritorTXT.class);
    private String nomeArquivo = "";

    public <T> String escreve(String mensagem, String tipo, String diretorio) throws IOException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = currentDateTime.format(formatter);
        if (tipo.equals("entrada")) {
            nomeArquivo = "pre-impressao-" + formattedDateTime + ".txt";
        } else if (tipo.equals("saída")) {
            nomeArquivo = "pre-impressao-" + formattedDateTime + "-retorno.txt";
        }
        logger.info("Salvando arquivo {} na pasta de {}...", nomeArquivo, tipo);
        FileWriter arquivo = new FileWriter(diretorio + nomeArquivo);
        arquivo.write(mensagem);
        arquivo.close();
        logger.info("Arquivo {} salvo na pasta de {} com sucesso!", nomeArquivo, tipo);
        return nomeArquivo;
    }
}
