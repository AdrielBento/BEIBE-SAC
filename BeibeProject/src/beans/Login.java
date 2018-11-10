package beans;

import java.io.Serializable;

public class Login implements Serializable {
	
	private int id;
	private String nome;
	
	public Login(){
	}
	
	public String getNome() {
	    return nome;
	}
	
	public void setNome(String nome) {
	    this.nome = nome;
	}
	
	public int getId() {
	    return id;
	}
	
	public void setId(int idUser) {
	    this.id = idUser;
	} 

}
