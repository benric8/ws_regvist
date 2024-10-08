package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "MAE_PERFIL", schema = BaseEntity.ESQUEMA_BD)
public class MaePerfil extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "N_ID_PERFIL")
	private Long nIdPerfil;
	@Column(name = "X_DESCRIPCION")
	private String xDescripcion;
	@Column(name = "L_FLAG_ACTIVO")
	private String lFlagActivo;
	@Column(name = "F_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fRegistro = new Date();
    @OneToMany(mappedBy = "maePerfil", fetch = FetchType.LAZY)
    private List<CfgPerfilOpcion> cfgPerfilOpcionList;
    @OneToMany(mappedBy = "maePerfil", fetch = FetchType.LAZY)
    private List<MaeUsuario> maeUsuarioList;
    // Alexis T�vara VPP v.1.0.7
    @Column(name = "L_CITAS")
    private String lCitas;
    
    public MaePerfil() {
		super();
	}
    public MaePerfil(Long nIdPerfil) {
		super();
		this.nIdPerfil = nIdPerfil;
	}
	public Long getnIdPerfil() {
		return nIdPerfil;
	}
	public void setnIdPerfil(Long nIdPerfil) {
		this.nIdPerfil = nIdPerfil;
	}
	public String getxDescripcion() {
		return xDescripcion;
	}
	public void setxDescripcion(String xDescripcion) {
		this.xDescripcion = xDescripcion;
	}
	public String getlFlagActivo() {
		return lFlagActivo;
	}
	public void setlFlagActivo(String lFlagActivo) {
		this.lFlagActivo = lFlagActivo;
	}
	public List<CfgPerfilOpcion> getCfgPerfilOpcionList() {
		return cfgPerfilOpcionList;
	}
	public void setCfgPerfilOpcionList(List<CfgPerfilOpcion> cfgPerfilOpcionList) {
		this.cfgPerfilOpcionList = cfgPerfilOpcionList;
	}
	public List<MaeUsuario> getMaeUsuarioList() {
		return maeUsuarioList;
	}
	public void setMaeUsuarioList(List<MaeUsuario> maeUsuarioList) {
		this.maeUsuarioList = maeUsuarioList;
	}
	public Date getfRegistro() {
		return fRegistro;
	}
	public void setfRegistro(Date fRegistro) {
		this.fRegistro = fRegistro;
	}
	// Alexis T�vara VPP v.1.0.7
	public String getlCitas() {
		return lCitas;
	}
	public void setlCitas(String lCitas) {
		this.lCitas = lCitas;
	}
	 
	
}
