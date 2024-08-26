package pe.gob.pj.administrativos.visitas.model.util;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

import pe.gob.pj.administrativos.visitas.model.client.Configuration;
import pe.gob.pj.administrativos.visitas.model.client.Property;
import pe.gob.pj.api.commons.utility.ValidarUtil;

public class ConstantesVisitas implements Serializable {

	private static final long serialVersionUID = 1L;
		
	public static final String ACTION_MANTENIMIENTO_USUARIO ="man-usuarios";
	public static final String ACTION_MANTENIMIENTO_TIPO_MOTIVO ="man-tipo-motivo";
	public static final String ACTION_MANTENIMIENTO_PUNTO_CONTROL ="man-punto-control";
	public static final String ACTION_MANTENIMIENTO_LUGAR ="man-lugares";
	public static final String ACTION_VISITAS = "visitas-principal";
	public static final String ACTION_AGREGAR_VISITAS = "visitas-agregar";
	public static final String PAGINA_CAMBIO_CLAVE = "/pages/seguridad/cambiarClave.xhtml";
	public static final String ACTION_CAMBIO_CLAVE = "seg-cambio-clave";
	public static final String PAGINA_BIENVENIDA = "/pages/bienvenida.xhtml";
	public static final String PAGINA_FORGOT_PASSWORD = "/pages/seguridad/forgotPassword.xhtml";
	public static final String PAGINA_RECUPERAR_PASSWORD = "/pages/seguridad/prForm.xhtml";
	public static final String PAGINA_LOGIN = "/pages/seguridad/login.xhtml";
	public static final String ACTION_BIENVENIDA = "bienvenida";
	public static final String ACTION_REPORTE_VISITAS = "reporte-visitas";

	/*********************** PARAMETROS DE CONSULTA PARA LAS CONFIGURACIONES DE LOS CLIENTES WS *******************************/
	public static final String CODIGO_TABLA_EXPEDIENTE					= "TBL_CONF_EXPEDIENTE";
	public static final String CODIGO_TABLA_ARANCEL						= "TBL_CONF_ARANCEL";
	public static final String CODIGO_TABLA_SIGNER						= "TBL_CONF_SIGNER";
	public static final String CODIGO_TABLA_ALFRESCO					= "TBL_CONF_ALFRESCO";
	public static final String CODIGO_TABLA_RENIEC						= "TBL_CONF_RENIEC";
	public static final String CODIGO_TABLA_PIDE						= "TBL_CONF_PIDE";
	public static final String CODIGO_TABLA_PDF							= "TBL_CONF_PDF";
	public static final String CODIGO_TABLA_EMAIL						= "TBL_CONF_EMAIL";
	public static final String CODIGO_TABLA_TERMINOS					= "TBL_CONF_TERMINOS";
	public static final String CODIGO_TABLA_COLAS						= "TBL_CONF_COLA";
	
	/*********************** MANEJO DE ERRORES *******************************/
	public static final String DIVISOR_ERROR_1 	= "[";
	public static final String DIVISOR_ERROR_2	= "]";
	public static final String DIVISOR_ERROR_3 	= ": ";
	public static final String DIVISOR_ERROR_4 	= "ERROR SINOE: ";
	public static final String NIVEL_APP_DAO		 = "DAO";
	public static final String NIVEL_APP_SERVICE	 = "SERVICE";
	public static final String NIVEL_APP_MAGBEAN = "MAGBEAN";
	public static final String ERROR_DOS_REGISTROS = "query did not return a unique result: 2";
	public static final String MSG_GENERO_ERROR_OPERACION = "Se generó un problema en la operación";
	public static final String MSG_GENERO_NO_SELECCION = "No seleccionó ningún registro";
	public static final String MSG_PERMISOS_ASIGNADOS = "El Perfil no tiene asignado ningún permiso ";
	public static final String DATOS_SESSION_NO_ENCONTRADOS = "No se encontraron datos en Sesion ";
	public static final String MSG_RESULTADO_VACIO = "No se encontraron registros ";

	/********************** ERROR EN FORMULARIOS ******************************/
	public static final String ERROR_BUSCAR = "Ocurrió un error al buscar información";
	public static final String ERROR_GRABAR = "Ocurrió un error al grabar la información";
	public static final String ERROR_GRABAR_GRUPO = "No se permite modificar el Grupo Predeterminado del Poder Judicial";
	public static final String SUCCESS_GRABAR = "Registro grabado exitosamente";
	/*Estado del Registro*/
	public static final String ACTIVO="1";
	public static final String INACTIVO="0";
	public static final String INDICADOR_CUOTA="1";

	public static final Long ESTADO_PETICION_CORRECTO = 1L;
	public static final Long ESTADO_PETICION_INCOMPLETO = 2L;
	public static final Long CODIGO_DOC_RUC = 2l;
	public static final String TIPO_DOCUMENTO_DNI_S ="1";
	public static final String TIPO_DOCUMENTO_RUC_S ="2";
	public static final Long ESTADO_PETICION_INCORRECTO = 3L;
	
	public static final Long OPCION_PRINCIPAL=-1L;
	public static final String URL_PATTERN_XHTML		= "xhtml";
	public static final String URL_PATTERN_PAGES		= "pages";
	public static final String POINT 					= ".";
	public static final String SEMICOLON 					= ";";
	public static final String PAGINA_ERROR=  "error403";
	public static final String PAGINA_NO_ENCONTRADA=  "error404";
	/*---------- ERROR ----------------*/
	public static final String ACTION_REDIRECT_NO_PAGE = "/pages/error/error404.xhtml";
	public static final String ACTION_REDIRECT_ERROR = "/pages/error/error403.xhtml";
	public static final String ACTION_FIN_SESION = "/pages/error/finSesion.xhtml";
	public static final String ACTION_ERROR = "/pages/error/error.xhtml";
	public static final String ACTION_ACTUALIZA_NAVEGADOR = "/pages/error/actualizarNavegador.xhtml";
	
	public static final String ACTION_CONSULTA_VISITA_EXTERNA = "consulta-externa-visita";
	
	public static final String CARPETA_RAIZ=  "/pages/";
	public static final String ACTION_INICIO = "/pages/seguridad/login.xhtml";

	
	public static final String PAGINA_SELECCION_PERFIL_ENTIDAD = "seleccionePerfilEntidad.xhtml";
	public static final String USUARIO_BLOQUEADO="1";
	public static final String VERIFICA_CAMPO_NO_VACIO="SI";
	public static final String LETRA_VACIO="";
	
	public static final Long LONG_CERO	 	= 0L;
	public static final Long  idPerfilAdmin	 	= 1L;
	public static final String  CORREO_TOQUEN_GENERADO	 	= "0";
	public static final String  CORREO_NO_ENVIADO	 	= "2";
	public static final String  CLAVE_ACTUALIZADO	 	= "1";
	
	/*CONSTANTES RENIEC*/
	
	// 2. WEB SERVICE RENIEC
	public static final String WSRENIEC_ENDPOINT=Configuration.getInstance().getProperty(Property.WSRENIEC_ENDPOINT);
	public static final String WSRENIEC_DNI=Configuration.getInstance().getProperty(Property.WSRENIEC_DNI);
	public static final String WSRENIEC_MOTIVO=Configuration.getInstance().getProperty(Property.WSRENIEC_MOTIVO);
	public static final String WSRENIEC_USERNAME=Configuration.getInstance().getProperty(Property.WSRENIEC_USERNAME);
	public static final String WSRENIEC_PASSWORD=Configuration.getInstance().getProperty(Property.WSRENIEC_PASSWORD);
	public static final String WSRENIEC_CODIGO_CLIENTE=Configuration.getInstance().getProperty(Property.WSRENIEC_CODIGO_CLIENTE);
	public static final String WSRENIEC_CODIGO_ROL=Configuration.getInstance().getProperty(Property.WSRENIEC_CODIGO_ROL);
	public static final String RENIEC_TIPO_BUSQUEDA_NOMBRE = "1";
	public static final String RENIEC_TIPO_BUSQUEDA_DNI = "2";
	public static final String RENIEC_USUARIO_CONSULTA_DEFAULT = "uc_visitas";
	public static final String RENIEC_METODO_AUTENTIFICACION = "/api/authenticate";
	public static final String RENIEC_METODO_CONSULTA = "/consultar/persona";
	public static final String CODIGO_OPERACION_CORRECTA = "0000";
	public static final String VALOR_DEFECTO_ESPACIO_EN_BLANCO = " ";
	public static final String STATUS_BAD_REQUEST = "400 Bad Request";
	public static final String HEADER_USERNAME_RENIEC_REST = "username";
	public static final String HEADER_PASSWORD_RENIEC_REST ="password";
	public static final String HEADER_CODIGO_CLIENTE_RENIEC_REST ="codigoCliente";
	public static final String HEADER_CODIGO_APLICATIVO_RENIEC_REST ="codigoRol";
	public static final String HEADER_PARAMETERS_HTTP_RENIEC_REST ="parameters";
	
	
	
	// 3. WEB SERVICE PIDE
	public static final String PARAM_CONFIG_USER_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_USER_PIDE);
	public static final String PARAM_CONFIG_PASS_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_PASS_PIDE);
	public static final String PARAM_CONFIG_ENDPOINT_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_ENDPOINT_PIDE);
	public static final String PARAM_CONFIG_TIMEOUT_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_TIMEOUT_PIDE);
	public static final String PARAM_CONFIG_CODIGO_APLICATIVO_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_CODIGO_APLICATIVO_PIDE);
	public static final String PARAM_CONFIG_CODIGO_ROL_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_CODIGO_ROL_PIDE);
	public static final String PARAM_CONFIG_CODIGO_CLIENTE_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_CODIGO_CLIENTE_PIDE);
	// 4. CORREO	
	public static final String SMTP_IP = Configuration.getInstance().getProperty(Property.SMTP_IP);
	public static final String SMTP_PROTOCOLO = Configuration.getInstance().getProperty(Property.SMTP_PROTOCOLO);
	public static final String SMTP_PUERTO = Configuration.getInstance().getProperty(Property.SMTP_PUERTO);
	public static final String SMTP_CORREO_EMISOR = Configuration.getInstance().getProperty(Property.SMTP_CORREO_EMISOR);	
	public static final String SMTP_LOCALHOST=Configuration.getInstance().getProperty(Property.SMTP_LOCALHOST);
	/*constantes Siau*/
	public static final String URL_APP = Configuration.getInstance().getProperty(Property.URL_APP);
	    
	    
	/*Reporte*/
    public static final String IMAGE_REPORT_PATH 		= new StringBuilder("resources").append(File.separator)
			.append("images").append(File.separator).toString();
    public static final String REPORT_PATH 				= new StringBuilder("pages").append(File.separator)
			.append("reporte").append(File.separator).append("plantilla").append(File.separator).toString();
    public static final String RUTA_RECUPERAR_CONTRASENIA 	="/pages/seguridad/recuperacion/recuperarClaveUsuario.xhtml";
    public static final String TITULO_RECUPERAR_CONTRASENIA= "Recuperación de contraseña - SICAU";
    public static final String LOGO_PJ= "logo-pj.png";
    
    
    
    /*General */
	public static Long  NUM_DOC_DNI 		= 1L;
	public static Long  NUM_DOC_RUC 		= 2L;
	public static Long  NUM_DOC_CE  		= 3L;
	public static Long  NUM_DOC_PAS  		= 4L;
	
	public static Long  tamanioDNI 		= 8L;
	public static Long  tamanioRUC 	= 11L;
	public static Long  tamanioMax 	= 9L;
	public static Long  tamanioCE 		= 12L;
	public static Long  tamanioPAS 		= 12L;
	
	public static final boolean TRUE 		= true;
	public static final boolean FALSE 		= false;
	public static final String INDICADOR_GRUPO 	= "1";
	public static final String plazoUrlActivaHrs = "24";
	
	/*REPORTES */
	public static final short FORMATO_REPORTE_PDF 		= 1;
	public static final short FORMATO_REPORTE_XLS 		= 2;
	
	public static final short PRESENTACION_DATOS 		= 1;
	public static final short PRESENTACION_GRAFICO 		= 2;
	public static final String EXTENSION_FORMATO_PDF 	= "pdf";
	public static final String EXTENSION_FORMATO_XLS 	= "xls";
	public static final String APPLICATION_PDF 			= "application/pdf";
	public static final String APPLICATION_XLS 			= "application/xls";
	
	/*AGRUPAMIENTO REPORTES */
	
	public static final String AGRUPA_OPCION="3";
	public static final String AGRUPA_SISTEMA="2";
	public static final String AGRUPA_ENTIDAD="1";
	
	/*********************** ESTADOS OPERACION CORRECTA *******************************/
	public static final String CODIGO_OPERACION_INCORRECTA="E00";
	public static final String CODIGO_OPERACION_CORRECTA_WS="0000";
	
	/*********************** CODIGO TIPO DE DOCUMENTO PARA AGREGAR TERCERO / OTROS *******************************/
	public static final String TIPO_DOCUMENTO_DNI ="1";
	public static final String TIPO_DOCUMENTO_RUC ="2";
	public static final String TIPO_DOCUMENTO_CE ="3";
	
	public static final String STRING_UNO	 		= "1";
	public static final String STRING_DOS	 		= "2";
	public static final String STRING_REG	 		= "30";
	
	/*---------------------------- REDIRECT REPORTES -------------------------------*/
	public static final String REDIRECT_REPORTE_SOLICITUDES_ENTIDAD			= "reporte-solicitudes-entidad";
	public static final String REDIRECT_REPORTE_PETICIONES			= "reporte-peticiones";

	/*EQUIVALENCIA TIPO DOCUMENTO SIGA*/
	
	public static final String DNI_SIGA  ="01";
	public static final String CARNET_EXTRANJERIA_SIGA  ="04";
	public static final String PASAPORTE_SIGA  ="10";
	
	/*IR A VISITAS PRINCIPAL*/
	
	
	public static final String IR_PRINCIPAL_VISITAS		= "/pages/configuracion/visitas/principalVisitas.xhtml";
	public static final String IR_CAMBIAR_CLAVE		= "/pages/seguridad/cambiarClave.xhtml";
	/* PERFILES DE USUARIO*/
	
	public static Long  PERFIL_ADMINISTRADOR_SISTEMA = 1L;
	public static Long  PERFIL_ADMINISTRADOR_CORTE = 2L;
	public static Long  PERFIL_OPERADOR = 3L;
	
	public static String  DESCRIPCION_PERFIL_ADMINISTRADOR_CORTE = "ADMINISTRADOR DE CORTE";
	public static String  DESCRIPCION_PERFIL_OPERADOR = "OPERADOR";

	public static String NOMBRE_REPORTE_VISITAS="reporteVisitas";	
		
	
	public static boolean validaValor(Map.Entry<String, Object> map, String campo){
		boolean estado = false;
		if( map.getKey().equals(campo) ){
			if( map.getValue() instanceof String  && !ValidarUtil.isNull( (String)map.getValue() )){
				estado = true;
			}else if( map.getValue() instanceof Long  && !ValidarUtil.isNull( map.getValue().toString() )){
				estado = true;
			}
		}
		return estado;
	}
	public static String devuelveMensaje(String origenMensaje){
		if(  origenMensaje==null){
			 return "";
		}
		String descripcion="";
		
		//AUTENTICACION("A"), AUTORIZACION("Z"), VALIDACION_CUOTA("C"), VALIDACION_ENTIDAD_INCORRECTA("E"), EJECUCIUON_METODO_WS("M");
			if(origenMensaje.equals("A")){
				descripcion="AUTENTICACION";
			} else if(origenMensaje.equals("Z")){
				descripcion="AUTORIZACION";
			}else if(origenMensaje.equals("C")){
					descripcion="VALIDACION DE CUOTA";
			}else if(origenMensaje.equals("E")){
				descripcion="VALIDACION DE ENTIDAD";
			}else if(origenMensaje.equals("M")){
				descripcion="EJECUCION DE METODO";
			}
		
		return descripcion;
	}
	
	//TODO CRC Parametro RESETEO_CLAVE
	public static final String RESETEO_CLAVE = Configuration.getInstance().getProperty(Property.RESETEO_CLAVE);
	//TODO 20180621 MEU
	public static final String CONSULTA_SUNAT = "https://e-consultaruc.sunat.gob.pe/cl-ti-itmrconsruc/jcrS00Alias";	
	public static final String TIPO_PERSONA_NATURAL = "PN";
	public static final String TIPO_PERSONA_JURIDICA = "PJ";
	public static final String MENSAJE_NO_ENCONTRADO = "No se encontró el RUC buscado en los servicios de SUNAT.";
	public static final String MENSAJE_NO_ENCONTRADO_BASE = "No se encontró la razon social en la base de datos.";
	public static final String TIPO_BUSQUEDA_DNI = "DNI";
	public static final String TIPO_BUSQUEDA_NYA = "NYA";
	public static final String PERSONA_NATURAL = "PERSONA NATURAL";
	public static final String RESTRICCION = "N";
	
	public static final int PLATAFORMA_REGISTRO=1;
	public static final int PLATAFORMA_JUEZ=2;
}
