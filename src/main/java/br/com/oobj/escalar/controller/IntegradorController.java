package br.com.oobj.escalar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class IntegradorController {

    @PostMapping("/pre-impressao")
    public ResponseEntity<String> chegadaRequisicao(@RequestBody String requisicao) {
        System.out.println(requisicao);
        try {
            return new ResponseEntity<>("{\"preImpressaoSolicitada\": \"true\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"mensagem\": \"Erro ao carregar arquivo!\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
