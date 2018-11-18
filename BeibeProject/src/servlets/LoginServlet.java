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

import beans.Endereco;
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
		String path = "";

		try {

			Usuario user = LoginFacade.loginIn(req);

			if (user == null) {
				throw new Exception("Usuario não encontrado");
			}

			Login lb = new Login();
			lb.setId(user.getId());
			lb.setEndereco(new Endereco(user.getEndereco().getId()));
			lb.setNome(user.getNome());
			lb.setTipo(user.getTipo());
			session.setAttribute("login", lb);

			switch (user.getTipo()) {
			case "C":
				path = "Cliente";
				break;
			case "F":
				path = "Funcionario";
				break;
			case "G":
				path = "Gerente";
				break;
			}

			resp.sendRedirect("http://localhost:8080/BeibeProject/" + path);

		} catch (Exception e) {
			req.setAttribute("message", "Senha ou Email Invalidos.");
			req.setAttribute("type","error");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(req, resp);
		}
	}

}
