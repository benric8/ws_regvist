package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "MAE_TIPO_DOCUMENTO", schema = BaseEntity.ESQUEMA_BD)
public class MaeTipoDocumento extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "N_ID_TIPO_DOCUMENTO")
	private Long nIdTipoDocumento;

	@Column(name = "X_DESCRIPCION")
	private String xDescripcion;

	@Column(name = "X_ABREVIATURA")
	private String xAbreviatura;
	
	@Column(name = "X_EQUIVALENCIA")
	private String xEquivalencia;

	@Column(name = "L_FLAG_ACTIVO")
	private String lFlagActivo;

	@Column(name = "F_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fRegistro = new Date();

	@OneToMany(mappedBy = "maeTipoDocumento", fetch = FetchType.LAZY)
	private List<MaeUsuario> maeUsuarioList;

	@OneToMany(mappedBy = "maeTipoDocumento", fetch = FetchType.LAZY)
	private List<MaeColaborador> maePersonaList;
	
	@OneToMany(mappedBy = "maeTipoDocumento", fetch = FetchType.LAZY)
	private List<MovVisita> movVisitaList;

	public MaeTipoDocumento() {
		super();
	}
	
	public MaeTipoDocumento(Long nIdTipoDocumento) {
		super();
		this.nIdTipoDocumento = nIdTipoDocumento;
	}
	
	public Long getnIdTipoDocumento() {
		return nIdTipoDocumento;
	}

	public void setnIdTipoDocumento(Long nIdTipoDocumento) {
		this.nIdTipoDocumento = nIdTipoDocumento;
	}

	public String getxDescripcion() {
		return xDescripcion;
	}

	public void setxDescripcion(String xDescripcion) {
		this.xDescripcion = xDescripcion;
	}

	public String getxAbreviatura() {
		return xAbreviatura;
	}

	public void setxAbreviatura(String xAbreviatura) {
		this.xAbreviatura = xAbreviatura;
	}

	public String getlFlagActivo() {
		return lFlagActivo;
	}

	public void setlFlagActivo(String lFlagActivo) {
		this.lFlagActivo = lFlagActivo;
	}

	public List<MaeUsuario> getMaeUsuarioList() {
		return maeUsuarioList;
	}

	public void setMaeUsuarioList(List<MaeUsuario> maeUsuarioList) {
		this.maeUsuarioList = maeUsuarioList;
	}

	public List<MaeColaborador> getMaePersonaList() {
		return maePersonaList;
	}

	public void setMaePersonaList(List<MaeColaborador> maePersonaList) {
		this.maePersonaList = maePersonaList;
	}

	public Date getfRegistro() {
		return fRegistro;
	}

	public void setfRegistro(Date fRegistro) {
		this.fRegistro = fRegistro;
	}

	public List<MovVisita> getMovVisitaList() {
		return movVisitaList;
	}

	public void setMovVisitaList(List<MovVisita> movVisitaList) {
		this.movVisitaList = movVisitaList;
	}

	public String getxEquivalencia() {
		return xEquivalencia;
	}

	public void setxEquivalencia(String xEquivalencia) {
		this.xEquivalencia = xEquivalencia;
	}
	
	

	
}
