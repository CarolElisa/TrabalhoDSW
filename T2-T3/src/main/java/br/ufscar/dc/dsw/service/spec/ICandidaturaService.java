package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Profissional;

public interface ICandidaturaService {

	Candidatura buscarPorId(Long id);
	List<Candidatura> buscarPorProfissional(Profissional p);
	List<Candidatura> buscarPorVaga(Long idVaga);

	void salvar(Candidatura empresa);
	
	void excluir(long id);
}
