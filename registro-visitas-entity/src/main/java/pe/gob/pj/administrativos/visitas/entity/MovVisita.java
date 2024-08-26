package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MOV_VISITA", schema = BaseEntity.ESQUEMA_BD)
public class MovVisita extends BaseEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="MOV_VISITA_GENERATOR", sequenceName="USRREGVIS.USEQ_MOV_VISITA", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MOV_VISITA_GENERATOR")
	@Column(name = "N_ID_VISITA")
	private Long nIdVisita;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "N_ID_COLABORADOR")
	private MaeColaborador visitado;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "N_ID_TIPO_DOCUMENTO")
	private MaeTipoDocumento maeTipoDocumento;
	
	
	@Column(name = "F_H_INGRESO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fHIngreso = new Date();
	
	@Column(name = "F_H_SALIDA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fHSalida = new Date();
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "N_ID_TIPO_MOTIVO")
	private MaeTipoMotivo maeTipoMotivo;
	
	@Column(name = "X_DESCRIPCION_MOTIVO")
	private String xDescripcionMotivo;
	
	@Column(name = "X_GRUPO_VISITA")
	private String xGrupoVisita;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "N_ID_ENTIDAD")
	private MaeEntidad maeEntidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "N_ID_USUARIO")
	private MaeUsuario maeUsuario;
	
	@Column(name = "X_OBSERVACIONES")
	private String xObservaciones;
	
	@Column(name = "F_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fRegistro = new Date();
	
	@Column(name = "X_NOMBRES")
	private String xNombres;
	
	@Column(name = "X_APELLIDO_PATERNO")
	private String xApellidoPaterno;
	
	@Column(name = "X_APELLIDO_MATERNO")
	private String xApellidoMaterno;
	
	@Column(name = "X_NUMERO_DOCUMENTO")
	private String xNumeroDocumento;
	
	@Column(name = "X_LUGAR_DESCRIPCION")
	private String xLugarDescripcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "N_ID_PUNTO_CONTROL")
	private CfgPuntoControl cfgPuntoControl;
	
	@Column(name="L_FUERA_HORARIO", length=1)
	private String lFueraHorario;

	@Column(name="N_PLATAFORMA")
	private int nPlataforma;

	public Long getnIdVisita() {
		return nIdVisita;
	}

	public void setnIdVisita(Long nIdVisita) {
		this.nIdVisita = nIdVisita;
	}

	public Date getfHIngreso() {
		return fHIngreso;
	}

	public void setfHIngreso(Date fHIngreso) {
		this.fHIngreso = fHIngreso;
	}

	public Date getfHSalida() {
		return fHSalida;
	}

	public void setfHSalida(Date fHSalida) {
		this.fHSalida = fHSalida;
	}

	public String getxDescripcionMotivo() {
		return xDescripcionMotivo;
	}

	public void setxDescripcionMotivo(String xDescripcionMotivo) {
		this.xDescripcionMotivo = xDescripcionMotivo;
	}

	public String getxGrupoVisita() {
		return xGrupoVisita;
	}

	public void setxGrupoVisita(String xGrupoVisita) {
		this.xGrupoVisita = xGrupoVisita;
	}

	public String getxObservaciones() {
		return xObservaciones;
	}

	public void setxObservaciones(String xObservaciones) {
		this.xObservaciones = xObservaciones;
	}

	public Date getfRegistro() {
		return fRegistro;
	}

	public void setfRegistro(Date fRegistro) {
		this.fRegistro = fRegistro;
	}

	public MaeColaborador getVisitado() {
		return visitado;
	}

	public void setVisitado(MaeColaborador visitado) {
		this.visitado = visitado;
	}

	public MaeTipoMotivo getMaeTipoMotivo() {
		return maeTipoMotivo;
	}

	public void setMaeTipoMotivo(MaeTipoMotivo maeTipoMotivo) {
		this.maeTipoMotivo = maeTipoMotivo;
	}

	public MaeEntidad getMaeEntidad() {
		return maeEntidad;
	}

	public void setMaeEntidad(MaeEntidad maeEntidad) {
		this.maeEntidad = maeEntidad;
	}

	public MaeUsuario getMaeUsuario() {
		return maeUsuario;
	}

	public void setMaeUsuario(MaeUsuario maeUsuario) {
		this.maeUsuario = maeUsuario;
	}

	public MaeTipoDocumento getMaeTipoDocumento() {
		return maeTipoDocumento;
	}

	public void setMaeTipoDocumento(MaeTipoDocumento maeTipoDocumento) {
		this.maeTipoDocumento = maeTipoDocumento;
	}

	public String getxNombres() {
		return xNombres;
	}

	public void setxNombres(String xNombres) {
		this.xNombres = xNombres;
	}

	public String getxApellidoPaterno() {
		return xApellidoPaterno;
	}

	public void setxApellidoPaterno(String xApellidoPaterno) {
		this.xApellidoPaterno = xApellidoPaterno;
	}

	public String getxApellidoMaterno() {
		return xApellidoMaterno;
	}

	public void setxApellidoMaterno(String xApellidoMaterno) {
		this.xApellidoMaterno = xApellidoMaterno;
	}

	public String getxNumeroDocumento() {
		return xNumeroDocumento;
	}

	public void setxNumeroDocumento(String xNumeroDocumento) {
		this.xNumeroDocumento = xNumeroDocumento;
	}

	public String getxLugarDescripcion() {
		return xLugarDescripcion;
	}

	public void setxLugarDescripcion(String xLugarDescripcion) {
		this.xLugarDescripcion = xLugarDescripcion;
	}

	public CfgPuntoControl getCfgPuntoControl() {
		return cfgPuntoControl;
	}

	public void setCfgPuntoControl(CfgPuntoControl cfgPuntoControl) {
		this.cfgPuntoControl = cfgPuntoControl;
	}

	public String getlFueraHorario() {
		return lFueraHorario;
	}

	public void setlFueraHorario(String lFueraHorario) {
		this.lFueraHorario = lFueraHorario;
	}

	public int getnPlataforma() {
		return nPlataforma;
	}

	public void setnPlataforma(int nPlataforma) {
		this.nPlataforma = nPlataforma;
	}

}
