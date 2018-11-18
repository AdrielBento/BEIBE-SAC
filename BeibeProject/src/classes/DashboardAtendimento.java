package classes;

public class DashboardAtendimento {
	private String tipo;
	private Integer qtdAtendimentos;
	private Integer qtdAtendimentosAbertos;
	
	public DashboardAtendimento() {
		
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getQtdAtendimentos() {
		return qtdAtendimentos;
	}
	public void setQtdAtendimentos(Integer qtdAtendimentos) {
		this.qtdAtendimentos = qtdAtendimentos;
	}
	public Integer getQtdAtendimentosAbertos() {
		return qtdAtendimentosAbertos;
	}
	public void setQtdAtendimentosAbertos(Integer qtdAtendimentosAbertos) {
		this.qtdAtendimentosAbertos = qtdAtendimentosAbertos;
	}	
	
}
