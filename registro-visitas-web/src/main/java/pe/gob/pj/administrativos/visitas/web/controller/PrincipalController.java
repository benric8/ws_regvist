package pe.gob.pj.administrativos.visitas.web.controller;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.model.util.NavigationVisitasConstantes;
import pe.gob.pj.administrativos.visitas.service.SeguridadService;
import pe.gob.pj.api.commons.utility.FacesContextUtil;

@ManagedBean(name = "principalMB")
@ViewScoped
public class PrincipalController extends BaseController{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PrincipalController.class);
	
	@ManagedProperty(value = "#{seguridadService}")
	private SeguridadService seguridadService;
	
	public void handlerSecurityAccessGranted() throws IOException {

		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	String url=FacesContextUtil.getURL();
    	
    	try{
	    	if (url.contains(ConstantesVisitas.CARPETA_RAIZ)) {
		    	if(this.getSessionController()==null || this.getSessionController().getUsuarioSession()==null  ){
		    		redirectPage(ConstantesVisitas.ACTION_FIN_SESION);
		    	} else if ( this.getSessionController().getListaActions()==null  || this.getSessionController().getListaActions().isEmpty()) {
		    		request.getSession().invalidate();
			    	redirectPage(ConstantesVisitas.ACTION_REDIRECT_ERROR);
			    }
			} else {
				redirectPage(ConstantesVisitas.ACTION_REDIRECT_NO_PAGE);
			}
    	} catch(Exception e){
    		logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
    				ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
    	}
	}

	public void irLogin() {
		try {
			redirectPage(ConstantesVisitas.ACTION_INICIO);
			//this.navigate(null, NavigationVisitasConstantes.LOGIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public String mostrarLogin(){
		return NavigationVisitasConstantes.LOGIN;
    }
    
    
	public SeguridadService getSeguridadService() {
		return seguridadService;
	}

	public void setSeguridadService(SeguridadService seguridadService) {
		this.seguridadService = seguridadService;
	}
	
}