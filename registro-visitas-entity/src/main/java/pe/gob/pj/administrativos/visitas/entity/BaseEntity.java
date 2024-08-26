
package pe.gob.pj.administrativos.visitas.entity;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 4367017366378853215L;
	public static final String ESQUEMA_BD = "USRREGVIS";
	public static final String ESQUEMA_BD_SIGA = "ADMINPJ";
	public static final String ESTADO_REGISTRO_ACTIVO = "1";
	public static final String ESTADO_REGISTRO_INACTIVO = "0";
	public static final String STRING_UNO = "1";
	public static final String STRING_CERO = "0";


	@Column(name = "N_USUARIO_OPERACION", nullable = false, precision = 19)
	private Long nUsuarioOperacion;
	
	@Column(name = "F_OPERACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fOperacion = new Date();

	@Column(name = "X_IP_OPERACION", nullable = false, length = 45)
	private String xIpOperacion;

	@Column(name = "X_MAC_OPERACION", length = 250)
	private String xMacOperacion;

	@Column(name = "X_IP_WAN_OPERACION", length = 250)
	private String xIpWanOperacion;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String obtenerNombreTabla(Class clase) {
		Annotation anotacion = clase.getAnnotation(Table.class);
		Table tabla = (Table) anotacion;
		String nombreTabla = tabla.name();
		return nombreTabla;
	}

	public Long getnUsuarioOperacion() {
		return nUsuarioOperacion;
	}

	public void setnUsuarioOperacion(Long nUsuarioOperacion) {
		this.nUsuarioOperacion = nUsuarioOperacion;
	}

	public Date getfOperacion() {
		return fOperacion;
	}

	public void setfOperacion(Date fOperacion) {
		this.fOperacion = fOperacion;
	}

	public String getxIpOperacion() {
		return xIpOperacion;
	}

	public void setxIpOperacion(String xIpOperacion) {
		this.xIpOperacion = xIpOperacion;
	}

	public String getxMacOperacion() {
		return xMacOperacion;
	}

	public void setxMacOperacion(String xMacOperacion) {
		this.xMacOperacion = xMacOperacion;
	}

	public String getxIpWanOperacion() {
		return xIpWanOperacion;
	}

	public void setxIpWanOperacion(String xIpWanOperacion) {
		this.xIpWanOperacion = xIpWanOperacion;
	}
	
}