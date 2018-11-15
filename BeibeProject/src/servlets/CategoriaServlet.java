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

import com.google.gson.Gson;

import beans.Categoria;
import classes.Resposta;
import dao.CategoriaDao;
import exceptions.ErroAddCategoria;
import facades.CategoriaFacade;

/**
 * Servlet implementation class CategoriaServlet
 */
@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter("action");
		String json = "";
		String path = "";

		switch (action) {

		case "addCategoria":

			try {
				String nome = String.valueOf(req.getParameter("nomeCategoria"));
				Categoria categoria = new Categoria(nome)
				categoria = CategoriaFacade.addCategoria(categoria);

				json = new Gson().toJson(new Resposta(true, categoria));

			} catch (ErroAddCategoria e) {
				json = new Gson().toJson(new Resposta(e.getMessage(), false));
			} finally {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}

			break;

		case "getCategoria":

			try {
				List<Categoria> listCategoria;
				listCategoria = CategoriaDao.getCategorias();
				req.setAttribute("categorias", listCategoria);
				req.setAttribute("menuActive", 3);
				path = "/categoria.jsp";

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
				CategoriaFacade.updateCategoria(req);
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
