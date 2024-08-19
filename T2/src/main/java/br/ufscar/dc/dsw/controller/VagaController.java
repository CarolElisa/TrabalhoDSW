package br.ufscar.dc.dsw.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Controller
@RequestMapping("/vagas")
public class VagaController {

	@Autowired
	private IVagaService vagaService;

	@Autowired
	private IEmpresaService empresaService;

	@GetMapping("/cadastrar")
	public String cadastrar(Vaga vaga) {
		return "vaga/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("vagas", vagaService.buscarTodos());
		return "vaga/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Vaga vaga, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "vaga/cadastro";
		}

		vagaService.salvar(vaga);
		attr.addFlashAttribute("sucess", "vaga.create.sucess");
		return "redirect:/vagas/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("vaga", vagaService.buscarPorId(id));
		return "vaga/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Vaga vaga, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "vaga/cadastro";
		}

		vagaService.salvar(vaga);
		attr.addFlashAttribute("sucess", "vaga.edit.sucess");
		return "redirect:/vagas/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		vagaService.excluir(id);
		attr.addFlashAttribute("sucess", "vaga.delete.sucess");
		return "redirect:/vagas/listar";
	}

	@ModelAttribute("empresas")
	public List<Empresa> listaEmpresas() {
		return empresaService.buscarTodos();
	}
}
