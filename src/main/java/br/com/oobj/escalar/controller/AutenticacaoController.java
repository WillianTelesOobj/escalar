package br.com.oobj.escalar.controller;

import br.com.oobj.escalar.processador.ProcessadorDeArquivos;
import br.com.oobj.escalar.security.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacaoController {

    private final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);
    private final TokenService tokenService;

    public AutenticacaoController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> autenticar() {
        try {
            String token = tokenService.gerarToken();
            logger.info("Bearer Token: " + token);
            return new ResponseEntity<>("{\"tokenGeradoComSucesso\": \"true\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"Erro ao gerar Token!\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
