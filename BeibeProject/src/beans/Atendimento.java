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

}
