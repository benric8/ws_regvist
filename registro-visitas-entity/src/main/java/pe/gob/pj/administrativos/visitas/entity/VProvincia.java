package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "V_PROVINCIA",schema = BaseEntity.ESQUEMA_BD_SIGA)
public class VProvincia  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_PROVINCIA")
	private String idProvincia;
	
	@Column(name = "ID_DEPARTAMENTO")
	private String idDepartamento;
	
	@Column(name = "NOM_PROVINCIA")
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
