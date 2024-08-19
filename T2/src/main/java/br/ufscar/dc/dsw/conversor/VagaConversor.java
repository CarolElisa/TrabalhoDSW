package br.ufscar.dc.dsw.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Component
public class VagaConversor implements Converter<String, Vaga>{

	@Autowired
	private IVagaService service;
	
	@Override
	public Vaga convert(String text) {
		
		if (text.isEmpty()) {
		 return null;	
		}
		
		Long id = Long.valueOf(text);	
		return service.buscarPorId(id);
	}
}
