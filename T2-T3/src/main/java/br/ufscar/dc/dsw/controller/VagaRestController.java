package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@CrossOrigin
@RestController
public class VagaRestController {

	@Autowired
	private IVagaService service;

    @Autowired
    private IEmpresaService serviceEmpresa;

	@GetMapping(path = "/api/vagas")
	public ResponseEntity<List<Vaga>> lista() {
		List<Vaga> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
    
    @GetMapping(path = "/api/vagas/{id}")
	public ResponseEntity<Vaga> listaVagas(@PathVariable("id") long id) {
		Vaga vaga = service.buscarPorId(id);
		if (vaga == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(vaga);
	}

    @GetMapping(path = "/api/vagas/empresas/{id}")
	public ResponseEntity<List<Vaga>> listaPorEmpresas(@PathVariable("id") long id) {
        Empresa empresa = serviceEmpresa.buscarPorId(id);
        if (empresa == null) {
			return ResponseEntity.notFound().build();
		}
        
        List<Vaga> vaga = service.buscarPorEmpresa(empresa);
		if (vaga == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(vaga);
	}
}