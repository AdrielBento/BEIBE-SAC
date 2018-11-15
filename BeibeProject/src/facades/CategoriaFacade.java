package facades;

import javax.servlet.ServletRequest;

import beans.Categoria;
import dao.CategoriaDao;
import exceptions.ErroAddCategoria;

public class CategoriaFacade {

		public static Categoria addCategoria(Categoria categoria) throws ErroAddCategoria {
		// verify if category is a valid record
		//
		// if(categoria.getName().isEmpty()){
		//	 throw new Exception("Category's name can't be blank. Are you kiding me?!")
		// }
		
		return CategoriaDao.addCategoria(categoria);
	}

	public static void updateCategoria(ServletRequest req) throws Exception {
		String nome = String.valueOf(req.getParameter("nomeCategoria"));
		Integer id = Integer.parseInt(req.getParameter("id"));
		CategoriaDao.updateCategoria(new Categoria(id,nome));		
	}
	
}
