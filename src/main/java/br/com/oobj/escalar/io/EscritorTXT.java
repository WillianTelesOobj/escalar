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

    public <T> String escreve(String mensagem, String tipo, String diretorio) throws IOException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = currentDateTime.format(formatter);
        if (tipo.equals("entrada")) {
            logger.info("Salvando arquivo de entrada...");
            String nomeArquivoEntrada = "pre-impressao-" + formattedDateTime + ".txt";
            FileWriter entrada = new FileWriter(diretorio + nomeArquivoEntrada);
            entrada.write(mensagem);
            entrada.close();
            logger.info("Arquivo de entrada salvo com sucesso!");
            return nomeArquivoEntrada;
        } else if (tipo.equals("saida")) {
            logger.info("Salvando arquivo de saída...");
            String nomeArquivoSaida = "pre-impressao-" + formattedDateTime + "-retorno.txt";
            FileWriter saida = new FileWriter(diretorio + nomeArquivoSaida);
            saida.write(mensagem);
            saida.close();
            logger.info("Arquivo de saída salvo com sucesso!");
            return nomeArquivoSaida;
        }
        return null;
    }
}
