package br.ufscar.dc.dsw.controller;

import java.security.Principal;
import java.util.List;

import jakarta.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.SystemProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Candidatura;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.impl.EmpresaService;
import br.ufscar.dc.dsw.service.impl.UsuarioService;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IVagaService;
import br.ufscar.dc.dsw.service.spec.ICandidaturaService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Controller
@RequestMapping("/candidaturas")
public class CandidaturaController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private IVagaService vagaService;

	@Autowired
	private IEmpresaService empresaService;

	@Autowired
	private IProfissionalService profissionalService;

	@Autowired
	private ICandidaturaService candidaturaService;

	@GetMapping("/cadastrar")
	public String cadastrar(Candidatura candidatura) {
		return "candidatura/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioService.buscarPorUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Profissional profissional = profissionalService.buscarPorUsuario(usuario);

        if (profissional == null) {
            return "home";
        }
		else
		{
			List<Candidatura> candidaturas = candidaturaService.buscarPorProfissional(profissional);
			model.addAttribute("candidaturas", candidaturas);
		}

		return "candidatura/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Candidatura candidatura, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "candidatura/cadastro";
		}

		candidaturaService.salvar(candidatura);
		attr.addFlashAttribute("sucess", "candidatura.create.sucess");
		return "redirect:/candidaturas/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("candidatura", candidaturaService.buscarPorId(id));
		return "candidatura/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Candidatura candidatura, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "vaga/cadastro";
		}

		candidaturaService.salvar(candidatura);
		attr.addFlashAttribute("sucess", "candidatura.edit.sucess");
		return "redirect:/candidaturas/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		candidaturaService.excluir(id);
		attr.addFlashAttribute("sucess", "candidatura.delete.sucess");
		return "redirect:/vagas/listar";
	}

	@ModelAttribute("empresas")
	public List<Empresa> listaEmpresas() {
		return empresaService.buscarTodos();
	}

}
