package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Atendimento;
import beans.Login;
import dao.AtendimentoDao;
import exceptions.ErroGetAtendimentos;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		
		String action = "";
		Login lb = (Login) 	session.getAttribute("login");
		
		switch (action) {

		default:
			
			try {
				List<Atendimento> listAtendimentos = AtendimentoDao.getAtendimentos();
				
				
			} catch (ErroGetAtendimentos e) {
				
			}
		
			
			
			break;

		}

	}

}
