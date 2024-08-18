package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.dao.IVagaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;

@SpringBootApplication
public class VagasMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(VagasMvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IEmpresaDAO empresaDAO, IVagaDAO vagaDAO) {
		return (args) -> {
						
			Empresa e1 = new Empresa();
			e1.setCNPJ("55.789.390/0008-99");
			e1.setNome("Tech Mania");
			empresaDAO.save(e1);
			
			Empresa e2 = new Empresa();
			e2.setCNPJ("71.150.470/0001-40");
			e2.setNome("Escola do Fulaninho");
			empresaDAO.save(e2);
			
			Empresa e3 = new Empresa();
			e3.setCNPJ("32.106.536/0001-82");
			e3.setNome("Padaria do Manoel");
			empresaDAO.save(e3);
			
			Vaga v1 = new Vaga();
			v1.setNome("Analista de QA");
			v1.setNivel("Sênior");
			v1.setDescricao("Vaga para analista de qualidade com pelo menos 2 anos de experiência com Testes Unitários Automatizados");
			v1.setAnosContrato(3);
			v1.setSalario(BigDecimal.valueOf(5000.00));
			v1.setEmpresa(e1);
			vagaDAO.save(v1);
			
			Vaga v2 = new Vaga();
			v2.setNome("Professor Substituto Geografia");
			v2.setNivel("Ensino Fundamental");
			v2.setDescricao("Vaga para professor com pelo menos 2 anos de formado no curso de Geografia");
			v2.setAnosContrato(2);
			v2.setSalario(BigDecimal.valueOf(2500.99));
			v2.setEmpresa(e2);
			vagaDAO.save(v2);
			
			Vaga v3 = new Vaga();
			v3.setNome("Atendente");
			v3.setNivel("Básico");
			v3.setDescricao("Vaga para atendente sem necessidade de experiência prévia");
			v3.setAnosContrato(1);
			v3.setSalario(BigDecimal.valueOf(1200));
			v3.setEmpresa(e3);
			vagaDAO.save(v3);
	
		};
	}
}
