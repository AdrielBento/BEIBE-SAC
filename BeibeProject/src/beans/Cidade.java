package beans;

import java.io.Serializable;

public class Cidade implements Serializable {
	
	private Integer id;
	private String nome;
	private Estado estado;
	
	public Cidade(){};
	
	
	public Cidade(Integer id) {
		this.id = id;
	}

	public Cidade(Integer id,Estado estado) {
		this.id = id;
		this.estado = estado;
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
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
