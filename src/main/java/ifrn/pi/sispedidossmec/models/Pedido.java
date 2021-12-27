package ifrn.pi.sispedidossmec.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String escola;
	private String responsavel;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEscola() {
		return escola;
	}
	public void setEscola(String escola) {
		this.escola = escola;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
}
