package pe.gob.pj.administrativos.visitas.model.bean;

import java.io.Serializable;

public class Persona  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String nroDocumentoIdentidad; 
	private String codigoVerificacion; 
	private String tipoFichaRegistral; 
	private String apellidoPaterno; 
	private String apellidoMaterno; 
	private String nombres; 
	private String datos; 
	private String flagImagen;
	public String getNroDocumentoIdentidad() {
		return nroDocumentoIdentidad;
	}
	public void setNroDocumentoIdentidad(String nroDocumentoIdentidad) {
		this.nroDocumentoIdentidad = nroDocumentoIdentidad;
	}
	public String getCodigoVerificacion() {
		return codigoVerificacion;
	}
	public void setCodigoVerificacion(String codigoVerificacion) {
		this.codigoVerificacion = codigoVerificacion;
	}
	public String getTipoFichaRegistral() {
		return tipoFichaRegistral;
	}
	public void setTipoFichaRegistral(String tipoFichaRegistral) {
		this.tipoFichaRegistral = tipoFichaRegistral;
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
	public String getDatos() {
		return datos;
	}
	public void setDatos(String datos) {
		this.datos = datos;
	}
	public String getFlagImagen() {
		return flagImagen;
	}
	public void setFlagImagen(String flagImagen) {
		this.flagImagen = flagImagen;
	}
	@Override
	public String toString() {
		return nroDocumentoIdentidad + "\t" + codigoVerificacion + "\t" + tipoFichaRegistral + "\t" + apellidoPaterno
				+ "\t" + apellidoMaterno + "\t" + nombres + "\t" + datos + "\t" + flagImagen + "\n";
	} 
	
	
	
}
