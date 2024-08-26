package pe.gob.pj.administrativos.visitas.model.dto;

import java.util.Date;




public class TipoMotivoDto extends BaseDto{


	private Long idTipoMotivo;
	private String descripcion;
	private Date fechaRegistro;
	private String estado;
	private boolean lbEstadoRegistro;
	
	public Long getIdTipoMotivo() {
		return idTipoMotivo;
	}
	public void setIdTipoMotivo(Long idTipoMotivo) {
		this.idTipoMotivo = idTipoMotivo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public boolean isLbEstadoRegistro() {
		return lbEstadoRegistro;
	}
	public void setLbEstadoRegistro(boolean lbEstadoRegistro) {
		this.lbEstadoRegistro = lbEstadoRegistro;
	}
	
	
	

	



}
