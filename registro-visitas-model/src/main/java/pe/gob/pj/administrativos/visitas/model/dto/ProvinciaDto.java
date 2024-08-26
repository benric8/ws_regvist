package pe.gob.pj.administrativos.visitas.model.dto;

import java.io.Serializable;



public class ProvinciaDto extends BaseDto implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idDepartamento;
	private String idProvincia;
	private String nomProvincia;
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
	public String getNomProvincia() {
		return nomProvincia;
	}
	public void setNomProvincia(String nomProvincia) {
		this.nomProvincia = nomProvincia;
	}
	
	
}
