package pe.gob.pj.administrativos.visitas.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.pj.administrativos.visitas.model.bean.CaptchaBean;
import pe.gob.pj.administrativos.visitas.model.dto.TipoDocumentoDto;
import pe.gob.pj.administrativos.visitas.model.dto.UsuarioDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesMensaje;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.model.util.NavigationVisitasConstantes;
import pe.gob.pj.administrativos.visitas.model.util.ParametrosDeBusqueda;
import pe.gob.pj.administrativos.visitas.service.CorreoService;
import pe.gob.pj.administrativos.visitas.service.MaeTipoDocumentoService;
import pe.gob.pj.administrativos.visitas.service.MaeUsuarioService;
import pe.gob.pj.api.commons.constants.Constantes;
import pe.gob.pj.api.commons.utility.CryptoUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;
import pe.gob.pj.api.mail.properties.MessageProperties;

@ManagedBean(name = "recoveryMB")
@ViewScoped
public class RecuperarCredencialesController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(RecuperarCredencialesController.class.getName());

	private String email;
	private String textoCaptcha;
	private String txtUsuario;
	private List<UsuarioDto> listaUsuario;
	private String captcha;
	private CaptchaBean captchaBean;
	private Long tipDocumento;
	private List<TipoDocumentoDto> lstTipoDoc;
	private Long tamanio;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Pattern pattern;

	@ManagedProperty(value = "#{maeTipoDocumentoService}")
	private MaeTipoDocumentoService maeTipoDocumentoService;

	@ManagedProperty(value = "#{maeUsuarioService}")
	private MaeUsuarioService maeUsuarioService;

	@ManagedProperty(value = "#{correoService}")
	private CorreoService correoService;

	@ManagedProperty(value = "#{captchaMB}")
	private CaptchaController captchaMB;

	@PostConstruct
	public void init() {
		try {
			boolean navegadorPermitido = validarNavegador();
			if( navegadorPermitido ) {
				refrescarCaptcha();
				cargarTipoDocumento();
				pattern = Pattern.compile(EMAIL_PATTERN);
			}
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
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
			
			captchaBean = captchaMB.generarNuevaImagen();
			captcha = "";						
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	public void refresh(final ComponentSystemEvent event) {
		if(FacesContext.getCurrentInstance().isValidationFailed() == true) {
			refrescarCaptcha();
		}
	}
	
	public void enviar() {
		String navigate = null;
		try {

			if (validarDatos()) {
				if (listaUsuario.size() > 1) {
					// en caso tenga varias cuentas

					for (UsuarioDto datoCorreo : listaUsuario) {
						String link = generarLink(datoCorreo.getnIdUsuario());
						if (link == null) {
							addErrorMessage("", ConstantesMensaje.operacionFallida);
							refrescarCaptcha();
							//return null;
							navigate(null,ConstantesVisitas.PAGINA_FORGOT_PASSWORD);
						} else {
							datoCorreo.setUrlRecuperacion(link);
						}
					}

					datosEnvioCorreoVariasCuentas(listaUsuario, ConstantesVisitas.plazoUrlActivaHrs);
				} else {
					// En caso tenga una sola cuenta

					String link = generarLink(listaUsuario.get(0).getnIdUsuario());
					if (link == null) {
						addErrorMessage("", ConstantesMensaje.operacionFallida);
						//return null;
						navigate(null,ConstantesVisitas.PAGINA_FORGOT_PASSWORD);
					} else {
						datosEnvioCorreo(listaUsuario, link, ConstantesVisitas.plazoUrlActivaHrs);
					}
				}
				addInfoMessage("", "Se ha enviado un correo electrónico a la direción proporcionada.");
				//navigate = NavigationVisitasConstantes.LOGIN;
				navigate(null,ConstantesVisitas.PAGINA_LOGIN);
			} else {
				refrescarCaptcha();
			}

		} catch (Exception e) {
			refrescarCaptcha();
			addErrorMessage("", ConstantesMensaje.operacionFallida);
			navigate(null,ConstantesVisitas.PAGINA_FORGOT_PASSWORD);
			logger.error("Error en Controller: RecuperarCredenciales.java:", e);
		}

		//return navigate;
	}

	public void listenerTipoDocumento() {

		this.txtUsuario = "";

		if (!ValidarUtil.isNullOrEmpty(tipDocumento)) {
			if (Long.compare(tipDocumento, ConstantesVisitas.NUM_DOC_DNI) == 0) {
				tamanio = ConstantesVisitas.tamanioDNI;
			} else if (Long.compare(tipDocumento, ConstantesVisitas.NUM_DOC_CE) == 0) {
				tamanio = ConstantesVisitas.tamanioCE;
			} else if (Long.compare(tipDocumento, ConstantesVisitas.NUM_DOC_RUC) == 0) {
				tamanio = ConstantesVisitas.tamanioRUC;
			} else {
				tamanio = ConstantesVisitas.LONG_CERO;
			}
		} else {
			tamanio = ConstantesVisitas.LONG_CERO;
		}
	}

	public String generarLink(Long id) throws Exception {

		try {
			UsuarioDto bean = new UsuarioDto();
			StringBuilder link = new StringBuilder();
			String token = CryptoUtil.generarClaveVisual();
			String tokenEncriptado = CryptoUtil.encriptar(token);
			String idEcriptado = CryptoUtil.encriptar(String.valueOf(id));
			bean.setnIdUsuario(id);
			bean.setCodigoEncriptado(idEcriptado);
			bean.setToken(token);
			bean.setFechaSolicitudCambioClave(Calendar.getInstance().getTime());

			int result = maeUsuarioService.solicitarRecuperacionClave(bean);// seguridadService.solicitarRecuperarClave(bean);
			if (result >= 1) {
				// cambiar por properties
				
				boolean cortar = ConstantesVisitas.URL_APP.substring(ConstantesVisitas.URL_APP.length() - 1, ConstantesVisitas.URL_APP.length()).equals("/") ? true : false;
				
				String urlApp = ConstantesVisitas.URL_APP + (cortar ? "pages/seguridad/prForm.xhtml" : "/pages/seguridad/prForm.xhtml");

				link.append(urlApp);
				link.append("?autentifcar=");
				link.append(tokenEncriptado);
				link.append("&check=");
				link.append(idEcriptado);
				return link.toString();

			} else {
				return null;
			}

		} catch (Exception e) {
			logger.error("Error al generar link - RecuperarCredencialesController.java", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Recuperacion de datos para casos que tenga una sola cuenta
	 * 
	 * @param datoUsuario
	 *            datos de usuario
	 * @param link
	 *            autentificado
	 * @param Tipo
	 *            de recuperacion (contraseña , usuario)
	 * @param Tiempo
	 *            valido en horas del Link
	 * @return datos que se envia por correo.
	 */
	public void datosEnvioCorreo(List<UsuarioDto> lista, String link, String tiempoValido) throws Exception {

		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setDestinatarios(lista.get(0).getCorreo().trim());
		messageProperties.setAsunto("Recuperación de credenciales - Registro de visitas");

		StringBuilder sbMensaje = new StringBuilder();
		sbMensaje.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" >");
		sbMensaje.append("<head>");
		sbMensaje.append("<META HTTP-EQUIV='Content-Type' CONTENT='text/html; charset=UTF-8' />");
		sbMensaje.append("<title>RECUPERACIÓN DE CREDENCIALES</title>");
		sbMensaje.append("<style type=\"text/css\">");
		sbMensaje.append(
				".style1 {font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; font-size: 81.25%; color:#212121}");
		sbMensaje.append(
				".style2 {font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; font-size: 81.25%; color:#212121}");
		sbMensaje.append(
				".style3 {font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; font-size: 85%; color:#8A0808}");
		sbMensaje.append("</style>");
		sbMensaje.append("</head>");
		sbMensaje.append("<body BGCOLOR=\"#F2F2F2\">");
		sbMensaje.append("<table width=\"100%\" border=\"0\" cellpadding=\"1\" cellspacing=\"0\">");
		sbMensaje.append("<tr>");
		sbMensaje.append(
				"<th  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style3\">");
		sbMensaje.append("<b>PODER JUDICIAL DEL PERÚ<br> REGISTRO DE VISITAS </br></th>");
		sbMensaje.append("</tr>");
		sbMensaje.append("<tr>");
		sbMensaje.append(
				"<th  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style1\">");
		sbMensaje.append("RECUPERACIÓN DE CREDENCIALES DE ACCESO <br><br></th>");
		sbMensaje.append("</tr>");

		sbMensaje.append("<tr>");
		sbMensaje.append(
				"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\"><b>Estimado/a </b>");
		sbMensaje.append(lista.get(0).getxApellidoPaterno()).append(" ").append(lista.get(0).getxApellidoMaterno())
				.append(", ").append(lista.get(0).getxNombre()).append("</th>");
		sbMensaje.append("</tr>");
		sbMensaje.append("<tr>");
		sbMensaje.append(
				"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\">");
		sbMensaje.append("</br>Hemos recibido su pedido para recuperar la contraseña de su cuenta: </th>");
		sbMensaje.append("</tr>");
		sbMensaje.append("<tr>");
		sbMensaje.append(
				"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\"><b>&nbsp;&#8226; Nombre de usuario: </b>");
		sbMensaje.append(lista.get(0).getxUsuario()).append("</th>");
		sbMensaje.append("</tr>");
		sbMensaje.append("<tr>");
		sbMensaje.append(
				"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\"></br>");
		sbMensaje.append("Para realizar el cambio de contraseña dar click en el siguiente enlace: <a href=")
				.append(link).append("> Cambiar contraseña</a></th>");
		sbMensaje.append("</tr>");
		sbMensaje.append("<tr>");
		sbMensaje.append(
				"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\"></br>");
		sbMensaje.append("Si no puede abrir el link, copie y pegue la siguiente dirección en su navegador:: <a href=")
				.append(link).append(">").append(link).append("</a></th>");
		sbMensaje.append("</tr>");
		sbMensaje.append("<tr>");
		sbMensaje.append(
				"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\">");
		sbMensaje.append("</br>El tiempo válido para el enlace es de ").append(tiempoValido).append(" horas.</a></th>");
		sbMensaje.append("</tr>");

		sbMensaje.append("<tr>");
		sbMensaje.append(
				"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\"></br>Gracias.</th>");
		sbMensaje.append("</tr>");
		sbMensaje.append("</table></body></html>");

		messageProperties.setContenido(sbMensaje.toString());
		correoService.enviar(messageProperties);
	}

	/**
	 * Recuperacion de datos para el casos que tenga varias cuentas.
	 * 
	 * @param lista
	 *            de usuario que tiene mas de una cuenta
	 * @param Tipo
	 *            de recuperacion (contraseña , usuario)
	 * @param Tiempo
	 *            valido en horas del Link
	 * @return datos que se envia por correo
	 */
	public void datosEnvioCorreoVariasCuentas(List<UsuarioDto> lista, String tiempoValido) throws Exception {
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setDestinatarios(lista.get(0).getCorreo().trim());
		messageProperties.setAsunto("Recuperación de credenciales - Registro de visitas");

		UsuarioDto datoRecuperacion = new UsuarioDto();
		Iterator<UsuarioDto> it = lista.iterator();

		StringBuilder sbMensajeRecuperar = new StringBuilder();

		sbMensajeRecuperar.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" >");
		sbMensajeRecuperar.append("<head>");
		sbMensajeRecuperar.append("<META HTTP-EQUIV='Content-Type' CONTENT='text/html; charset=UTF-8' />");
		sbMensajeRecuperar.append("<title>RECUPERACIÓN DE CREDENCIALES</title>");
		sbMensajeRecuperar.append("<style type=\"text/css\">");
		sbMensajeRecuperar.append(
				".style1 {font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; font-size: 81.25%; color:#212121}");
		sbMensajeRecuperar.append(
				".style2 {font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; font-size: 81.25%; color:#212121}");
		sbMensajeRecuperar.append(
				".style3 {font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; font-size: 85%; color:#8A0808}");
		sbMensajeRecuperar.append("</style>");
		sbMensajeRecuperar.append("</head>");
		sbMensajeRecuperar.append("<body BGCOLOR=\"#F2F2F2\">");
		sbMensajeRecuperar.append("<table width=\"100%\" border=\"0\" cellpadding=\"1\" cellspacing=\"0\">");
		sbMensajeRecuperar.append("<tr>");
		sbMensajeRecuperar.append(
				"<th  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style3\">");
		sbMensajeRecuperar.append("<b>PODER JUDICIAL DEL PERÚ<br> REGISTRO DE VISITAS </br></th>");
		sbMensajeRecuperar.append("</tr>");
		sbMensajeRecuperar.append("<tr>");
		sbMensajeRecuperar.append(
				"<th  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style1\">");
		sbMensajeRecuperar.append("RECUPERACIÓN DE CREDENCIALES DE ACCESO <br><br></th>");
		sbMensajeRecuperar.append("</tr>");

		sbMensajeRecuperar.append("<tr>");
		sbMensajeRecuperar.append(
				"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\"><b>Estimado/a </b>");
		sbMensajeRecuperar.append(lista.get(0).getxApellidoPaterno()).append(" ")
				.append(lista.get(0).getxApellidoMaterno()).append(", ").append(lista.get(0).getxNombre())
				.append("</th>");
		sbMensajeRecuperar.append("</tr>");
		sbMensajeRecuperar.append("<tr>");
		sbMensajeRecuperar.append(
				"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\">");
		sbMensajeRecuperar.append("</br>Hemos recibido su pedido para recuperar la contraseña de su cuenta: </th>");
		sbMensajeRecuperar.append("</tr>");

		int i = 0;
		while (it.hasNext()) {
			i = i + 1;
			datoRecuperacion = it.next();
			sbMensajeRecuperar.append("<tr>");
			sbMensajeRecuperar.append(
					"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\"><br/><b>&nbsp;&#8226; Nombre de usuario: </b>");
			sbMensajeRecuperar.append(datoRecuperacion.getxUsuario()).append("</th>");
			sbMensajeRecuperar.append("</tr>");
			sbMensajeRecuperar.append("<tr>");
			sbMensajeRecuperar.append(
					"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\">");
			sbMensajeRecuperar
					.append("<br/>Para realizar el cambio de contraseña dar click en el siguiente enlace: <a href=")
					.append(datoRecuperacion.getUrlRecuperacion()).append("> Cambiar contraseña</a></th>");
			sbMensajeRecuperar.append("</tr>");
			sbMensajeRecuperar.append("<tr>");
			sbMensajeRecuperar.append(
					"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\"></br>");
			sbMensajeRecuperar.append(
					"Si no puede abrir el link, copie y pegue la siguiente dirección en Su navegador:: <a href=")
					.append(datoRecuperacion.getUrlRecuperacion()).append(">")
					.append(datoRecuperacion.getUrlRecuperacion()).append("</a></th>");
			sbMensajeRecuperar.append("</tr>");

		}

		sbMensajeRecuperar.append("<tr>");
		sbMensajeRecuperar.append(
				"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\">");
		sbMensajeRecuperar.append("</br>El tiempo válido para el enlace es de ").append(tiempoValido)
				.append(" horas.</a></th>");
		sbMensajeRecuperar.append("</tr>");

		sbMensajeRecuperar.append("<tr>");
		sbMensajeRecuperar.append(
				"<th align=\"left\"  width=\"100%\" bordercolor=\"#F2F2F2\" bgcolor=\"#F2F2F2\" scope=\"col\"><span class=\"style2\"></br>Gracias.</th>");
		sbMensajeRecuperar.append("</tr>");
		sbMensajeRecuperar.append("</table></body></html>");

		messageProperties.setContenido(sbMensajeRecuperar.toString());
		correoService.enviar(messageProperties);
	}

	public boolean validarDatos() {

		boolean resultado = true;

		String exclusionCaracteresRaros = " ";

		if (ValidarUtil.isNull(this.captcha)) {

			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Ingrese el código Captcha.");
			resultado = false;

		} else if (!captchaMB.validarCaptcha(this.captcha, captchaBean.getSessionID())) {

			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Código captcha incorrecto.");
			resultado = false;

		} else {
			
			exclusionCaracteresRaros = "-_";
			
			if (ValidarUtil.isNullOrEmpty(this.getTipDocumento())) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Seleccione el tipo de documento de identidad");
				resultado = false;
			} else if (ValidarUtil.isNull(this.getTxtUsuario())) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Ingrese el número de documento de identidad");
				resultado = false;
			} else if ( ValidarUtil.isNullOrEmpty(this.email) ){
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Ingrese un correo electrónico.");
				resultado = false;
			} else {				
				if (this.getTipDocumento() == ConstantesVisitas.NUM_DOC_DNI && this.getTxtUsuario().length() != ConstantesVisitas.tamanioDNI) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Nro. de Documento de Identidad debe tener " + ConstantesVisitas.tamanioDNI + " caracteres de tamaño");
					resultado = false;
				} else if (this.getTipDocumento() == ConstantesVisitas.NUM_DOC_RUC && this.getTxtUsuario().length() != ConstantesVisitas.tamanioRUC) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Nro. de Documento de Identidad debe tener " + ConstantesVisitas.tamanioRUC + " caracteres de tamaño");
					resultado = false;
				} else if (this.getTipDocumento() == ConstantesVisitas.NUM_DOC_DNI || this.getTipDocumento() == ConstantesVisitas.NUM_DOC_RUC) {
					if (!ValidarUtil.validaFormatoNumerico(this.getTxtUsuario().trim())) {
						addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "El Documento de Identidad  debe contener solo números");
						resultado = false;
					}
				} else if (!ValidarUtil.validarFormatoEspecial(this.getTxtUsuario().trim(), true, exclusionCaracteresRaros)) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "El documento de Identidad contiene caracteres inválidos");
					resultado = false;
				} else if ( !pattern.matcher(this.email.toString()).matches()) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Formato de correo electrónico incorrecto. Ingrese un correo válido.");
					resultado = false;
				}
			}
		}		

		if (resultado) {
			Map<String, Object> params = new HashMap<>();
			params.put(ParametrosDeBusqueda.USUA_TIPO_DOC, this.getTipDocumento());
			params.put(ParametrosDeBusqueda.USUA_NRO_DOC, this.getTxtUsuario().trim());
			params.put(ParametrosDeBusqueda.USUA_CORREO, this.getEmail().trim().toUpperCase());
			params.put(ParametrosDeBusqueda.USUA_ESTADO, ParametrosDeBusqueda.INDICADOR_ACTIVO);
			
			listaUsuario = maeUsuarioService.buscar(params);
			
			if (ValidarUtil.isNullOrEmpty(listaUsuario)) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Usuario o correo electrónico no registrados");
				resultado = false;
			}
		}

		return resultado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTextoCaptcha() {
		return textoCaptcha;
	}

	public void setTextoCaptcha(String textoCaptcha) {
		this.textoCaptcha = textoCaptcha;
	}

	public String getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(String txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public void setMaeUsuarioService(MaeUsuarioService maeUsuarioService) {
		this.maeUsuarioService = maeUsuarioService;
	}

	public List<UsuarioDto> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<UsuarioDto> listaUsuario) {
		this.listaUsuario = listaUsuario;
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

	public void setCorreoService(CorreoService correoService) {
		this.correoService = correoService;
	}

	public void setCaptchaMB(CaptchaController captchaMB) {
		this.captchaMB = captchaMB;
	}

	public CaptchaController getCaptchaMB() {
		return captchaMB;
	}

	public Long getTipDocumento() {
		return tipDocumento;
	}

	public void setTipDocumento(Long tipDocumento) {
		this.tipDocumento = tipDocumento;
	}

	public List<TipoDocumentoDto> getLstTipoDoc() {
		return lstTipoDoc;
	}

	public void setLstTipoDoc(List<TipoDocumentoDto> lstTipoDoc) {
		this.lstTipoDoc = lstTipoDoc;
	}

	public void setMaeTipoDocumentoService(MaeTipoDocumentoService maeTipoDocumentoService) {
		this.maeTipoDocumentoService = maeTipoDocumentoService;
	}

	public Long getTamanio() {
		return tamanio;
	}

	public void setTamanio(Long tamanio) {
		this.tamanio = tamanio;
	}
}