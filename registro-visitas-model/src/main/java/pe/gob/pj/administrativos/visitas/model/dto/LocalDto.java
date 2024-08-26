package pe.gob.pj.administrativos.visitas.model.dto;

import java.io.Serializable;



public class LocalDto extends BaseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String idCodLocal;
	private String descripcion;
	private String direccion;
	private String idUniEje;
	private String idCorte;
	private String idDepartamento;
	private String idProvincia;
	private String idDistrito;
	public String getIdCodLocal() {
		return idCodLocal;
	}
	public void setIdCodLocal(String idCodLocal) {
		this.idCodLocal = idCodLocal;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getIdUniEje() {
		return idUniEje;
	}
	public void setIdUniEje(String idUniEje) {
		this.idUniEje = idUniEje;
	}
	public String getIdCorte() {
		return idCorte;
	}
	public void setIdCorte(String idCorte) {
		this.idCorte = idCorte;
	}
	public String getIdDepartamento() {
		return idDepartamento;
	}
	public void setIdDepartamento(String idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	public String getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getIdDistrito() {
		return idDistrito;
	}
	public void setIdDistrito(String idDistrito) {
		this.idDistrito = idDistrito;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
