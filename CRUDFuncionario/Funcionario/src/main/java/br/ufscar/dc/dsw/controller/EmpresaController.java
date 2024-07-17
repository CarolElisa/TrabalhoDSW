package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.EmpresaDAO;

import br.ufscar.dc.dsw.domain.Empresa;


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

@WebServlet(urlPatterns = "/empresas/*")
public class EmpresaController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private EmpresaDAO dao;

    @Override
    public void init() {
        dao = new EmpresaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
                
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
        System.out.println("Number of companies: " + listaEmpresas.size());
        for (Empresa empresa : listaEmpresas) {
            System.out.println(empresa.getCnpj() + " - " + empresa.getEmail()+ " - " + empresa.getSenha()+ " - " + empresa.getCidade()+ " - " + empresa.getDescricao()+ " - " + empresa.getNome());
        }
        request.setAttribute("listaEmpresas", listaEmpresas);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/portais/empresa_lista.jsp");
        dispatcher.forward(request, response);
    }


    private Map<String, String> getEmpresas() {
        Map <String,String> empresas = new HashMap<>();
        for (Empresa empresa: new EmpresaDAO().getAll()) {
            empresas.put(empresa.getCnpj(), empresa.getNome());
        }
        return empresas;
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("empresa", getEmpresas());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/portais/empresa_formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cnpj = (request.getParameter("cnpj"));
        Empresa empresa = dao.get(cnpj);
        request.setAttribute("empresa", empresa);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/portais/empresa_formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cidade = request.getParameter("cidade");
        Empresa empresa = new Empresa(cnpj, nome, descricao, email, senha, cidade);
        dao.insert(empresa);
        response.sendRedirect("empresa_lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            String cnpj = request.getParameter("cnpj");
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String cidade = request.getParameter("cidade");

        //Empresa empresa = new EmpresaDAO().get(empresaID);
        
        Empresa empresa = new Empresa(cnpj, nome, descricao, email, senha, cidade);
        dao.update(empresa);
        response.sendRedirect("empresa_lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String cnpj = request.getParameter("cnpj");

        Empresa empresa = new Empresa(cnpj);
        dao.delete(empresa);
        response.sendRedirect("empresa_lista");
    }
}
