package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Cidade;
import beans.Estado;
import exceptions.ErroGetCidades;
import exceptions.ErroGetEstados;

public class EstadoDao {

	public static List<Estado> getEstados() throws ErroGetEstados{
		
		PreparedStatement st = null;
		List<Estado> listEstado = new ArrayList();
				
		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("SELECT * FROM tb_estado");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Estado estado = new Estado();
				estado.setId(rs.getInt("id"));
				estado.setNome(rs.getString("nome"));
				listEstado.add(estado);
			}

			return listEstado;
		} catch (Exception e) {
			throw new ErroGetEstados(e.getMessage());
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
