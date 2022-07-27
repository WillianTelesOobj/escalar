package br.com.oobj.escalar.io;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EscritorTXT {

    public <T> void escreve(String mensagem, String tipo, String diretorio) {
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
            String formattedDateTime = currentDateTime.format(formatter);
            if (tipo.equals("entrada")) {
                FileWriter entrada = new FileWriter(diretorio + "pre-impressao-" + formattedDateTime + ".txt");
                entrada.write(mensagem);
                entrada.close();
                System.out.println("Arquivo de entrada salvo com sucesso!");
            } else if (tipo.equals("saida")) {
                FileWriter saida = new FileWriter(diretorio + "pre-impressao-" + formattedDateTime + "-retorno.txt");
                saida.write(mensagem);
                saida.close();
                System.out.println("Arquivo de saída salvo com sucesso!");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo!");
            e.printStackTrace();
        }
    }
}
