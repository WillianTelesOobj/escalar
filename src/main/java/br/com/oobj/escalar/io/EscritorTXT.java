package br.com.oobj.escalar.io;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EscritorTXT {

    public <T> void escreve(String requisicao) {
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
            String formattedDateTime = currentDateTime.format(formatter);
            FileWriter entrada = new FileWriter("src/main/resources/entrada/pre-impressao-" + formattedDateTime + ".txt");
            entrada.write(requisicao);
            entrada.close();
            System.out.println("Arquivo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo!");
            e.printStackTrace();
        }
    }
}
