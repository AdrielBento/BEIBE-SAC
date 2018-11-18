package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Cidade;
import beans.Endereco;
import beans.Estado;
import beans.Usuario;
import exceptions.ErroAddAtendimento;
import exceptions.ErroAddUsuario;
import exceptions.ErroGetUsuarioLogin;

public class UsuarioDao {

	private final static String addUsuarioQuery = "insert into tb_usuario(nome,cpf,email,telefone,senha,tipoPerfil,idEndereco)values(?,?,?,?,?,?,?)";
	private final static String addEnderecoQuery = "insert into tb_endereco(rua,numero,complemento,bairro,cep,idCidade) values(?,?,?,?,?,?) ";
	private final static String updateEnderecoQuery = "UPDATE tb_endereco SET rua = ?,numero=?,complemento=?,bairro=?,cep=?,idCidade=? where id = ?";
	private final static String updateUsuarioQuery = "UPDATE tb_usuario SET nome=?,telefone=?,senha=?  where id = ?";
	private final static String updateUsuarioSemSenhaQuery = "UPDATE tb_usuario SET nome=?,telefone=? where id = ?";
	private final static String getUsuarioQuery = " SELECT u.id,u.nome,u.senha,u.cpf,u.email,u.telefone,u.tipoPerfil,u.idEndereco,e.rua,e.numero,e.complemento,e.bairro,e.cep,e.idCidade,c.idEstado FROM tb_usuario u "
			+ "INNER JOIN tb_endereco e on e.id = u.idEndereco "
			+ "INNER JOIN  tb_cidade c  on c.id = e.idCidade where u.id = ?";

	public static Usuario getUsuarioLogin(Usuario user) throws ErroGetUsuarioLogin {

		PreparedStatement st = null;
		Usuario u = null;

		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("SELECT id,nome,idEndereco,tipoPerfil FROM tb_usuario WHERE email = ? AND senha = ?");

			st.setString(1, user.getEmail());
			st.setString(2, user.getSenha());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				u.setTipo(rs.getString("tipoPerfil"));
				u.setEndereco(new Endereco(rs.getInt("idEndereco")));
			}

			return u;
		} catch (Exception e) {
			throw new ErroGetUsuarioLogin(e.getMessage());
		} finally {

			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public static void addUsuario(Usuario user) throws ErroAddUsuario {

		PreparedStatement st = null;

		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement(addUsuarioQuery, PreparedStatement.RETURN_GENERATED_KEYS);

			st.setString(1, user.getNome());
			st.setString(2, user.getCpf());
			st.setString(3, user.getEmail());
			st.setString(4, user.getTelefone());
			st.setString(5, user.getSenha());
			st.setString(6, user.getTipo());
			st.setInt(7, user.getEndereco().getId());
			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();

			if (!rs.next()) {
				throw new ErroAddUsuario("Usuário não foi inserido");
			}

		} catch (Exception e) {
			throw new ErroAddUsuario(e.getMessage());
		} finally {

			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
				}
			}
		}

	}

	public static Integer addEndereco(Endereco end) throws Exception {

		PreparedStatement st = null;
		Integer idEndereco = null;

		try (Connection con = ConnectionFactory.getConnection()) {

			con.setAutoCommit(false);

			st = con.prepareStatement(addEnderecoQuery, PreparedStatement.RETURN_GENERATED_KEYS);

			st.setString(1, end.getRua());
			st.setString(2, end.getNumero());
			st.setString(3, end.getComplemento());
			st.setString(4, end.getBairro());
			st.setString(5, end.getCep());
			st.setInt(6, end.getCidade().getId());
			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();

			con.commit();

			while (rs.next()) {
				idEndereco = rs.getInt(1);
			}

			return idEndereco;

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

	public static void updateUsuario(Usuario user) throws Exception {

		PreparedStatement st = null;

		try (Connection con = ConnectionFactory.getConnection()) {
			
			if(user.getSenha().isEmpty()) {
				st = con.prepareStatement(updateUsuarioSemSenhaQuery);		
			}else {
				st = con.prepareStatement(updateUsuarioQuery);
			}
			
			st.setString(1, user.getNome());
			st.setString(2, user.getTelefone());
			
			if(user.getSenha().isEmpty() || user.getSenha().equals("")) {				
				st.setInt(3, user.getId());				
			}else {
				st.setString(3, user.getSenha());
				st.setInt(4, user.getId());				
			}
	
			Integer rows = st.executeUpdate();

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

	public static void updateEndereco(Endereco end) throws Exception {

		PreparedStatement st = null;

		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement(updateEnderecoQuery);
			st.setString(1, end.getRua());
			st.setString(2, end.getNumero());
			st.setString(3, end.getComplemento());
			st.setString(4, end.getBairro());
			st.setString(5, end.getCep());
			st.setInt(6, end.getCidade().getId());
			st.setInt(7, end.getId());
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

	public static void removeUsuario(Integer idUsuario) throws Exception {

		PreparedStatement st = null;

		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("DELETE FROM tb_usuario where id = ?");
			st.setInt(1, idUsuario);

			Integer rows = st.executeUpdate();

			if (rows <= 0) {
				throw new Exception("Usuario não foi removido");
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

	public static Usuario getUsuario(Integer idUsuario) throws ErroGetUsuarioLogin {

		PreparedStatement st = null;
		Usuario u = null;

		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement(getUsuarioQuery);
			st.setInt(1, idUsuario);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				u = new Usuario();
				Endereco endereco = new Endereco();

				endereco.setId(rs.getInt("idEndereco"));
				endereco.setRua(rs.getString("rua"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCep(rs.getString("cep"));
				endereco.setCidade(new Cidade(rs.getInt("idCidade"), new Estado(rs.getInt("idEstado"))));
				
				u.setSenha(rs.getString("senha"));
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				u.setCpf(rs.getString("cpf"));
				u.setEmail(rs.getString("email"));
				u.setTelefone(rs.getString("telefone"));
				u.setTipo(rs.getString("tipoPerfil"));
				u.setEndereco(endereco);
			}

			return u;
		} catch (Exception e) {
			throw new ErroGetUsuarioLogin(e.getMessage());
		} finally {

			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public static List<Usuario> getFuncionarioEGerente(Integer idUsuario) throws Exception {
		
		PreparedStatement st = null;
		List<Usuario> listUsuarios = new ArrayList<Usuario>();

		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("SELECT * FROM tb_usuario where tipoPerfil != 'C' AND id != ?");
			st.setInt(1,idUsuario);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Usuario u = new Usuario();				
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				u.setCpf(rs.getString("cpf"));
				u.setEmail(rs.getString("email"));
				u.setTelefone(rs.getString("telefone"));
				u.setTipo(rs.getString("tipoPerfil"));
				listUsuarios.add(u);
			}

			return listUsuarios;
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
