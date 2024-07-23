package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.EmpresaDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/empresas/*")
public class EmpresaController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private EmpresaDAO dao;
    private UsuarioDAO daoUsuario;
    @Override
    public void init() {
        dao = new EmpresaDAO();
        daoUsuario = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
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

    private void lista(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Empresa> listaEmpresas = new EmpresaDAO().getAll();
        request.setAttribute("listaEmpresas", listaEmpresas);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/empresa/lista.jsp");
        dispatcher.forward(request, response);
    }
    


    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cnpj = (request.getParameter("cnpj"));
        request.setAttribute("empresa", null);
        request.setAttribute("cnpj", cnpj);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/empresa/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cnpj = request.getParameter("cnpj");
        //Long id = Long.parseLong(request.getParameter(cnpj));
        Empresa empresa = dao.get(cnpj);
        request.setAttribute("empresa", empresa);
        //request.setAttribute("cnpj", cnpj);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/empresa/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String usuario_nome = request.getParameter("usuNome");
        String usuario_login = request.getParameter("usuLogin");
        String usuario_senha = request.getParameter("usuSenha");
        String usuario_papel = request.getParameter("usuPapel");
        String usuario_doc = request.getParameter("cnpj");        
        Usuario usuario = new Usuario(usuario_nome, usuario_login, usuario_senha, usuario_papel, usuario_doc);        
        daoUsuario.insert(usuario);


        String cnpj = usuario_doc;
        String nome = usuario_nome;
        String descricao = request.getParameter("descricao");
        String email = usuario_login;
        String senha = usuario_senha;
        String cidade = request.getParameter("cidade");
        Empresa empresa = new Empresa(usuario.getId(), cnpj, nome, descricao, email, senha, cidade);
        dao.insert(empresa);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            String usuario_nome = request.getParameter("usuNome");
            String usuario_login = request.getParameter("usuLogin");
            String usuario_senha = request.getParameter("usuSenha");
            String usuario_papel = request.getParameter("usuPapel");
            String usuario_doc = request.getParameter("cnpj");        
            Usuario usuario = new Usuario(usuario_nome, usuario_login, usuario_senha, usuario_papel, usuario_doc);        
            daoUsuario.insert(usuario);
        
            
            String cnpj = usuario_doc;
            String nome = usuario_nome;
            String descricao = request.getParameter("descricao");
            String email = usuario_login;
            String senha = usuario_senha;
            String cidade = request.getParameter("cidade");
        
        Empresa empresa = new Empresa(usuario.getId(), cnpj, nome, descricao, email, senha, cidade);
        dao.update(empresa);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String cnpj = request.getParameter("cnpj");

        Empresa empresa = new Empresa(id, cnpj);
        dao.delete(empresa);
        response.sendRedirect("lista");
    }
}
