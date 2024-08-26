package pe.gob.pj.administrativos.visitas.model.bean;

import java.io.Serializable;

public class ResponseConsultaReniecWs implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Mensaje mensaje;
    protected ResponseConsultaReniecWsData responseConsultaReniecWsData;
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	public ResponseConsultaReniecWsData getResponseConsultaReniecWsData() {
		return responseConsultaReniecWsData;
	}
	public void setResponseConsultaReniecWsData(ResponseConsultaReniecWsData responseConsultaReniecWsData) {
		this.responseConsultaReniecWsData = responseConsultaReniecWsData;
	}
    
    
    
}


