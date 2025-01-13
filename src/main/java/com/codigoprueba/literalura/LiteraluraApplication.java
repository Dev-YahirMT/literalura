package com.codigoprueba.literalura;

import com.codigoprueba.literalura.Model.Autores;
import com.codigoprueba.literalura.principal.Principal;
import com.codigoprueba.literalura.service.AutoresRepository;
import com.codigoprueba.literalura.service.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibrosRepository repository;
	@Autowired
	private AutoresRepository repositorioAutor;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository,repositorioAutor);
		principal.muestraElMenu();
	}
}
