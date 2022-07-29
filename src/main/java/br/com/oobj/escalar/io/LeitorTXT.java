package br.com.oobj.escalar.io;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class LeitorTXT {
    public <T> String leia(String arquivo, String diretorio) {
        String arquivoLido = "";
        try {
            BufferedReader buffRead = new BufferedReader(new FileReader(diretorio+arquivo));
            String linha = "";
            while (true) {
                if (linha != null) {
                    arquivoLido += linha + "\n";
                } else
                    break;
                linha = buffRead.readLine();
            }
            buffRead.close();
            return arquivoLido;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
}