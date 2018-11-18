package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Cidade;
import classes.Resposta;
import dao.CidadeDao;
import exceptions.ErroGetCidades;

@WebServlet("/Cidade")
public class CidadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {

		String path = "",json="";

		try {

			Integer idEstado = Integer.parseInt(req.getParameter("idEstado"));
			List<Cidade> listCidades = CidadeDao.getCidadesEstado(idEstado);

			if (listCidades == null) {
				throw new ErroGetCidades("Lista de cidade é igual a NULL");
			} else {
				json = new Gson().toJson(new Resposta(true, listCidades));
			}

		} catch (ErroGetCidades e) {
			json = new Gson().toJson(new Resposta(e.getMessage(),false));
		} finally {

			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(json);
		}
	}

}
