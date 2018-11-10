package facades;

import javax.servlet.http.HttpServletRequest;

import beans.Usuario;
import classes.Utils;
import dao.UsuarioDao;
import exceptions.ErroCriptografiaSenhaException;
import exceptions.ErroGetUsuarioLogin;

public class LoginFacade {
	
	
	public static Usuario loginIn(HttpServletRequest req) throws ErroCriptografiaSenhaException, ErroGetUsuarioLogin {
		
		try {
			
			Usuario user = new Usuario();
			user.setEmail(String.valueOf(req.getParameter("email")));
			String criptaSenha = Utils.encrypt(String.valueOf(req.getParameter("senha")));	
			String criptSenha = String.valueOf(req.getParameter("senha"));
			user.setSenha(criptSenha);
			
			return UsuarioDao.getUsuarioLogin(user);
		
		} catch (ErroCriptografiaSenhaException e) {
			throw new ErroCriptografiaSenhaException(e.getMessage());
		}catch(ErroGetUsuarioLogin e) {			
			throw new ErroGetUsuarioLogin(e.getMessage());
		}	
	}

}
