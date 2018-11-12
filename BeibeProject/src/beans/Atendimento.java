package beans;

import java.io.Serializable;
import java.util.Date;

public class Atendimento implements Serializable {

	private Integer id;
	private Date dataHora;
	private String status;
	private String solucao;
	private String descricao;
	private TipoAtendimento tipo;
	private Produto produto;
	private Usuario usuario;
	
	
	public Atendimento() {
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDataHora() {
		return dataHora;
	}


	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getSolucao() {
		return solucao;
	}


	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public TipoAtendimento getTipo() {
		return tipo;
	}


	public void setTipo(TipoAtendimento tipo) {
		this.tipo = tipo;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
