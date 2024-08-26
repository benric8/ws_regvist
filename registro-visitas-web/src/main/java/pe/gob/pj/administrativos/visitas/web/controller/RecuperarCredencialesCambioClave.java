package pe.gob.pj.administrativos.visitas.web.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.pj.administrativos.visitas.model.bean.CaptchaBean;
import pe.gob.pj.administrativos.visitas.model.dto.UsuarioDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.model.util.NavigationVisitasConstantes;
import pe.gob.pj.administrativos.visitas.model.util.ParametrosDeBusqueda;
import pe.gob.pj.administrativos.visitas.service.MaeUsuarioService;
import pe.gob.pj.api.commons.constants.Constantes;
import pe.gob.pj.api.commons.utility.CryptoUtil;
import pe.gob.pj.api.commons.utility.FechaUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;

@ManagedBean(name = "pwdChange")
@ViewScoped
public class RecuperarCredencialesCambioClave extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(RecuperarCredencialesCambioClave.class.getName());

	private boolean renderGuardar;
	private String parametroCheck;
	private String parametroautentificar;
	private String clave1;
	private String clave2;
	private UsuarioDto usuario = new UsuarioDto();
	private String captcha;
	private CaptchaBean captchaBean;

	@ManagedProperty(value = "#{maeUsuarioService}")
	private MaeUsuarioService maeUsuarioService;
	
	@ManagedProperty(value = "#{captchaMB}")
	private CaptchaController captchaMB;

	@PostConstruct
	public void init() {
		HttpSession session = (HttpSession) getContext().getSession(false);
		
		try {
			
			boolean navegadorPermitido = validarNavegador();
			if( navegadorPermitido ) {
			
				Map<String, String> params = getContext().getRequestParameterMap();
				parametroCheck = (String) params.get("check");
				parametroautentificar = (String) params.get("autentifcar");
				
				if (!ValidarUtil.isNull(parametroautentificar) && !ValidarUtil.isNull(parametroautentificar)) {
					if( verificaUrl(parametroCheck, parametroautentificar) ) {
						refrescarCaptcha();
						
						boolean renderOk = session.getAttribute("recuperaRenderOk") == null ? false : (boolean) session.getAttribute("recuperaRenderOk");
						
						if (renderOk) {
							renderGuardar = true;
							usuario = (UsuarioDto) session.getAttribute("recuperaUsuario");
						}
						
						session.setAttribute("recuperaRenderOk", renderGuardar);
						session.setAttribute("recuperaUsuario", usuario);
					}else {
						PrimeFaces.current().ajax().update(":listaMensajes");
						redirectPage(ConstantesVisitas.ACTION_INICIO);
					}
				}else {
					addErrorMessage(Constantes.mensajeErrorTitulo, "Los parámetros de autentificación para acceder al cambio de contraseña son incorrectos.");
					PrimeFaces.current().ajax().update(":listaMensajes");
					redirectPage(ConstantesVisitas.ACTION_INICIO);
				}
			
			}
			
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
		}
		
	}

	/**
	 * El metodo verifica la autentificacion de la URL al iniciar la pagina.*
	 */
	public boolean verificaUrl(String ckeckPara, String autenPara) {
		
		boolean urlValida = true;
		
		try {
			Map<String, Object> params = new HashMap<>();
			params.put(ParametrosDeBusqueda.USUA_ID_ENCRYPT, ckeckPara);
			params.put(ParametrosDeBusqueda.USUA_ESTADO, ParametrosDeBusqueda.INDICADOR_ACTIVO);

			List<UsuarioDto> listaUsuario = maeUsuarioService.buscar(params);
			if (listaUsuario != null & listaUsuario.size() > 0)
				usuario = listaUsuario.get(0);

			if (usuario == null || ValidarUtil.isNullOrEmpty(usuario.getnIdUsuario())) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Usuario no identificado");
				urlValida = false;
			}else if (!autenPara.equals(CryptoUtil.encriptar(usuario.getToken()))) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Usuario no identificado");
				urlValida = false;
			}else {
				Calendar calendar = Calendar.getInstance();
	
				if (!ValidarUtil.isNullOrEmpty(usuario.getFechaCambioClave())) {
					if (FechaUtil.getDiferenciaFechaSegundo(new Timestamp(usuario.getFechaSolicitudCambioClave().getTime()),
							new Timestamp(usuario.getFechaCambioClave().getTime())) > Constantes.LONG_CERO) {
						if (FechaUtil.getDiferenciaFecha(new Timestamp(calendar.getTime().getTime()),
								new Timestamp(usuario.getFechaSolicitudCambioClave().getTime())) > Long.parseLong(ConstantesVisitas.plazoUrlActivaHrs)) {
							addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Venció el plazo para cambiar la contraseña. Por favor vuelva a solicitarlo.");
							urlValida = false;
						}
					} else {
						addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "El enlace ya fue utilizado. Si desea cambiar la contraseña, por favor vuelva a solicitarlo.");
						urlValida = false;
					}
				} else if (FechaUtil.getDiferenciaFecha(new Timestamp(calendar.getTime().getTime()),
						new Timestamp(usuario.getFechaSolicitudCambioClave().getTime())) > Long.parseLong(ConstantesVisitas.plazoUrlActivaHrs)) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Se venció el plazo para cambiar la contraseña, Por favor vuelva a solicitarlo.");
					urlValida = false;
				}
				renderGuardar = true;
			}
		} catch (Exception e) {
			logger.error("Error verificando url - Recuperación de contraseña: ", e);
		}
		
		return urlValida;
	}
	
	public void refrescarCaptcha() {
		try {
			
			captchaBean = captchaMB.generarNuevaImagen();
			captcha = "";						
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	public void guardar() {
		String navigation = null;
		try {
			
			if (validarDatosUsuario()) {

				UsuarioDto bean = new UsuarioDto();
				bean.setnIdUsuario(usuario.getnIdUsuario());
				bean.setxPassword(this.clave1);
				bean.setFechaCambioClave(Calendar.getInstance().getTime());

				maeUsuarioService.actualizarClave(bean);

				addInfoMessage(Constantes.mensajeInformativoTitulo, "Contraseña cambiada correctamente.");

				renderGuardar = false;
				FacesContext context = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
				session.setAttribute("recuperaRenderOk", renderGuardar);
				//navigation = NavigationVisitasConstantes.LOGIN;
				navigate(null,ConstantesVisitas.PAGINA_LOGIN);
			}else {
				refrescarCaptcha();				
			}
		} catch (Exception e) {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Ocurrió un error al procesar la solicitud. Por favor intente nuevamente");
			navigate(null,ConstantesVisitas.PAGINA_RECUPERAR_PASSWORD);
			logger.error("Error en cambio de contraseña: ", e);
		}
		//return navigation;
	}

	private boolean validarDatosUsuario() throws Exception {

		boolean resultado = true;
		
		if (ValidarUtil.isNull(this.captcha)) {

			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Ingrese el código Captcha.");
			resultado = false;

		} else if (!captchaMB.validarCaptcha(this.captcha, captchaBean.getSessionID())) {

			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Código captcha incorrecto.");
			resultado = false;

		} else { 
				
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
		
		}
		
		return resultado;

	}

	/*public void txtContrasenhaActual_validate(FacesContext context, UIComponent component, Object value) {
		String s = String.valueOf(value);

		if (!s.matches("^(?:\\S{5,14}\\S)?$")) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"La clave no debe de tener menos de 6 dígitos ni contener espacios en blanco", ""));
		}
		// if(!this.clave1.equals(this.clave2))
		// throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
		// "Las contraseñas ingresadas no son iguales.",""));
	}*/

	public boolean isRenderGuardar() {
		return renderGuardar;
	}

	public boolean getRenderGuardar() {
		return renderGuardar;
	}

	public void setRenderGuardar(boolean renderGuardar) {
		this.renderGuardar = renderGuardar;
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

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto bean) {
		this.usuario = bean;
	}

	public void setMaeUsuarioService(MaeUsuarioService maeUsuarioService) {
		this.maeUsuarioService = maeUsuarioService;
	}

	public String getParametroCheck() {
		return parametroCheck;
	}

	public void setParametroCheck(String parametroCheck) {
		this.parametroCheck = parametroCheck;
	}

	public String getParametroautentificar() {
		return parametroautentificar;
	}

	public void setParametroautentificar(String parametroautentificar) {
		this.parametroautentificar = parametroautentificar;
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

	public CaptchaController getCaptchaMB() {
		return captchaMB;
	}

	public void setCaptchaMB(CaptchaController captchaMB) {
		this.captchaMB = captchaMB;
	}

	public MaeUsuarioService getMaeUsuarioService() {
		return maeUsuarioService;
	}
	
	
}