package facades;

import javax.servlet.ServletRequest;

import beans.Categoria;
import dao.CategoriaDao;
import exceptions.ErroAddCategoria;

public class CategoriaFacade {

	public static Categoria addCategoria(Categoria categoria) throws ErroAddCategoria {		
		return CategoriaDao.addCategoria(categoria);
	}

	public static void updateCategoria(Categoria categoria) throws Exception {
		CategoriaDao.updateCategoria(categoria);		
	}

	public static void removeCategoria(Categoria categoria) throws Exception {
		CategoriaDao.removeCategoria(categoria);				
	}
}
