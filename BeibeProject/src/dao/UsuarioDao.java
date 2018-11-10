package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Usuario;
import exceptions.ErroGetUsuarioLogin;

public class UsuarioDao {

	public static Usuario getUsuarioLogin(Usuario user) throws ErroGetUsuarioLogin {	  
		  		  
	    PreparedStatement st = null;
	    Usuario u = null;
           
	    try (Connection con = ConnectionFactory.getConnection()){
	        
	        st = con.prepareStatement("SELECT id,nome FROM tb_usuario WHERE email = ? AND senha = ?");
	        
	        st.setString(1,user.getEmail());
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

}
