package beans;

import java.io.Serializable;

public class Estado implements Serializable{

	private Integer id;
	private String uf;
	private String nome;
	
	public Estado() {};
	
	
	public Estado(int id) {
		this.id = id;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}	
	
}
