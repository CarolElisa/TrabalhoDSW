package br.ufscar.dc.dsw.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.impl.ProfissionalService;
import br.ufscar.dc.dsw.service.impl.UsuarioService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private IProfissionalService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Profissional profissional, ModelMap model) {
		model.addAttribute("usuarios", usuarioService.getUsuariosSemProfissional());
		model.addAttribute("profissional", new Profissional());
		model.addAttribute("editando", false);
		return "profissional/cadastro";
	}


	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("profissionais",service.buscarTodos());
		return "profissional/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "profissional/cadastro";
		}
		
		service.salvar(profissional);
		attr.addFlashAttribute("sucess", "profissional.create.sucess");
		return "redirect:/profissionais/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		Profissional profissional = service.buscarPorId(id);
		model.addAttribute("usuarios", profissionalService.buscarUsuarioProfissionalListado(id));
		model.addAttribute("profissional", profissional);
		model.addAttribute("editando", false);
		return "profissional/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {

		service.salvar(profissional);
		attr.addFlashAttribute("sucess", "profissional.edit.sucess");
	
		return "redirect:/profissionais/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (service.profissionalTemCandidaturas(id)) {
			model.addAttribute("fail", "profissional.delete.fail");
		} else {
			service.excluir(id);
			model.addAttribute("sucess", "profissional.delete.sucess");
		}
		return listar(model);
	}
}
