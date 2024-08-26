package pe.gob.pj.administrativos.visitas.model.dto;

import java.io.Serializable;

public class TipoDocumentoDto extends BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idTipoDocumento;
	private String descripcion;
	private String abreviatura;
	private String estado;
	private String equivalencia;
	
	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEquivalencia() {
		return equivalencia;
	}
	public void setEquivalencia(String equivalencia) {
		this.equivalencia = equivalencia;
	}

	
	

}
