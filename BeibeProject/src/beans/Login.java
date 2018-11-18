package beans;

import java.io.Serializable;

public class Login implements Serializable {
	
	private int id;
	private String nome;
	private Endereco endereco;
	private String tipo;
	
	public Login(){
	}
	
	public String getNome() {
	    return nome;
	}
	
	public void setNome(String nome) {
	    this.nome = nome;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public int getId() {
	    return id;
	}
	
	public void setId(int idUser) {
	    this.id = idUser;
	}

	public void setEndereco(Endereco endereco ) {
		this.endereco = endereco;		
	}



}
