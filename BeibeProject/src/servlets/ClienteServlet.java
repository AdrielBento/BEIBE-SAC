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
import beans.Cidade;
import beans.Estado;
import beans.Login;
import beans.Produto;
import beans.Usuario;
import classes.Resposta;
import dao.AtendimentoDao;
import dao.CidadeDao;
import dao.EstadoDao;
import dao.ProdutoDao;
import dao.UsuarioDao;
import facades.AtendimentoFacade;

@WebServlet("/Cliente")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {

		HttpSession session = ((HttpServletRequest) req).getSession(true);
		Login lb = (Login) session.getAttribute("login");
		
		if(lb == null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(req, resp);
		}else if(!lb.getTipo().equals("C")) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/permissao.jsp");
			rd.forward(req, resp);
		}
		

		String action = String.valueOf(req.getParameter("action"));
		String json = "";
		String path = "";
		Integer menu = null;

		switch (action) {

		case "formAtendimento":
			try {
				List<Produto> listProdutos = ProdutoDao.getProdutos();
				req.setAttribute("produtos", listProdutos);
				path = "/WEB-INF/views/addAtendimentoCliente.jsp";
			} catch (Exception e) {
				req.setAttribute("javax.servlet.jsp.jspException", e);
				req.setAttribute("javax.servlet.error.status_code", 500);
				path = "/WEB-INF/views/erro.jsp";
			}

			break;

		case "getAtendimentosCliente":

			try {

				List<Atendimento> listAtendimentos = AtendimentoDao.getAtendimentosCliente(lb.getId());
				List<Produto> listProdutos = ProdutoDao.getProdutos();
				req.setAttribute("atendimentos", listAtendimentos);
				req.setAttribute("produtos", listProdutos);			
				path = "/WEB-INF/views/atendimentosCliente.jsp";
			} catch (Exception e) {
				req.setAttribute("javax.servlet.jsp.jspException", e);
				req.setAttribute("javax.servlet.error.status_code", 500);
				path = "/WEB-INF/views/erro.jsp";
			}

			break;

		case "getCliente":

			try {

				Integer id = lb.getId();
				Usuario user = UsuarioDao.getUsuario(id);
				Integer idEstado = user.getEndereco().getCidade().getEstado().getId();
				List<Estado> listEstados = EstadoDao.getEstados();
				List<Cidade> listCidades = CidadeDao.getCidadesEstado(idEstado);
				req.setAttribute("cliente", user);
				req.setAttribute("estados", listEstados);
				req.setAttribute("cidades", listCidades);	
				path = "/WEB-INF/views/editCliente.jsp";				
			} catch (Exception e) {
				req.setAttribute("javax.servlet.jsp.jspException", e);
				req.setAttribute("javax.servlet.error.status_code", 500);
				path = "/WEB-INF/views/erro.jsp";
			}
			break;
			
		 default: 
			 
				try {

					List<Atendimento> listAtendimentos = AtendimentoDao.getAtendimentosCliente(lb.getId());
					List<Produto> listProdutos = ProdutoDao.getProdutos();
					req.setAttribute("atendimentos", listAtendimentos);
					req.setAttribute("produtos", listProdutos);			
					path = "/WEB-INF/views/atendimentosCliente.jsp";
				} catch (Exception e) {
					req.setAttribute("javax.servlet.jsp.jspException", e);
					req.setAttribute("javax.servlet.error.status_code", 500);
					path = "/WEB-INF/views/erro.jsp";
				}
			 
			 
			 break;
		}

		if (json.isEmpty()) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
			rd.forward(req, resp);
		}

	}

}
