package pe.gob.pj.administrativos.visitas.model.bean;

import java.io.Serializable;

public class ConsultadoReniec implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoConsulta;
	private String nroDocumentoIdentidad;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombres;
	
	public String getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	public String getNroDocumentoIdentidad() {
		return nroDocumentoIdentidad;
	}
	public void setNroDocumentoIdentidad(String nroDocumentoIdentidad) {
		this.nroDocumentoIdentidad = nroDocumentoIdentidad;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	
		
}