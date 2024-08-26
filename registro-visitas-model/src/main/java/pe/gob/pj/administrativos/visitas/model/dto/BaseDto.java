package pe.gob.pj.administrativos.visitas.model.dto;

import java.util.Date;

public class BaseDto {

	private Date fOperacion;
	private Long nUsuarioOperacion;
	private String xIpOperacion;
	private String xIpWanOperacion;
	private String xMacOperacion;
	
	public BaseDto() {
		super();
	}
	public Date getfOperacion() {
		return fOperacion;
	}
	public void setfOperacion(Date fOperacion) {
		this.fOperacion = fOperacion;
	}
	public Long getnUsuarioOperacion() {
		return nUsuarioOperacion;
	}
	public void setnUsuarioOperacion(Long nUsuarioOperacion) {
		this.nUsuarioOperacion = nUsuarioOperacion;
	}
	public String getxIpOperacion() {
		return xIpOperacion;
	}
	public void setxIpOperacion(String xIpOperacion) {
		this.xIpOperacion = xIpOperacion;
	}
	public String getxIpWanOperacion() {
		return xIpWanOperacion;
	}
	public void setxIpWanOperacion(String xIpWanOperacion) {
		this.xIpWanOperacion = xIpWanOperacion;
	}
	public String getxMacOperacion() {
		return xMacOperacion;
	}
	public void setxMacOperacion(String xMacOperacion) {
		this.xMacOperacion = xMacOperacion;
	}
}
