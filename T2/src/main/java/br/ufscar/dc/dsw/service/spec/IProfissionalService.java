package br.ufscar.dc.dsw.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Candidatura;

public interface IProfissionalService {

	Profissional buscarPorId(Long id);


	Profissional buscarPorCpf(String cpf);
	Profissional buscarPorUsuario(Usuario usuario);

	List<Profissional> buscarTodos();

	void salvar(Profissional profissional);

	void excluir(Long id);
	
	boolean profissionalTemCandidaturas(Long id);

	List<Candidatura> buscarCandidaturaPorProfissional(Profissional profissional);

	List<Usuario> buscarUsuarioProfissionalListado(Long id);
	List<Profissional> buscarProfissionalListado(Long id);
	
}
