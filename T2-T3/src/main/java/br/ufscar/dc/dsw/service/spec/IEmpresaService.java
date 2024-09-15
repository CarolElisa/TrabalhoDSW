package br.ufscar.dc.dsw.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;

public interface IEmpresaService {

	Empresa buscarPorId(Long id);


	Empresa buscarPorCnpj(String cnpj);
	Empresa buscarPorUsuario(Usuario usuario);

	List<Empresa> buscarTodos();

	void salvar(Empresa empresa);

	void excluir(Long id);
	
	boolean empresaTemVagas(Long id);

	List<Vaga> buscarVagasPorEmpresa(Empresa empresa);

	
}
