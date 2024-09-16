package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;

@SuppressWarnings("unchecked")
public interface IVagaDAO extends CrudRepository<Vaga, Long>{

	Vaga findById(long id);

	List<Vaga> findAll();

	List<Vaga> findByStatus(String status);
	
	Vaga save(Vaga vaga);
	void deleteById(Long id);

	List<Vaga> findByEmpresa(Empresa empresa);

	@Query("select v from Vaga v " +
	"left join Candidatura c on c.vaga.id = v.id and c.profissional.id = :profissionalId " +
	"where v.status = 'Aberta' and c is null")
List<Vaga> findJobWithoutApply(Long profissionalId);
}