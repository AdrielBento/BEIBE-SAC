package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Endereco;
import beans.Usuario;
import exceptions.ErroAddUsuario;
import exceptions.ErroGetUsuarioLogin;

public class UsuarioDao {

	private final static String addUsuarioQuery = "insert into tb_usuario(nome,cpf,email,telefone,senha,tipoPerfil,idEndereco)values(?,?,?,?,?,?,?)";
	private final static String addEnderecoQuery ="insert into tb_endereco(rua,numero,complemento,bairro,cep,idCidade) values(?,?,?,?,?,?) ";
	public static Usuario getUsuarioLogin(Usuario user) throws ErroGetUsuarioLogin {

		PreparedStatement st = null;
		Usuario u = null;

		try (Connection con = ConnectionFactory.getConnection()) {

			st = con.prepareStatement("SELECT id,nome FROM tb_usuario WHERE email = ? AND senha = ?");

			st.setString(1, user.getEmail());
			st.setString(2, user.getSenha());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
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
			
			
			Integer idEndereco = addEndereco(user.getEndereco());
			
			if (idEndereco == null) {
				throw new ErroAddUsuario("Endereco não adicionado");
			}
			
			st = con.prepareStatement(addUsuarioQuery, PreparedStatement.RETURN_GENERATED_KEYS);

			st.setString(1, user.getNome());
			st.setString(2, user.getCpf());
			st.setString(3, user.getEmail());
			st.setString(4, user.getTelefone());
			st.setString(5, user.getSenha());
			st.setString(6, user.getTipo());
			st.setInt(7, idEndereco);				
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
	
	public static Integer addEndereco(Endereco end) throws ErroAddUsuario {

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
			
			while(rs.next()) {
				idEndereco = rs.getInt(1);
			}
			
			return idEndereco;

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

}
