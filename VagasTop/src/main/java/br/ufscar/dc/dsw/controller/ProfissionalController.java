package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/profissionais/*")
public class ProfissionalController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private ProfissionalDAO dao;

    @Override
    public void init() {
        dao = new ProfissionalDAO();
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
        List<Profissional> listaProfissionais = dao.getAll();
        request.setAttribute("listaProfissionais", listaProfissionais);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/lista.jsp");
        dispatcher.forward(request, response);
    }

    /*private Map<Long, String> getEditoras() {
        Map <Long,String> editoras = new HashMap<>();
        for (Editora editora: new EditoraDAO().getAll()) {
            editoras.put(editora.getId(), editora.getNome());
        }
        return editoras;
    }*/
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("profissional", null);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        Profissional profissional = dao.get(cpf);
        request.setAttribute("profissional", profissional);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profissional/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String datanasc = request.getParameter("datanasc");
        
        Profissional profissional = new Profissional (cpf, nome, email, senha, telefone, sexo, datanasc);
        dao.insert(profissional);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String datanasc = request.getParameter("datanasc");
                
        Profissional profissional = new Profissional(nome, email, senha, telefone, sexo, datanasc);
        dao.update(profissional);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String cpf = request.getParameter("cpf");

        Profissional profissional = new Profissional(cpf);
        dao.delete(profissional);
        response.sendRedirect("lista");
    }
}
