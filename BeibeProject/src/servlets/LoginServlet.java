package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import com.google.gson.*;
import com.google.gson.Gson;

import beans.Login;
import beans.Usuario;
import classes.Resposta;
import classes.Utils;
import exceptions.ErroCriptografiaSenhaException;
import facades.LoginFacade;

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession(true);
		String json = "";

		try {

			Usuario user = LoginFacade.loginIn(req);

			if (user == null) {
				throw new Exception("Usuario não encontrado");
			}
			

			Login lb = new Login();
			lb.setId(user.getId());
			lb.setNome(user.getNome());

			session.setAttribute("login", lb);
			
//			json = new Gson().toJson(new Resposta(true));

		} catch (Exception e) {

			json = new Gson().toJson(new Resposta(e.getMessage(), false));

		} finally {

			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
		}
	}

}
