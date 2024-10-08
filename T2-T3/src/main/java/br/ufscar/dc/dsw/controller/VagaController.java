package br.ufscar.dc.dsw.controller;

import java.security.Principal;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.ufscar.dc.dsw.service.impl.UsuarioService;
import br.ufscar.dc.dsw.service.spec.ICandidaturaService;
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

    @Autowired
    private ICandidaturaService candidaturaService;

    @GetMapping("/listacandidaturas/{id}")
    public String listarCandidaturas(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("candidaturas", candidaturaService.buscarPorVaga(id));
        return "vaga/listacandid";
    }



    @GetMapping("/listartodos")
    public String listartodos(ModelMap model) {
        
        String status = "Aberta";
        model.addAttribute("vagas", vagaService.buscarPorStatus(status));
        return "vaga/listatodos";
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
        } else {
            List<Vaga> vagas = vagaService.buscarPorEmpresa(empresa);
            model.addAttribute("vagas", vagas);
        }

        return "vaga/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Vaga vaga, BindingResult result, RedirectAttributes attr, Principal principal) {

        if (result.hasErrors()) {
            return "vaga/cadastro";
        }

        String username = principal.getName();
        Usuario usuario = usuarioService.buscarPorUsername(username);
        
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Empresa empresa = empresaService.buscarPorUsuario(usuario);

        // Verifica se o usuário está vinculado a uma empresa
        if (empresa == null) {
            throw new RuntimeException("Usuário não vinculado a uma empresa.");
        }


        vaga.setEmpresa(empresa); // Garante que a vaga esteja associada à empresa correta

        vagaService.salvar(vaga);
        attr.addFlashAttribute("success", "vaga.create.success");
        return "redirect:/vagas/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("vaga", vagaService.buscarPorId(id));
        return "vaga/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Vaga vaga, BindingResult result, RedirectAttributes attr, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioService.buscarPorUsername(username);

        if (result.hasErrors()) {
            return "vaga/cadastro";
        }
        Empresa empresa = empresaService.buscarPorUsuario(usuario);

        // Verifica se o usuário está vinculado a uma empresa
        if (empresa == null) {
            throw new RuntimeException("Usuário não vinculado a uma empresa.");
        }  
        vaga.setEmpresa(empresa);
        vagaService.salvar(vaga);
        attr.addFlashAttribute("success", "vaga.edit.success");
        return "redirect:/vagas/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        vagaService.excluir(id);
        attr.addFlashAttribute("success", "vaga.delete.success");
        return "redirect:/vagas/listar";
    }

    @ModelAttribute("empresas")
    public List<Empresa> listaEmpresas() {
        return empresaService.buscarTodos();
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Vaga vaga, ModelMap model, Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioService.buscarPorUsername(username);
        
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found");
        }
    
        Empresa empresa = empresaService.buscarPorUsuario(usuario);
        
        // Verifica se o usuário está vinculado a uma empresa
        if (empresa == null) {
            throw new RuntimeException("Usuário não vinculado a uma empresa.");
        }
        
        vaga.setEmpresa(empresa); // Define a empresa da vaga automaticamente
        model.addAttribute("vaga", vaga);
        return "vaga/cadastro";
    }
}

