package br.com.oobj.escalar.controller;

import br.com.oobj.escalar.io.EscritorTXT;
import br.com.oobj.escalar.jms.Consumidor;
import br.com.oobj.escalar.jms.Produtor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class IntegradorController {
    private final EscritorTXT escritorTXT = new EscritorTXT();
    private final Produtor produtor = new Produtor();
    private final Consumidor consumidor = new Consumidor();

    @PostMapping("/pre-impressao")
    public ResponseEntity<String> chegadaRequisicao(@RequestBody String requisicao) {
        try {
            escritorTXT.escreve(requisicao);
            produtor.enviaMensagem(requisicao);
            consumidor.recebeMensagem();
            return new ResponseEntity<>("{\"preImpressaoSolicitada\": \"true\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"mensagem\": \"Erro ao carregar arquivo!\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
