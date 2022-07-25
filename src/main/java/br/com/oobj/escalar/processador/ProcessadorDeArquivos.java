//package br.com.oobj.escalar.processador;
//
//import br.com.oobj.escalar.io.LeitorTXT;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static java.util.Objects.requireNonNull;
//
//public class ProcessadorDeArquivos {
//    private final LeitorTXT leitor = new LeitorTXT();
//
//    public void processaArquivosDo(String diretorio) throws IOException {
//        Set<File> arquivos = listFilesFrom(diretorio);
//
//        for (File arquivo : arquivos) {
//            checaSeEhTXT(arquivo);
////            leitor.leia(String.valueOf(arquivo));
//            leitor.leia(String.valueOf(arquivo), StandardCharsets.ISO_8859_1);
//        }
//    }
//
//    private void checaSeEhTXT(File arquivo) {
//        String nomeDoArquivo = arquivo.getName();
//        if (!nomeDoArquivo.endsWith(".txt")) {
//            throw new IllegalArgumentException("Formato inválido do arquivo: " + nomeDoArquivo);
//        }
//    }
//
//    private Set<File> listFilesFrom(String diretorio) {
//        return Stream.of(requireNonNull(new File(diretorio).listFiles()))
//                .filter(file -> !file.isDirectory())
//                .collect(Collectors.toSet());
//    }
//}
