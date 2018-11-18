package beans;

import java.io.Serializable;

public class Usuario implements Serializable {
	
	private Integer id;
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private String senha;
	private Endereco endereco;
	private String tipo;

	public Usuario() {};
			
	public Usuario(String nome, String cpf, String email, String telefone, String senha, Endereco endereco, String tipo) {
		
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.endereco = endereco;
		this.tipo = tipo;
	}
	
	public Usuario(Integer id,String nome, String cpf, String email, String telefone, String senha, Endereco endereco, String tipo) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.endereco = endereco;
		this.tipo = tipo;
	}

	public Usuario(Integer id,String nome, String telefone, String senha, Endereco endereco, String tipo) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.senha = senha;
		this.endereco = endereco;
		this.tipo = tipo;
	}

	public Usuario(Integer id) {
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


}
