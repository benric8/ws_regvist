package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "V_CORTES",schema = BaseEntity.ESQUEMA_BD_SIGA)
public class VCorte implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(name = "ANIO_PROCESO")
	private String anioProceso;
	
	@Column(name = "ID_UNI_EJE")
	private String idUniEje;
	
	@Id
	@Column(name = "ID_CORTE")
	private String idCorte;
	
	@Column(name = "NOM_CORTE")
	private String nomCorte;

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

	public String getNomCorte() {
		return nomCorte;
	}

	public void setNomCorte(String nomCorte) {
		this.nomCorte = nomCorte;
	}
	
	
	
	

}
