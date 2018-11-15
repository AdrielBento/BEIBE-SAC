package beans;

import java.io.Serializable;

public class Produto implements Serializable {
	
	private Integer id;
	private String nome;
	private String descricao;
	private String peso;
	private Categoria categoria;
	
	public Produto() {};
	
	public Produto(String nome, String descricao, String peso, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.peso = peso;
		this.categoria = categoria;
	}

	public Produto(Integer id, String nome, String descricao, String peso, Categoria categoria) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.peso = peso;
		this.categoria = categoria;
	}

	public Produto(Integer id) {
		this.id = id;
	}

	public Produto(String nome) {
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	
}
