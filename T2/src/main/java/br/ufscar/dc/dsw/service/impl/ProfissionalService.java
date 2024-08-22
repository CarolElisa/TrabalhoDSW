package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Service
@Transactional(readOnly = false)
public class ProfissionalService implements IProfissionalService {

	@Autowired
	IProfissionalDAO dao;
	
	public void salvar(Profissional profissional) {
		dao.save(profissional);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Profissional buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Profissional> buscarTodos() {
		return dao.findAll();
	}
	
	@Transactional(readOnly = true)
	public boolean profissionalTemCandidaturas(Long id) {
		return !dao.findById(id.longValue()).getCandidaturas().isEmpty(); 
	}

    @Transactional
    public Profissional buscarPorCpf(String cpf) {
        return dao.findByCpf(cpf);
    }

    @Transactional(readOnly = true)
    public Profissional buscarPorUsuario(Usuario usuario) {
        return dao.findByUsuario(usuario);
    }

	@Transactional(readOnly = true)
	public List<Usuario> buscarUsuarioProfissionalListado(Long id)
	{
		System.out.println(id + " BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		return dao.findUsuarioProfissionalListado(id);
	}

	@Transactional
	
	public List<Candidatura> buscarCandidaturaPorProfissional(Profissional profissional){
		return null;//dao.findAllCandidaturaByProfissional(profissional.getId());
	}

	@Transactional
	public List<Profissional> buscarProfissionalListado(Long id){
		return dao.findProfissionalListado(id);
	}

}
