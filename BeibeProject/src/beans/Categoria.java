package beans;

import java.io.Serializable;

public class Categoria implements Serializable {

	private Integer id;
	private String nome;
	

	public Categoria() {
	
	}
	
	
	public Categoria(String nome) {
		this.nome = nome;
	}


	public Categoria(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}


	public Categoria(Integer id) {
		this.id = id;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


}
