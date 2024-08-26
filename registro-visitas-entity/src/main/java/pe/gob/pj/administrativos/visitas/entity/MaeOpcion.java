package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "MAE_OPCION", schema = BaseEntity.ESQUEMA_BD)
public class MaeOpcion extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "N_ID_OPCION")
	private Long nIdOpcion;
	
	@Column(name = "N_ID_OPCION_PADRE")
	private Long nIdOpcionPadre;
	
	@Column(name = "N_ORDEN")
	private Long nOrden;
	
	@Column(name = "X_DESCRIPCION")
	private String xDescripcion;
	
	@Column(name = "X_URL")
	private String xUrl;
	@Column(name = "L_FLAG_ACTIVO")
	private String lFlagActivo;
	
	@Column(name = "F_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fRegistro = new Date();
	
	
    @OneToMany(mappedBy = "maeOpcion", fetch = FetchType.LAZY)
    private List<CfgPerfilOpcion> cfgPerfilOpcionList;


	public Long getnIdOpcion() {
		return nIdOpcion;
	}


	public void setnIdOpcion(Long nIdOpcion) {
		this.nIdOpcion = nIdOpcion;
	}


	public String getxDescripcion() {
		return xDescripcion;
	}


	public void setxDescripcion(String xDescripcion) {
		this.xDescripcion = xDescripcion;
	}


	public String getxUrl() {
		return xUrl;
	}


	public void setxUrl(String xUrl) {
		this.xUrl = xUrl;
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


	public List<CfgPerfilOpcion> getCfgPerfilOpcionList() {
		return cfgPerfilOpcionList;
	}


	public void setCfgPerfilOpcionList(List<CfgPerfilOpcion> cfgPerfilOpcionList) {
		this.cfgPerfilOpcionList = cfgPerfilOpcionList;
	}


	public Long getnIdOpcionPadre() {
		return nIdOpcionPadre;
	}


	public void setnIdOpcionPadre(Long nIdOpcionPadre) {
		this.nIdOpcionPadre = nIdOpcionPadre;
	}


	public Long getnOrden() {
		return nOrden;
	}


	public void setnOrden(Long nOrden) {
		this.nOrden = nOrden;
	}
	
	
	
	
	
}
