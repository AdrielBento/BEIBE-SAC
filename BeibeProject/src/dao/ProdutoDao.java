package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Produto;

import exceptions.ErroAddProduto;

public class ProdutoDao {
	
	public static List<Produto> getProdutos() throws Exception {

		PreparedStatement st = null;
		List<Produto> listProduto = null;

		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("SELECT * FROM tb_produto");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Produto p = new Produto();
				listProduto = new ArrayList<Produto>();
				p.setNome(rs.getString("nome"));
			}

			return listProduto;
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {

			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	public static void addProduto(Produto p) throws ErroAddProduto {
		
		PreparedStatement st = null;

		try (Connection con = ConnectionFactory.getConnection()) {			
			
		
			st = con.prepareStatement("INSERT INTO tb_produto(nome,descricao,peso,idCategoria) values(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);					
			st.setString(1,p.getNome());
			st.setString(2,p.getDescricao());
			st.setString(3,p.getPeso());
			st.setInt(4,p.getCategoria().getId());
			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();

			if (!rs.next()) {
				throw new ErroAddProduto("A produto não foi inserido");
			}
			
		} catch (Exception e) {
			throw new ErroAddProduto(e.getMessage());
		} finally {

			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
				}
			}
		}
		
	}
}
