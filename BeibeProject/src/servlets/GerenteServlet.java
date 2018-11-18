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
import beans.Cidade;
import beans.Estado;
import beans.Login;
import beans.Usuario;
import classes.DashboardAtendimento;
import dao.AtendimentoDao;
import dao.CidadeDao;
import dao.EstadoDao;
import dao.UsuarioDao;
import facades.UsuarioFacade;

@WebServlet("/Gerente")
public class GerenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {

		HttpSession session = ((HttpServletRequest) req).getSession(true);
		Login lb = (Login) session.getAttribute("login");

		if (lb == null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(req, resp);
		} else if (!lb.getTipo().equals("G")) {
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
				req.setAttribute("stackTrace",writer.toString());
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
				req.setAttribute("stackTrace",writer.toString());
				path = "/WEB-INF/views/erro.jsp";
			}
			break;

		case "dashboard":
			try {

				Double atendimentos = AtendimentoDao.totalAtendimentos();
				Double atendimentosAbertos = AtendimentoDao.totalAtendimentoAbertos();
				Double porcentagemAbertos = (double) Math.round((atendimentosAbertos * 100) / atendimentos);
				List<DashboardAtendimento> atendimentosTipos = AtendimentoDao.atendimentosPorTipo();
				req.setAttribute("totalAtendimentos", atendimentos);
				req.setAttribute("atendimentosAbertos", atendimentosAbertos);
				req.setAttribute("porcentagemAbertos", porcentagemAbertos);
				req.setAttribute("atendimentos", atendimentosTipos);
				path = "/WEB-INF/views/dashboard.jsp";
			} catch (Exception e) {
				StringWriter writer = new StringWriter();				
				e.printStackTrace(new PrintWriter(writer));//			
				req.setAttribute("ExceptionMessage", e.getMessage());
				req.setAttribute("stackTrace",writer.toString());
				path = "/WEB-INF/views/erro.jsp";
			}
			break;

		case "listFuncionarioGerente":
			try {
				Integer idUsuario = lb.getId();
				List<Usuario> listUsuarios = UsuarioDao.getFuncionarioEGerente(idUsuario);
				req.setAttribute("usuarios", listUsuarios);
				path = "/WEB-INF/views/listFuncionarioGerente.jsp";
			} catch (Exception e) {
				StringWriter writer = new StringWriter();				
				e.printStackTrace(new PrintWriter(writer));//			
				req.setAttribute("ExceptionMessage", e.getMessage());
				req.setAttribute("stackTrace",writer.toString());
				path = "/WEB-INF/views/erro.jsp";
			}
			break;

		case "editUsuario":
			try {

				Integer idUsuario = Integer.parseInt(req.getParameter("id"));
				Usuario user = UsuarioDao.getUsuario(idUsuario);
				Integer idEstado = user.getEndereco().getCidade().getEstado().getId();
				List<Estado> listEstados = EstadoDao.getEstados();
				List<Cidade> listCidades = CidadeDao.getCidadesEstado(idEstado);
				req.setAttribute("estados", listEstados);
				req.setAttribute("cidades", listCidades);
				req.setAttribute("usuario", user);
				path = "/WEB-INF/views/editFuncionarioGerente.jsp";
			} catch (Exception e) {
				StringWriter writer = new StringWriter();				
				e.printStackTrace(new PrintWriter(writer));//			
				req.setAttribute("ExceptionMessage", e.getMessage());
				req.setAttribute("stackTrace",writer.toString());
				path = "/WEB-INF/views/erro.jsp";
			}
			break;
			
		case "addFuncionarioGerente":
			try {
				List<Estado> listEstados = EstadoDao.getEstados();
				req.setAttribute("estados", listEstados);
				path="/WEB-INF/views/addFuncionarioGerente.jsp";
			} catch (Exception e) {
				StringWriter writer = new StringWriter();				
				e.printStackTrace(new PrintWriter(writer));//			
				req.setAttribute("ExceptionMessage", e.getMessage());
				req.setAttribute("stackTrace",writer.toString());
				path = "/WEB-INF/views/erro.jsp";
			}
			break;

		default:
			try {

				Double atendimentos = AtendimentoDao.totalAtendimentos();
				Double atendimentosAbertos = AtendimentoDao.totalAtendimentoAbertos();
				Double porcentagemAbertos = (double) Math.round((atendimentosAbertos * 100) / atendimentos);
				List<DashboardAtendimento> atendimentosTipos = AtendimentoDao.atendimentosPorTipo();
				
				req.setAttribute("totalAtendimentos", atendimentos);
				req.setAttribute("atendimentosAbertos", atendimentosAbertos);
				req.setAttribute("porcentagemAbertos", porcentagemAbertos);
				req.setAttribute("atendimentos", atendimentosTipos);
				
				path = "/WEB-INF/views/dashboard.jsp";
			} catch (Exception e) {
				StringWriter writer = new StringWriter();				
				e.printStackTrace(new PrintWriter(writer));//			
				req.setAttribute("ExceptionMessage", e.getMessage());
				req.setAttribute("stackTrace",writer.toString());
				path = "/WEB-INF/views/erro.jsp";
			}
			break;
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
		rd.forward(req, resp);
	}

}
