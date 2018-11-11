package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Cidade;
import beans.Estado;
import beans.Usuario;
import exceptions.ErroGetCidades;
import exceptions.ErroGetUsuarioLogin;

public class CidadeDao {

	public static List<Cidade> getCidades() throws ErroGetCidades{
		
		PreparedStatement st = null;
		List<Cidade> listCidade = new ArrayList();
				
		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("SELECT * FROM tb_cidade");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Cidade cidade = new Cidade();
				cidade.setId(rs.getInt("id"));
				cidade.setNome(rs.getString("nome"));
				cidade.setEstado(new Estado(rs.getInt("idEstado")));
				listCidade.add(cidade);
			}

			return listCidade;
		} catch (Exception e) {
			throw new ErroGetCidades(e.getMessage());
		} finally {

			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
				}
			}
		}		
	}	
	
	public static List<Cidade> getCidadesEstado(Integer idEstado) throws ErroGetCidades {
		PreparedStatement st = null;
		List<Cidade> listCidade = null;
				
		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("SELECT * FROM tb_cidade where idEstado = ?");
			st.setInt(1,idEstado);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				listCidade = new ArrayList();
				Cidade cidade = new Cidade();
				cidade.setId(rs.getInt("id"));
				cidade.setNome(rs.getString("nome"));
				listCidade.add(cidade);
			}

			return listCidade;
		} catch (Exception e) {
			throw new ErroGetCidades(e.getMessage());
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
