package pe.gob.pj.administrativos.visitas.model.dto;

import java.util.Date;

import org.primefaces.model.StreamedContent;

import pe.gob.pj.administrativos.visitas.entity.CfgPuntoControl;

public class VisitaDto extends BaseDto{
	
	private Long numeroVisita;
	private Date fechaHoraIngreso ;
	private Date fechaHoraSalida;
	private String descripcionMotivo;
	private String grupoVisita;
	private String observaciones;
	private Date fechaRegistro ;
	private ColaboradorDto visitado;
	private UsuarioDto usuario;
	private EntidadDto entidad;
	private TipoMotivoDto tipoMotivo;
	private TipoDocumentoDto tipoDocumento;
	private String fechaIngreso;
	private String horaIngreso;
	private String fechaSalida;
	private String horaSalida;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String numeroDocumento;
	private StreamedContent foto;
	private PuntoControlDto puntoControl;
	private String lugar;
	private String lFueraHorario;
	private int nPlataforma;
	private String plataforma;
	
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Long getNumeroVisita() {
		return numeroVisita;
	}
	public void setNumeroVisita(Long numeroVisita) {
		this.numeroVisita = numeroVisita;
	}
	public Date getFechaHoraIngreso() {
		return fechaHoraIngreso;
	}
	public void setFechaHoraIngreso(Date fechaHoraIngreso) {
		this.fechaHoraIngreso = fechaHoraIngreso;
	}
	public Date getFechaHoraSalida() {
		return fechaHoraSalida;
	}
	public void setFechaHoraSalida(Date fechaHoraSalida) {
		this.fechaHoraSalida = fechaHoraSalida;
	}
	public String getDescripcionMotivo() {
		return descripcionMotivo;
	}
	public void setDescripcionMotivo(String descripcionMotivo) {
		this.descripcionMotivo = descripcionMotivo;
	}
	public String getGrupoVisita() {
		return grupoVisita;
	}
	public void setGrupoVisita(String grupoVisita) {
		this.grupoVisita = grupoVisita;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public ColaboradorDto getVisitado() {
		return visitado;
	}
	public void setVisitado(ColaboradorDto visitado) {
		this.visitado = visitado;
	}
	public UsuarioDto getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}
	public EntidadDto getEntidad() {
		return entidad;
	}
	public void setEntidad(EntidadDto entidad) {
		this.entidad = entidad;
	}
	public TipoDocumentoDto getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumentoDto tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getHoraIngreso() {
		return horaIngreso;
	}
	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}
	public TipoMotivoDto getTipoMotivo() {
		return tipoMotivo;
	}
	public void setTipoMotivo(TipoMotivoDto tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}
	public String getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public String getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}
	public StreamedContent getFoto() {
		return foto;
	}
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public PuntoControlDto getPuntoControl() {
		return puntoControl;
	}
	public void setPuntoControl(PuntoControlDto puntoControl) {
		this.puntoControl = puntoControl;
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
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	
}
