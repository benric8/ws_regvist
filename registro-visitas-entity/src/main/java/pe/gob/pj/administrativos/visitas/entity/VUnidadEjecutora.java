package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "V_UNIDAD_EJECUTORA",schema = BaseEntity.ESQUEMA_BD_SIGA)
public class VUnidadEjecutora  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_UNI_EJE")
	private String idUniEje;

	@Column(name = "ANIO_PROCESO")
	private String anioProceso;

	@Column(name = "NOM_UNI_EJE")
	private String nomUniEje;


	
	public String getNomUniEje() {
		return nomUniEje;
	}

	public void setNomUniEje(String nomUniEje) {
		this.nomUniEje = nomUniEje;
	}

	public String getIdUniEje() {
		return idUniEje;
	}

	public void setIdUniEje(String idUniEje) {
		this.idUniEje = idUniEje;
	}

	public String getAnioProceso() {
		return anioProceso;
	}

	public void setAnioProceso(String anioProceso) {
		this.anioProceso = anioProceso;
	}
	
	

}
