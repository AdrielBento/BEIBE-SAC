package beans;

import java.io.Serializable;

public class TipoAtendimento implements Serializable {

	private Integer id;
	private String nome;
	
	
	public TipoAtendimento() {
		
	}


	public TipoAtendimento(Integer id) {
		this.id = id;
	}


	public TipoAtendimento(String nome) {
		this.nome = nome;
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
