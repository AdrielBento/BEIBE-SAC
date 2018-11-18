package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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

import beans.Atendimento;
import beans.Login;
import beans.Produto;
import dao.AtendimentoDao;
import dao.ProdutoDao;

@WebServlet("/Funcionario")
public class FuncionarioServlet extends HttpServlet {
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

		String path = "", action = String.valueOf(req.getParameter("action"));

		switch (action) {
		
		case "getAtendimentosAbertos":
			try {
				List<Atendimento> listAtendimentos = AtendimentoDao.getAtendimentosAbertos();
				req.setAttribute("atendimentos", listAtendimentos);
				path = "/WEB-INF/views/atendimentosAbertos.jsp";
			} catch (Exception e) {
				StringWriter writer = new StringWriter();
				e.printStackTrace(new PrintWriter(writer));//
				req.setAttribute("ExceptionMessage", e.getMessage());
				req.setAttribute("stackTrace", writer.toString());
				path = "/WEB-INF/views/erro.jsp";
			}
			break;
		case "getAtendimentos":
			try {
				List<Atendimento> listAtendimentos = AtendimentoDao.getAtendimentos();
				req.setAttribute("atendimentos", listAtendimentos);
				path = "/WEB-INF/views/atendimentos.jsp";
			} catch (Exception e) {
				StringWriter writer = new StringWriter();
				e.printStackTrace(new PrintWriter(writer));//
				req.setAttribute("ExceptionMessage", e.getMessage());
				req.setAttribute("stackTrace", writer.toString());
				path = "/WEB-INF/views/erro.jsp";
			}
			break;
		case "viewAtendimento":
			try {

				Integer id = Integer.parseInt(req.getParameter("id"));
				List<Produto> listProdutos = ProdutoDao.getProdutos();
				Atendimento atendimento = AtendimentoDao.getAtendimento(id);
				req.setAttribute("produtos", listProdutos);
				req.setAttribute("atendimento", atendimento);
				path = "/WEB-INF/views/resolveAtendimento.jsp";
			} catch (Exception e) {
				StringWriter writer = new StringWriter();
				e.printStackTrace(new PrintWriter(writer));//
				req.setAttribute("ExceptionMessage", e.getMessage());
				req.setAttribute("stackTrace", writer.toString());
				path = "/WEB-INF/views/erro.jsp";
			}

			break;

		case "resolveAtendimento":

			try {

				Integer id = Integer.parseInt(req.getParameter("id"));
				String solucao = String.valueOf(req.getParameter("solucao"));
				AtendimentoDao.resolveAtendimento(new Atendimento(id, solucao));
				path = "/Funcionario";
			} catch (Exception e) {
				StringWriter writer = new StringWriter();
				e.printStackTrace(new PrintWriter(writer));//
				req.setAttribute("ExceptionMessage", e.getMessage());
				req.setAttribute("stackTrace", writer.toString());
				path = "/WEB-INF/views/erro.jsp";
			}

			break;

		default:
			try {
				List<Atendimento> listAtendimentos = AtendimentoDao.getAtendimentosAbertos();
				req.setAttribute("atendimentos", listAtendimentos);
				path = "/WEB-INF/views/atendimentosAbertos.jsp";
			} catch (Exception e) {
				StringWriter writer = new StringWriter();
				e.printStackTrace(new PrintWriter(writer));//
				req.setAttribute("ExceptionMessage", e.getMessage());
				req.setAttribute("stackTrace", writer.toString());
				path = "/WEB-INF/views/erro.jsp";
			}
			break;
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
		rd.forward(req, resp);

	}

}
