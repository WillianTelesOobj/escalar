package br.com.oobj.escalar.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LeitorTXT {
//    public static void leia(String diretorio) throws IOException {
//        try {
//            BufferedReader buffRead = new BufferedReader(new FileReader(diretorio));
//            String linha = "";
//            while (true) {
//                if (linha != null && !linha.equals("25000;STAPLE_TOP_LEFT")) {
//                    System.out.println(linha);
//                } else
//                    break;
//                linha = buffRead.readLine();
//            }
//            buffRead.close();
//        } catch (IOException ex) {
//            throw new IllegalStateException(ex);
//        }
//    }

    public static String leia(String diretorio, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(diretorio));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }
}
