package classes;

public class Resposta {
	
	private String mensagem;
	private Boolean status;
	private String error;
	
	public Resposta(String msg,Boolean status,String error) {
		this.error = error;
		this.mensagem= msg;
		this.status = status;
	}
	
	public Resposta(String msg,Boolean status) {
		this.mensagem= msg;
		this.status = status;
	}
	
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	

}
