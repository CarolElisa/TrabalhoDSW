package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.dao.VagaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/vagas/*")
public class VagaController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private VagaDAO dao;

    @Override
    public void init() {
        dao = new VagaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		Erro erros = new Erro();
 
		if (usuario == null) {
			response.sendRedirect(request.getContextPath());
			return;
		} else if (!usuario.getPapel().equals("ADMIN")) {
			erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
			return;
		}
*/		
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vaga> listaVagas = null;
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        System.err.println("DOCUMENTO POR EMPRESA: " + usuario.getPapel());
        if(usuario.getPapel().equals("ADMIN"))
        {
            listaVagas = dao.getAll();
        }
        else if(usuario.getPapel().equals("FUNC"))
        {
            listaVagas = dao.getAll();
        }
        else if(usuario.getPapel().equals("EMPR"))
        {
            
            listaVagas = dao.getPorEmpresa(usuario.getDocumento());
            
        }
        
        
        request.setAttribute("listaVagas", listaVagas);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vaga/lista.jsp");
        dispatcher.forward(request, response);
    }

    private Map<String, String> getEmpresas() {
        Map<String, String> empresas = new HashMap<>();
        for (Empresa empresa : new EmpresaDAO().getAll()) {
            empresas.put(empresa.getCnpj(), empresa.getNome());
        }
        return empresas;
    }

    private Map<String, String> getEmpresaEspecifica(String cnpj) {
        Map<String, String> empresas = new HashMap<>();
        for (Empresa empresa : new EmpresaDAO().getEmpresaEspecifica(cnpj)) {
            empresas.put(empresa.getCnpj(), empresa.getNome());
        }
        return empresas;
    }


    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String tipoUser = (String) request.getSession().getAttribute("tipoUser");
            String cnpj = (String) request.getSession().getAttribute("documento");
            System.out.println("TIPO USER: " + tipoUser);
        if(tipoUser.equals("empresa"))
        {
            
            System.out.println("CNPJ:" + cnpj);
            request.setAttribute("empresas", getEmpresaEspecifica(cnpj));
        }
        else
        {
            request.setAttribute("empresas", getEmpresas());
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vaga/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            Long id = Long.parseLong(request.getParameter("id"));

            Vaga vaga = dao.get(id);
        
            request.setAttribute("vaga", vaga);
        
            request.setAttribute("empresas", getEmpresaEspecifica(vaga.getEmpresaCnpj()));
        
            RequestDispatcher dispatcher = request.getRequestDispatcher("/vaga/formulario.jsp");
        
            dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String funcao = request.getParameter("funcao");
        String nivel = request.getParameter("nivel");
        Integer anosContrato = Integer.parseInt(request.getParameter("anosContrato"));
        Float salario = Float.parseFloat(request.getParameter("salario"));

        String empresaCNPJ = request.getParameter("empresa");
        
        Empresa empresa = new EmpresaDAO().get(empresaCNPJ);

        Vaga vaga = new Vaga(funcao, nivel, anosContrato, salario, empresa.getCnpj(), empresa.getNome());
        dao.insert(vaga);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String titulo = request.getParameter("funcao");
        String autor = request.getParameter("nivel");
        Integer ano = Integer.parseInt(request.getParameter("anosContrato"));
        Float preco = Float.parseFloat(request.getParameter("salario"));

        String empresaCNPJ = request.getParameter("empresa");
        Empresa empresa = new EmpresaDAO().get(empresaCNPJ);

        Vaga vaga = new Vaga(id, titulo, autor, ano, preco, empresa);
        dao.update(vaga);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Vaga vaga = new Vaga(id);
        dao.delete(vaga);
        response.sendRedirect("lista");
    }
}