package br.com.oobj.escalar.controller;

import br.com.oobj.escalar.processador.ProcessadorDeArquivos;
import br.com.oobj.escalar.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegradorController {
    private final ProcessadorDeArquivos processadorDeArquivos = new ProcessadorDeArquivos();
    private final TokenService tokenService;

    public IntegradorController(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @PostMapping("/api/pre-impressao")
    public ResponseEntity<String> chegadaRequisicao(@RequestBody String requisicao) {
        try {
            processadorDeArquivos.processaArquivo(requisicao);
            return new ResponseEntity<>("{\"preImpressaoSolicitada\": \"true\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"Erro ao carregar arquivo!\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        if (ResponseEntity == HttpStatus.FORBIDDEN) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> autenticar() {
        try {
            String token = tokenService.gerarToken();
            System.out.println("Bearer Token: " + token);
            return new ResponseEntity<>("{\"tokenGeradoComSucesso\": \"true\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"Erro ao gerar Token!\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
