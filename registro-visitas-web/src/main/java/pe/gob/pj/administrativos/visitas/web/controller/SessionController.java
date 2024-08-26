package pe.gob.pj.administrativos.visitas.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.MenuModel;

import pe.gob.pj.administrativos.visitas.entity.CfgPuntoControl;
import pe.gob.pj.administrativos.visitas.model.dto.LocalDto;
import pe.gob.pj.administrativos.visitas.model.dto.OpcionDto;
import pe.gob.pj.administrativos.visitas.model.dto.PerfilDto;
import pe.gob.pj.administrativos.visitas.model.dto.PuntoControlDto;
import pe.gob.pj.administrativos.visitas.model.dto.UsuarioDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;

@ManagedBean(name="sessionMB")
@SessionScoped
public class SessionController implements Serializable {
	
	private static final long serialVersionUID = -4397383446648652353L;
	private MenuModel model;
    private String rutaTemplate;
    private boolean esPublico;
    private String redirecReporte;
    private String institucionComplete;
    private String isPageValidate;
    private String ipconexion;
	private UsuarioDto usuarioSession;
	private List<LocalDto> listaLocal;
	//private PerfilDto perfilSession;

    private List<String> listaActions;
    private List<OpcionDto> listaPermisosMenu;

	private String filtroLocal;
	private PuntoControlDto puntoControlSession;
	
	private boolean mostrarMenu;
	
	/**
	 * Constructor de la clase.
	 */
	public SessionController() {
		
		puntoControlSession = new PuntoControlDto();
		//listaPuntoControl = new ArrayList<>();
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public String getRutaTemplate() {
		return rutaTemplate;
	}

	public void setRutaTemplate(String rutaTemplate) {
		this.rutaTemplate = rutaTemplate;
		if( rutaTemplate == null || rutaTemplate.equals("/templates/publico/publico-principal.xhtml")  ){
			this.esPublico = true;
		}else if( rutaTemplate.equals("/templates/principal.xhtml")  ){
			this.esPublico = false;
		}
	}
	
	
	
	/*
	public void listarPuntosControl() {
		
		try {
			
			List<PuntoControlDto> listaPuntoControl = cfgPuntoControlService.listarPuntoControlLocal(filtroLocal,getSessionController().getUsuarioSession().getIdUnidadEjecutora()
					,getSessionController().getUsuarioSession().getIdCorte());

	
		}catch(Exception e){
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(),  e));
		}
		
	}*/

	public boolean isEsPublico() {
		return esPublico;
	}
	
	public String getRedirecReporte() {
		return redirecReporte;
	}

	public void setRedirecReporte(String redirecReporte) {
		this.redirecReporte = redirecReporte;
	}

	public String getInstitucionComplete() {
		return institucionComplete;
	}

	public void setInstitucionComplete(String institucionComplete) {
		this.institucionComplete = institucionComplete;
	}

	public String getIsPageValidate() {
		return isPageValidate;
	}

	public void setIsPageValidate(String isPageValidate) {
		this.isPageValidate = isPageValidate;
	}

	public String getIpconexion() {
		return ipconexion;
	}

	public void setIpconexion(String ipconexion) {
		this.ipconexion = ipconexion;
	}

	public UsuarioDto getUsuarioSession() {
		return usuarioSession;
	}

	public void setUsuarioSession(UsuarioDto usuarioSession) {
		this.usuarioSession = usuarioSession;
	}

	public List<String> getListaActions() {
		return listaActions;
	}
	public void setListaActions(List<String> listaActions) {
		this.listaActions = listaActions;
	}
	/*
	public PerfilDto getPerfilSession() {
		return perfilSession;
	}
	public void setPerfilSession(PerfilDto perfilSession) {
		this.perfilSession = perfilSession;
	}
	*/
	public List<OpcionDto> getListaPermisosMenu() {
		return listaPermisosMenu;
	}
	public void setListaPermisosMenu(List<OpcionDto> listaPermisosMenu) {
		this.listaPermisosMenu = listaPermisosMenu;
	}

	public List<LocalDto> getListaLocal() {
		return listaLocal;
	}

	public void setListaLocal(List<LocalDto> listaLocal) {
		this.listaLocal = listaLocal;
	}

	public String getFiltroLocal() {
		return filtroLocal;
	}

	public void setFiltroLocal(String filtroLocal) {
		this.filtroLocal = filtroLocal;
	}

	public PuntoControlDto getPuntoControlSession() {
		return puntoControlSession;
	}

	public void setPuntoControlSession(PuntoControlDto puntoControlSession) {
		this.puntoControlSession = puntoControlSession;
	}

	public boolean isMostrarMenu() {
		return mostrarMenu;
	}

	public void setMostrarMenu(boolean mostrarMenu) {
		this.mostrarMenu = mostrarMenu;
	}

}
