package pe.gob.pj.administrativos.visitas.model.bean;

import java.io.Serializable;

public class ComboWebNuevoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String valor;
	private String texto;
	
	public ComboWebNuevoBean(String valor, String texto) {
		this.valor = valor;
		this.texto = texto;
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	

}
