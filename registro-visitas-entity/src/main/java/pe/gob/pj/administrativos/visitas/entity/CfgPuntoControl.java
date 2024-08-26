package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CFG_PUNTO_CONTROL", schema = BaseEntity.ESQUEMA_BD)
public class CfgPuntoControl extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="CFG_PUNTO_CONTROL_GENERATOR", sequenceName="USRREGVIS.USEQ_CFG_PUNTO_CONTROL", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CFG_PUNTO_CONTROL_GENERATOR")
	@Column(name = "N_ID_PUNTO_CONTROL")
	private Long nIdPuntoControl;


	@Column(name = "L_FLAG_ACTIVO")
	private String lFlagActivo;
	
	@Column(name = "F_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fRegistro = new Date();
	
	@Column(name = "N_ID_OFICINA")
	private String nIdOficina;
	@Column(name = "X_DESCRIPCION_OFICINA")
	private String xDescripcionOficina;
	@Column(name = "N_ID_LOCAL")
	private String nIdLocal;
	@Column(name = "X_DESCRIPCION_LOCAL")
	private String xDescripcionLocal;
	
	@Column(name = "X_NOMBRE")
	private String xNombre;
	
	@Column(name = "N_ID_UNI_EJE")
	private String nIdUniEje;
	
	//@Column(name = "N_ID_CORTE")
	//private String nIdCorte;
	//TODO CCRV Propiedades
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "N_ID_CORTE", referencedColumnName="ID_CORTE")
	private VCorte vCorte;
	
	
	@OneToMany(mappedBy = "cfgPuntoControl", fetch = FetchType.LAZY)
	private List<CfgPuntoControl> cfgPuntoControl;


	public Long getnIdPuntoControl() {
		return nIdPuntoControl;
	}

	public void setnIdPuntoControl(Long nIdPuntoControl) {
		this.nIdPuntoControl = nIdPuntoControl;
	}



	public String getlFlagActivo() {
		return lFlagActivo;
	}

	public void setlFlagActivo(String lFlagActivo) {
		this.lFlagActivo = lFlagActivo;
	}

	public Date getfRegistro() {
		return fRegistro;
	}

	public void setfRegistro(Date fRegistro) {
		this.fRegistro = fRegistro;
	}

	public String getnIdOficina() {
		return nIdOficina;
	}

	public void setnIdOficina(String nIdOficina) {
		this.nIdOficina = nIdOficina;
	}

	public String getxDescripcionOficina() {
		return xDescripcionOficina;
	}

	public void setxDescripcionOficina(String xDescripcionOficina) {
		this.xDescripcionOficina = xDescripcionOficina;
	}

	public String getnIdLocal() {
		return nIdLocal;
	}

	public void setnIdLocal(String nIdLocal) {
		this.nIdLocal = nIdLocal;
	}

	public String getxDescripcionLocal() {
		return xDescripcionLocal;
	}

	public void setxDescripcionLocal(String xDescripcionLocal) {
		this.xDescripcionLocal = xDescripcionLocal;
	}

	public String getxNombre() {
		return xNombre;
	}

	public void setxNombre(String xNombre) {
		this.xNombre = xNombre;
	}

	public String getnIdUniEje() {
		return nIdUniEje;
	}

	public void setnIdUniEje(String nIdUniEje) {
		this.nIdUniEje = nIdUniEje;
	}

	public List<CfgPuntoControl> getCfgPuntoControl() {
		return cfgPuntoControl;
	}

	public void setCfgPuntoControl(List<CfgPuntoControl> cfgPuntoControl) {
		this.cfgPuntoControl = cfgPuntoControl;
	}

	public VCorte getvCorte() {
		return vCorte;
	}

	public void setvCorte(VCorte vCorte) {
		this.vCorte = vCorte;
	}

	






	
}
