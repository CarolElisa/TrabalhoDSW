package br.ufscar.dc.dsw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.ICandidaturaService;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Controller
@RequestMapping("/candidaturas")
public class CandidaturaController {
	
	@Autowired
	private ICandidaturaService service;
	
	@Autowired
	private IVagaService vagaService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Candidatura candidatura) {
		String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		candidatura.setUsuario(this.getUsuario());
		candidatura.setData(data);
		return "candidatura/cadastro";
	}
	
	private Usuario getUsuario() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDetails.getUsuario();
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
					
		model.addAttribute("candidaturas",service.buscarTodosPorUsuario(this.getUsuario()));
		
		return "candidatura/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Candidatura candidatura, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "candidatura/cadastro";
		}
		
		service.salvar(candidatura);
		attr.addFlashAttribute("sucess", "candidatura.create.sucess");
		return "redirect:/candidaturas/listar";
	}
	
	@ModelAttribute("vagas")
	public List<Vaga> listaVagas() {
		return vagaService.buscarTodos();
	}
}
