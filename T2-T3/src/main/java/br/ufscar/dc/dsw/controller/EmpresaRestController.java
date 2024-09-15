package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;

@CrossOrigin
@RestController
public class EmpresaRestController {

	@Autowired
	private IEmpresaService service;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	private void parse(Usuario usuario, JSONObject json) {

		Map<String, Object> map = (Map<String, Object>) json.get("usuario");
		
		Object id = map.get("id");
		if (id instanceof Integer) {
			usuario.setId(((Integer) id).longValue());
		} else {
			usuario.setId((Long) id);
		}
	}

	private void parse(Empresa empresa, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				empresa.setId(((Integer) id).longValue());
			} else {
				empresa.setId((Long) id);
			}
		}

		empresa.setNome((String) json.get("nome"));
		empresa.setCnpj((String) json.get("cnpj"));
        empresa.setCidade((String) json.get("cidade"));
		
		Usuario usuario = new Usuario();
		parse(usuario, json);
		empresa.setUsuario(usuario);
	}


	@GetMapping(path = "/api/empresas")
	public ResponseEntity<List<Empresa>> lista() {
		List<Empresa> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
    
    @GetMapping(path = "/api/empresas/{id}")
	public ResponseEntity<Empresa> listaPorId(@PathVariable("id") long id) {
		Empresa empresa = service.buscarPorId(id);
		if (empresa == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(empresa);
	}

    @GetMapping(path = "/api/empresas/cidades/{cidade}")
	public ResponseEntity<Empresa> listaPorCidade(@PathVariable("cidade") String cidade) {
		Empresa empresa = service.buscarPorCidade(cidade);
		if (empresa == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(empresa);
	}

	@PostMapping(path = "/api/empresas")
	@ResponseBody
	public ResponseEntity<Empresa> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Empresa empresa = new Empresa();
				parse(empresa, json);
				service.salvar(empresa);
				return ResponseEntity.ok(empresa);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@PutMapping(path = "/api/empresas/{id}")
	public ResponseEntity<Empresa> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Empresa empresa = service.buscarPorId(id);
				if (empresa == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(empresa, json);
					service.salvar(empresa);
					return ResponseEntity.ok(empresa);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@DeleteMapping(path = "/api/empresas/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Empresa empresa = service.buscarPorId(id);
		if (empresa == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
}
