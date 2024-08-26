package pe.gob.pj.administrativos.visitas.model.bean;

import java.io.Serializable;

public class ResponseConsultaReniecWsData implements Serializable{
	
	private static final long serialVersionUID = 1L;

	protected String codigo;
	protected String descripcion;
	protected String codigoOperacion;
	protected PersonaReniecBean data;
	
	@Override
	public String toString() {
		return "[codigo=" + codigo + ", descripcion=" + descripcion + ", codigoOperacion="
				+ codigoOperacion + ", data=" + data + "]";
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public PersonaReniecBean getData() {
		return data;
	}

	public void setData(PersonaReniecBean data) {
		this.data = data;
	}
	
	
	
}
