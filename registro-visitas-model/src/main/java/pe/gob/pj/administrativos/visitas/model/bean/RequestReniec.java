package pe.gob.pj.administrativos.visitas.model.bean;


public class RequestReniec {

	private String formatoRespuesta;
	private String consultante;
	private String motivo;
	private ConsultadoReniec personaConsultada;
	private PaginaReniec pagination;
	private AuditoriaReniec auditoria;
	
	public String getFormatoRespuesta() {
		return formatoRespuesta;
	}
	public void setFormatoRespuesta(String formatoRespuesta) {
		this.formatoRespuesta = formatoRespuesta;
	}
	public String getConsultante() {
		return consultante;
	}
	public void setConsultante(String consultante) {
		this.consultante = consultante;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public ConsultadoReniec getPersonaConsultada() {
		return personaConsultada;
	}
	public void setPersonaConsultada(ConsultadoReniec personaConsultada) {
		this.personaConsultada = personaConsultada;
	}
	public PaginaReniec getPagination() {
		return pagination;
	}
	public void setPagination(PaginaReniec pagination) {
		this.pagination = pagination;
	}
	public AuditoriaReniec getAuditoria() {
		return auditoria;
	}
	public void setAuditoria(AuditoriaReniec auditoria) {
		this.auditoria = auditoria;
	}
	
	
}