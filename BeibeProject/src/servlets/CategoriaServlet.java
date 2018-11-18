package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.Categoria;
import beans.Login;
import classes.Resposta;
import dao.CategoriaDao;
import exceptions.ErroAddCategoria;


/**
 * Servlet implementation class CategoriaServlet
 */
@WebServlet("/Categoria")
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {

		HttpSession session = ((HttpServletRequest) req).getSession(true);
		Login lb = (Login) session.getAttribute("login");
		
		if(lb == null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(req, resp);
		}else if(!lb.getTipo().equals("F")) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/permissao.jsp");
			rd.forward(req, resp);
		}
		
		String action = req.getParameter("action");
		String json = "";
		String path = "";

		switch (action) {

		case "addCategoria":

			try {
				String nome = String.valueOf(req.getParameter("nomeCategoria"));
				Categoria categoria = new Categoria(nome);
				categoria = CategoriaDao.addCategoria(categoria);

				json = new Gson().toJson(new Resposta(true, categoria));

			} catch (ErroAddCategoria e) {
				json = new Gson().toJson(new Resposta(e.getMessage(), false));
			} finally {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}

			break;

		case "getCategorias":

			try {
				List<Categoria> listCategoria;
				listCategoria = CategoriaDao.getCategorias();
				req.setAttribute("categorias", listCategoria);
				req.setAttribute("menuActive", 3);
				path = "/WEB-INF/views/categoria.jsp";

			} catch (Exception e) {
				req.setAttribute("javax.servlet.jsp.jspException", e);
//	             request.setAttribute("msg", "Erro ao listar os cliente");
//	             request.setAttribute("page", "http://localhost:8080/MeuTADS/ClienteServlet");
				req.setAttribute("javax.servlet.error.status_code", 500);
				path = "/erro.jsp";
			}
			break;

		case "removeCategoria":
			try {
				
				Integer id = Integer.parseInt(req.getParameter("id"));
				CategoriaDao.removeCategoria(id);				
				json = new Gson().toJson(new Resposta(true));
				
			} catch (Exception e) {
				json = new Gson().toJson(new Resposta(e.getMessage(), false));

			} finally {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}
			break;

		case "updateCategoria":

			try {
				String nome = String.valueOf(req.getParameter("nomeCategoria"));
				Integer id = Integer.parseInt(req.getParameter("id"));
				
				Categoria categoria = new Categoria(id, nome);
				CategoriaDao.updateCategoria(categoria);

				json = new Gson().toJson(new Resposta(true));
		
			} catch (Exception e) {
				json = new Gson().toJson(new Resposta(e.getMessage(), false));
			
			} finally {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}

			break;

		}

		if (json == "") {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
			rd.forward(req, resp);
		}
	}
}
