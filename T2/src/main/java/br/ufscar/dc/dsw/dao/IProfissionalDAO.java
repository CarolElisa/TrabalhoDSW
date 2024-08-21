package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;

@SuppressWarnings("unchecked")
public interface IProfissionalDAO extends CrudRepository<Profissional, Long> {

    Profissional findById(long id);

    Profissional findByCpf(String cpf);
    Profissional findByUsuario(Usuario usuario);
	
    List<Profissional> findAll();

    //@Query("SELECT c FROM Candidatura c WHERE c.profissional.id = :profissionalId")
    //List<Candidatura> findAllCandidaturaByProfissional(@Param("profissionalId") Long profissionalId);
    //Profissional save(Profissional profissional);

    void deleteById(Long id);
}