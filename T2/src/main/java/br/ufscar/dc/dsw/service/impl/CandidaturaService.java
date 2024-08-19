package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ICandidaturaDAO;
import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.ICandidaturaService;

@Service
@Transactional(readOnly = false)
public class CandidaturaService implements ICandidaturaService {

	@Autowired
	ICandidaturaDAO dao;
	
	public void salvar(Candidatura candidatura) {
		dao.save(candidatura);
	}

	@Transactional(readOnly = true)
	public Candidatura buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Candidatura> buscarTodosPorUsuario(Usuario u) {
		return dao.findAllByUsuario(u);
	}
}
