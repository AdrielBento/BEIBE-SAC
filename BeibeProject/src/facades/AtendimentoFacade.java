package facades;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Atendimento;
import beans.Login;
import beans.Produto;
import beans.TipoAtendimento;
import beans.Usuario;
import dao.AtendimentoDao;
import exceptions.ErroAddAtendimento;

public class AtendimentoFacade {

	public static void addAtendimento(ServletRequest req) throws ErroAddAtendimento {
		
		HttpSession session = ((HttpServletRequest) req).getSession(true);
				
		String descricao = String.valueOf("descricao");
		TipoAtendimento tipo = new TipoAtendimento(Integer.parseInt(req.getParameter("tipo")));		
		Produto produto = new Produto(Integer.parseInt(req.getParameter("produto")));
		String status = "A";
		Login lb = (Login) session.getAttribute("login");
		Usuario usuario = new Usuario(lb.getId());
		
		AtendimentoDao.addAtendimento(new Atendimento(status,descricao,tipo,produto,usuario));
		
	}

}
