package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.dao.ICandidaturaDAO;
import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.dao.IVagaDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Usuario;

@SpringBootApplication
public class VagasTopApplication {

	public static void main(String[] args) {
		SpringApplication.run(VagasTopApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IEmpresaDAO empresaDAO, IVagaDAO vagaDAO, IProfissionalDAO profissionalDAO, ICandidaturaDAO candidaturaDAO) {
		return (args) -> {
			
					           // Create and save Usuario entities for Admin and Empresa
            Usuario adm = new Usuario();
            adm.setUsername("admin");
            adm.setPassword(encoder.encode("admin"));
            adm.setRole("ROLE_ADMIN");
            adm.setEnabled(true);
            usuarioDAO.save(adm);

            Usuario usuario1 = new Usuario();
            usuario1.setUsername("empr");
            usuario1.setPassword(encoder.encode("empr"));
            usuario1.setRole("ROLE_EMPR");
            usuario1.setEnabled(true);
            usuarioDAO.save(usuario1);

            Usuario usuario2 = new Usuario();
            usuario2.setUsername("empr2");
            usuario2.setPassword(encoder.encode("empr2"));
            usuario2.setRole("ROLE_EMPR");
            usuario2.setEnabled(true);
            usuarioDAO.save(usuario2);

            // Create and save Empresa entities
            Empresa empresa1 = new Empresa();
            empresa1.setCnpj("413.808.653-01");
            empresa1.setNome("Empresa Nome");
            empresa1.setUsuario(usuario1); // Link to Usuario
            empresaDAO.save(empresa1);

            Empresa empresa2 = new Empresa();
            empresa2.setCnpj("413.808.653-02");
            empresa2.setNome("Empresa 2");
            empresa2.setUsuario(usuario2); // Link to Usuario
            empresaDAO.save(empresa2);

            // Retrieve the saved Empresa entities
            Empresa savedEmpresa1 = empresaDAO.findByCnpj("413.808.653-01");
            Empresa savedEmpresa2 = empresaDAO.findByCnpj("413.808.653-02");

            // Create and save Vaga entities
            Vaga vaga1 = new Vaga();
            vaga1.setNome("Vaga Nome");
            vaga1.setNivel("Junior");
            vaga1.setAnosContrato(1);
            vaga1.setSalario(new BigDecimal("3000.00"));
            vaga1.setEmpresa(savedEmpresa1); // Link to Empresa
            vagaDAO.save(vaga1);

            Vaga vaga2 = new Vaga();
            vaga2.setNome("Vaga Nome 2");
            vaga2.setNivel("Senior");
            vaga2.setAnosContrato(2);
            vaga2.setSalario(new BigDecimal("5000.00"));
            vaga2.setEmpresa(savedEmpresa2); // Link to Empresa
            vagaDAO.save(vaga2);

            // Create and save Usuario entities for Profissional
            Usuario usuarioProfissional1 = new Usuario();
            usuarioProfissional1.setUsername("prof1");
            usuarioProfissional1.setPassword(encoder.encode("prof1"));
            usuarioProfissional1.setRole("ROLE_USER");
            usuarioProfissional1.setEnabled(true);
            usuarioDAO.save(usuarioProfissional1);

            Usuario usuarioProfissional2 = new Usuario();
            usuarioProfissional2.setUsername("prof2");
            usuarioProfissional2.setPassword(encoder.encode("prof2"));
            usuarioProfissional2.setRole("ROLE_USER");
            usuarioProfissional2.setEnabled(true);
            usuarioDAO.save(usuarioProfissional2);

            // Create and save Profissional entities
            Profissional profissional1 = new Profissional();
            profissional1.setCpf("123.456.789-01");
            profissional1.setNome("Profissional 1");
            profissional1.setUsuario(usuarioProfissional1);
            profissionalDAO.save(profissional1);

            Profissional profissional2 = new Profissional();
            profissional2.setCpf("123.456.789-02");
            profissional2.setNome("Profissional 2");
            profissional2.setUsuario(usuarioProfissional2);
            profissionalDAO.save(profissional2);

            // Create and save Candidatura (Profissional applying to Vaga)
            Candidatura candidatura1 = new Candidatura();
            candidatura1.setProfissional(profissional1);
            candidatura1.setVaga(vaga1);
            candidaturaDAO.save(candidatura1);

            Candidatura candidatura2 = new Candidatura();
            candidatura2.setProfissional(profissional2);
            candidatura2.setVaga(vaga2);
            candidaturaDAO.save(candidatura2);
		};
	}
}
