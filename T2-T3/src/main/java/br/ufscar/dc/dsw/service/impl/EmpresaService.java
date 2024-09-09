package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;

@Service
@Transactional(readOnly = false)
public class EmpresaService implements IEmpresaService {

	@Autowired
	IEmpresaDAO dao;
	
	public void salvar(Empresa empresa) {
		dao.save(empresa);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Empresa buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Empresa> buscarTodos() {
		return dao.findAll();
	}
	
	@Transactional(readOnly = true)
	public boolean empresaTemVagas(Long id) {
		return !dao.findById(id.longValue()).getVagas().isEmpty(); 
	}

    @Transactional
    public Empresa buscarPorCnpj(String cnpj) {
        return dao.findByCnpj(cnpj);
    }

    @Transactional
    public Empresa buscarPorUsuario(Usuario usuario) {
        return dao.findByUsuario(usuario);
    }



	@Transactional
	
	public List<Vaga> buscarVagasPorEmpresa(Empresa empresa){
		return dao.findAllVagaByEmpresa(empresa.getId());
	}


}
