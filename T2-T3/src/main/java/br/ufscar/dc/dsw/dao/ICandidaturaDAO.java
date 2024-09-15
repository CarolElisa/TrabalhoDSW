package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Usuario;

@SuppressWarnings("unchecked")
public interface ICandidaturaDAO extends CrudRepository<Candidatura, Long>{

	Candidatura findById(long id);
	
	List<Candidatura> findAllByProfissional(Profissional p);
	@Query("select c from Candidatura c where c.vaga.id = :id")
	List<Candidatura> findAllByVaga(Long id);
	void deleteById(long id);

	Candidatura save(Candidatura candidatura);

}