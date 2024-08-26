package pe.gob.pj.administrativos.visitas.model.bean;

import java.io.Serializable;

public class ResponseConsultaReniecWsNombre implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Mensaje mensaje;
    protected ResponseConsultaReniecWsPersona responseConsultaReniecWsPersona;
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	public ResponseConsultaReniecWsPersona getResponseConsultaReniecWsPersona() {
		return responseConsultaReniecWsPersona;
	}
	public void setResponseConsultaReniecWsPersona(ResponseConsultaReniecWsPersona responseConsultaReniecWsPersona) {
		this.responseConsultaReniecWsPersona = responseConsultaReniecWsPersona;
	}
    
    
    
}


