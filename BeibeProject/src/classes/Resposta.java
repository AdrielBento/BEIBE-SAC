package classes;

public class Resposta {
	
	private String mensagem;
	private Boolean status;
	private String error;
	private Object data;
	
	public Resposta() {	
	}
	
	public Resposta(String msg,Boolean status,String error) {
		this.error = error;
		this.mensagem= msg;
		this.status = status;
	}
	
	public Resposta(String msg,Boolean status) {
		this.mensagem= msg;
		this.status = status;
	}
	
	public Resposta(Boolean status) {			
		this.status = status;
	}
	
	public Resposta(Boolean status,Object data) {			
		this.status = status;
		this.data = data;
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
