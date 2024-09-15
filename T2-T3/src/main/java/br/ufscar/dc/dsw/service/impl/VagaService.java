package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IVagaDAO;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Service
@Transactional(readOnly = false)
public class VagaService implements IVagaService {

	@Autowired
	IVagaDAO dao;
	
	public void salvar(Vaga vaga) {
		dao.save(vaga);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Vaga buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Vaga> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Vaga> buscarPorEmpresa(Empresa e)
	{
		return dao.findByEmpresa(e);
	}

	@Transactional
    public List<Vaga> buscarVagasPorEmpresa(Empresa empresa) {
        return dao.findByEmpresa(empresa);
    }

	@Transactional
	public List<Vaga> buscarSemInscricao(Long id)
	{
		return  dao.findJobWithoutApply(id);
	}
	
}