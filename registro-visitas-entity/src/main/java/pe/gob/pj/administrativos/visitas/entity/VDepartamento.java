package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "V_DEPARTAMENTO",schema = BaseEntity.ESQUEMA_BD_SIGA)
public class VDepartamento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_DEPARTAMENTO")
	private String idDepartamento;
	
	@Column(name = "NOM_DEPARTAMENTO")
	private String nomDepartamento;

	public String getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(String idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNomDepartamento() {
		return nomDepartamento;
	}

	public void setNomDepartamento(String nomDepartamento) {
		this.nomDepartamento = nomDepartamento;
	}
	
	
	
	

}
