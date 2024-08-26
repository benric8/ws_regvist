package pe.gob.pj.administrativos.visitas.model.dto;

import java.util.Date;
import java.util.List;

import pe.gob.pj.administrativos.visitas.entity.MovVisita;


public class ColaboradorDto extends BaseDto{

	
	private Long idColaborador;
	private String codigoSiga;
	private String codigoCargoSiga;
	private String descripcionCargoSiga;
	private String codigoLocalSiga;
	private String descripcionLocalSiga;
	private String codigoOficinaSiga;
	private String descripcionOficinaSiga;	
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private TipoDocumentoDto tipoDocumento;
	private String numeroDocumento;
	private Date fechaRegistro;
	private List<VisitaDto> listaVisitas;

	

	public Long getIdColaborador() {
		return idColaborador;
	}
	public void setIdColaborador(Long idColaborador) {
		this.idColaborador = idColaborador;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
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
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public TipoDocumentoDto getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumentoDto tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getCodigoSiga() {
		return codigoSiga;
	}
	public void setCodigoSiga(String codigoSiga) {
		this.codigoSiga = codigoSiga;
	}
	public String getCodigoCargoSiga() {
		return codigoCargoSiga;
	}
	public void setCodigoCargoSiga(String codigoCargoSiga) {
		this.codigoCargoSiga = codigoCargoSiga;
	}
	public String getDescripcionCargoSiga() {
		return descripcionCargoSiga;
	}
	public void setDescripcionCargoSiga(String descripcionCargoSiga) {
		this.descripcionCargoSiga = descripcionCargoSiga;
	}

	
	public String getCodigoLocalSiga() {
		return codigoLocalSiga;
	}
	public void setCodigoLocalSiga(String codigoLocalSiga) {
		this.codigoLocalSiga = codigoLocalSiga;
	}
	public String getDescripcionLocalSiga() {
		return descripcionLocalSiga;
	}
	public void setDescripcionLocalSiga(String descripcionLocalSiga) {
		this.descripcionLocalSiga = descripcionLocalSiga;
	}
	public String getCodigoOficinaSiga() {
		return codigoOficinaSiga;
	}
	public void setCodigoOficinaSiga(String codigoOficinaSiga) {
		this.codigoOficinaSiga = codigoOficinaSiga;
	}
	public String getDescripcionOficinaSiga() {
		return descripcionOficinaSiga;
	}
	public void setDescripcionOficinaSiga(String descripcionOficinaSiga) {
		this.descripcionOficinaSiga = descripcionOficinaSiga;
	}
	public List<VisitaDto> getListaVisitas() {
		return listaVisitas;
	}
	public void setListaVisitas(List<VisitaDto> listaVisitas) {
		this.listaVisitas = listaVisitas;
	}
	
	


	
	
	
}
