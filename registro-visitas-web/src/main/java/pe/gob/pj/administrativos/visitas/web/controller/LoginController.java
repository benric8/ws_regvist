package pe.gob.pj.administrativos.visitas.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.gob.pj.administrativos.visitas.model.bean.CaptchaBean;
import pe.gob.pj.administrativos.visitas.model.dto.CorteDto;
import pe.gob.pj.administrativos.visitas.model.dto.LocalDto;
import pe.gob.pj.administrativos.visitas.model.dto.OpcionDto;
import pe.gob.pj.administrativos.visitas.model.dto.PuntoControlDto;
import pe.gob.pj.administrativos.visitas.model.dto.TipoDocumentoDto;
import pe.gob.pj.administrativos.visitas.model.dto.UnidadEjecutoraDto;
import pe.gob.pj.administrativos.visitas.model.dto.UsuarioDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.model.util.NavigationVisitasConstantes;
import pe.gob.pj.administrativos.visitas.model.util.ParametrosDeBusqueda;
import pe.gob.pj.administrativos.visitas.service.CfgPuntoControlService;
import pe.gob.pj.administrativos.visitas.service.MaeTipoDocumentoService;
import pe.gob.pj.administrativos.visitas.service.SeguridadService;
import pe.gob.pj.administrativos.visitas.service.VCorteService;
import pe.gob.pj.administrativos.visitas.service.VUnidadEjecutoraService;
import pe.gob.pj.api.commons.constants.Constantes;
import pe.gob.pj.api.commons.utility.CommonsUtilities;
import pe.gob.pj.api.commons.utility.ValidarUtil;

/**
* Resumen.
* Objeto : LoginController
* Descripción : Es una clase controlador de vistas que realiza el login del usuario. 
* Fecha de Creación : 02/01/2019
* Autor : Carlos Arroyo
* ------------------------------------------------------------------------
* Modificaciones
* Fecha                  Nombre                     Descripción
* ------------------------------------------------------------------------
* 08/05/2019			Carlos Arroyo				CRC: Cambio por Reseteo de Clave
* 15/06/2022			Alexis Távara				VPP: Validar solo Perfiles Permitidos v.1.0.7
*/
@ManagedBean(name = "loginMB")
@ViewScoped
public class LoginController extends BaseController {

	private static final long serialVersionUID = -6938261489229261293L;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
		
	private String numeroDoc;
	private String password;
	private Long tipDocumento;
	private UsuarioDto entity;
	
	private MenuModel model;
	private List<TipoDocumentoDto> lstTipoDoc;
	private List<String> listaActions =null;
	private Long tamanio;
	
	private String captcha;
    private CaptchaBean captchaBean;
    
	private boolean numeroDocumentoHabilitado;
	private PuntoControlDto puntoControl;
	private List<PuntoControlDto> listaPuntoControl;

	@ManagedProperty(value="#{maeTipoDocumentoService}")
	private MaeTipoDocumentoService maeTipoDocumentoService;

	@ManagedProperty(value = "#{seguridadService}")
	private SeguridadService seguridadService;
		
	@ManagedProperty(value = "#{unidadEjecutoraService}")
	private VUnidadEjecutoraService vUnidadEjecutoraService;

	@ManagedProperty(value = "#{corteService}")
	private VCorteService vCorteService;
	
	@ManagedProperty(value = "#{cfgPuntoControlService}")
	private CfgPuntoControlService cfgPuntoControlService;
	
	
	@PostConstruct
	public void init(){
		try {
			
			this.numeroDoc=null;
			this.password=null;	
			this.numeroDocumentoHabilitado = false;
			this.tipDocumento = 0L;
			puntoControl = new PuntoControlDto();
			listaPuntoControl = new ArrayList<>();
			cargarTipoDocumento();
			refrescarCaptcha();
		
		}catch(Exception excepcion){
			refrescarCaptcha();
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
				Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion) );
		}
	}
	
	private void cargarTipoDocumento() {
		try {
			Map<String, Object> parametrosBusqueda = new HashMap<>();			
			List<Long> listaTipoDocDefecto = new ArrayList<>();
			listaTipoDocDefecto.add(ConstantesVisitas.NUM_DOC_DNI);
			listaTipoDocDefecto.add(ConstantesVisitas.NUM_DOC_CE);
			listaTipoDocDefecto.add(ConstantesVisitas.NUM_DOC_PAS);
			parametrosBusqueda.put(ParametrosDeBusqueda.TIPO_DOCU_LIST, listaTipoDocDefecto);
			parametrosBusqueda.put(ParametrosDeBusqueda.ESTADO_REGISTRO, ConstantesVisitas.ACTIVO);
			this.lstTipoDoc = maeTipoDocumentoService.buscar(parametrosBusqueda);
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), 
				ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
		}
	}
	
	public void refrescarCaptcha() {  	
		try {
			CaptchaController cap = this.obtenerBean("captchaMB");
			captchaBean = cap.generarNuevaImagen();
			captcha = "";
		}catch(Exception excepcion){
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
				Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion) );
		}
	}
	
	private boolean validarCampos(){

		boolean resultado = true;
		
		try {
			
			CaptchaController cap = this.obtenerBean("captchaMB");
			String exclusionCaracteresRaros = " ";
			
			if( ValidarUtil.isNull( this.captcha ) ){
				
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Ingrese el código Captcha." );
				resultado = false;
				
			}else if( !cap.validarCaptcha( this.captcha, captchaBean.getSessionID() ) ) {
			
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Código captcha incorrecto.");
				resultado = false;
	            
	        }else{
				
	        	if(ValidarUtil.isNullOrEmpty(this.getTipDocumento())){
	        		addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Seleccione el tipo de documento de identidad" );
	        		resultado = false;
	        	}
		  		
	        	if( ValidarUtil.isNull( this.getNumeroDoc() )    ){		
	        		addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Ingrese el número de documento de identidad" );
	        		resultado = false;
	        	}
 
	        	if (this.getTipDocumento()==ConstantesVisitas.NUM_DOC_DNI && this.getNumeroDoc().length() != ConstantesVisitas.tamanioDNI){
	        		addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Nro. de Documento de Identidad debe tener "+ConstantesVisitas.tamanioDNI+" caracteres de tamaño" );
	        		resultado = false;
	        	} else if (this.getTipDocumento()==ConstantesVisitas.NUM_DOC_RUC && this.getNumeroDoc().length() != ConstantesVisitas.tamanioRUC){
	        		addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Nro. de Documento de Identidad debe tener "+ConstantesVisitas.tamanioRUC+" caracteres de tamaño" );
	        		resultado = false;
	        	} else if (this.getTipDocumento()==ConstantesVisitas.NUM_DOC_DNI ||this.getTipDocumento()==ConstantesVisitas.NUM_DOC_RUC){
			  		if (!ValidarUtil.validaFormatoNumerico( this.getNumeroDoc().trim() )){
			  			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "El Documento de Identidad  debe contener solo números" );
			  			resultado = false;
			  		} 
			  	}else{
			  		exclusionCaracteresRaros="-_";
					if (!ValidarUtil.validarFormatoEspecial( this.getNumeroDoc().trim() ,true,exclusionCaracteresRaros)){
						addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "El documento de Identidad contiene caracteres inválidos" );
						resultado = false;
					}
				}
	        	
	        	if(  ValidarUtil.isNull( this.getPassword() ) ){		
	        		addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Ingrese contraseña." );
	        		resultado = false;
	        	} else {
				  	exclusionCaracteresRaros="-_#@$.";
					if (!ValidarUtil.validarFormatoEspecial( this.getPassword().trim() ,true,exclusionCaracteresRaros)){
						addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Contraseña contiene caracteres inválidos" );
						resultado =  false;
					}
	        	}
				
	        }
			
		}catch(Exception e){
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
				Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), e) );
			resultado = false;
		}
		return resultado;
	}
	
	/** TODO CRC Metodo login
	* Descripción: Permite realizar el login y cargar a la sesion los datos del usuario.
	* @param No aplica
	* @return Retorna al formulario de Bienvenida del sistema.
	* @exception Manejo de errores no clasificados.
	*/
	public String login(){
		
		String navigate = null;
		getSessionController().setIpconexion(CommonsUtilities.getRemoteIpAddress());
		
		try {
			
			if( !validarCampos()){
				refrescarCaptcha();
				return navigate;
			}
			
			//Busqueda anterior
			entity = seguridadService.obtenerUsuario(this.getNumeroDoc(),this.getTipDocumento(), this.getPassword());				
			
			if( entity == null ){
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Nro. de Documento de Identidad o contraseña son incorrectos" );
				refrescarCaptcha();
				return   navigate;
		    }
			
			if( !entity.getlFlagActivo().equals(ConstantesVisitas.ACTIVO) ){					
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "El usuario se encuentra deshabilitado" );
				refrescarCaptcha();
				return   navigate;
			}
			
			// Valida que solo se permita iniciar sesión con los perfiles permitidos en el sistema - Alexis Távara 2022 VPP v.1.0.7
			if( !entity.getPerfilDto().getnIdPerfil().equals(ConstantesVisitas.PERFIL_ADMINISTRADOR_SISTEMA) && !entity.getPerfilDto().getnIdPerfil().equals(ConstantesVisitas.PERFIL_ADMINISTRADOR_CORTE)
					&& !entity.getPerfilDto().getnIdPerfil().equals(ConstantesVisitas.PERFIL_OPERADOR ) ){					
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "El usuario posee un perfil no permitido" );
				refrescarCaptcha();
				return   navigate;
			}
			
			//Busqueda actual
			
			UnidadEjecutoraDto unidadEjecutora = null;
			CorteDto corte = null;
			
			try {
				unidadEjecutora = vUnidadEjecutoraService.obtenerUnidadEjecutora(obtenerAnioActual().toString(), entity.getIdUnidadEjecutora());
			}catch(Exception e) {
				refrescarCaptcha();
				addFatalMessage(Constantes.mensajeFatalTitulo, "No se pudo obtener los datos de la Unidad Ejecutora." );
				return navigate;
			}
			
			try {
				corte = vCorteService.obtenerCorte(obtenerAnioActual(), entity.getIdUnidadEjecutora(), entity.getIdCorte());
			}catch(Exception e) {
				refrescarCaptcha();
				addFatalMessage(Constantes.mensajeFatalTitulo, "No se pudo obtener los datos de la Corte." );
				return navigate;
			}
			
			entity.setDescripcionCorte(corte.getNombreCorte());
			entity.setDescripcionUnidadEjecutora(unidadEjecutora.getNombreUniEje());

			this.getSessionController().setUsuarioSession(entity);
			
			long idSistema= 1l;	    	
			String mensajeValidacion=creacionSession(idSistema);
			
			if(mensajeValidacion!=null){
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, mensajeValidacion);
				refrescarCaptcha();
				navigate=NavigationVisitasConstantes.LOGIN;
			} else {
				this.numeroDoc=null;
				this.password=null;
				
				//TODO CRC Filtro para usuario  con reseteo de clave
				/////////////////////////////////////////////////////////////////////////////////////////////////
				if(entity.getlFlagReseteo()!=null && entity.getlFlagReseteo().equals(ConstantesVisitas.ACTIVO)) {
					navigate=ConstantesVisitas.IR_CAMBIAR_CLAVE;
					this.getSessionController().getUsuarioSession().setnIdUsuario(entity.getnIdUsuario());
					this.getSessionController().getUsuarioSession().setlFlagReseteo(ConstantesVisitas.ACTIVO);
					this.getSessionController().getUsuarioSession().getPerfilDto().setnIdPerfil(entity.getPerfilDto().getnIdPerfil());
				}else {
					/////////////////////////////////////////////////////////////////////////////////////////////////
					if ( (Long.compare( this.getSessionController().getUsuarioSession().getPerfilDto().getnIdPerfil(), ConstantesVisitas.PERFIL_ADMINISTRADOR_CORTE)==0)
							||(Long.compare( this.getSessionController().getUsuarioSession().getPerfilDto().getnIdPerfil(), ConstantesVisitas.PERFIL_ADMINISTRADOR_SISTEMA)==0)) {
						this.getSessionController().setMostrarMenu(true);
						navigate=NavigationVisitasConstantes.PAGINA_BIENVENIDA;					
					}else {					
						this.getSessionController().setMostrarMenu(false);
						buscarLocales();
						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaLocal", getSessionController().getListaLocal());
						navigate=NavigationVisitasConstantes.PAGINA_SELECCION_LOCAL;
					}
					/////////////////////////////////////////////////////////////////////////////////////////////////
				}
				/////////////////////////////////////////////////////////////////////////////////////////////////
			}
			return navigate;
			
		}catch (IOException e) {
			logger.error(e.getMessage());
			refrescarCaptcha();
		} catch (Exception e1) {
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
				ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), e1) );
			refrescarCaptcha();
			return null;
		}
		  return navigate;
	}

	public void listenerTipoDocumento(){

		numeroDoc = "";
		password = "";
		if(!ValidarUtil.isNullOrEmpty(tipDocumento)){
			if ( Long.compare(tipDocumento, ConstantesVisitas.NUM_DOC_DNI)==0 ){
				tamanio = ConstantesVisitas.tamanioDNI;
				numeroDocumentoHabilitado = true;
			} else if ( Long.compare(tipDocumento, ConstantesVisitas.NUM_DOC_CE)==0 ){
				tamanio = ConstantesVisitas.tamanioCE;
				numeroDocumentoHabilitado = true;
			} else if ( Long.compare(tipDocumento, ConstantesVisitas.NUM_DOC_PAS)==0 ){
				tamanio = ConstantesVisitas.tamanioPAS;
				numeroDocumentoHabilitado = true;
			} 
		
			else{
				tamanio = ConstantesVisitas.LONG_CERO;
				numeroDocumentoHabilitado = false;
			}
		}else {
			tamanio = ConstantesVisitas.LONG_CERO;
			numeroDocumentoHabilitado = false;
		}
	}

	public String cerrarSessionRetorno(){		

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
		
			if (getSessionController()!=null) {
				this.getSessionController().setUsuarioSession(null);
				this.getSessionController().setModel(null);
				this.getSessionController().setListaActions(null);
				this.getSessionController().setListaPermisosMenu(null);
			}
			this.setPassword(null);
			session.invalidate();
			return NavigationVisitasConstantes.LOGIN;
	
		}catch(Exception e){
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
				ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(),  e));
		return null;
		}
	}
	

	
	private String creacionSession(long idSistema) throws Exception{
		String MensajeValidacion=null;
		if(this.getSessionController().getUsuarioSession()==null){
			 return ConstantesVisitas.DATOS_SESSION_NO_ENCONTRADOS;
		}
		
		List<OpcionDto> listaPermisos = null;
		listaPermisos = seguridadService.obtenerPermisos(this.getSessionController().getUsuarioSession().getPerfilDto().getnIdPerfil());

		
		if( listaPermisos != null &&  listaPermisos.size()>0 ){
			contruirMenu(listaPermisos,true);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.getNumeroDoc());			    			
			this.getSessionController().setModel(model);
			this.getSessionController().setListaPermisosMenu(listaPermisos);
			this.getSessionController().setListaActions( this.listaActions );
		}  else{
			MensajeValidacion=ConstantesVisitas.MSG_PERMISOS_ASIGNADOS;
		}
		return MensajeValidacion;
	}

	public DefaultSubMenu  contruirMenu( List<OpcionDto> listaPermisos, boolean primero ) throws Exception{
	    	
		  if(primero){
			  model = new DefaultMenuModel();
		    listaActions = new ArrayList<String>();  
		  }
	    	
			DefaultSubMenu submenu = null;
			for (OpcionDto padre : listaPermisos) {
				 
					 submenu = new DefaultSubMenu( padre.getxNombre() );	 
				 
				 
				 DefaultMenuItem item = null;
				if( padre.getListaSubMenus() != null  && padre.getListaSubMenus().size() > 0 ){
					ordenar( padre.getListaSubMenus());
					for (OpcionDto hijo : padre.getListaSubMenus()) {
						DefaultSubMenu submenu2 = new DefaultSubMenu( hijo.getxNombre() );
						item = new DefaultMenuItem( hijo.getxNombre() );
						if(hijo.getListaSubMenus()!=null && hijo.getListaSubMenus().size()>0){
							List<OpcionDto> listasubPermisos= new ArrayList<OpcionDto>();
							listasubPermisos.add(hijo);
							submenu2= contruirMenu( listasubPermisos, false );
							submenu.addElement( submenu2 );
						}  else{
							item = new DefaultMenuItem( hijo.getxNombre() );
	    					item.setCommand( hijo.getxMetodo() );
	    					item.setAjax(false);
	    					submenu.addElement( item );
	    					item.setCommand( hijo.getxMetodo() );
							item.setAjax(false);
						}
						listaActions.add( hijo.getxMetodo() );
						}
					}else{
						item = new DefaultMenuItem( padre.getxNombre() );
						item.setIcon("");
						item.setCommand( padre.getxMetodo() );
						item.setAjax(false);
						submenu.addElement( item );
						listaActions.add( padre.getxMetodo() );
					}
				if(primero){
				 this.model.addElement( submenu );
				}
			}
			return submenu;
	}
		
	private void ordenar(List<OpcionDto> listaPermisos) throws Exception{
		  if(listaPermisos!=null && !listaPermisos.isEmpty()){
				Collections.sort(listaPermisos, new Comparator<OpcionDto>() {
					@Override
					public int compare(OpcionDto item01, OpcionDto item02) {
						Integer num1=item01.getnOrden()==null?0:item01.getnOrden();
						Integer num2=item02.getnOrden()==null?0:item02.getnOrden();
						return new Integer(num1).compareTo(num2);
					}
				});	
			}
	}
		
	@SuppressWarnings("unused")
	public String redireccionarOpcionMenu() throws Exception {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		String metodo = null;
		List<OpcionDto> listaPermisos = null;
		
		try {
		
			metodo = externalContext.getRequestParameterMap().get("metodoSeleccionado");
			 
			if( metodo==null ) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "No se pudo redireccionar a la opción seleccionada.");				
			}else {	

			}
		
		}catch (Exception e) {
			e.printStackTrace();
	    } 
		
		return metodo;
	}
	
	public void cerrarSessionAplicativo(){		

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession)context.getExternalContext().getSession(false);

			if (getSessionController()!=null) {
				this.getSessionController().setUsuarioSession(null);
				this.getSessionController().setModel(null);
				this.getSessionController().setListaActions(null);
				this.getSessionController().setListaPermisosMenu(null);
			}
			this.setPassword(null);
			session.invalidate();
	
		}catch(Exception e){
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
				ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(),  e));
		}
	}
	
	/*Logica para la seleccion de un punto de control*/
	public void buscarLocales(){		
		try {			
			List<LocalDto> listaLocal = cfgPuntoControlService.listarLocales(getSessionController().getUsuarioSession().getIdUnidadEjecutora()
					,getSessionController().getUsuarioSession().getIdCorte());
			getSessionController().setListaLocal(listaLocal);	
		}catch(Exception e){
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(),  e));
		}
	}

	public void listarPuntosControl() {
		
		try {
			
			listaPuntoControl = cfgPuntoControlService.
						listarPuntoControlLocal(this.puntoControl.getnIdLocal(),
												getSessionController().getUsuarioSession().getIdUnidadEjecutora(),
												getSessionController().getUsuarioSession().getIdCorte());


		
			}catch(Exception e){
				logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
						ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(),  e));
			}

		
	}

	public String continuarLogin() {
		
		String navigate="";
		try {
			
			if(ValidarUtil.isNullOrEmpty(this.puntoControl.getnIdLocal())){
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Debe seleccionar un local.");
			}else if(ValidarUtil.isNullOrEmpty(this.puntoControl.getnIdPuntoControl())){
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Debe seleccionar un punto de control.");
			}else {			
				navigate=NavigationVisitasConstantes.PAGINA_BIENVENIDA;
				this.puntoControl = (PuntoControlDto) BeanUtils.cloneBean(puntoControl);
				puntoControl.setNombre(obtenerDescripcionPuntoControl(puntoControl.getnIdPuntoControl()));
				getSessionController().setPuntoControlSession(puntoControl);
				this.getSessionController().setMostrarMenu(true);
			}
			
		}catch(Exception e){
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(),  e));
		}
		
		return navigate;
	}
	
	public String obtenerDescripcionPuntoControl(Long idPuntoControl) {
		String nombre="";
		for (PuntoControlDto punto:listaPuntoControl) {
			if (punto.getnIdPuntoControl()==idPuntoControl) {
				nombre=punto.getNombre();
				break;
			}
		}
		return nombre;
	}
	
	public String cambiarClave() {
		try {
			redirectPage(ConstantesVisitas.IR_CAMBIAR_CLAVE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ConstantesVisitas.IR_CAMBIAR_CLAVE;
	}
	
	public SeguridadService getSeguridadService() {
		return seguridadService;
	}

	public void setSeguridadService(SeguridadService seguridadService) {
		this.seguridadService = seguridadService;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TipoDocumentoDto> getLstTipoDoc() {
		return lstTipoDoc;
	}

	public void setLstTipoDoc(List<TipoDocumentoDto> lstTipoDoc) {
		this.lstTipoDoc = lstTipoDoc;
	}
	public Long getTipDocumento() {
		return tipDocumento;
	}

	public void setTipDocumento(Long tipDocumento) {
		this.tipDocumento = tipDocumento;
	}
	public MaeTipoDocumentoService getMaeTipoDocumentoService() {
		return maeTipoDocumentoService;
	}
	public void setMaeTipoDocumentoService(MaeTipoDocumentoService maeTipoDocumentoService) {
			this.maeTipoDocumentoService = maeTipoDocumentoService;
	}
	public String getNumeroDoc() {
		return numeroDoc;
	}

	public void setNumeroDoc(String numeroDoc) {
		this.numeroDoc = numeroDoc;
	}
	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}
	public List<String> getListaActions() {
		return listaActions;
	}

	public void setListaActions(List<String> listaActions) {
		this.listaActions = listaActions;
	}
	
	public Long getTamanio() {
		return tamanio;
	}

	public void setTamanio(Long tamanio) {
		this.tamanio = tamanio;
	}

	public boolean isNumeroDocumentoHabilitado() {
		return numeroDocumentoHabilitado;
	}

	public void setNumeroDocumentoHabilitado(boolean numeroDocumentoHabilitado) {
		this.numeroDocumentoHabilitado = numeroDocumentoHabilitado;
	}
	
	public String getCaptcha() {
		return captcha;
	}
	
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	public CaptchaBean getCaptchaBean() {
		return captchaBean;
	}
	
	public void setCaptchaBean(CaptchaBean captchaBean) {
		this.captchaBean = captchaBean;
	}

	public UsuarioDto getEntity() {
		return entity;
	}

	public void setEntity(UsuarioDto entity) {
		this.entity = entity;
	}

	public VUnidadEjecutoraService getvUnidadEjecutoraService() {
		return vUnidadEjecutoraService;
	}

	public void setvUnidadEjecutoraService(VUnidadEjecutoraService vUnidadEjecutoraService) {
		this.vUnidadEjecutoraService = vUnidadEjecutoraService;
	}

	public VCorteService getvCorteService() {
		return vCorteService;
	}

	public void setvCorteService(VCorteService vCorteService) {
		this.vCorteService = vCorteService;
	}

	public CfgPuntoControlService getCfgPuntoControlService() {
		return cfgPuntoControlService;
	}

	public void setCfgPuntoControlService(CfgPuntoControlService cfgPuntoControlService) {
		this.cfgPuntoControlService = cfgPuntoControlService;
	}

	public PuntoControlDto getPuntoControl() {
		return puntoControl;
	}

	public void setPuntoControl(PuntoControlDto puntoControl) {
		this.puntoControl = puntoControl;
	}

	public List<PuntoControlDto> getListaPuntoControl() {
		return listaPuntoControl;
	}

	public void setListaPuntoControl(List<PuntoControlDto> listaPuntoControl) {
		this.listaPuntoControl = listaPuntoControl;
	}
	
	
	
	
}
