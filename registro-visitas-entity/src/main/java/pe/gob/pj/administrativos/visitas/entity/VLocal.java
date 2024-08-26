package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "V_LOCALES",schema = BaseEntity.ESQUEMA_BD_SIGA)
public class VLocal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "IDCODLOCAL")
	private String idCodLocal;
	@Column(name = "MLODESCRI")
	private String mLoDescri;
	@Column(name = "MLODIRECCION")
	private String mLoDireccion;
	@Column(name = "ID_UNI_EJE")
	private String idUniEje;
	@Column(name = "ID_CORTE")
	private String idCorte;
	@Column(name = "ID_DEPARTAMENTO")
	private String idDepartamento;
	@Column(name = "ID_PROVINCIA")
	private String idProvincia;
	@Column(name = "ID_DISTRITO")
	private String idDistrito;
	public String getIdCodLocal() {
		return idCodLocal;
	}
	public void setIdCodLocal(String idCodLocal) {
		this.idCodLocal = idCodLocal;
	}
	public String getmLoDescri() {
		return mLoDescri;
	}
	public void setmLoDescri(String mLoDescri) {
		this.mLoDescri = mLoDescri;
	}
	public String getmLoDireccion() {
		return mLoDireccion;
	}
	public void setmLoDireccion(String mLoDireccion) {
		this.mLoDireccion = mLoDireccion;
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
	
	
	
	
}
