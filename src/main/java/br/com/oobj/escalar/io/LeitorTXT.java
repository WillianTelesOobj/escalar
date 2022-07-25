//package br.com.oobj.escalar.io;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class LeitorTXT {
//    public static String leia(String diretorio) {
//        String mensagem = "";
//        try {
//            BufferedReader buffRead = new BufferedReader(new FileReader(diretorio));
//            String linha = "";
//            while (true) {
//                if (linha != null && !linha.equals("25000;STAPLE_TOP_LEFT")) {
//                    mensagem += linha + System.lineSeparator();
//                } else {
//                    break;
//                }
//                linha = buffRead.readLine();
//            }
//            buffRead.close();
//        } catch (IOException ex) {
//            throw new IllegalStateException(ex);
//        }
//        return mensagem;
//    }
//
////    public static String leia(String diretorio, Charset encoding) throws IOException {
////        byte[] encoded = Files.readAllBytes(Paths.get(diretorio));
////        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
////    }
//}
