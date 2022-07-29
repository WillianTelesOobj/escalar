package br.com.oobj.escalar.io;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EscritorTXT {

    public <T> String escreve(String mensagem, String tipo, String diretorio) throws IOException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = currentDateTime.format(formatter);
        if (tipo.equals("entrada")) {
            FileWriter entrada = new FileWriter(diretorio + "pre-impressao-" + formattedDateTime + ".txt");
            entrada.write(mensagem);
            entrada.close();
            System.out.println("Arquivo de entrada salvo com sucesso!");
            return "pre-impressao-" + formattedDateTime + ".txt";
        } else if (tipo.equals("saida")) {
            FileWriter saida = new FileWriter(diretorio + "pre-impressao-" + formattedDateTime + "-retorno.txt");
            saida.write(mensagem);
            saida.close();
            System.out.println("Arquivo de saída salvo com sucesso!");
            return "pre-impressao-" + formattedDateTime + "-retorno.txt";
        }
        return null;
    }
}
