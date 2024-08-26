package pe.gob.pj.administrativos.visitas.model.dto;

import java.io.Serializable;

public class UnidadEjecutoraDto extends BaseDto implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String anioProceso;
	private String idUniEje;
	private String nombreUniEje;

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
	public String getNombreUniEje() {
		return nombreUniEje;
	}
	public void setNombreUniEje(String nombreUniEje) {
		this.nombreUniEje = nombreUniEje;
	}
	
	

	
}
