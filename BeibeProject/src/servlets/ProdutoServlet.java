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
import beans.Produto;
import classes.Resposta;
import dao.CategoriaDao;
import dao.ProdutoDao;
import exceptions.ErroAddCategoria;
import exceptions.ErroAddProduto;
import facades.ProdutoFacade;

@WebServlet("/Produto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {

		HttpSession session = ((HttpServletRequest) req).getSession(true);
		Login lb = (Login) session.getAttribute("login");

		if (lb == null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(req, resp);
		} else if (!lb.getTipo().equals("F")) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/permissao.jsp");
			rd.forward(req, resp);
		}

		String json = "",path = "",action = String.valueOf(req.getParameter("action"));
	
		switch (action) {

		case "addProduto":

			try {
				Produto produto = ProdutoFacade.addProduto(req);
				json = new Gson().toJson(new Resposta(true, produto));
			} catch (ErroAddProduto e) {
				json = new Gson().toJson(new Resposta(e.getMessage(), false));
			} finally {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}

			break;

		case "getProdutos":

			try {

				List<Categoria> listCategoria = CategoriaDao.getCategorias();
				List<Produto> listProduto = ProdutoDao.getProdutos();
				req.setAttribute("produtos", listProduto);
				req.setAttribute("categoria", listCategoria);
				req.setAttribute("menuActive", 3);
				path = "/WEB-INF/views/produto.jsp";

			} catch (Exception e) {
				req.setAttribute("javax.servlet.jsp.jspException", e);
				req.setAttribute("javax.servlet.error.status_code", 500);
				path = "/WEB-INF/views/erro.jsp";
			}

			break;

		case "removeProduto":

			try {

				Integer id = Integer.parseInt(req.getParameter("id"));
				ProdutoDao.removeProduto(id);
				json = new Gson().toJson(new Resposta(true));
			} catch (Exception e) {
				json = new Gson().toJson(new Resposta(e.getMessage(), false));
			} finally {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}

			break;

		case "getProduto":

			try {

				Integer id = Integer.parseInt(req.getParameter("idProduto"));
				Produto p = ProdutoDao.getProduto(id);

				if (p == null) {
					throw new Exception("Produto não existe");
				}

				json = new Gson().toJson(new Resposta(true, p));
			} catch (Exception e) {
				json = new Gson().toJson(new Resposta(e.getMessage(), false));
			} finally {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}

			break;

		case "updateProduto":

			try {
				ProdutoFacade.updateProduto(req);
				json = new Gson().toJson(new Resposta(true));
			} catch (Exception e) {
				json = new Gson().toJson(new Resposta(e.getMessage(), false));
			} finally {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}

			break;

		default:
			try {

				List<Categoria> listCategoria = CategoriaDao.getCategorias();
				List<Produto> listProduto = ProdutoDao.getProdutos();
				req.setAttribute("produtos", listProduto);
				req.setAttribute("categoria", listCategoria);
				req.setAttribute("menuActive", 3);
				path = "/WEB-INF/views/produto.jsp";

			} catch (Exception e) {
				req.setAttribute("javax.servlet.jsp.jspException", e);
				req.setAttribute("javax.servlet.error.status_code", 500);
				path = "/WEB-INF/views/erro.jsp";
			}
			break;

		}

		if (json == "") {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
			rd.forward(req, resp);
		}

	}
}
