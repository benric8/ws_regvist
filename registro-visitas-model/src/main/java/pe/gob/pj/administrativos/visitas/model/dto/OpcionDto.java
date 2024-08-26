package pe.gob.pj.administrativos.visitas.model.dto;

import java.io.Serializable;

import java.util.List;

public class OpcionDto extends BaseDto implements Serializable,Comparable<OpcionDto>,Cloneable{

	private static final long serialVersionUID = -6092218058733617917L;
	private Long nIdOpcion;
	private Long nIdOpcionPadre;
	private Integer nOrden;

	private String cReferencia;
	private String xDescripcion;
	private String xNombreSistema;
	private String xNombrePadre;
	
	private String xMetodo;
	private String xNombre;
	private List<OpcionDto> listaSubMenus;
	private boolean bIndicadorLlenadoHijos;
	private boolean bRegistroTemp;
	
	private String lEstadoRegistro;
	private boolean lbEstadoRegistro;
	private String lUsaCuotaEntidad;
	private boolean lbUsaCuotaEntidad;
	
	private boolean lbAsignado;

	public OpcionDto() {
	}
	public OpcionDto(Long nIdOpcion) {
		this.setnIdOpcion(nIdOpcion);
	}
	
	public OpcionDto(Long nIdOpcion, String xNombre, String xMetodo) {
		super();
		this.nIdOpcion = nIdOpcion;
		this.xNombre = xNombre;
		this.xMetodo = xMetodo;
	}	
	public Long getnIdOpcionPadre() {
		return nIdOpcionPadre;
	}

	public void setnIdOpcionPadre(Long nIdOpcionPadre) {
		this.nIdOpcionPadre = nIdOpcionPadre;
	}

	public Integer getnOrden() {
		return nOrden;
	}

	public void setnOrden(Integer nOrden) {
		this.nOrden = nOrden;
	}
	public String getcReferencia() {
		return cReferencia;
	}
	public void setcReferencia(String cReferencia) {
		this.cReferencia = cReferencia;
	}
	public String getlEstadoRegistro() {
		return lEstadoRegistro;
	}
	public void setlEstadoRegistro(String lEstadoRegistro) {
		this.lEstadoRegistro = lEstadoRegistro;
	}
	public String getxDescripcion() {
		return xDescripcion;
	}
	public void setxDescripcion(String xDescripcion) {
		this.xDescripcion = xDescripcion;
	}
	
	public String getxNombrePadre() {
		return xNombrePadre;
	}
	public void setxNombrePadre(String xNombrePadre) {
		this.xNombrePadre = xNombrePadre;
	}
	public String getxNombreSistema() {
		return xNombreSistema;
	}
	public void setxNombreSistema(String xNombreSistema) {
		this.xNombreSistema = xNombreSistema;
	}
	
	public String getxMetodo() {
		return xMetodo;
	}
	public void setxMetodo(String xMetodo) {
		this.xMetodo = xMetodo;
	}
	public String getxNombre() {
		return xNombre;
	}
	public void setxNombre(String xNombre) {
		this.xNombre = xNombre;
	}

	public Long getnIdOpcion() {
		return nIdOpcion;
	}

	public void setnIdOpcion(Long nIdOpcion) {
		this.nIdOpcion = nIdOpcion;
	}
	public List<OpcionDto> getListaSubMenus() {
		return listaSubMenus;
	}

	public void setListaSubMenus(List<OpcionDto> listaSubMenus) {
		this.listaSubMenus = listaSubMenus;
	}
	public boolean isbIndicadorLlenadoHijos() {
		return bIndicadorLlenadoHijos;
	}
	public void setbIndicadorLlenadoHijos(boolean bIndicadorLlenadoHijos) {
		this.bIndicadorLlenadoHijos = bIndicadorLlenadoHijos;
	}
	public boolean isbRegistroTemp() {
		return bRegistroTemp;
	}
	public void setbRegistroTemp(boolean bRegistroTemp) {
		this.bRegistroTemp = bRegistroTemp;
	}
	
	public void setLbEstadoRegistro(boolean lbEstadoRegistro) {
		this.lbEstadoRegistro = lbEstadoRegistro;
	}
	public boolean isLbEstadoRegistro() {
		return lbEstadoRegistro;
	}
	
	public void setlUsaCuotaEntidad(String lUsaCuotaEntidad) {
		this.lUsaCuotaEntidad = lUsaCuotaEntidad;
	}
	
	public String getlUsaCuotaEntidad() {
		return lUsaCuotaEntidad;
	}
	
	public void setLbUsaCuotaEntidad(boolean lbUsaCuotaEntidad) {
		this.lbUsaCuotaEntidad = lbUsaCuotaEntidad;
	}
	public boolean isLbUsaCuotaEntidad() {
		return lbUsaCuotaEntidad;
	}
	
	public void setLbAsignado(boolean lbAsignado) {
		this.lbAsignado = lbAsignado;
	}
	
	public boolean isLbAsignado() {
		return lbAsignado;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nIdOpcion == null) ? 0 : nIdOpcion.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpcionDto other = (OpcionDto) obj;
		if (nIdOpcion == null) {
			if (other.nIdOpcion != null)
				return false;
		} else if (!nIdOpcion.equals(other.nIdOpcion))
			return false;
		return true;
	}
	@Override
	public int compareTo(OpcionDto o) {
		 return this.getnIdOpcion().compareTo(o.getnIdOpcion());
	}
	
	public Object clone(){
	        Object obj=null;
	        try{
	            obj=super.clone();
	        }catch(CloneNotSupportedException ex){
	            System.out.println(" no se puede duplicar");
	        }
	        return obj;
	    }
	
	

}