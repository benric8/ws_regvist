package pe.gob.pj.administrativos.visitas.model.bean;

import java.io.Serializable;

public class ResponseConsultaReniecWsPersona implements Serializable{
	
	private static final long serialVersionUID = 1L;

	protected String codigo;
	protected String descripcion;
	protected String codigoOperacion;
	protected Registro data;
	
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

	public Registro getData() {
		return data;
	}

	public void setData(Registro data) {
		this.data = data;
	}
	
	
	
}