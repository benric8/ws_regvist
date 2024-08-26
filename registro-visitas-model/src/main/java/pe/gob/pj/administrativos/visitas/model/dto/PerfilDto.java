package pe.gob.pj.administrativos.visitas.model.dto;

import java.io.Serializable;
import java.util.Date;

public class PerfilDto extends BaseDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long nIdPerfil;
	private String xDescripcion;
	private String lFlagActivo;
	private Date fRegistro = new Date();
	
		public PerfilDto() {
		super();
	}
	public Long getnIdPerfil() {
		return nIdPerfil;
	}
	public void setnIdPerfil(Long nIdPerfil) {
		this.nIdPerfil = nIdPerfil;
	}
	public String getxDescripcion() {
		return xDescripcion;
	}
	public void setxDescripcion(String xDescripcion) {
		this.xDescripcion = xDescripcion;
	}
	public String getlFlagActivo() {
		return lFlagActivo;
	}
	public void setlFlagActivo(String lFlagActivo) {
		this.lFlagActivo = lFlagActivo;
	}
	public Date getfRegistro() {
		return fRegistro;
	}
	public void setfRegistro(Date fRegistro) {
		this.fRegistro = fRegistro;
	}
	
	
	
	 
	
}
