package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Usuario;

public interface IVagaService {

	Vaga buscarPorId(Long id);
	
	List<Vaga> buscarTodos();

	List<Vaga> buscarPorStatus(String status);
	
	void salvar(Vaga vaga);
	
	void excluir(Long id);

	List<Vaga> buscarPorEmpresa(Empresa e);

	List<Vaga> buscarSemInscricao(Long id);
	
}
