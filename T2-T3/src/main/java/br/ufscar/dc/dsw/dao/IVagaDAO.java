package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.*;

@SuppressWarnings("unchecked")
public interface IVagaDAO extends CrudRepository<Vaga, Long>{

	Vaga findById(long id);

	List<Vaga> findAll();
	
	Vaga save(Vaga vaga);

	void deleteById(Long id);

	List<Vaga> findByEmpresa(Empresa empresa);

	@Query("select v from Vaga v where id not in (select c.vaga_id from Candidatura c where c.profissional_id not in (:id)) ")
	List<Vaga> findJobWithoutApply(Long id);
}