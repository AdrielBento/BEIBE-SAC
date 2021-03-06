package facades;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Cidade;
import beans.Endereco;
import beans.Login;
import beans.Usuario;
import classes.Utils;
import dao.UsuarioDao;
import exceptions.ErroAddUsuario;
import exceptions.ErroCriptografiaSenhaException;

public class UsuarioFacade {

	public static void addUsuario(ServletRequest req) throws ErroCriptografiaSenhaException, ErroAddUsuario {

		String nome = String.valueOf(req.getParameter("nome"));
		String cpf = String.valueOf(req.getParameter("cpf"));
		String email = String.valueOf(req.getParameter("email"));
		String senha = String.valueOf(req.getParameter("senha"));
		String telefone = String.valueOf(req.getParameter("telefone"));
		String cep = String.valueOf(req.getParameter("cep"));
		String bairro = String.valueOf(req.getParameter("bairro"));
		String rua = String.valueOf(req.getParameter("rua"));
		String complemento = String.valueOf(req.getParameter("complemento"));
		String numero = String.valueOf(req.getParameter("numero"));
		Integer idCidade = Integer.parseInt(req.getParameter("cidade"));
		String tipoPerfil = String.valueOf(req.getParameter("tipoPerfil"));

		cep = cep.replace("-", "").trim();
		cpf = cpf.replace(".", "").trim();
		cpf = cpf.replace("-", "").trim();
		
		telefone  = telefone.replace("-", "").trim();
		telefone  = telefone.replace("(", "").trim();
		telefone  = telefone.replace(")", "").trim();
		telefone  = telefone.trim();
		
		String encryptSenha = Utils.encrypt(senha);
		Endereco endereco = new Endereco(rua, numero, complemento, bairro, cep, new Cidade(idCidade));

		try {

			Usuario user = new Usuario(nome, cpf, email, telefone, encryptSenha, endereco, tipoPerfil);
			Integer idEndereco = UsuarioDao.addEndereco(user.getEndereco());
			user.getEndereco().setId(idEndereco);

			if (idEndereco == null)
				throw new Exception("Endereco n�o adicionado");

			UsuarioDao.addUsuario(user);

		} catch (Exception e) {
			throw new ErroAddUsuario(e.getMessage());
		}

	}

	public static void updateUsuario(ServletRequest req) throws Exception {

		String nome = String.valueOf(req.getParameter("nome"));
		String senha = String.valueOf(req.getParameter("senha"));
		String telefone = String.valueOf(req.getParameter("telefone"));
		String cep = String.valueOf(req.getParameter("cep"));
		String bairro = String.valueOf(req.getParameter("bairro"));
		String rua = String.valueOf(req.getParameter("rua"));
		String complemento = String.valueOf(req.getParameter("complemento"));
		String numero = String.valueOf(req.getParameter("numero"));
		Integer idCidade = Integer.parseInt(req.getParameter("cidade"));
		String tipoPerfil = String.valueOf(req.getParameter("tipoPerfil"));
		Integer idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
		Integer idEndereco = Integer.parseInt(req.getParameter("idEndereco"));
		String encryptSenha = senha;

		
		if(!senha.isEmpty() || !senha.equals("")) {
			encryptSenha = Utils.encrypt(senha);			
		}
		
		Endereco endereco = new Endereco(idEndereco,rua, numero, complemento, bairro, cep, new Cidade(idCidade));

		try {

			Usuario user = new Usuario(idUsuario,nome,telefone, encryptSenha, endereco, tipoPerfil);
			UsuarioDao.updateEndereco(user.getEndereco());
			UsuarioDao.updateUsuario(user);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

}
