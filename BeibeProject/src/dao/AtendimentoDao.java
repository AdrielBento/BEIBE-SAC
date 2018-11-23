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
import classes.DashboardAtendimento;
import exceptions.ErroAddAtendimento;
import exceptions.ErroGetAtendimentos;
import exceptions.ErroGetCidades;

public class AtendimentoDao {
	
	private final static String addAtendimentoQuery  = "INSERT INTO tb_atendimento(status,descricao,idTipoAtendimento,idProduto,idUsuario,dataHora)values(?,?,?,?,?,now())";
	private final static String getAtendimentosAbertosQuery =  "SELECT a.id,a.dataHora,p.nome as produto ,t.nome as tipo,a.status FROM tb_atendimento a INNER JOIN tb_produto p on p.id = a.idProduto "
			+ " INNER JOIN tb_tipoatendimento t on t.id = a.idTipoAtendimento where a.status = 'A' ORDER BY a.dataHora ASC ";
	private final static String getAtendimentosQuery = "SELECT a.id,a.dataHora,p.nome as produto ,t.nome as tipo,a.status FROM tb_atendimento a INNER JOIN tb_produto p on p.id = a.idProduto "
			+ " INNER JOIN tb_tipoatendimento t on t.id = a.idTipoAtendimento  ORDER BY a.dataHora DESC";
	private final static String getAtendimentosClienteQuery = "SELECT a.id,a.dataHora,p.nome as produto ,t.nome as tipo,a.status FROM tb_atendimento a INNER JOIN tb_produto p on p.id = a.idProduto "
			+ " INNER JOIN tb_tipoatendimento t on t.id = a.idTipoAtendimento where a.idUsuario = ? ORDER BY a.dataHora DESC";
	private final static String getAtendimentoQuery = "select a.dataHora,a.status,a.id,a.descricao,a.solucao,t.id as tipo,p.id as produto FROM tb_atendimento a"
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
	
	public static List<Atendimento> getAtendimentosCliente(Integer idCliente) throws ErroGetAtendimentos {
			
			PreparedStatement st = null;
			List<Atendimento> listAtendimentos = new ArrayList();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			try (Connection con = ConnectionFactory.getConnection()) {
	
				st = con.prepareStatement(getAtendimentosClienteQuery);
				st.setInt(1,idCliente);
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
	
				atendimento.setId(rs.getInt("id"));
				String dataFormatada = dateFormat.format(rs.getTimestamp("dataHora"));  
	            Date data = dateFormat.parse(dataFormatada); 
	            atendimento.setDataHora(data);
	            atendimento.setProduto(new Produto(rs.getInt("produto")));
	            atendimento.setStatus(rs.getString("status"));
	            atendimento.setDescricao("descricao");
	            atendimento.setSolucao(rs.getString("solucao"));
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

	public static List<Atendimento> getAtendimentosAbertos() throws Exception{
		
		PreparedStatement st = null;
		List<Atendimento> listAtendimentos = new ArrayList();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement(getAtendimentosAbertosQuery);
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

	public static void resolveAtendimento(Atendimento atendimento) throws Exception {
		
		PreparedStatement st = null;
					
		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("UPDATE tb_atendimento SET status = 'F',solucao= ? WHERE id = ?");
			st.setString(1,atendimento.getSolucao());
			st.setInt(2,atendimento.getId());
			Integer rows = st.executeUpdate();
			
//			if(rows <= 0) {
//				throw new Exception("Atendimento não foi resolvido");
//			}			
//		
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

	public static Double totalAtendimentoAbertos() throws Exception 
	{
		PreparedStatement st = null;
		Double total = 0.0;
		
		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("SELECT count(id) as total FROM tb_atendimento where status = 'A'");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				total = (double) rs.getInt("total");
			}
			
			return total;
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

	public static Double totalAtendimentos() throws Exception {
		PreparedStatement st = null;
		Double total = 0.0;
		
		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("SELECT count(id) as total FROM tb_atendimento");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				total = (double) rs.getInt("total");
			}

			return total;
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
	
	public static List<DashboardAtendimento> atendimentosPorTipo() throws Exception {
		
		PreparedStatement st = null;
		List<DashboardAtendimento> atendimentos = new ArrayList();
		DashboardAtendimento db =  null;
				
		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("select t.nome, (select count(a1.id) from tb_atendimento  a1 where a1.idTipoAtendimento = a.idTipoAtendimento) as atendimentos, \r\n" + 
					"(select count(a2.id) from tb_atendimento a2 where a2.idTipoAtendimento = a.idTipoAtendimento AND a2.status = 'A') as aberto \r\n" + 
					"from tb_atendimento a \r\n" + 
					"INNER JOIN tb_tipoatendimento t on t.id = a.idTipoAtendimento\r\n" + 
					"group by a.idTipoAtendimento;\r\n");
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
			    db = new DashboardAtendimento();
				db.setTipo(rs.getString("nome"));
				db.setQtdAtendimentos(rs.getInt("atendimentos"));
				db.setQtdAtendimentosAbertos(rs.getInt("aberto"));
				atendimentos.add(db);
			}

			return atendimentos;
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
