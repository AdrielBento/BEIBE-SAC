package facades;

import javax.servlet.ServletRequest;

import beans.Categoria;
import dao.CategoriaDao;
import exceptions.ErroAddCategoria;

public class CategoriaFacade {

	public static Categoria addCategoria(ServletRequest req) throws ErroAddCategoria {
		
		String nome = String.valueOf(req.getParameter("nomeCategoria"));
		Categoria c = CategoriaDao.addCategoria(new Categoria(nome));		
		return c;
	}

	public static void updateCategoria(ServletRequest req) throws Exception {
		String nome = String.valueOf(req.getParameter("nomeCategoria"));
		Integer id = Integer.parseInt(req.getParameter("id"));
		CategoriaDao.updateCategoria(new Categoria(id,nome));		
	}
	
}
