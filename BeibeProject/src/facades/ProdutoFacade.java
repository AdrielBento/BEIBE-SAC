package facades;

import javax.servlet.ServletRequest;

import beans.Categoria;
import beans.Produto;
import dao.ProdutoDao;
import exceptions.ErroAddProduto;


public class ProdutoFacade {
	
	
	public static Produto addProduto(ServletRequest req) throws ErroAddProduto {
		
		String nome = String.valueOf(req.getParameter("nome"));
		String descricao = String.valueOf(req.getParameter("descricao"));
		String peso = String.valueOf(req.getParameter("peso"));
		Integer idCategoria = Integer.parseInt(req.getParameter("categoria"));
		
		Produto p = ProdutoDao.addProduto(new Produto(nome,descricao,peso,new Categoria(idCategoria)));	
		return p;
	}

	public static void updateProduto(ServletRequest req) throws Exception {
		String nome = String.valueOf(req.getParameter("nome"));
		String descricao = String.valueOf(req.getParameter("descricao"));
		String peso = String.valueOf(req.getParameter("peso"));		
		Integer idCategoria = Integer.parseInt(req.getParameter("categoria"));
		Integer id = Integer.parseInt(req.getParameter("id"));
		ProdutoDao.updateProduto(new Produto(id,nome,descricao,peso,new Categoria(idCategoria)));		
	}
	
	
	
}
