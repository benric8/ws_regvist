package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "MAE_USUARIO", schema = BaseEntity.ESQUEMA_BD)
public class MaeUsuario extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="MAE_USUARIO_IDUSUARIO_GENERATOR", sequenceName="USRREGVIS.USEQ_MAE_USUARIO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAE_USUARIO_IDUSUARIO_GENERATOR")
	@Column(name="N_ID_USUARIO", unique=true, nullable=false, precision=9)
	private Long nIdUsuario;
	
	@Column(name = "X_USUARIO")
	private String xUsuario;

	@Column(name = "X_PASSWORD")
	private String xPassword;

	@Column(name = "X_NOMBRE")
	private String xNombre;

	@Column(name = "X_APELLIDO_PATERNO")
	private String xApellidoPaterno;

	@Column(name = "X_APELLIDO_MATERNO")
	private String xApellidoMaterno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "N_ID_TIPO_DOCUMENTO")
	private MaeTipoDocumento maeTipoDocumento;

	@Column(name = "X_NRO_DOCUMENTO")
	private String xNroDocumento;

	@Column(name = "X_CELULAR")
	private String xCelular;

	@Column(name = "X_TELEFONO")
	private String xTelefono;

	@Column(name = "X_ANEXO")
	private String xAnexo;
	
	@Column(name = "X_CODIGO_SIGA")
	private String xCodigoSiga;
	

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "N_ID_PERFIL")
	private MaePerfil maePerfil;

	@Column(name = "L_FLAG_ACTIVO")
	private String lFlagActivo;
	
	@Column(name = "F_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fRegistro = new Date();

	@Column(name = "N_ID_UNI_EJE")
	private String nIdUniEje;
	
	@Column(name = "N_ID_CORTE")
	private String nIdCorte;

	@Column(name = "X_CORREO")
	private String xCorreo;

    @OneToMany(mappedBy = "maeUsuario", fetch = FetchType.LAZY)
    private List<MovVisita> movVisitaList;
    
	
    @Column(name = "X_CODIGO_ENCRIPTADO")
    private String codigoEncriptado;
    
    @Column(name = "X_TOKEN")
    private String token;
    
    @Column(name = "F_SOLICITUD_CAMBIO_CLAVE")
    private Date fechaSolicitudCambioClave;
    
    @Column(name = "F_CAMBIO_CLAVE")
    private Date fechaCambioClave;
    
    //TODO CRC Campos fecha y indicador de reseteo
    @Column(name = "F_RESETEO_CLAVE")
    private Date fechaReseteoClave;
    
	@Column(name = "L_FLAG_RESETEO")
	private String lFlagReseteo;
	
	// Alexis Távara VPP v.1.0.7
	@Column(name = "L_CITAS")
	private String lCitas;
    
	public Long getnIdUsuario() {
		return nIdUsuario;
	}

	public void setnIdUsuario(Long nIdUsuario) {
		this.nIdUsuario = nIdUsuario;
	}

	public String getxUsuario() {
		return xUsuario;
	}

	public void setxUsuario(String xUsuario) {
		this.xUsuario = xUsuario;
	}

	public String getxPassword() {
		return xPassword;
	}

	public void setxPassword(String xPassword) {
		this.xPassword = xPassword;
	}

	public String getxNroDocumento() {
		return xNroDocumento;
	}

	public void setxNroDocumento(String xNroDocumento) {
		this.xNroDocumento = xNroDocumento;
	}

	public String getxCelular() {
		return xCelular;
	}

	public void setxCelular(String xCelular) {
		this.xCelular = xCelular;
	}

	public String getxTelefono() {
		return xTelefono;
	}

	public void setxTelefono(String xTelefono) {
		this.xTelefono = xTelefono;
	}

	public String getxAnexo() {
		return xAnexo;
	}

	public void setxAnexo(String xAnexo) {
		this.xAnexo = xAnexo;
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

	public MaeTipoDocumento getMaeTipoDocumento() {
		return maeTipoDocumento;
	}

	public void setMaeTipoDocumento(MaeTipoDocumento maeTipoDocumento) {
		this.maeTipoDocumento = maeTipoDocumento;
	}

	public MaePerfil getMaePerfil() {
		return maePerfil;
	}

	public void setMaePerfil(MaePerfil maePerfil) {
		this.maePerfil = maePerfil;
	}

	public List<MovVisita> getMovVisitaList() {
		return movVisitaList;
	}

	public void setMovVisitaList(List<MovVisita> movVisitaList) {
		this.movVisitaList = movVisitaList;
	}

	public String getxNombre() {
		return xNombre;
	}

	public void setxNombre(String xNombre) {
		this.xNombre = xNombre;
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

	public String getxCodigoSiga() {
		return xCodigoSiga;
	}

	public void setxCodigoSiga(String xCodigoSiga) {
		this.xCodigoSiga = xCodigoSiga;
	}

	public String getnIdUniEje() {
		return nIdUniEje;
	}

	public void setnIdUniEje(String nIdUniEje) {
		this.nIdUniEje = nIdUniEje;
	}



	public String getnIdCorte() {
		return nIdCorte;
	}

	public void setnIdCorte(String nIdCorte) {
		this.nIdCorte = nIdCorte;
	}

	public String getxCorreo() {
		return xCorreo;
	}

	public void setxCorreo(String xCorreo) {
		this.xCorreo = xCorreo;
	}

	public String getCodigoEncriptado() {
		return codigoEncriptado;
	}

	public void setCodigoEncriptado(String codigoEncriptado) {
		this.codigoEncriptado = codigoEncriptado;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getFechaSolicitudCambioClave() {
		return fechaSolicitudCambioClave;
	}

	public void setFechaSolicitudCambioClave(Date fechaSolicitudCambioClave) {
		this.fechaSolicitudCambioClave = fechaSolicitudCambioClave;
	}

	public Date getFechaCambioClave() {
		return fechaCambioClave;
	}

	public void setFechaCambioClave(Date fechaCambioClave) {
		this.fechaCambioClave = fechaCambioClave;
	}

	public Date getFechaReseteoClave() {
		return fechaReseteoClave;
	}

	public void setFechaReseteoClave(Date fechaReseteoClave) {
		this.fechaReseteoClave = fechaReseteoClave;
	}

	public String getlFlagReseteo() {
		return lFlagReseteo;
	}

	public void setlFlagReseteo(String lFlagReseteo) {
		this.lFlagReseteo = lFlagReseteo;
	}

	// Alexis Távara VPP v.1.0.7
	public String getlCitas() {
		return lCitas;
	}

	public void setlCitas(String lCitas) {
		this.lCitas = lCitas;
	}	
	
	
}
