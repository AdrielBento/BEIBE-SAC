package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Atendimento;
import beans.Cidade;
import beans.Estado;
import exceptions.ErroGetAtendimentos;
import exceptions.ErroGetCidades;

public class AtendimentoDao {
	
	public static List<Atendimento> getAtendimentos() throws ErroGetAtendimentos {
		
		PreparedStatement st = null;
		List<Atendimento> listAtendimentos = null;
				
		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("SELECT * FROM tb_atendimento");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Atendimento a  = new Atendimento();
				listAtendimentos = new ArrayList();
				a.setId();
				a.setDataHora();
				a.setProduto();
				a.setStatus();
				a.setTipo();				
			}

			return listAtendimentos;
		} catch (Exception e) {
			throw new ErroGetAtendimentos(e.getMessage());
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
