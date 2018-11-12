package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Atendimento;
import beans.Categoria;
import exceptions.ErroAddCategoria;
import exceptions.ErroAddUsuario;
import exceptions.ErroGetAtendimentos;

public class CategoriaDao {

	public static List<Categoria> getCategoria() throws Exception {

		PreparedStatement st = null;
		List<Categoria> listCategoria = null;

		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("SELECT * FROM tb_categoria");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Categoria ca = new Categoria();
				listCategoria = new ArrayList();
				ca.setId(rs.getInt("id"));
				ca.setNome(rs.getString("nome"));
			}

			return listCategoria;
			
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
	
	public static void addCategoria(Categoria c) throws ErroAddCategoria {
		PreparedStatement st = null;

		try (Connection con = ConnectionFactory.getConnection()) {			
			
		
			st = con.prepareStatement("INSERT INTO tb_categoria(nome) values(?)", PreparedStatement.RETURN_GENERATED_KEYS);					
			st.setString(1,c.getNome());
			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();

			if (!rs.next()) {
				throw new ErroAddCategoria("A categoria não foi inserido");
			}
			
		} catch (Exception e) {
			throw new ErroAddCategoria(e.getMessage());
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
