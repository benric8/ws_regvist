package pe.gob.pj.administrativos.visitas.web.controller;

import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.gob.pj.administrativos.visitas.model.dto.UsuarioDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.model.util.NavigationVisitasConstantes;
import pe.gob.pj.administrativos.visitas.service.MaeUsuarioService;
import pe.gob.pj.api.commons.constants.Constantes;
import pe.gob.pj.api.commons.utility.ValidarUtil;

/**
* Resumen.
* Objeto : CambioClaveController
* Descripción : Es una clase controlador de vistas que permite el cambio de clave del usuario. 
* Fecha de Creación : 02/01/2019
* Autor : Carlos Arroyo
* ------------------------------------------------------------------------
* Modificaciones
* Fecha                  Nombre                     Descripción
* ------------------------------------------------------------------------
* 08/05/2019			Carlos Arroyo				CRC: Cambio por Reseteo de Clave
*/
@ManagedBean(name = "cambioClaveMB")
@ViewScoped
public class CambioClaveController extends BaseController {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CambioClaveController.class.getName());

	private String clave1;
	private String clave2;
	
	@ManagedProperty(value = "#{maeUsuarioService}")
	private MaeUsuarioService maeUsuarioService;

	@PostConstruct
	public void init() {
		
		try {
			boolean navegadorPermitido = validarNavegador();
			if( navegadorPermitido ) {
				if( !verificarPermiso( ConstantesVisitas.ACTION_CAMBIO_CLAVE)  ){
					this.noTienePermisos();
				}else{
					//CODE HERE
				}
			}
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
		}
		
	}

	
	private boolean validarDatos() {
		
		boolean resultado = true;
		
		if (ValidarUtil.isNullOrEmpty(this.clave1)) {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Ingrese nueva contraseña");
			resultado = false;
		} else if (ValidarUtil.isNullOrEmpty(this.clave2)) {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Confirme la nueva contraseña");
			resultado = false;
		} else if (this.clave1.length() <= 7) {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "La nueva contraseña debe tener una longitud mínima de 8 caracteres");
			resultado = false;
		} else if (this.clave1.contains(" ")) {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "La nueva contraseña no debe tener espacios en blanco.");
			resultado = false;
		} else if (!this.clave1.equals(this.clave2)) {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Las contraseñas no coindicen.");
			resultado = false;
		} else {
		
			// VALIDAR FORTALEZA DE LA CLAVE
			boolean hasNumber = this.clave1.matches(".*\\d.*");
			boolean hasAlphabet = this.clave1.matches(".*[a-zA-Z]+.*");
	
			if (!hasNumber || !hasAlphabet) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "La nueva contraseña debe tener al menos un número y una letra.");
				resultado = false;
			}
		
		}
		
		return resultado;
		
	}
	
	/**
	* Descripción: Permite guardar la clave cambiada. 
	* @param No aplica.
	* @return No aplica.
	* @exception Manejo de errores no clasificados.
	*/
	public void guardar() {
		String navigation = null;
		try {
			
			if( validarDatos() ) {
			
				UsuarioDto bean = new UsuarioDto();
				bean.setnIdUsuario(this.getSessionController().getUsuarioSession().getnIdUsuario());
				bean.setxPassword(this.clave1);
				bean.setFechaCambioClave(Calendar.getInstance().getTime());

				//TODO CRC Muestra Popup de Confirmacion de Cambio de clave.
				//////////////////////////////////////////////////////////////////////////////////////////////////////////
				String reseteo = this.getSessionController().getUsuarioSession().getlFlagReseteo();
				if(reseteo!=null && reseteo.equals(ConstantesVisitas.ACTIVO)) {
					bean.setlFlagReseteo(ConstantesVisitas.INACTIVO);
					this.getSessionController().getUsuarioSession().setlFlagReseteo(null);
					maeUsuarioService.levantarReseteo(bean);
					PrimeFaces.current().executeScript("PF('dlgConfirmacionCC').show();");
				}else {
				//////////////////////////////////////////////////////////////////////////////////////////////////////////
					maeUsuarioService.actualizarClave(bean);
					addInfoMessage(Constantes.mensajeInformativoTitulo, "Contraseña cambiada correctamente. Ingrese nuevamente a su cuenta.");
					PrimeFaces.current().ajax().update("listaMensajes");
					redirectPage(ConstantesVisitas.PAGINA_BIENVENIDA);
				//////////////////////////////////////////////////////////////////////////////////////////////////////////
				}
				//////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				//FacesContext context = FacesContext.getCurrentInstance();
				//HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
				/*if (getSessionController()!=null) {
					this.getSessionController().setUsuarioSession(null);
					this.getSessionController().setModel(null);
					this.getSessionController().setListaActions(null);
					this.getSessionController().setListaPermisosMenu(null);
				}*/
				//session.invalidate();
				//navigation = NavigationVisitasConstantes.PAGINA_BIENVENIDA;
			}else {
				PrimeFaces.current().ajax().update("listaMensajes");
				//redirectPage(ConstantesVisitas.PAGINA_CAMBIO_CLAVE);
				//navigation = ConstantesVisitas.ACTION_CAMBIO_CLAVE;
			}
		} catch (Exception e) {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Ocurrió un error al procesar la solicitud. Por favor intente nuevamente");
			logger.error("Error en cambio de contraseña: ", e);
		}
		//return navigation;
	}


	public String getClave1() {
		return clave1;
	}


	public void setClave1(String clave1) {
		this.clave1 = clave1;
	}


	public String getClave2() {
		return clave2;
	}


	public void setClave2(String clave2) {
		this.clave2 = clave2;
	}


	public MaeUsuarioService getMaeUsuarioService() {
		return maeUsuarioService;
	}


	public void setMaeUsuarioService(MaeUsuarioService maeUsuarioService) {
		this.maeUsuarioService = maeUsuarioService;
	}

	/** TODO CRC Metodo mostrarLogin
	* Descripción: Permite cerrar sesion y salir del sistema. 
	* @param No aplica
	* @return Retorna al formulario de Login.
	* @exception Manejo de errores no clasificados.
	*/
	public String mostrarLogin(){
    	
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
		
			if (getSessionController()!=null) {
				this.getSessionController().setUsuarioSession(null);
				this.getSessionController().setModel(null);
				this.getSessionController().setListaActions(null);
				this.getSessionController().setListaPermisosMenu(null);
			}
			session.invalidate();
	
		}catch(Exception e){
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
				ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(),  e));
		}
		return NavigationVisitasConstantes.LOGIN;
    }
	
}