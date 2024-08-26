package pe.gob.pj.administrativos.visitas.model.dto;

import java.io.Serializable;
import java.util.Date;




public class EntidadDto  extends BaseDto implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idEntidad;
	private String ruc;
	private String razonSocial;
	private String estado;
	private Date fechaRegistro ;
	private boolean lbEstadoRegistro;
	
	
	public Long getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public boolean isLbEstadoRegistro() {
		return lbEstadoRegistro;
	}
	public void setLbEstadoRegistro(boolean lbEstadoRegistro) {
		this.lbEstadoRegistro = lbEstadoRegistro;
	}
	
	
	

	

}
