package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Categoria;
import beans.Produto;
import exceptions.ErroAddCategoria;
import exceptions.ErroAddProduto;

public class ProdutoDao {
	
	public static List<Produto> getProdutos() throws Exception {

		PreparedStatement st = null;
		List<Produto> listProduto =  new ArrayList<Produto>();;

		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("SELECT p.nome as nomeProduto,c.nome as nomeCategoria,p.id,p.peso FROM tb_produto p INNER JOIN tb_categoria c on c.id = p.idCategoria ");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Produto p = new Produto();
				p.setNome(rs.getString("nomeProduto"));
//				p.setDescricao(rs.getString("descricao"));
				p.setId(rs.getInt("id"));
				p.setPeso(rs.getString("peso"));
				p.setCategoria(new Categoria(rs.getString("nomeCategoria")));
				listProduto.add(p);
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
	
	public static Produto addProduto(Produto p) throws ErroAddProduto {
		
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
			}else {
				return p;
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
	
	public static void removeProduto(Integer id) throws Exception {

		PreparedStatement st = null;

		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("DELETE FROM tb_produto where id = ?");
			st.setInt(1,id);
			st.executeUpdate();			
			
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
	
	public static void updateProduto(Produto p) throws Exception {
		PreparedStatement st = null;

		try (Connection con = ConnectionFactory.getConnection()) {			
			
		
			st = con.prepareStatement("UPDATE tb_produto SET nome = ?,descricao = ?,idCategoria = ?,peso = ? WHERE id = ?", PreparedStatement.RETURN_GENERATED_KEYS);					
			st.setString(1,p.getNome());
			st.setString(2,p.getDescricao());
			st.setInt(3,p.getCategoria().getId());
			st.setString(4, p.getPeso());
			st.setInt(5,p.getId());
			Integer rows = st.executeUpdate();
			
			if(rows <= 0) {
				throw new Exception("Nao foi atualizados");
			}
		
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

	public static Produto getProduto(Integer id) throws Exception {
		PreparedStatement st = null;
		Produto p = null;

		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("SELECT * FROM tb_produto where id = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				p = new Produto();
				p.setNome(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setId(rs.getInt("id"));
				p.setPeso(rs.getString("peso"));
				p.setCategoria(new Categoria(rs.getInt("idCategoria")));				
			}

			return p;
			
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
	
}
