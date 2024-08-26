package pe.gob.pj.administrativos.visitas.model.dto;

import java.io.Serializable;
import java.util.Date;

public class UsuarioDto extends BaseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long nIdUsuario;
	private String xUsuario;
	private String xPassword;
	private String xPasswordEncriptado;
	private String xNombre;
	private String xApellidoPaterno;	
	private String xApellidoMaterno;
	private String xNroDocumento;
	private String xCelular;
	private String xTelefono;
	private String xAnexo;	
	private String lFlagActivo;
	private Date fRegistro = new Date();
	private String xNombreCompleto;
	private PerfilDto perfilDto;
	private TipoDocumentoDto tipoDocumentoDto;
	private boolean flagCambiarClave;
	private boolean lbEstadoRegistro;
	private String xCodigoSiga;
	private String idUnidadEjecutora;
	private String idCorte;
	private String descripcionUnidadEjecutora;
	private String descripcionCorte;
	private String descripcionPerfilRegistro;
	private String cargo;
	private String correo;
	//recuperar clave
	private String urlRecuperacion;
	private String codigoEncriptado;
    private String token;
    private Date fechaSolicitudCambioClave;
    private Date fechaCambioClave;
    //TODO CRC Variables Reseteo de clave 
    private Date fechaReseteoClave;
    private String lFlagReseteo;

    
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

	public String getUrlRecuperacion() {
		return urlRecuperacion;
	}

	public void setUrlRecuperacion(String urlRecuperacion) {
		this.urlRecuperacion = urlRecuperacion;
	}

	public UsuarioDto(){
		this.nIdUsuario=0l; 
		
	}
	
	public UsuarioDto(UsuarioDto u) {
		super();
		this.nIdUsuario = u.nIdUsuario;
		this.xUsuario = u.xUsuario;
		this.xPassword = u.xPassword;
		this.xPasswordEncriptado = u.xPasswordEncriptado;
		this.xNombre = u.xNombre;
		this.xApellidoPaterno = u.xApellidoPaterno;
		this.xApellidoMaterno = u.xApellidoMaterno;
		this.xNroDocumento = u.xNroDocumento;
		this.xCelular = u.xCelular;
		this.xTelefono = u.xTelefono;
		this.xAnexo = u.xAnexo;	
		this.lFlagActivo = u.lFlagActivo;
		this.fRegistro = u.fRegistro;
		this.tipoDocumentoDto = u.tipoDocumentoDto;
		this.perfilDto = u.perfilDto;
		this.xCodigoSiga = u.xCodigoSiga;
		
	}
	
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
	public String getxPasswordEncriptado() {
		return xPasswordEncriptado;
	}
	public void setxPasswordEncriptado(String xPasswordEncriptado) {
		this.xPasswordEncriptado = xPasswordEncriptado;
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
	public String getxNombre() {
		return xNombre;
	}
	public void setxNombre(String xNombre) {
		this.xNombre = xNombre;
	}
	public String getxNombreCompleto() {
		return xNombreCompleto;
	}
	public void setxNombreCompleto(String xNombreCompleto) {
		this.xNombreCompleto = xNombreCompleto;
	}
	public Date getfRegistro() {
		return fRegistro;
	}
	public void setfRegistro(Date fRegistro) {
		this.fRegistro = fRegistro;
	}
	public PerfilDto getPerfilDto() {
		return perfilDto;
	}
	public void setPerfilDto(PerfilDto perfilDto) {
		this.perfilDto = perfilDto;
	}
	public TipoDocumentoDto getTipoDocumentoDto() {
		return tipoDocumentoDto;
	}
	public void setTipoDocumentoDto(TipoDocumentoDto tipoDocumentoDto) {
		this.tipoDocumentoDto = tipoDocumentoDto;
	}
	public boolean isFlagCambiarClave() {
		return flagCambiarClave;
	}
	public void setFlagCambiarClave(boolean flagCambiarClave) {
		this.flagCambiarClave = flagCambiarClave;
	}
	public boolean isLbEstadoRegistro() {
		return lbEstadoRegistro;
	}
	public void setLbEstadoRegistro(boolean lbEstadoRegistro) {
		this.lbEstadoRegistro = lbEstadoRegistro;
	}
	public String getxCodigoSiga() {
		return xCodigoSiga;
	}
	public void setxCodigoSiga(String xCodigoSiga) {
		this.xCodigoSiga = xCodigoSiga;
	}

	public String getIdUnidadEjecutora() {
		return idUnidadEjecutora;
	}

	public void setIdUnidadEjecutora(String idUnidadEjecutora) {
		this.idUnidadEjecutora = idUnidadEjecutora;
	}

	public String getIdCorte() {
		return idCorte;
	}

	public void setIdCorte(String idCorte) {
		this.idCorte = idCorte;
	}

	public String getDescripcionUnidadEjecutora() {
		return descripcionUnidadEjecutora;
	}

	public void setDescripcionUnidadEjecutora(String descripcionUnidadEjecutora) {
		this.descripcionUnidadEjecutora = descripcionUnidadEjecutora;
	}

	public String getDescripcionCorte() {
		return descripcionCorte;
	}

	public void setDescripcionCorte(String descripcionCorte) {
		this.descripcionCorte = descripcionCorte;
	}
	
	public String getDescripcionPerfilRegistro() {
		return descripcionPerfilRegistro;
	}
	
	public void setDescripcionPerfilRegistro(String descripcionPerfilRegistro) {
		this.descripcionPerfilRegistro = descripcionPerfilRegistro;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

}
