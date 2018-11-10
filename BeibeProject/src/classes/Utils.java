package classes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import exceptions.ErroCriptografiaSenhaException;

public class Utils {

	public Utils() {};

    public static String encrypt(String senha) throws ErroCriptografiaSenhaException {        
        
        try {
        	
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new ErroCriptografiaSenhaException();
        }
     
    }

	

}
