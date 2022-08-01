package br.com.oobj.escalar;

import br.com.oobj.escalar.processador.CriadorDeDiretorios;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EscalarApplication {

	private static CriadorDeDiretorios criadorDeDiretorios;

	public EscalarApplication(CriadorDeDiretorios criadorDeDiretorios) {
		EscalarApplication.criadorDeDiretorios = criadorDeDiretorios;
	}

	public static void main(String[] args) {
		SpringApplication.run(EscalarApplication.class, args);

		criadorDeDiretorios.criarDiretorios();
	}
}
