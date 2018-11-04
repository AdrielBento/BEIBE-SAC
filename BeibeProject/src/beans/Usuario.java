package beans;

import java.io.Serializable;

public class Usuario implements Serializable {

	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private String senha;
	private Endereco endereco;
	private char tipo;
	
	
	
	public Usuario() {
		
	}

}
