package facades;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import beans.Cidade;
import beans.Endereco;
import beans.Usuario;
import classes.Utils;
import dao.UsuarioDao;
import exceptions.ErroAddUsuario;
import exceptions.ErroCriptografiaSenhaException;

public class ClienteFacade {

	public static void addCliente(ServletRequest req) throws ErroCriptografiaSenhaException, ErroAddUsuario {

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
		

        cep = cep.replace("-", "").trim();
        cpf = cpf.replace(".", "").trim();
        cpf = cpf.replace("-", "").trim();

		String encryptSenha = Utils.encrypt(senha);
		Endereco endereco = new Endereco(rua, numero, complemento, bairro, cep, new Cidade(idCidade));
		UsuarioDao.addUsuario(new Usuario(nome, cpf, email, telefone, encryptSenha, endereco, "C"));

	}
	
	
	
	
	
}
