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

import beans.Atendimento;
import beans.Produto;
import classes.Resposta;
import dao.AtendimentoDao;
import dao.ProdutoDao;
import facades.AtendimentoFacade;

/**
 * Servlet implementation class Atendimento
 */
@WebServlet("/Atendimento")
public class AtendimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {

		String action = String.valueOf(req.getParameter("action"));
		String json = "";
		String path = "";
		
		switch (action) {
		case "addAtendimento":

			try {

				AtendimentoFacade.addAtendimento(req);
				json = new Gson().toJson(new Resposta(true));
			} catch (Exception e) {
				json = new Gson().toJson(new Resposta(e.getMessage(), false));
			} finally {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}

			break;
			
		case "formAtendimento":
			try {
				List<Produto> listProdutos = ProdutoDao.getProdutos();				
				req.setAttribute("produtos", listProdutos);
				path="/WEB-INF/views/addAtendimentoCliente.jsp";
			} catch (Exception e) {
				req.setAttribute("javax.servlet.jsp.jspException", e);
				req.setAttribute("javax.servlet.error.status_code", 500);
				path = "/WEB-INF/views/erro.jsp";
			}
			
			break;

		case "getAtendimentos":
			
			try {

				List<Atendimento> listAtendimentos = AtendimentoDao.getAtendimentos();
				List<Produto> listProdutos = ProdutoDao.getProdutos();				
				req.setAttribute("atendimentos", listAtendimentos);
				req.setAttribute("produtos", listProdutos);
				path="/WEB-INF/views/atendimentosCliente.jsp";
			} catch (Exception e) {
				req.setAttribute("javax.servlet.jsp.jspException", e);
//	             request.setAttribute("msg", "Erro ao listar os cliente");
//	             request.setAttribute("page", "http://localhost:8080/MeuTADS/ClienteServlet");
				req.setAttribute("javax.servlet.error.status_code", 500);
				path = "/WEB-INF/views/erro.jsp";
			}

			break;

		case "viewAtendimento":
			
			try {
				
				Integer id  = Integer.parseInt(req.getParameter("id"));
				List<Produto> listProdutos = ProdutoDao.getProdutos();		
				Atendimento atendimento = AtendimentoDao.getAtendimento(id);
				req.setAttribute("produtos", listProdutos);

			} catch (Exception e) {
				req.setAttribute("javax.servlet.jsp.jspException", e);
//	             request.setAttribute("msg", "Erro ao listar os cliente");
//	             request.setAttribute("page", "http://localhost:8080/MeuTADS/ClienteServlet");
				req.setAttribute("javax.servlet.error.status_code", 500);
				path = "/WEB-INF/views/erro.jsp";
			}
			break;

		case "removeAtendimento":
			
			try {
				
				Integer id  = Integer.parseInt(req.getParameter("id"));
				AtendimentoDao.remove(id);
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
