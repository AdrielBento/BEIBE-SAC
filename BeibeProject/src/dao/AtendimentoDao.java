package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.Atendimento;
import beans.Cidade;
import beans.Estado;
import beans.Produto;
import beans.TipoAtendimento;
import exceptions.ErroAddAtendimento;
import exceptions.ErroGetAtendimentos;
import exceptions.ErroGetCidades;

public class AtendimentoDao {
	
	private final static String addAtendimentoQuery  = "INSERT INTO tb_atendimento(status,descricao,idTipoAtendimento,idProduto,idUsuario,dataHora)values(?,?,?,?,?,now())";
	private final static String getAtendimentosQuery = "SELECT a.id,a.dataHora,p.nome as produto ,t.nome as tipo,a.status FROM tb_atendimento a INNER JOIN tb_produto p on p.id = a.idProduto "
			+ " INNER JOIN tb_tipoatendimento t on t.id = a.idTipoAtendimento";
	private final static String getAtendimentoQuery = "select a.dataHora,a.status,a.descricao,a.solucao,t.id,p.id FROM tb_atendimento a"
			+ " INNER JOIN tb_tipoatendimento t on t.id = a.idTipoAtendimento INNER JOIN tb_produto p on p.id = a.idProduto where a.id = ?";
	
	public static List<Atendimento> getAtendimentos() throws ErroGetAtendimentos {
		
		PreparedStatement st = null;
		List<Atendimento> listAtendimentos = new ArrayList();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement(getAtendimentosQuery);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Atendimento a  = new Atendimento();
				a.setId(rs.getInt("id"));
				String dataFormatada = dateFormat.format(rs.getTimestamp("dataHora"));  
	            Date data = dateFormat.parse(dataFormatada); 
				a.setDataHora(data);
				a.setProduto(new Produto(rs.getString("produto")));
				a.setStatus(rs.getString("status"));
				a.setTipo(new TipoAtendimento(rs.getString("tipo")));	
				listAtendimentos.add(a);
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

	public static void addAtendimento(Atendimento atendimento) throws ErroAddAtendimento {
		
		PreparedStatement st = null;
						
		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement(addAtendimentoQuery);
			
			st.setString(1,atendimento.getStatus());
			st.setString(2, atendimento.getDescricao());
			st.setInt(3, atendimento.getTipo().getId());
			st.setInt(4, atendimento.getProduto().getId());
			st.setInt(5, atendimento.getUsuario().getId());
			
			Integer rows = st.executeUpdate();
					
			if(rows <= 0) {
				throw new ErroAddAtendimento();
			}			
			
		} catch (Exception e) {
			throw new ErroAddAtendimento(e.getMessage());
		} finally {

			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
				}
			}
		}	
		
	}

	public static void remove(Integer idAtendimento) throws Exception {
		
		PreparedStatement st = null;
		
		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("delete from tb_atendimento where id = ?");			
			st.setInt(1, idAtendimento);
						
			Integer rows = st.executeUpdate();
					
			if(rows <= 0) {
				throw new Exception("O Atendimento não foi excluido");
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

	public static Atendimento getAtendimento(Integer id) throws Exception {
			
		PreparedStatement st = null;
		Atendimento atendimento  = new Atendimento();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		try (Connection con = ConnectionFactory.getConnection()) {
			
			st = con.prepareStatement(getAtendimentoQuery);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
	
//				a.setId(rs.getInt("id"));
				String dataFormatada = dateFormat.format(rs.getTimestamp("dataHora"));  
	            Date data = dateFormat.parse(dataFormatada); 
	            atendimento.setDataHora(data);
	            atendimento.setProduto(new Produto(rs.getInt("produto")));
	            atendimento.setStatus(rs.getString("status"));
	            atendimento.setTipo(new TipoAtendimento(rs.getInt("tipo")));	
				 
			}

			return atendimento;
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
