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

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.impl.EmpresaService;
import br.ufscar.dc.dsw.service.impl.UsuarioService;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IVagaService;

@Controller
@RequestMapping("/vagas")
public class VagaController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private IVagaService vagaService;

	@Autowired
	private IEmpresaService empresaService;

	@GetMapping("/cadastrar")
	public String cadastrar(Vaga vaga) {
		return "vaga/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioService.buscarPorUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Empresa empresa = empresaService.buscarPorUsuario(usuario);

        if (empresa == null) {
            model.addAttribute("vagas", vagaService.buscarTodos());
        }
		else
		{
			List<Vaga> vagas = vagaService.buscarPorEmpresa(empresa);
			model.addAttribute("vagas", vagas);
		}

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

/*
	@GetMapping("listarPorEmpresa")
    public String listarVagasPorEmpresa(ModelMap model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioService.buscarPorUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Empresa empresa = empresaService.buscarPorUsuario(usuario);

        if (empresa == null) {
            throw new RuntimeException("Empresa not found for the user");
        }
		List<Vaga> vagas = vagaService.buscarPorEmpresa(empresa);

		System.err.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.err.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.err.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.err.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		System.err.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		for(Vaga vaga: vagas)
		{
			System.err.println(vaga.getNome());
		}
        model.addAttribute("vagas", vagas);
        return "vaga/lista";
		
    }*/
}
