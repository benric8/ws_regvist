package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "CFG_PERFIL_OPCION", schema = BaseEntity.ESQUEMA_BD)
public class CfgPerfilOpcion extends BaseEntity implements Serializable  {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "N_ID_PERFIL_OPCION")
	private Long nIdPerfilOpcion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "N_ID_PERFIL")
	private MaePerfil maePerfil;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "N_ID_OPCION")
	private MaeOpcion maeOpcion;
	
	@Column(name = "L_FLAG_ACTIVO")
	private String lFlagActivo;

	
	@Column(name = "F_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fRegistro = new Date();
	
	public Long getnIdPerfilOpcion() {
		return nIdPerfilOpcion;
	}

	public void setnIdPerfilOpcion(Long nIdPerfilOpcion) {
		this.nIdPerfilOpcion = nIdPerfilOpcion;
	}

	public MaePerfil getMaePerfil() {
		return maePerfil;
	}

	public void setMaePerfil(MaePerfil maePerfil) {
		this.maePerfil = maePerfil;
	}

	public MaeOpcion getMaeOpcion() {
		return maeOpcion;
	}

	public void setMaeOpcion(MaeOpcion maeOpcion) {
		this.maeOpcion = maeOpcion;
	}

	public Date getfRegistro() {
		return fRegistro;
	}

	public void setfRegistro(Date fRegistro) {
		this.fRegistro = fRegistro;
	}

	public String getlFlagActivo() {
		return lFlagActivo;
	}

	public void setlFlagActivo(String lFlagActivo) {
		this.lFlagActivo = lFlagActivo;
	}


	
	
}
