package br.com.oobj.escalar.controller;

import br.com.oobj.escalar.processador.ProcessadorDeArquivos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegradorController {

    private final ProcessadorDeArquivos processadorDeArquivos;

    public IntegradorController(ProcessadorDeArquivos processadorDeArquivos) {
        this.processadorDeArquivos = processadorDeArquivos;
    }

    @PostMapping("/api/pre-impressao")
    public ResponseEntity<String> chegadaRequisicao(@RequestBody String requisicao) {
        try {
            if (requisicao.trim().startsWith("IMPRESSORA;MANIFESTO") && requisicao.trim().endsWith("25000;STAPLE_TOP_LEFT")) {
                processadorDeArquivos.processaArquivo(requisicao);
                return new ResponseEntity<>("{\"preImpressaoSolicitada\": \"true\"}", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{\"O arquivo enviado está fora do padrão especificado!\"}", HttpStatus.PRECONDITION_FAILED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("{\"Erro ao carregar arquivo!\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
