package pe.gob.pj.administrativos.visitas.model.dto;

import java.io.Serializable;
import java.util.Date;

public class PuntoControlDto extends BaseDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long nIdPuntoControl;
	private String lFlagActivo;
	private Date fRegistro;
	private String nIdOficina;
	private String xDescripcionOficina;
	private String nIdLocal;
	private String xDescripcionLocal;
	private String nombre;
	private boolean lbEstadoRegistro;
	private String idUnidadEjecutora;
	private String idCorte;
	private String descripcionUnidadEjecutora;
	private String descripcionCorte;
		
		
	public PuntoControlDto() {
		super();
	}
	
	public String getPuntoControl() {
		String resultado=null;

			if(nombre==null && xDescripcionLocal==null && xDescripcionOficina==null) {
				resultado="";
			}else {
				resultado=nombre+" / ";
				resultado=(xDescripcionLocal!=null)?resultado+xDescripcionLocal+" / ":resultado+" / ";
				resultado=(xDescripcionOficina!=null)?resultado+xDescripcionOficina+" / ":resultado+" / ";
			}
		
		return resultado;
	}
	
	public Long getnIdPuntoControl() {
		return nIdPuntoControl;
	}
	public void setnIdPuntoControl(Long nIdPuntoControl) {
		this.nIdPuntoControl = nIdPuntoControl;
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
	public String getnIdOficina() {
		return nIdOficina;
	}
	public void setnIdOficina(String nIdOficina) {
		this.nIdOficina = nIdOficina;
	}
	public String getxDescripcionOficina() {
		return xDescripcionOficina;
	}
	public void setxDescripcionOficina(String xDescripcionOficina) {
		this.xDescripcionOficina = xDescripcionOficina;
	}
	public String getnIdLocal() {
		return nIdLocal;
	}
	public void setnIdLocal(String nIdLocal) {
		this.nIdLocal = nIdLocal;
	}
	public String getxDescripcionLocal() {
		return xDescripcionLocal;
	}
	public void setxDescripcionLocal(String xDescripcionLocal) {
		this.xDescripcionLocal = xDescripcionLocal;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isLbEstadoRegistro() {
		return lbEstadoRegistro;
	}

	public void setLbEstadoRegistro(boolean lbEstadoRegistro) {
		this.lbEstadoRegistro = lbEstadoRegistro;
	}

	public String getIdUnidadEjecutora() {
		return idUnidadEjecutora;
	}

	public void setIdUnidadEjecutora(String idUnidadEjecutora) {
		this.idUnidadEjecutora = idUnidadEjecutora;
	}

	public String getIdCorte() {
		return idCorte;
	}

	public void setIdCorte(String idCorte) {
		this.idCorte = idCorte;
	}

	public String getDescripcionUnidadEjecutora() {
		return descripcionUnidadEjecutora;
	}

	public void setDescripcionUnidadEjecutora(String descripcionUnidadEjecutora) {
		this.descripcionUnidadEjecutora = descripcionUnidadEjecutora;
	}

	public String getDescripcionCorte() {
		return descripcionCorte;
	}

	public void setDescripcionCorte(String descripcionCorte) {
		this.descripcionCorte = descripcionCorte;
	}


	
}
