package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "V_OFICINAS",schema = BaseEntity.ESQUEMA_BD_SIGA)
public class VOficina implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID_OFICINA")
	private String idOficina;
	
	@Column(name = "NOM_OFICINA")
	private String nomOficina;
	
	@Column(name = "OFICINA_DIRECCION")
	private String oficinaDireccion;
	
	@Column(name = "OFICINA_DISTRITO")
	private String oficinaDistrito;
	
	@Column(name = "DIRECCION")
	private String direccion;
	
	@Column(name = "UBIGEO")
	private String ubigeo;
	
	@Column(name = "NOM_DISTRITO")
	private String nomDistrito;
	
	@Column(name = "NOM_PROVINCIA")
	private String nomProvincia;
	
	@Column(name = "NOM_DEPARTAMENTO")
	private String nomDepartamento;
	
	@Column(name = "ANIO_PROCESO")
	private String anioProceso;
	
	@Column(name = "ID_UNI_EJE")
	private String idUniEje;
	
	@Column(name = "NOM_UNI_EJE")
	private String nomUniEje;
	
	@Column(name = "ID_CORTE")
	private String idCorte;
	
	@Column(name = "NOM_CORTE")
	private String nomCorte;
	
	@Column(name = "ID_TIP_DEP")
	private String idTipDep;
	
	@Column(name = "NOM_TIP_DEP")
	private String nomTipDep;
	
	@Column(name = "ID_ESP_DEP")
	private String idEspDep;
	
	@Column(name = "NOM_ESP_DEP")
	private String nomEspDep;
	
	@Column(name = "ID_SUB_ESP_DEP")
	private String idSubEspDep;
	
	@Column(name = "NOM_SUB_ESP_DEP")
	private String nomSubEspDep;
	
	@Column(name = "ID_LOCAL")
	private String idLocal;
	
	@Column(name = "IDOFIESTDEP")
	private String idOfiEstDep;

	public String getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(String idOficina) {
		this.idOficina = idOficina;
	}


	public String getNomOficina() {
		return nomOficina;
	}

	public void setNomOficina(String nomOficina) {
		this.nomOficina = nomOficina;
	}

	public String getOficinaDireccion() {
		return oficinaDireccion;
	}

	public void setOficinaDireccion(String oficinaDireccion) {
		this.oficinaDireccion = oficinaDireccion;
	}

	public String getOficinaDistrito() {
		return oficinaDistrito;
	}

	public void setOficinaDistrito(String oficinaDistrito) {
		this.oficinaDistrito = oficinaDistrito;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}

	public String getNomDistrito() {
		return nomDistrito;
	}

	public void setNomDistrito(String nomDistrito) {
		this.nomDistrito = nomDistrito;
	}

	public String getNomProvincia() {
		return nomProvincia;
	}

	public void setNomProvincia(String nomProvincia) {
		this.nomProvincia = nomProvincia;
	}

	public String getNomDepartamento() {
		return nomDepartamento;
	}

	public void setNomDepartamento(String nomDepartamento) {
		this.nomDepartamento = nomDepartamento;
	}

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

	public String getNomUniEje() {
		return nomUniEje;
	}

	public void setNomUniEje(String nomUniEje) {
		this.nomUniEje = nomUniEje;
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

	public String getIdTipDep() {
		return idTipDep;
	}

	public void setIdTipDep(String idTipDep) {
		this.idTipDep = idTipDep;
	}

	public String getNomTipDep() {
		return nomTipDep;
	}

	public void setNomTipDep(String nomTipDep) {
		this.nomTipDep = nomTipDep;
	}

	public String getIdEspDep() {
		return idEspDep;
	}

	public void setIdEspDep(String idEspDep) {
		this.idEspDep = idEspDep;
	}

	public String getNomEspDep() {
		return nomEspDep;
	}

	public void setNomEspDep(String nomEspDep) {
		this.nomEspDep = nomEspDep;
	}

	public String getIdSubEspDep() {
		return idSubEspDep;
	}

	public void setIdSubEspDep(String idSubEspDep) {
		this.idSubEspDep = idSubEspDep;
	}

	public String getNomSubEspDep() {
		return nomSubEspDep;
	}

	public void setNomSubEspDep(String nomSubEspDep) {
		this.nomSubEspDep = nomSubEspDep;
	}

	public String getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(String idLocal) {
		this.idLocal = idLocal;
	}

	public String getIdOfiEstDep() {
		return idOfiEstDep;
	}

	public void setIdOfiEstDep(String idOfiEstDep) {
		this.idOfiEstDep = idOfiEstDep;
	}
	
	

}
