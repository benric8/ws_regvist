package pe.gob.pj.administrativos.visitas.model.dto;

import java.io.Serializable;


public class CorteDto extends BaseDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String anioProceso;
	private String idUniEje;
	private String idCorte;
	private String nombreCorte;
	
	public String getAnioProceso() {
		return anioProceso;
	}
	public void setAnioProceso(String anioProceso) {
		this.anioProceso = anioProceso;
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
	public String getNombreCorte() {
		return nombreCorte;
	}
	public void setNombreCorte(String nombreCorte) {
		this.nombreCorte = nombreCorte;
	}
	
	
	
}
