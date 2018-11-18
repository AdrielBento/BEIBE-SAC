package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.Atendimento;
import beans.Login;
import classes.Resposta;
import dao.AtendimentoDao;
import dao.UsuarioDao;
import exceptions.ErroAddUsuario;
import exceptions.ErroCriptografiaSenhaException;
import exceptions.ErroGetAtendimentos;
import facades.UsuarioFacade;

@WebServlet("/Usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(true);

		String action = "", json = "", path = "";
		Login lb = (Login) session.getAttribute("login");
		action = req.getParameter("action");

		switch (action) {

		case "addUsuario":

			try {
				UsuarioFacade.addUsuario(req);
				json = new Gson().toJson(new Resposta(true));
			} catch (ErroCriptografiaSenhaException e) {
				json = new Gson().toJson(new Resposta(e.getMessage(), false));
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			} catch (ErroAddUsuario e) {

				json = new Gson().toJson(new Resposta(e.getMessage(), false));
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}

			break;
		case "removeUsuario":
			try {

				Integer idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
				UsuarioDao.removeUsuario(idUsuario);
				json = new Gson().toJson(new Resposta(true));
			} catch (Exception e) {
				json = new Gson().toJson(new Resposta(e.getMessage(), false));
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}

			break;
		case "updateUsuario":
			try {
				UsuarioFacade.updateUsuario(req);
				json = new Gson().toJson(new Resposta(true));
			} catch (Exception e) {
				json = new Gson().toJson(new Resposta(e.getMessage(), false));
			} finally {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}
			break;

		case "getUsuario":
			try {

				Integer idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
				UsuarioDao.getUsuario(idUsuario);
				json = new Gson().toJson(new Resposta(true));
			} catch (Exception e) {
				json = new Gson().toJson(new Resposta(e.getMessage(), false));
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().write(json);
			}

			break;
		}

//		if (json.isEmpty()) {
//			RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
//			rd.forward(req, resp);
//		}

	}

}
