package beans;

import java.io.Serializable;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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

	public Atendimento(Integer id, String solucao) {
		this.id = id;
		this.solucao = solucao;
	}

	public Atendimento(String status, String descricao, TipoAtendimento tipo, Produto produto, Usuario usuario) {
		this.status = status;
		this.descricao = descricao;
		this.tipo = tipo;
		this.produto = produto;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public Boolean getLate() {

		Date hoje = new Date();

		long diferencaMilis = Math.abs(hoje.getTime() - dataHora.getTime());
		long diff = TimeUnit.DAYS.convert(diferencaMilis, TimeUnit.MILLISECONDS);

		if (diff > 7) {
			return true;
		}			

		return false;
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
