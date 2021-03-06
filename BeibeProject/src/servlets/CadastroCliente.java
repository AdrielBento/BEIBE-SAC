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

import beans.Cidade;
import beans.Estado;
import classes.Resposta;
import dao.CidadeDao;
import dao.EstadoDao;
import exceptions.ErroAddUsuario;
import exceptions.ErroCriptografiaSenhaException;
import exceptions.ErroGetCidades;
import exceptions.ErroGetEstados;
import facades.UsuarioFacade;

@WebServlet("/CadastroCliente")
public class CadastroCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");

		String action = String.valueOf(req.getParameter("action"));
		String path = "";

		try {

			List<Estado> listEstado = EstadoDao.getEstados();
			List<Cidade> listCidade = CidadeDao.getCidades();

			req.setAttribute("cidades", listCidade);
			req.setAttribute("estados", listEstado);
			path = "/WEB-INF/views/cadastroCliente.jsp";

		} catch (ErroGetEstados | ErroGetCidades e) {
			req.setAttribute("javax.servlet.jsp.jspException", e);
			req.setAttribute("javax.servlet.error.status_code", 500);
			path = "/WEB-INF/views/erro.jsp";
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
		rd.forward(req, resp);

	}

}
