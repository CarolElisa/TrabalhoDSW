package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.dao.IVagaDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Usuario;

@SpringBootApplication
public class LivrariaMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaMvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IEmpresaDAO empresaDAO, IVagaDAO vagaDAO) {
		return (args) -> {
			// Create and set up an Empresa object (which also acts as a Usuario)
			Empresa empresa = new Empresa();
			empresa.setUsername("empr");
			empresa.setPassword(encoder.encode("empr"));
			empresa.setRole("ROLE_ADMIN");
			empresa.setEnabled(true);
			
			empresa.setCnpj("413.808.653-01");
			empresa.setNome("Empresa Nome");
	
			// Save Empresa (which will also save the Usuario information)
			empresaDAO.save(empresa);
	
			// Optionally, create and save a Vaga associated with the Empresa
			Vaga vaga = new Vaga();
			vaga.setNome("Vaga Nome");
			vaga.setNivel("Junior");
			vaga.setAnosContrato(1);
			vaga.setSalario(new BigDecimal("3000.00"));
			vaga.setEmpresa(empresa); // Set the empresa in Vaga
			vagaDAO.save(vaga);
		};
	}
}
