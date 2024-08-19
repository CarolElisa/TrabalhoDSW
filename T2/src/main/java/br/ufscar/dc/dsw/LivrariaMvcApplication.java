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
			
			Usuario u1 = new Usuario();
			u1.setUsername("admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setCPF("012.345.678-90");
			u1.setName("Administrador");
			u1.setRole("ROLE_ADMIN");
			u1.setEnabled(true);
			usuarioDAO.save(u1);
			
			Usuario u2 = new Usuario();
			u2.setUsername("beltrano");
			u2.setPassword(encoder.encode("123"));
			u2.setCPF("985.849.614-10");
			u2.setName("Beltrano Andrade");
			u2.setRole("ROLE_USER");
			u2.setEnabled(true);
			usuarioDAO.save(u2);
			
			Usuario u3 = new Usuario();
			u3.setUsername("fulano");
			u3.setPassword(encoder.encode("123"));
			u3.setCPF("367.318.380-04");
			u3.setName("Fulano Silva");
			u3.setRole("ROLE_USER");
			u3.setEnabled(true);
			usuarioDAO.save(u3);
			
			Empresa e1 = new Empresa();
			e1.setCNPJ("55.789.390/0008-99");
			e1.setNome("Companhia das Letras");
			empresaDAO.save(e1);
			
			Empresa e2 = new Empresa();
			e2.setCNPJ("71.150.470/0001-40");
			e2.setNome("Record");
			empresaDAO.save(e2);
			
			Empresa e3 = new Empresa();
			e3.setCNPJ("32.106.536/0001-82");
			e3.setNome("Objetiva");
			empresaDAO.save(e3);
			
			Vaga l1 = new Vaga();
			l1.setNome("Ensaio sobre a Cegueira");
			l1.setNivel("José Saramago");
			l1.setAnosContrato(1995);
			l1.setSalario(BigDecimal.valueOf(54.9));
			l1.setEmpresa(e1);
			vagaDAO.save(l1);
			
			Vaga l2 = new Vaga();
			l2.setNome("Cem anos de Solidão");
			l2.setNivel("Gabriel Garcia Márquez");
			l2.setAnosContrato(1977);
			l2.setSalario(BigDecimal.valueOf(59.9));
			l2.setEmpresa(e2);
			vagaDAO.save(l2);
			
			Vaga l3 = new Vaga();
			l3.setNome("Diálogos Impossíveis");
			l3.setNivel("Luis Fernando Verissimo");
			l3.setAnosContrato(2012);
			l3.setSalario(BigDecimal.valueOf(22.9));
			l3.setEmpresa(e3);
			vagaDAO.save(l3);
		};
	}
}
