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
@Table(name = "MAE_COLABORADOR", schema = BaseEntity.ESQUEMA_BD)
public class MaeColaborador extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="MAE_COLABORADOR_GENERATOR", sequenceName="USRREGVIS.USEQ_MAE_COLABORADOR", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAE_COLABORADOR_GENERATOR")
	@Column(name = "N_ID_COLABORADOR")
	private Long nIdColaborador;
	@Column(name = "X_NOMBRE")
	private String xNombre;
	@Column(name = "X_APELLIDO_PATERNO")
	private String xApellidoPaterno;
	@Column(name = "X_APELLIDO_MATERNO")
	private String xApellidoMaterno;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "N_ID_TIPO_DOCUMENTO")
	private MaeTipoDocumento maeTipoDocumento;
	@Column(name = "N_NRO_DOCUMENTO")
	private String nNroDocumento;
	
	@Column(name = "X_CODIGO_SIGA")
	private String xCodigoSiga;
	@Column(name = "X_CODIGO_CARGO_SIGA")
	private String xCodigoCargoSiga;
	@Column(name = "X_DESCRIPCION_CARGO_SIGA")
	private String xDescripcionCargoSiga;
	@Column(name = "X_CODIGO_LOCAL_SIGA")
	private String xCodigoLocalSiga;
	@Column(name = "X_DESCRIPCION_LOCAL_SIGA")
	private String xDescripcionLocalSiga;

	@Column(name = "X_CODIGO_OFICINA_SIGA")
	private String xCodigoOficinaSiga;
	@Column(name = "X_DESCRIPCION_OFICINA_SIGA")
	private String xDescripcionOficinaSiga;



	@Column(name = "F_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fRegistro = new Date();

	@OneToMany(mappedBy = "visitado", fetch = FetchType.LAZY)
	private List<MovVisita> movVisitaVisitadoLst;



	public Long getnIdColaborador() {
		return nIdColaborador;
	}

	public void setnIdColaborador(Long nIdColaborador) {
		this.nIdColaborador = nIdColaborador;
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

	public MaeTipoDocumento getMaeTipoDocumento() {
		return maeTipoDocumento;
	}

	public void setMaeTipoDocumento(MaeTipoDocumento maeTipoDocumento) {
		this.maeTipoDocumento = maeTipoDocumento;
	}

	public String getnNroDocumento() {
		return nNroDocumento;
	}

	public void setnNroDocumento(String nNroDocumento) {
		this.nNroDocumento = nNroDocumento;
	}

	public Date getfRegistro() {
		return fRegistro;
	}

	public void setfRegistro(Date fRegistro) {
		this.fRegistro = fRegistro;
	}

	public List<MovVisita> getMovVisitaVisitadoLst() {
		return movVisitaVisitadoLst;
	}

	public void setMovVisitaVisitadoLst(List<MovVisita> movVisitaVisitadoLst) {
		this.movVisitaVisitadoLst = movVisitaVisitadoLst;
	}

	public String getxCodigoSiga() {
		return xCodigoSiga;
	}

	public void setxCodigoSiga(String xCodigoSiga) {
		this.xCodigoSiga = xCodigoSiga;
	}

	public String getxCodigoCargoSiga() {
		return xCodigoCargoSiga;
	}

	public void setxCodigoCargoSiga(String xCodigoCargoSiga) {
		this.xCodigoCargoSiga = xCodigoCargoSiga;
	}

	public String getxDescripcionCargoSiga() {
		return xDescripcionCargoSiga;
	}

	public void setxDescripcionCargoSiga(String xDescripcionCargoSiga) {
		this.xDescripcionCargoSiga = xDescripcionCargoSiga;
	}



	public String getxCodigoLocalSiga() {
		return xCodigoLocalSiga;
	}

	public void setxCodigoLocalSiga(String xCodigoLocalSiga) {
		this.xCodigoLocalSiga = xCodigoLocalSiga;
	}

	public String getxDescripcionLocalSiga() {
		return xDescripcionLocalSiga;
	}

	public void setxDescripcionLocalSiga(String xDescripcionLocalSiga) {
		this.xDescripcionLocalSiga = xDescripcionLocalSiga;
	}

	public String getxCodigoOficinaSiga() {
		return xCodigoOficinaSiga;
	}

	public void setxCodigoOficinaSiga(String xCodigoOficinaSiga) {
		this.xCodigoOficinaSiga = xCodigoOficinaSiga;
	}

	public String getxDescripcionOficinaSiga() {
		return xDescripcionOficinaSiga;
	}

	public void setxDescripcionOficinaSiga(String xDescripcionOficinaSiga) {
		this.xDescripcionOficinaSiga = xDescripcionOficinaSiga;
	}







}
