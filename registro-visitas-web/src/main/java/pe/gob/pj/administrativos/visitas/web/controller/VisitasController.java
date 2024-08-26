package pe.gob.pj.administrativos.visitas.web.controller;

import java.awt.image.BufferedImage;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.gob.pj.administrativos.visitas.model.bean.AuditoriaReniec;
import pe.gob.pj.administrativos.visitas.model.bean.ConsultadoReniec;
import pe.gob.pj.administrativos.visitas.model.bean.Mensaje;
import pe.gob.pj.administrativos.visitas.model.bean.PaginaReniec;
import pe.gob.pj.administrativos.visitas.model.bean.ParamConfigPideBean;
import pe.gob.pj.administrativos.visitas.model.bean.ParamConfigReniecBean;
import pe.gob.pj.administrativos.visitas.model.bean.Persona;
import pe.gob.pj.administrativos.visitas.model.bean.PersonaReniecBean;
import pe.gob.pj.administrativos.visitas.model.bean.RequestReniec;
import pe.gob.pj.administrativos.visitas.model.bean.ResponseConsultaReniecWs;
import pe.gob.pj.administrativos.visitas.model.bean.ResponseConsultaReniecWsData;
import pe.gob.pj.administrativos.visitas.model.bean.ResponseConsultaReniecWsNombre;
import pe.gob.pj.administrativos.visitas.model.bean.ResponseConsultaReniecWsPersona;
import pe.gob.pj.administrativos.visitas.model.client.ConstantesConfig;
import pe.gob.pj.administrativos.visitas.model.dto.ColaboradorDto;
import pe.gob.pj.administrativos.visitas.model.dto.EntidadDto;
import pe.gob.pj.administrativos.visitas.model.dto.TipoDocumentoDto;
import pe.gob.pj.administrativos.visitas.model.dto.TipoMotivoDto;
import pe.gob.pj.administrativos.visitas.model.dto.TrabajadorDto;
import pe.gob.pj.administrativos.visitas.model.dto.VisitaDto;
import pe.gob.pj.administrativos.visitas.service.MaeColaboradorService;
import pe.gob.pj.administrativos.visitas.service.MaeEntidadService;
import pe.gob.pj.administrativos.visitas.service.MaeTipoDocumentoService;
import pe.gob.pj.administrativos.visitas.service.MaeTipoMotivoService;
import pe.gob.pj.administrativos.visitas.service.MovVisitaService;
import pe.gob.pj.administrativos.visitas.service.PideWsService;
import pe.gob.pj.administrativos.visitas.service.ReniecWsService;
import pe.gob.pj.administrativos.visitas.service.TrabajadorSigaService;
import pe.gob.pj.administrativos.visitas.service.VUnidadEjecutoraService;
import pe.gob.pj.administrativos.visitas.model.util.*;
import pe.gob.pj.api.commons.constants.Constantes;
import pe.gob.pj.api.commons.constants.sicau.ConstantesSicau;
import pe.gob.pj.api.commons.utility.CommonsUtilities;
import pe.gob.pj.api.commons.utility.ConvertirUtil;
import pe.gob.pj.api.commons.utility.FechaUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;
import pe.gob.pj.pidews.type.RequestConsultarDatosPrincipalesRUCType;
import pe.gob.pj.pidews.type.ResponseConsultarDatosPrincipalesRUCType;
import pe.gob.pj.pidews.ws.Auditoria;
import pe.gob.pj.pidews.ws.Seguridad;
import pe.gob.pj.ws.client.reniec.consultas.wsreniec.ConsultaReniec;
import pe.gob.pj.ws.client.reniec.consultas.wsreniec.ConsultaReniecResponse;

@ViewScoped
@ManagedBean(name = "visitasMB")
public class VisitasController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(VisitasController.class.getName());
	private static final String CODIFICADOR_UTF8=";charset=utf-8";

	@ManagedProperty(value = "#{visitaService}")
	private MovVisitaService movVisitaService;

	@ManagedProperty(value = "#{reniecWsService}")
	private ReniecWsService reniecWsService;

	@ManagedProperty(value = "#{maeTipoDocumentoService}")
	private MaeTipoDocumentoService maeTipoDocumentoService;

	@ManagedProperty(value = "#{colaboradorService}")
	private MaeColaboradorService maePersonaService;

	@ManagedProperty(value = "#{trabajadorSigaService}")
	private TrabajadorSigaService trabajadorSigaService;

	@ManagedProperty(value = "#{entidadService}")
	private MaeEntidadService maeEntidadService;

	@ManagedProperty(value = "#{unidadEjecutoraService}")
	private VUnidadEjecutoraService vUnidadEjecutoraService;

	@ManagedProperty(value = "#{tipoMotivoService}")
	private MaeTipoMotivoService maeTipoMotivoService;

	@ManagedProperty(value="#{pideWsService}")
	private PideWsService pideWsService;
	
	private ColaboradorDto visitado;
	private EntidadDto entidad;
	private List<TipoDocumentoDto> listaTipoDocumentoVisitante;
	private List<TipoDocumentoDto> listaTipoDocumentoVisitado;
	private TipoDocumentoDto tipoDocumentoVisitante;
	private TipoDocumentoDto tipoDocumentoVisitado;
	private List<VisitaDto> listaVisitas;
	//private ColaboradorDto persona;
	private VisitaDto visita;
	private List<TrabajadorDto> listaTrabajadores;
	
	//private String nroDocumentoVisitante;
	//private String nroDocumentoVisitado;
	//private String nombreCompletoVisitado;
	
	private boolean flgDisableVisitado;
	private EntidadDto entidadSeleccionada;
	private List<EntidadDto> listaEntidades;
	private Integer lengthNumeroDocumento;
	private Integer lengthNumeroDocumentoTrabajador;
	private String trabajadorNombreCompleto;
	private String trabajadorNumeroDocumento;
	private TrabajadorDto trabajadorSeleccionado;
	private TipoMotivoDto motivo;
	private List<TipoMotivoDto> listaTipoMotivo;
	private String equivalencia;
	private List<TipoDocumentoDto> listaTipoDocumentoTrabajador;
	
	//private String filtroEstado;
	private String filtroVisitante;
	private Date filtroFechaInicio;
	private Date filtroFechaFinal;
	private String filtroNroDocumento;
	
	private String fechaMaxima;
	private String fechaMinima;
	private String mensajes;
	private VisitaDto visitaSeleccionada;
	
	//private EntidadDto entidadAgregar;
	private String filtroRazonSocialEntidad;
	private String filtroRucEntidad;
	private String filtroRazonSocialEntidadPide;
	private String filtroRucEntidadPide;
	private EntidadDto entidadPide;
	private boolean datosVisitanteDesHabilitado;
	private boolean documentoVisitanteDesHabilitado;
	private String trabajadorCargo;
	private String observacionesSalida;
	/////////////////////////////////////////////////////////
    private String tipoPersona;
    private String urlConsultaSUNAT;
    private String mensajeNoEncontrado;
	private PersonaReniecBean visitanteSeleccionado;
	private List<PersonaReniecBean> listaVisitantes;
    private String visitanteNumeroDocumento;
    private String visitanteNombres;
    private String visitanteApellidoPaterno;
    private String visitanteApellidoMaterno;
    private String tipoBusqVisitante;

	@PostConstruct
	public void init() {
		try {
			boolean navegadorPermitido = validarNavegador();
			if( navegadorPermitido ) {
				if( !verificarPermiso( ConstantesVisitas.ACTION_VISITAS)  ){
					this.noTienePermisos();
				}else{
			
					inicializarVariables();
					filtroFechaInicio = FechaUtil.getHoySinHora();
					filtroFechaFinal = FechaUtil.getHoySinHora();
					buscarVisitas();
				}
			}
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
		}
	}
	
	/** 
	* Descripción: Permite consultar el registro de visitas
	* @param No aplica
	* @return No aplica.
	* @exception Manejo de errores no clasificados.
	*/
	public void buscarVisitas() {
		try {

			Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();

			if (!ValidarUtil.isNullOrEmpty(filtroFechaInicio)) {
				parametrosBusqueda.put("fecha-inicio", FechaUtil.irInicioDia(filtroFechaInicio));
			}

			if (!ValidarUtil.isNullOrEmpty(filtroFechaFinal)) {
				parametrosBusqueda.put("fecha-fin", FechaUtil.irFinalDia(filtroFechaFinal));
			}

			if (!ValidarUtil.isNullOrEmpty(filtroVisitante)) {
				parametrosBusqueda.put("visitante", filtroVisitante.trim().toUpperCase());
			}
			
			if (!ValidarUtil.isNullOrEmpty(filtroNroDocumento)) {
				parametrosBusqueda.put("nrodocumento", filtroNroDocumento.trim());
			}
			
			parametrosBusqueda.put(ParametrosDeBusqueda.VIS_ID_LOCAL, getSessionController().getPuntoControlSession().getnIdLocal());
			parametrosBusqueda.put(ParametrosDeBusqueda.VIS_USUARIO, getSessionController().getUsuarioSession().getnIdUsuario());
			//TODO CCRV Carga el anio actual
			parametrosBusqueda.put("anio", obtenerAnioActual());
			///////////////////////////////////////////////////////////////////////////////////
			listaVisitas = movVisitaService.listar(parametrosBusqueda);
		} catch (Exception excepcion) {
			logger.error(excepcion.getMessage());
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
			addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorBuscarVisitas);
			excepcion.printStackTrace();
		}
	}

	public void inicializarVariables() {

		visitado = new ColaboradorDto();
		visita = new VisitaDto();
		tipoDocumentoVisitante = new TipoDocumentoDto();
		tipoDocumentoVisitado = new TipoDocumentoDto();
		entidad = new EntidadDto();
		entidadSeleccionada = new EntidadDto();
		lengthNumeroDocumento = 20;
		lengthNumeroDocumentoTrabajador=20;
		trabajadorSeleccionado = new TrabajadorDto();
		listaTipoDocumentoVisitado = new ArrayList<>();
		entidadPide = new EntidadDto(); 
		flgDisableVisitado=true;
		datosVisitanteDesHabilitado=true;
		documentoVisitanteDesHabilitado=true;
		motivo = new TipoMotivoDto();
		
		this.listaTipoDocumentoVisitante = buscarTipoDocumento();
		this.listaTipoDocumentoVisitado = buscarTipoDocumento();
		this.listaTipoMotivo = buscarTipoMotivo();
		
		//TODO 20180621 MEU
		tipoPersona=ConstantesVisitas.TIPO_PERSONA_JURIDICA;
		urlConsultaSUNAT = ConstantesVisitas.CONSULTA_SUNAT;
	}
	
	public List<TipoMotivoDto> buscarTipoMotivo() {
		List<TipoMotivoDto> lista = new ArrayList<>();
		try {
			lista = maeTipoMotivoService.listar(ConstantesVisitas.ACTIVO);
			
		} catch (Exception excepcion) {
			
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
			addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorBuscarTipoMotivo);
			
		}

		return lista;

	}

	public List<TipoDocumentoDto> buscarTipoDocumento() {

		List<TipoDocumentoDto> lista = new ArrayList<>();

		try {
			
			Map<String, Object> parametrosBusqueda = new HashMap<>();			
			List<Long> listaTipoDocDefecto = new ArrayList<>();
			listaTipoDocDefecto.add(ConstantesVisitas.NUM_DOC_DNI);
			listaTipoDocDefecto.add(ConstantesVisitas.NUM_DOC_CE);
			listaTipoDocDefecto.add(ConstantesVisitas.NUM_DOC_PAS);
			parametrosBusqueda.put(ParametrosDeBusqueda.TIPO_DOCU_LIST, listaTipoDocDefecto);
			parametrosBusqueda.put(ParametrosDeBusqueda.ESTADO_REGISTRO, ConstantesVisitas.ACTIVO);
			
			lista = maeTipoDocumentoService.buscar(parametrosBusqueda);

		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
			addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorBuscarTipoDocumento);
		}

		return lista;
	}



	public String nuevo() {
		visita = new VisitaDto();
		InputStream dbStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/no-photo.png");
		StreamedContent sinFoto = new DefaultStreamedContent(dbStream, "image/png");		
		visita.setFoto(sinFoto);
		
		return NavigationVisitasConstantes.VISITAS_AGREGAR;

	}

	public void abrirRegistrarSalida() {
		PrimeFaces.current().executeScript("PF('registrarSalida').show();");
		
	}
	public  void cancelarRegistrarSalida() {
		PrimeFaces.current().executeScript("PF('registrarSalida').hide();");
	}
	/*
	public void buscarSiga() {
		PrimeFaces.current().executeScript("PF('dlgSiga').show();");
	}*/

	public void buscarReniec() {
		this.visita.setNumeroDocumento("");
		this.visita.setNombres("");
		this.visita.setApellidoMaterno("");
		this.visita.setApellidoPaterno("");		
		this.visita.setTipoDocumento(tipoDocumentoVisitante);

		if (this.getTipoDocumentoVisitante().getIdTipoDocumento() == Long.parseLong(ConstantesVisitas.TIPO_DOCUMENTO_DNI_S)) {
			lengthNumeroDocumento = 8;
			datosVisitanteDesHabilitado = true;
			documentoVisitanteDesHabilitado = true;
		} else {
			lengthNumeroDocumento = 20;
			datosVisitanteDesHabilitado = false;
			documentoVisitanteDesHabilitado = false;
		}
	}
/*
	public void buscarPersonaReniec() {

		try {
			
			if (this.getTipoDocumentoVisitante().getIdTipoDocumento() == Long.parseLong(ConstantesVisitas.TIPO_DOCUMENTO_DNI_S)) {
				
				if (this.visita.getNumeroDocumento().trim().length()==8) {

				ParamConfigReniecBean beanParam = new ParamConfigReniecBean();
				beanParam.setEndpoint(ConstantesVisitas.WSRENIEC_ENDPOINT);
				beanParam.setDniConsultante(ConstantesVisitas.WSRENIEC_DNI);
				beanParam.setTimeout(ConstantesVisitas.WSRENIEC_TIMEOUT);
	
				ConsultaReniec consultaReniecRequest = new ConsultaReniec();
				consultaReniecRequest.setReqDni(this.visita.getNumeroDocumento());
				consultaReniecRequest.setReqDniConsultante(beanParam.getDniConsultante());
				consultaReniecRequest.setReqUsuario(String.valueOf(getSessionController().getUsuarioSession().getnIdUsuario()));
				consultaReniecRequest.setReqIp(CommonsUtilities.getRemoteIpAddress());
				consultaReniecRequest.setReqTrama(ConstantesVisitas.LETRA_VACIO);
				consultaReniecRequest.setReqTipoConsulta(ConstantesVisitas.STRING_DOS);
				consultaReniecRequest.setReqNombres(ConstantesVisitas.LETRA_VACIO);
				consultaReniecRequest.setReqApellidoPaterno(ConstantesVisitas.LETRA_VACIO);
				consultaReniecRequest.setReqApellidoMaterno(ConstantesVisitas.LETRA_VACIO);
				consultaReniecRequest.setReqNroRegistros(ConstantesVisitas.LETRA_VACIO);
				consultaReniecRequest.setReqGrupo(ConstantesVisitas.LETRA_VACIO);
				consultaReniecRequest.setReqDniApoderado(ConstantesVisitas.LETRA_VACIO);
				consultaReniecRequest.setReqTipoVinculoApoderado(ConstantesVisitas.LETRA_VACIO);
	
				ConsultaReniecResponse responseReniec = null;
	
				responseReniec = reniecWsService.consultaReniec(Utilitarios.getPortReniec(beanParam), consultaReniecRequest);
	
				if (responseReniec != null) {
					if (!ConstantesVisitas.CODIGO_OPERACION_CORRECTA_WS.equals(responseReniec.getResCodigo())) {
						flgDisableVisitado = false;
						visita = new VisitaDto();
						tipoDocumentoVisitante = new TipoDocumentoDto();
						visita.setTipoDocumento(tipoDocumentoVisitante);
						addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.dniNoEncontradoReniec);
						PrimeFaces.current().ajax().update("listaMensajes");
						return;
					} else {
						
					
						
						PersonaReniecBean personaBean = Utilitarios.cargarPersonaReniecResponseBean(responseReniec);
						
						if(ValidarUtil.isNullOrEmpty(personaBean.getFecFall())) {
							this.visita.setApellidoPaterno(personaBean.getApePat());
							this.visita.setApellidoMaterno(personaBean.getApeMat());
							this.visita.setNombres(personaBean.getNombres());
							this.visita.setFoto(generaFoto(personaBean.getFoto()));
							this.visita.setNumeroDocumento(personaBean.getNroDNI());
							tipoDocumentoVisitante.setIdTipoDocumento(ConstantesVisitas.NUM_DOC_DNI);
							this.visita.setTipoDocumento(tipoDocumentoVisitante);
							flgDisableVisitado = true;
						}else {
							this.visita.setNumeroDocumento("");
							this.visita.setNombres("");
							this.visita.setApellidoMaterno("");
							this.visita.setApellidoPaterno("");		
							addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaPersonaFallecida);
							PrimeFaces.current().ajax().update("listaMensajes");
						}
					}
				}
			
			}else {
				this.visita.setNumeroDocumento("");
				this.visita.setNombres("");
				this.visita.setApellidoMaterno("");
				this.visita.setApellidoPaterno("");		
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaDNI8Caracteres);
				PrimeFaces.current().ajax().update("listaMensajes");
			}
				
			}

		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
			addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorBuscarReniec);
			PrimeFaces.current().ajax().update("listaMensajes");
		}
	}
*/
	public StreamedContent generaFoto(byte[] data) {
		StreamedContent foto = null;
		try {
			InputStream bis = new ByteArrayInputStream(data);
			BufferedImage bImage2 = ImageIO.read(bis);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(bImage2, "jpg", os);
			foto = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/jpg");

		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
			addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorCargarFoto);
		}
		return foto;
	}

	public void restaurarFormularioEntidad() {
		PrimeFaces.current().resetInputs("frmEntidad");
	}
	
	private Seguridad getSeguridadPide(ParamConfigPideBean paramConfigPideBean){
		Seguridad seguridad = new Seguridad();
		seguridad.setCodigoAplicativo(paramConfigPideBean.getCodigoAplicativo());
		seguridad.setCodigoCliente(paramConfigPideBean.getCodigoCliente());
		seguridad.setCodigoRol(paramConfigPideBean.getCodigoRol());
		
		return seguridad;
	}
	
	private Auditoria getAuditoriaPide(){
		Auditoria auditoria = new Auditoria();		
		auditoria.setIpPc(CommonsUtilities.getRemoteIpAddress());
		auditoria.setMacAddressPc(CommonsUtilities.getMAC());
		auditoria.setNombreSo("");
		auditoria.setPcName(CommonsUtilities.getPCName());
		auditoria.setUsuarioRed(String.valueOf(getSessionController().getUsuarioSession().getnIdUsuario()));
		auditoria.setUsuarioSis(String.valueOf(getSessionController().getUsuarioSession().getnIdUsuario()));		
		return auditoria;
	}

	public void buscarEntidades() {

		boolean val = true;

		if ((filtroRazonSocialEntidad!=null &&filtroRazonSocialEntidad.equals("") ||
		     filtroRazonSocialEntidad==null)&&
			(filtroRucEntidad!=null &&filtroRucEntidad.equals("") ||
			filtroRucEntidad==null)) {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaAlMenosUnFiltroEntidad);
			val = false;
			return;
		}

		if (val) {
			try {
				entidadSeleccionada = null;
				EntidadDto entidad = new EntidadDto();
				entidad.setRuc(filtroRucEntidad);
				entidad.setRazonSocial(filtroRazonSocialEntidad.trim().toUpperCase());
				
				Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
				
				
				if (!ValidarUtil.isNullOrEmpty(entidad.getRuc())) {
					parametrosBusqueda.put(ParametrosDeBusqueda.EN_RUC,"%"+entidad.getRuc()+"%" );
				}
				
				if (!ValidarUtil.isNullOrEmpty(entidad.getRazonSocial())) {
					parametrosBusqueda.put(ParametrosDeBusqueda.EN_RAZSO, "%"+entidad.getRazonSocial()+"%");
				}
				
				parametrosBusqueda.put(ParametrosDeBusqueda.EN_ESTADO, ConstantesVisitas.ACTIVO);
				
				try {
					this.listaEntidades = maeEntidadService.listar(parametrosBusqueda);

				}
				catch(Exception e) {
					addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorBuscarEntidad);
					return;
				}
				
				if(listaEntidades==null||listaEntidades.isEmpty()) {
					if (!verificarRUC(filtroRucEntidad)) {						
						if( ValidarUtil.isNullOrEmpty(filtroRucEntidad) ) {
							mensajeNoEncontrado=ConstantesVisitas.MENSAJE_NO_ENCONTRADO_BASE;
							PrimeFaces.current().executeScript("PF('dlgMensajeEntidad').show();");
						}
					}else {
						//PrimeFaces.current().executeScript("PF('dlgAgregarEntidad').show();");
					}
				}
			} catch (Exception excepcion) {
				logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
						Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
			}
		}
	}
	
	public void buscarEntidad() {
		entidadSeleccionada=null;
		listaEntidades = null;
		PrimeFaces.current().executeScript("PF('dlgEntidad').show();");
		filtroRazonSocialEntidad="";
		filtroRucEntidad="";
	}
	
	private boolean verificarRUC(String ruc) {
		
		boolean respuesta = Boolean.FALSE;
		try{
			if( !ValidarUtil.isNullOrEmpty(ruc) ) {
				ParamConfigPideBean entidadParametros = new ParamConfigPideBean();
				entidadParametros.setCodigoAplicativo(ConstantesConfig.PARAM_CONFIG_CODIGO_APLICATIVO_PIDE);
				entidadParametros.setCodigoCliente(ConstantesConfig.PARAM_CONFIG_CODIGO_CLIENTE_PIDE);
				entidadParametros.setCodigoRol(ConstantesConfig.PARAM_CONFIG_CODIGO_ROL_PIDE);
				entidadParametros.setEndpoint(ConstantesConfig.PARAM_CONFIG_ENDPOINT_PIDE);
				entidadParametros.setPass(ConstantesConfig.PARAM_CONFIG_PASS_PIDE);
				entidadParametros.setTimeout(ConstantesConfig.PARAM_CONFIG_TIMEOUT_PIDE);
				entidadParametros.setUser(ConstantesConfig.PARAM_CONFIG_USER_PIDE);
				
				RequestConsultarDatosPrincipalesRUCType requestDatosPrincipalesRUC = new RequestConsultarDatosPrincipalesRUCType();
				requestDatosPrincipalesRUC.setCRUC(ruc);
				ResponseConsultarDatosPrincipalesRUCType responsePide = pideWsService.consultarDatosPrincipalesRUC(Utilitarios.getPortPide(entidadParametros), getSeguridadPide(entidadParametros), getAuditoriaPide(), requestDatosPrincipalesRUC);
	
				if(responsePide != null && StringUtils.isNotBlank(responsePide.getDatosPrincipalesRUC().getXNombre())){
					if(responsePide.getMensaje() != null && ConstantesVisitas.CODIGO_OPERACION_CORRECTA_WS.equals(responsePide.getMensaje().getCodigo())){
						
						entidad.setRazonSocial(responsePide.getDatosPrincipalesRUC().getXNombre().trim());
						entidad.setRuc(responsePide.getDatosPrincipalesRUC().getCNumruc());
						entidadPide.setRazonSocial(responsePide.getDatosPrincipalesRUC().getXNombre().trim());
						entidadPide.setRuc(responsePide.getDatosPrincipalesRUC().getCNumruc());
						///////////////////////////////////////////////////////////////////////////////////////
						entidadPide.setEstado(ConstantesVisitas.ACTIVO);
						entidadPide.setFechaRegistro(FechaUtil.currentJavaUtilDate());
						entidadPide.setxIpOperacion(CommonsUtilities.getRemoteIpAddress());
						entidadPide.setfOperacion(FechaUtil.currentJavaUtilDate());
						entidadPide.setnUsuarioOperacion(getSessionController().getUsuarioSession().getnIdUsuario());
						EntidadDto entidadnueva= maeEntidadService.ingresar(entidadPide);
						//this.entidad = (EntidadDto) BeanUtils.cloneBean(entidadnueva);
						this.listaEntidades.add(entidadnueva);
						///////////////////////////////////////////////////////////////////////////////////////
						respuesta = Boolean.TRUE;
					}else{
						addWarnMessage(Constantes.mensajeAdvertenciaTitulo, responsePide.getMensaje().getDescripcion());
					}
				}else{
					//addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.noInformacionEntidadRuc);
					mensajeNoEncontrado=ConstantesVisitas.MENSAJE_NO_ENCONTRADO;
					PrimeFaces.current().executeScript("PF('dlgMensajeEntidad').show();");
				}
			}
			
		}catch(Exception excepcion){					
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion) );
		}
		return respuesta;
	}

	
	public void agregarEntidadPide() {
		
		try {
			
			Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
			
			
			if (!ValidarUtil.isNullOrEmpty(entidadPide.getRuc())) {
				parametrosBusqueda.put(ParametrosDeBusqueda.EN_RUC,"%"+entidad.getRuc()+"%" );
			}
			
			if (!ValidarUtil.isNullOrEmpty(entidadPide.getRazonSocial())) {
				parametrosBusqueda.put(ParametrosDeBusqueda.EN_RAZSO, "%"+entidadPide.getRazonSocial()+"%");
			}
			
			parametrosBusqueda.put(ParametrosDeBusqueda.EN_ESTADO, ConstantesVisitas.ACTIVO);
		
			List<EntidadDto> lista=maeEntidadService.listar(parametrosBusqueda);
			
			if (lista!=null &&!lista.isEmpty()) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaEntidadExiste);
				return;
			}else {
				entidadPide.setEstado(ConstantesVisitas.ACTIVO);
				entidadPide.setFechaRegistro(FechaUtil.currentJavaUtilDate());
				entidadPide.setxIpOperacion(CommonsUtilities.getRemoteIpAddress());
				entidadPide.setfOperacion(FechaUtil.currentJavaUtilDate());
				entidadPide.setnUsuarioOperacion(getSessionController().getUsuarioSession().getnIdUsuario());
				EntidadDto entidadnueva= maeEntidadService.ingresar(entidadPide);
				this.entidad = (EntidadDto) BeanUtils.cloneBean(entidadnueva);
				PrimeFaces.current().executeScript("PF('dlgAgregarEntidad').hide();");
				PrimeFaces.current().executeScript("PF('dlgEntidad').hide();");
			
			}
			
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
			addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.advertenciaEntidadExiste);
			PrimeFaces.current().executeScript("PF('dlgAgregarEntidad').hide();");
			PrimeFaces.current().executeScript("PF('dlgEntidad').hide();");
		}
	}
	
	public void seleccionarEntidad() {
		
		try {
			this.entidad = (EntidadDto) BeanUtils.cloneBean(entidadSeleccionada);
			PrimeFaces.current().executeScript("PF('dlgAgregarEntidad').hide();");
			PrimeFaces.current().executeScript("PF('dlgEntidad').hide();");
			
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
			addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.advertenciaEntidadExiste);
			PrimeFaces.current().executeScript("PF('dlgAgregarEntidad').hide();");
			PrimeFaces.current().executeScript("PF('dlgEntidad').hide();");
		}
	}
	
	public void buscarTrabajador() {
		
		trabajadorSeleccionado=null;
		listaTrabajadores = null;
		listaTipoDocumentoTrabajador = null;
		equivalencia = "";
		this.listaTipoDocumentoTrabajador = buscarTipoDocumento();
		trabajadorNumeroDocumento = "";
		trabajadorNombreCompleto = "";
		
		PrimeFaces.current().executeScript("PF('dlgTrabajador').show();");
	}

	public void restaurarFormularioTrabajador() {
		PrimeFaces.current().resetInputs("frmTrabajador");
	}
	
	public void cambiarTipoDocumentoTrabajador() {
		
		if (this.equivalencia.equals(ConstantesVisitas.DNI_SIGA)) {
			lengthNumeroDocumentoTrabajador = 8;

		} else {
			lengthNumeroDocumentoTrabajador = 20;
		}
	}


	public void buscarTrabajadores() {
		boolean val = true;

		
		if(ValidarUtil.isNullOrEmpty(trabajadorNumeroDocumento)&&
		   ValidarUtil.isNullOrEmpty(equivalencia)&&
		   ValidarUtil.isNullOrEmpty(trabajadorCargo)&&
		   ValidarUtil.isNullOrEmpty(trabajadorNombreCompleto)) {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaBuscarTrabajadorAlMenosUnValor);
			val=false;
			return;
		}
		
		
		if(!ValidarUtil.isNullOrEmpty(trabajadorNumeroDocumento)&&
			ValidarUtil.isNullOrEmpty(equivalencia)) {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaSiIngresaDocumentoIngreseTipo);
			val=false;
			return;	
		}
		
		if(!ValidarUtil.isNullOrEmpty(trabajadorNumeroDocumento)&&
		   !ValidarUtil.isNullOrEmpty(equivalencia) &&
			equivalencia.equals(ConstantesVisitas.DNI_SIGA)&&
			trabajadorNumeroDocumento.length()!=8) {
			
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaDNI8Caracteres);
			val=false;
			return;	
			
		}	
		
		if( !ValidarUtil.isNullOrEmpty(equivalencia)&&
			ValidarUtil.isNullOrEmpty(trabajadorNumeroDocumento)) {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaSiIngresaTipoIngreseDocumento);
			val=false;
			return;
		}
		
		if(!ValidarUtil.isNullOrEmpty(trabajadorNombreCompleto)&&
		    trabajadorNombreCompleto.length()<3) {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaNombreMenor3Caracteres);
			val=false;
			return;	
					
		}	
		
		if (val) {
			try {
				trabajadorSeleccionado = null;
				this.listaTrabajadores = trabajadorSigaService.buscar(
						equivalencia
						,trabajadorNumeroDocumento
						,trabajadorNombreCompleto
						,obtenerAnioActual()
						,"" 
						,""
						,trabajadorCargo);
				if (listaTrabajadores==null||listaTrabajadores.isEmpty()) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo,
						ConstantesMensaje.advertenciaNoTrabajador);
					return;
				}

			} catch (Exception excepcion) {
				logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
						Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
				addErrorMessage(Constantes.mensajeErrorTitulo,
						ConstantesMensaje.errorBuscarTrabajador);
			}

		}

	}

	public void seleccionarTrabajador() {

		try {

			TipoDocumentoDto tipoDocumentoEquivalencia = maeTipoDocumentoService.buscarXEquivalencia(trabajadorSeleccionado.getIddidcodigo());
			visitado.setNombres(trabajadorSeleccionado.getMppinombres());
			visitado.setApellidoPaterno(trabajadorSeleccionado.getMppiappater());
			visitado.setApellidoMaterno(trabajadorSeleccionado.getMppiapmater());
			visitado.setNumeroDocumento(trabajadorSeleccionado.getDipnumero());
			tipoDocumentoVisitado.setIdTipoDocumento(tipoDocumentoEquivalencia.getIdTipoDocumento());
			tipoDocumentoVisitado.setDescripcion(tipoDocumentoEquivalencia.getDescripcion());
			tipoDocumentoVisitado.setAbreviatura(tipoDocumentoEquivalencia.getAbreviatura());
			visitado.setTipoDocumento(tipoDocumentoVisitado);
			visitado.setCodigoSiga(trabajadorSeleccionado.getPercodold());
			visitado.setCodigoCargoSiga(trabajadorSeleccionado.getIdcargo());
			visitado.setDescripcionCargoSiga(trabajadorSeleccionado.getCargo());
			visitado.setCodigoOficinaSiga(trabajadorSeleccionado.getIdoficodigo());
			visitado.setDescripcionOficinaSiga(trabajadorSeleccionado.getOficina());
			visitado.setCodigoLocalSiga(trabajadorSeleccionado.getIdcodlocal_ub());
			visitado.setDescripcionLocalSiga(trabajadorSeleccionado.getNom_local());
			PrimeFaces.current().executeScript("PF('dlgTrabajador').hide();");
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(),  e));
		}
	}

	public void seleccionaFecha() {
		try {
			fechaMinima = ConvertirUtil.dateToString(filtroFechaInicio, "dd/MM/yyyy");
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
		}
	}

	public void limpiarCampos() {
		visita.setNombres("");
		visita.setApellidoPaterno("");
		visita.setApellidoMaterno("");
	}

	public void grabarVisita() {

		boolean grabar = false;
		
		try {
			grabar = validarVaciosVisita();
						
			if (grabar) {
				boolean validaVisitanteVisitado = validarVisitanteVisitado(visita,visitado);
				if(validaVisitanteVisitado) {
					visitado.setxIpOperacion(CommonsUtilities.getRemoteIpAddress());
					visitado.setfOperacion(FechaUtil.currentJavaUtilDate());
					visitado.setnUsuarioOperacion(getSessionController().getUsuarioSession().getnIdUsuario());
					visita.setFechaHoraIngreso(FechaUtil.currentJavaUtilDate());
					visita.setTipoMotivo(motivo);
					visita.setnPlataforma(ConstantesVisitas.PLATAFORMA_REGISTRO);
					visita.setlFueraHorario(ConstantesVisitas.INACTIVO);
					
					//TODO 20180621 MEU carga persona natural
					if(tipoPersona!=null && tipoPersona.equals(ConstantesVisitas.TIPO_PERSONA_NATURAL)) {
						Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
						parametrosBusqueda.put(ParametrosDeBusqueda.EN_RUC,"%-%" );
						parametrosBusqueda.put(ParametrosDeBusqueda.EN_ESTADO, ConstantesVisitas.ACTIVO);
						List<EntidadDto> lista = maeEntidadService.listar(parametrosBusqueda);
						if(lista!=null) entidad=lista.get(0);
					}
					visita.setEntidad(entidad);
					visita.setUsuario(getSessionController().getUsuarioSession());
					visita.setPuntoControl(getSessionController().getPuntoControlSession());
					visita.setxIpOperacion(CommonsUtilities.getRemoteIpAddress());
					visita.setfOperacion(FechaUtil.currentJavaUtilDate());
					visita.setnUsuarioOperacion(getSessionController().getUsuarioSession().getnIdUsuario());
					visita.setVisitado(visitado);
					visita.setNombres(visita.getNombres().toUpperCase());
					visita.setApellidoPaterno(visita.getApellidoPaterno().toUpperCase());
					if(visita.getApellidoMaterno()!=null) {
						visita.setApellidoMaterno(visita.getApellidoMaterno().toUpperCase());	
					}
					movVisitaService.ingresar(visita);
					
					addInfoMessage(Constantes.mensajeInformativoTitulo, ConstantesMensaje.exitoRegistroVisita);
					//actionRedirect = ConstantesVisitas.ACTION_VISITAS;
					//PrimeFaces.current().ajax().update("listaMensajes");
					//navigate(null,ConstantesVisitas.IR_PRINCIPAL_VISITAS);
					redirectPage(ConstantesVisitas.IR_PRINCIPAL_VISITAS);
				}
			}
			
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
			addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorGrabarVisita);
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	public String cancelarRegistroVisita() {
		try {
			this.visita = null;
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
		}
		
		return ConstantesVisitas.ACTION_VISITAS;
	}

	public void registrarSalida() {
		
		try {
			visitaSeleccionada.setxIpOperacion(CommonsUtilities.getRemoteIpAddress());
			visitaSeleccionada.setfOperacion(FechaUtil.currentJavaUtilDate());
			visitaSeleccionada.setnUsuarioOperacion(getSessionController().getUsuarioSession().getnIdUsuario());
			visitaSeleccionada.setFechaRegistro(FechaUtil.currentJavaUtilDate());
			visitaSeleccionada.setFechaHoraSalida(FechaUtil.currentJavaUtilDate());
			
			VisitaDto visita = (VisitaDto) BeanUtils.cloneBean(visitaSeleccionada);
			movVisitaService.actualizar(visita);
			filtroFechaInicio = FechaUtil.getHoySinHora();
			filtroFechaFinal = FechaUtil.getHoySinHora();
			buscarVisitas();
			visitaSeleccionada=null;
			PrimeFaces.current().executeScript("PF('registrarSalida').hide();");
			addInfoMessage(Constantes.mensajeInformativoTitulo, ConstantesMensaje.exitoRegistroSalidaVisita);
			
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
			addErrorMessage(Constantes.mensajeErrorTitulo,
					ConstantesMensaje.errorRegistrarSalida);
		}
	}
	
	public boolean validarVisitanteVisitado(VisitaDto vis, ColaboradorDto col) {
		boolean val=true;
		String documentoVisitante="";
		String documentoVisitado="";
		if(vis.getNumeroDocumento()!=null&&!vis.getNumeroDocumento().trim().equals("")) {
			 documentoVisitante=vis.getNumeroDocumento();
		}
		if(col.getNumeroDocumento()!=null&&!col.getNumeroDocumento().trim().equals("")) {
			documentoVisitado=col.getNumeroDocumento();
		}
		if(documentoVisitado.equals(documentoVisitante)) {
			val=false;
			addWarnMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.advertenciaElDNIVisitadoVisitanteCoincide);
		}
		return val;
	}

	public boolean validarVaciosVisita() {

		boolean val = true;

		// validar tamaño del DNI y otros documentos
		if (tipoDocumentoVisitante != null && tipoDocumentoVisitante.getIdTipoDocumento() != null
				&& tipoDocumentoVisitante.getIdTipoDocumento() != 00
				&& tipoDocumentoVisitante.getIdTipoDocumento().equals(ConstantesVisitas.NUM_DOC_DNI)) {

			if (visita.getNumeroDocumento().length() > 8) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaElDNIVisitadoVisitanteCoincide);
				val = false;
				return val;
			}
		}
		// Validacion de visitante

		if (tipoDocumentoVisitante == null || tipoDocumentoVisitante.getIdTipoDocumento() == null
				|| tipoDocumentoVisitante.getIdTipoDocumento() == 0) {
			val = false;
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaVisitanteTipoDocumento);
			return val;
		}
		if (visita == null || ((visita.getNumeroDocumento() != null && visita.getNumeroDocumento().trim().equals(""))
				|| visita.getNumeroDocumento() == null)) {
			val = false;
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaVisitanteNumeroDocumento);
			return val;
		}

		if (visita == null || ((visita.getNombres() != null && visita.getNombres().trim().equals(""))
				|| visita.getNombres() == null)) {
			val = false;
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaVisitanteNombre);
			return val;
		}
		
		if (visita == null || ((visita.getApellidoPaterno() != null && visita.getApellidoPaterno().trim().equals(""))
				|| visita.getApellidoPaterno() == null)) {
			val = false;
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaVisitantePaterno);
			return val;
		}

		// Validacion de entidad
		//TODO 20180621 MEU Filtro de tipo persona juridica
		if(tipoPersona!=null && tipoPersona.equals(ConstantesVisitas.TIPO_PERSONA_JURIDICA)) {
			if (entidad == null || ((entidad.getRazonSocial() != null && entidad.getRazonSocial().trim().equals(""))
					|| entidad.getRazonSocial() == null)) {
				val = false;
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaVisitanteEntidad);
				return val;
			}
		}

		// Validacion del visitado
		if (tipoDocumentoVisitado == null || tipoDocumentoVisitado.getIdTipoDocumento() == null
				|| tipoDocumentoVisitado.getIdTipoDocumento() == 0) {
			val = false;
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaVisitadoTipoDocumento);
			return val;
		}

		if (visitado == null
				|| ((visitado.getNumeroDocumento() != null && visitado.getNumeroDocumento().trim().equals(""))
						|| visitado.getNumeroDocumento() == null)) {
			val = false;
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaVisitadoNumeroDocumento);
			return val;
		}

		if (visitado == null || ((visitado.getNombres() != null && visitado.getNombres().trim().equals(""))
				|| visitado.getNombres() == null)) {
			val = false;
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaVisitadoNombre);
			return val;
		}

		if (visitado == null
				|| ((visitado.getDescripcionCargoSiga() != null && visitado.getDescripcionCargoSiga().trim().equals(""))
						|| visitado.getDescripcionCargoSiga() == null)) {
			val = false;
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaVisitadoCargo);
			return val;
		}

		if (visitado == null || ((visitado.getDescripcionOficinaSiga() != null
				&& visitado.getDescripcionOficinaSiga().trim().equals(""))
				|| visitado.getDescripcionOficinaSiga() == null)) {
			val = false;
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaVisitadoOficina);
			return val;
		}

		// Validacion del tipo motivo
		if (motivo == null || motivo.getIdTipoMotivo() == 0) {
			val = false;
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaVisitaTipoMotivo);
			return val;
		}

		// Validacion descripción motivo
		if (visita.getDescripcionMotivo() == null || visita.getDescripcionMotivo().trim().equals("")) {
			val = false;
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaVisitadoDescripcionMotivo);
			
			return val;
		}
		return val;
	}
	
	public void cerrarBusquedaEntidad() {
		PrimeFaces.current().executeScript("PF('dlgEntidad').hide()");
	}
	
	public void cerrarBusquedaLugar() {
		PrimeFaces.current().executeScript("PF('dlgLugar').hide()");
	}
	
	public void cerrarBusquedaTrabajador() {
		PrimeFaces.current().executeScript("PF('dlgTrabajador').hide()");
	}

	public void cerrarBusquedaAgregarEntidad() {
		PrimeFaces.current().executeScript("PF('dlgAgregarEntidad').hide();");
	}

	public void cerrarBusquedaMensajeEntidad() {
		PrimeFaces.current().executeScript("PF('dlgMensajeEntidad').hide();");
	}

	public void cerrarBusquedaVisitante() {
		PrimeFaces.current().executeScript("PF('dlgVisitante').hide()");
	}
	
	//TODO 20180621 MEU carga persona natural
	public void cambiarTipoPersona() {
		
		Map<String,String> params = this.getContext().getRequestParameterMap();
		tipoPersona = params.get("tipo");
		if(tipoPersona!=null && tipoPersona.equals(ConstantesVisitas.TIPO_PERSONA_NATURAL)) {
			this.entidad.setRazonSocial(ConstantesVisitas.PERSONA_NATURAL);
		}else {
			this.entidad.setRazonSocial("");
		}
	}
	
	public void limpiarFiltrosBusqueda() {
		visitanteNumeroDocumento="";
		visitanteNombres="";
		visitanteApellidoPaterno="";
		visitanteApellidoMaterno="";
	}
	
	public void buscarVisitante() {
		
		if(tipoDocumentoVisitante != null && tipoDocumentoVisitante.getIdTipoDocumento() != null
			&& tipoDocumentoVisitante.getIdTipoDocumento() != 0 ) {
			visitanteSeleccionado=null;
			listaVisitantes = null;
			visitanteNumeroDocumento = "";
			visitanteNombres = "";
			visitanteApellidoPaterno ="";
			visitanteApellidoMaterno = "";
			tipoBusqVisitante="DNI";
			PrimeFaces.current().executeScript("PF('dlgVisitante').show();");
		}else {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Seleccione el Tipo de Documento del Visitante.");
		}
	}
	
	public void buscarVisitantes() {
		
		boolean val = true;
		
		if(tipoBusqVisitante.equals(ConstantesVisitas.TIPO_BUSQUEDA_DNI)){
			if(ValidarUtil.isNullOrEmpty(visitanteNumeroDocumento)) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaDNI8Caracteres);
				val=false;
				return;	
			}				
			if(visitanteNumeroDocumento.length()!=8) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaDNI8Caracteres);
				val=false;
				return;	
			}	
		}else {
			if(ValidarUtil.isNullOrEmpty(visitanteNombres)) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaNombreNoIngresado);
				val=false;
				return;		
			}	
			//if(visitanteNombres.length()<3) 
			if(ValidarUtil.isNullOrEmpty(visitanteApellidoPaterno)) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaApePaternoNoIngresado);
				val=false;
				return;		
			}	
			//if(visitanteApellidoPaterno.length()<3)
			if(ValidarUtil.isNullOrEmpty(visitanteApellidoMaterno)) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaApeMaternoNoIngresado);
				val=false;
				return;		
			}	
			//if(visitanteApellidoMaterno.length()<3) 
		}

		if (val) {
			try {
				if(tipoBusqVisitante.equals(ConstantesVisitas.TIPO_BUSQUEDA_DNI)){
					listaVisitantes = buscarPersonaReniecRest(ConstantesVisitas.TIPO_BUSQUEDA_DNI, visitanteNumeroDocumento,
						ConstantesVisitas.LETRA_VACIO, ConstantesVisitas.LETRA_VACIO, ConstantesVisitas.LETRA_VACIO);
				}
					else {
					listaVisitantes = buscarPersonaReniecRest(ConstantesVisitas.TIPO_BUSQUEDA_NYA, ConstantesVisitas.LETRA_VACIO,
						visitanteNombres.toUpperCase(), visitanteApellidoPaterno.toUpperCase(), visitanteApellidoMaterno.toUpperCase());
				}
				
				if(listaVisitantes==null) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "No se encontraron registros. ");
					return;		
				}	
				
				
			} catch (Exception excepcion) {
				logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
						Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
				addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorBuscarVisitante);
			}
		}
	}
	
	public void seleccionarVisitante() {

		try {
			PersonaReniecBean personaBean = null;
			List<PersonaReniecBean> lista;
			
			if(visitanteSeleccionado!=null) {
				if(!ValidarUtil.isNullOrEmpty(visitanteSeleccionado.getSexo())) {
					// Seleccionado proviene busqueda por dni
					personaBean = visitanteSeleccionado;
				}else {
					// Seleccionado proviene busqueda por nombre, debes consultar por dni
					lista = buscarPersonaReniecRest(ConstantesVisitas.TIPO_BUSQUEDA_DNI, visitanteSeleccionado.getNroDocumentoIdentidad().trim(),
							ConstantesVisitas.LETRA_VACIO, ConstantesVisitas.LETRA_VACIO, ConstantesVisitas.LETRA_VACIO);
					personaBean = lista.get(0);
				}
		
				if(personaBean==null) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaNoSeleccionarPersona);
					PrimeFaces.current().ajax().update("listaMensajes");
					return;
				}
				
				//Validar si es fallecido, salir y mandar mensaje de error
				if(!ValidarUtil.isNullOrEmpty(personaBean.getFechaFallecimiento())) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaVisitanteFallecido);
					PrimeFaces.current().ajax().update("listaMensajes");
					return;
				}
				
				//Cargar la informacion de la visitante
				this.visita.setApellidoPaterno(personaBean.getApellidoPaterno());
				this.visita.setApellidoMaterno(personaBean.getApellidoMaterno());
				this.visita.setNombres(personaBean.getNombres());
				this.visita.setFoto(generaFoto(personaBean.getFoto()));
				this.visita.setNumeroDocumento(personaBean.getNroDocumentoIdentidad());
				PrimeFaces.current().executeScript("PF('dlgVisitante').hide();");
			}else {
				//No se encuentra seleccionado, salir y mandar mensaje de error
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.registroNoSeleccionado);
				PrimeFaces.current().ajax().update("listaMensajes");
				return;
			}
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(),  e));
		}
	}
	
//	public ArrayList<PersonaReniecBean> buscarPersonaReniec(String tipo, String nroDocumento, String nombre, String paterno, String materno) {
//
//		ArrayList<PersonaReniecBean> lista =null;
//		try {
//			ParamConfigReniecBean beanParam = new ParamConfigReniecBean();
//			beanParam.setEndpoint(ConstantesVisitas.WSRENIEC_ENDPOINT);
//			beanParam.setDniConsultante(ConstantesVisitas.WSRENIEC_DNI);
//			beanParam.setTimeout(ConstantesVisitas.WSRENIEC_TIMEOUT);
//	
//			ConsultaReniec consultaReniecRequest = new ConsultaReniec();
//			consultaReniecRequest.setReqTrama(ConstantesVisitas.LETRA_VACIO);
//			consultaReniecRequest.setReqDniConsultante(beanParam.getDniConsultante());
//			consultaReniecRequest.setReqUsuario(String.valueOf(getSessionController().getUsuarioSession().getnIdUsuario()));
//			consultaReniecRequest.setReqIp(CommonsUtilities.getRemoteIpAddress());
//			consultaReniecRequest.setReqNroRegistros(ConstantesVisitas.STRING_REG);
//			consultaReniecRequest.setReqGrupo(ConstantesVisitas.LETRA_VACIO);
//			consultaReniecRequest.setReqDniApoderado(ConstantesVisitas.LETRA_VACIO);
//			consultaReniecRequest.setReqTipoVinculoApoderado(ConstantesVisitas.LETRA_VACIO);
//			if(tipo.equals(ConstantesVisitas.TIPO_BUSQUEDA_DNI)){
//				consultaReniecRequest.setReqTipoConsulta(ConstantesVisitas.STRING_DOS);
//			}else {
//				consultaReniecRequest.setReqTipoConsulta(ConstantesVisitas.STRING_UNO);
//			}
//			consultaReniecRequest.setReqDni(nroDocumento);
//			consultaReniecRequest.setReqNombres(nombre);
//			consultaReniecRequest.setReqApellidoPaterno(paterno);
//			consultaReniecRequest.setReqApellidoMaterno(materno);
//			
//			ConsultaReniecResponse responseReniec = null;
//			responseReniec = reniecWsService.consultaReniec(Utilitarios.getPortReniec(beanParam), consultaReniecRequest);
//			int regPagina = Integer.parseInt(ConstantesVisitas.STRING_REG);
//			int totalReg = 0;
//			int nroPagina = 0;
//
//			if (responseReniec != null) {
//				if (ConstantesVisitas.CODIGO_OPERACION_CORRECTA_WS.equals(responseReniec.getResCodigo())) {
//					if(tipo.equals(ConstantesVisitas.TIPO_BUSQUEDA_DNI)){
//						PersonaReniecBean bean = Utilitarios.cargarPersonaReniecResponseBean(responseReniec);
//						lista =  new ArrayList<PersonaReniecBean>();
//						lista.add(bean);
//					}else {
//						StringBuffer trama = new StringBuffer();  
//						trama.append(responseReniec.getResListaPersonas());
//						if(!ValidarUtil.isNullOrEmpty(responseReniec.getResTotalRegistros())) {
//							totalReg = Integer.parseInt(responseReniec.getResTotalRegistros());
//							nroPagina = totalReg/regPagina;
//						}
//						for(int i=0; i<nroPagina; i++) {
//							consultaReniecRequest.setReqGrupo(String.valueOf((i+1)*regPagina));
//							responseReniec = reniecWsService.consultaReniec(Utilitarios.getPortReniec(beanParam), consultaReniecRequest);
//							trama.append("\n");
//							trama.append(responseReniec.getResListaPersonas());
//						}
//						lista = Utilitarios.cargarListaPersonaReniecResponseBean(trama.toString());
//					}
//				}
//			}
//		} catch (Exception excepcion) {
//			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
//				Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
//			addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorBuscarReniec);
//		}
//		return lista;
//	}

	public ArrayList<PersonaReniecBean> buscarPersonaReniecRest(String tipo, String nroDocumento, String nombre, String paterno, String materno) {

		ArrayList<PersonaReniecBean> lista =null;
		try {
			
			AuditoriaReniec auditoria = new AuditoriaReniec();
			auditoria.setUsuario(ConstantesVisitas.RENIEC_USUARIO_CONSULTA_DEFAULT);
			auditoria.setNombrePc(CommonsUtilities.getPCName());
			auditoria.setNumeroIp(CommonsUtilities.getRemoteIpAddress());
			auditoria.setDireccionMac(CommonsUtilities.getMAC());
							
			ConsultadoReniec consultado = new ConsultadoReniec();
			if(tipo.equals(ConstantesVisitas.TIPO_BUSQUEDA_DNI)){
				consultado.setTipoConsulta(ConstantesVisitas.RENIEC_TIPO_BUSQUEDA_DNI);
				consultado.setNroDocumentoIdentidad(nroDocumento);
			} else {
				consultado.setTipoConsulta(ConstantesVisitas.RENIEC_TIPO_BUSQUEDA_NOMBRE);
				consultado.setApellidoPaterno(paterno);
				consultado.setApellidoMaterno(materno);
				consultado.setNombres(nombre);
			}			
			
			PaginaReniec pag = new PaginaReniec();
			pag.setSize("30");
			pag.setPage("0");
			
			RequestReniec request = new RequestReniec();
			request.setFormatoRespuesta("json");
			request.setConsultante(ConstantesVisitas.WSRENIEC_DNI);
			request.setMotivo(ConstantesVisitas.WSRENIEC_MOTIVO);
			request.setPersonaConsultada(consultado);
			request.setPagination(pag);
			request.setAuditoria(auditoria);
			
			ObjectMapper mapper =  new ObjectMapper();
			String jsonRequest = mapper.writeValueAsString(request);
			int regPagina = Integer.parseInt(ConstantesVisitas.STRING_REG);
			int totalReg = 0;
			int nroPagina = 0;
			ResponseConsultaReniecWs responseConsultaReniecWs = null;
			ResponseConsultaReniecWsNombre responseConsultaReniecWsNombre = null;
			if(tipo.equals(ConstantesVisitas.TIPO_BUSQUEDA_DNI)){
//				logger.info("REQUEST [RENIEC DNI]:{}", jsonRequest);			
				responseConsultaReniecWs = consultaReniecRest(request);
//				logger.info("CONSULT RENIEC REST CONCLUYO SATISFACTORIAMENTE");
//				String  r = responseConsultaReniecWs.getResponseConsultaReniecWsData().toString();
//				logger.info(r);
			}
			else {
//				logger.info("REQUEST [RENIEC NOMBRE]:{}", jsonRequest);	
				responseConsultaReniecWsNombre = consultaReniecRestNombre(request);
//				logger.info("CONSULT RENIEC REST CONCLUYO SATISFACTORIAMENTE");
//				String  r = responseConsultaReniecWsNombre.getResponseConsultaReniecWsPersona().toString();
//				logger.info(r);
			}
				
//			logger.info("RENIEC DNI: "+responseConsultaReniecWs);
//			logger.info("RENIEC NOMBRE: "+responseConsultaReniecWsNombre);
			if (responseConsultaReniecWs != null) {

//				logger.info("RPTA DNI: "+responseConsultaReniecWs.getResponseConsultaReniecWsData().getCodigo());
				if (ConstantesVisitas.CODIGO_OPERACION_CORRECTA_WS.equals(responseConsultaReniecWs.getResponseConsultaReniecWsData().getCodigo())) {
					if(tipo.equals(ConstantesVisitas.TIPO_BUSQUEDA_DNI)){
						PersonaReniecBean bean = responseConsultaReniecWs.getResponseConsultaReniecWsData().getData();
						lista =  new ArrayList<PersonaReniecBean>();
						lista.add(bean);
			
					}else {
						StringBuffer trama = new StringBuffer();  
//						logger.info("TIPO_BUSQUEDA_NOMBRES");
						trama.append(responseConsultaReniecWsNombre.getResponseConsultaReniecWsPersona().getData().getPersonas().toString());
						if(!ValidarUtil.isNullOrEmpty(responseConsultaReniecWsNombre.getResponseConsultaReniecWsPersona().getData().getTotalRegistros())) {
//							logger.info("TOTAL REGISTROS: "+responseConsultaReniecWsNombre.getResponseConsultaReniecWsPersona().getData().getTotalRegistros());
							totalReg = responseConsultaReniecWsNombre.getResponseConsultaReniecWsPersona().getData().getTotalRegistros();
							nroPagina = totalReg/regPagina;
						}
//						logger.info(trama.toString());

						lista = Utilitarios.cargarListaPersonaReniecResponseBean(trama.toString());
					}
				}
			}
			
			if (responseConsultaReniecWsNombre != null ) {
//				logger.info("RPTA NOMBRE: "+responseConsultaReniecWsNombre.getResponseConsultaReniecWsPersona().getCodigo());
				if (ConstantesVisitas.CODIGO_OPERACION_CORRECTA_WS.equals(responseConsultaReniecWsNombre.getResponseConsultaReniecWsPersona().getCodigo())) {
					if(tipo.equals(ConstantesVisitas.TIPO_BUSQUEDA_DNI)){
						PersonaReniecBean bean = responseConsultaReniecWs.getResponseConsultaReniecWsData().getData();
						lista =  new ArrayList<PersonaReniecBean>();
						lista.add(bean);
			
					}else {
						StringBuffer trama = new StringBuffer();  
//						logger.info("TIPO_BUSQUEDA_NOMBRES");
						trama.append(responseConsultaReniecWsNombre.getResponseConsultaReniecWsPersona().getData().getPersonas().toString());
						if(!ValidarUtil.isNullOrEmpty(responseConsultaReniecWsNombre.getResponseConsultaReniecWsPersona().getData().getTotalRegistros())) {
//							logger.info("TOTAL REGISTROS: "+responseConsultaReniecWsNombre.getResponseConsultaReniecWsPersona().getData().getTotalRegistros());
							totalReg = responseConsultaReniecWsNombre.getResponseConsultaReniecWsPersona().getData().getTotalRegistros();
							nroPagina = totalReg/regPagina;
						}
//						logger.info(trama.toString());

						lista = Utilitarios.cargarListaPersonaReniecResponseBean(trama.toString());
					}
				}
			}
			
			
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
				Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
			addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorBuscarReniec);
		}
		return lista;
	}	
		
	public ResponseConsultaReniecWs consultaReniecRest(RequestReniec request) throws Exception  {
		
		ResponseConsultaReniecWs response = new ResponseConsultaReniecWs();
		Mensaje respuesta= null;
		
		try {
//			logger.info("consultaReniecRest");
			ResponseEntity<String> result=  this.autenticacionWebServiceReniec();  
			if(result.getStatusCode().equals(HttpStatus.OK)) {
				RestTemplate restTemplate = new RestTemplate();
				String token= result.getHeaders().get(HttpHeaders.AUTHORIZATION).toString();
				token=token.substring(1, token.length()-1);
				
				HttpHeaders  headers2      = new HttpHeaders();
				headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers2.set(HttpHeaders.AUTHORIZATION,token );
				headers2.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE+CODIFICADOR_UTF8);
				ObjectMapper mapper = new ObjectMapper();
			    String json = mapper.writeValueAsString(request);
//			    logger.info(json);
			    HttpEntity<String> entity2 = new HttpEntity<String>(json, headers2);
			    try {
			    	 ResponseEntity<ResponseConsultaReniecWsData> result2 = restTemplate.postForEntity(ConstantesVisitas.WSRENIEC_ENDPOINT.concat(ConstantesVisitas.RENIEC_METODO_CONSULTA), entity2, ResponseConsultaReniecWsData.class);
					    respuesta= new Mensaje();
					    respuesta.setCodigo(ConstantesVisitas.CODIGO_OPERACION_CORRECTA);
					    respuesta.setDescripcion(ConstantesVisitas.VALOR_DEFECTO_ESPACIO_EN_BLANCO);
					    
					    response.setMensaje(respuesta);
					    response.setResponseConsultaReniecWsData(result2.getBody());						    	
			    } catch(HttpStatusCodeException e) {
			    	if(e.getMessage()!=null&&e.getMessage().contains(ConstantesVisitas.STATUS_BAD_REQUEST)) {
				    	throw new Exception ("Ocurrio un error en el WS Reniec - parámetros incorrectos - Status Code: ".concat(ConstantesVisitas.STATUS_BAD_REQUEST).concat(" - Response Body: ".concat(e.getResponseBodyAsString())));
			    	}else{
			    		throw new Exception ("Ocurrio un error en el WS Reniec - Status Code: ".concat( String.valueOf(result.getStatusCode())).concat(" - Response Body: ".concat(e.getResponseBodyAsString())));
			    	}
			    }
			    
				
			}else {
				throw new Exception ("Ocurrio un error en el WS Consulta Reniec REST - StatusCode: ".concat( String.valueOf(result.getStatusCode())));		
			}			
		}catch(Exception e){
			throw  e;
		}
		
		return response;
	
	}
	
	
	public ResponseConsultaReniecWsNombre consultaReniecRestNombre(RequestReniec request) throws Exception  {
		
		ResponseConsultaReniecWsNombre response = new ResponseConsultaReniecWsNombre();
		Mensaje respuesta= null;
		
		try {
//			logger.info("consultaReniecRestxNombre");
			ResponseEntity<String> result=  this.autenticacionWebServiceReniec();  
			if(result.getStatusCode().equals(HttpStatus.OK)) {
				RestTemplate restTemplate = new RestTemplate();
				String token= result.getHeaders().get(HttpHeaders.AUTHORIZATION).toString();
				token=token.substring(1, token.length()-1);
				
				HttpHeaders  headers2      = new HttpHeaders();
				headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers2.set(HttpHeaders.AUTHORIZATION,token );
				headers2.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE+CODIFICADOR_UTF8);
				ObjectMapper mapper = new ObjectMapper();
			    String json = mapper.writeValueAsString(request);
//			    logger.info(json);
			    HttpEntity<String> entity2 = new HttpEntity<String>(json, headers2);
			    try {
			    	 ResponseEntity<ResponseConsultaReniecWsPersona> result2 = restTemplate.postForEntity(ConstantesVisitas.WSRENIEC_ENDPOINT.concat(ConstantesVisitas.RENIEC_METODO_CONSULTA), entity2, ResponseConsultaReniecWsPersona.class);
					    respuesta= new Mensaje();
					    respuesta.setCodigo(ConstantesVisitas.CODIGO_OPERACION_CORRECTA);
					    respuesta.setDescripcion(ConstantesVisitas.VALOR_DEFECTO_ESPACIO_EN_BLANCO);
					    response.setMensaje(respuesta);
					    response.setResponseConsultaReniecWsPersona(result2.getBody());						    	
			    } catch(HttpStatusCodeException e) {
			    	if(e.getMessage()!=null&&e.getMessage().contains(ConstantesVisitas.STATUS_BAD_REQUEST)) {
				    	throw new Exception ("Ocurrio un error en el WS Reniec - parámetros incorrectos - Status Code: ".concat(ConstantesVisitas.STATUS_BAD_REQUEST).concat(" - Response Body: ".concat(e.getResponseBodyAsString())));
			    	}else{
			    		throw new Exception ("Ocurrio un error en el WS Reniec - Status Code: ".concat( String.valueOf(result.getStatusCode())).concat(" - Response Body: ".concat(e.getResponseBodyAsString())));
			    	}
			    }
			    
				
			}else {
				throw new Exception ("Ocurrio un error en el WS Consulta Reniec REST - StatusCode: ".concat( String.valueOf(result.getStatusCode())));		
			}			
		}catch(Exception e){
			throw  e;
		}
		
		return response;
	
	}
	
	private ResponseEntity<String> autenticacionWebServiceReniec() throws Exception  {		
		ResponseEntity<String> result = null;
	    try {	  
//	    	logger.info("autenticacionWebServiceReniec");
	    	RestTemplate restTemplate = new RestTemplate();	     
		    HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		    logger.info(ConstantesVisitas.WSRENIEC_USERNAME);
//		    logger.info(ConstantesVisitas.WSRENIEC_PASSWORD);
//		    logger.info(ConstantesVisitas.WSRENIEC_CODIGO_CLIENTE);
//		    logger.info(ConstantesVisitas.WSRENIEC_CODIGO_ROL);
		    
		    headers.set(ConstantesVisitas.HEADER_USERNAME_RENIEC_REST, ConstantesVisitas.WSRENIEC_USERNAME);
		    headers.set(ConstantesVisitas.HEADER_PASSWORD_RENIEC_REST, ConstantesVisitas.WSRENIEC_PASSWORD);
		    headers.set(ConstantesVisitas.HEADER_CODIGO_CLIENTE_RENIEC_REST, ConstantesVisitas.WSRENIEC_CODIGO_CLIENTE);
		    headers.set(ConstantesVisitas.HEADER_CODIGO_APLICATIVO_RENIEC_REST, ConstantesVisitas.WSRENIEC_CODIGO_ROL);
//		    logger.info(headers.toString());
		    HttpEntity<String> entity = new HttpEntity<>(ConstantesVisitas.HEADER_PARAMETERS_HTTP_RENIEC_REST, headers);	  
//		    logger.info(entity.toString());
//		    logger.info(ConstantesVisitas.WSRENIEC_ENDPOINT.concat(ConstantesVisitas.RENIEC_METODO_AUTENTIFICACION));
		    result = restTemplate.exchange(ConstantesVisitas.WSRENIEC_ENDPOINT.concat(ConstantesVisitas.RENIEC_METODO_AUTENTIFICACION), HttpMethod.GET, entity, String.class);
	    	return result;
	    } catch(HttpStatusCodeException e) {
			if(e.getMessage()!=null && e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				throw  new Exception ("Ocurrio un error en el WS Consulta Reniec REST - autenticación - ".concat(e.getResponseBodyAsString()));
			 }else if(e.getMessage()!=null&&e.getMessage().contains(ConstantesVisitas.STATUS_BAD_REQUEST)) {
		    	throw new Exception ("Ocurrio un error en el WS Consulta Reniec REST - parámetros incorrectos - Status Code: ".concat(ConstantesVisitas.STATUS_BAD_REQUEST).concat(" - Response Body: ".concat(e.getResponseBodyAsString())));
	    	}else{
	    		throw new Exception ("Ocurrio un error en el WS Consulta Reniec REST - Status Code: ".concat( String.valueOf(e.getStatusCode())).concat(" - Response Body: ".concat(e.getResponseBodyAsString())));
	    	}
		}		    
	}
	
	public List<VisitaDto> getListaVisitas() {
		return listaVisitas;
	}

	public void setListaVisitas(List<VisitaDto> listaVisitas) {
		this.listaVisitas = listaVisitas;
	}

	public MovVisitaService getMovVisitaService() {
		return movVisitaService;
	}

	public void setMovVisitaService(MovVisitaService movVisitaService) {
		this.movVisitaService = movVisitaService;
	}

	public List<TipoDocumentoDto> getListaTipoDocumentoVisitante() {
		return listaTipoDocumentoVisitante;
	}

	public void setListaTipoDocumentoVisitante(List<TipoDocumentoDto> listaTipoDocumentoVisitante) {
		this.listaTipoDocumentoVisitante = listaTipoDocumentoVisitante;
	}

	public ColaboradorDto getVisitado() {
		return visitado;
	}

	public void setVisitado(ColaboradorDto visitado) {
		this.visitado = visitado;
	}

	public EntidadDto getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadDto entidad) {
		this.entidad = entidad;
	}

	public ReniecWsService getReniecWsService() {
		return reniecWsService;
	}

	public void setReniecWsService(ReniecWsService reniecWsService) {
		this.reniecWsService = reniecWsService;
	}

	public TipoDocumentoDto getTipoDocumentoVisitante() {
		return tipoDocumentoVisitante;
	}

	public void setTipoDocumentoVisitante(TipoDocumentoDto tipoDocumentoVisitante) {
		this.tipoDocumentoVisitante = tipoDocumentoVisitante;
	}

	public TipoDocumentoDto getTipoDocumentoVisitado() {
		return tipoDocumentoVisitado;
	}

	public void setTipoDocumentoVisitado(TipoDocumentoDto tipoDocumentoVisitado) {
		this.tipoDocumentoVisitado = tipoDocumentoVisitado;
	}

	public MaeTipoDocumentoService getMaeTipoDocumentoService() {
		return maeTipoDocumentoService;
	}

	public void setMaeTipoDocumentoService(MaeTipoDocumentoService maeTipoDocumentoService) {
		this.maeTipoDocumentoService = maeTipoDocumentoService;
	}

	public MaeColaboradorService getMaePersonaService() {
		return maePersonaService;
	}

	public void setMaePersonaService(MaeColaboradorService maePersonaService) {
		this.maePersonaService = maePersonaService;
	}

	public VisitaDto getVisita() {
		return visita;
	}

	public void setVisita(VisitaDto visita) {
		this.visita = visita;
	}

	public TrabajadorSigaService getTrabajadorSigaService() {
		return trabajadorSigaService;
	}

	public void setTrabajadorSigaService(TrabajadorSigaService trabajadorSigaService) {
		this.trabajadorSigaService = trabajadorSigaService;
	}

	public String getMensajes() {
		return mensajes;
	}

	public void setMensajes(String mensajes) {
		this.mensajes = mensajes;
	}

	public List<TrabajadorDto> getListaTrabajadores() {
		return listaTrabajadores;
	}

	public void setListaTrabajadores(List<TrabajadorDto> listaTrabajadores) {
		this.listaTrabajadores = listaTrabajadores;
	}

	public boolean isFlgDisableVisitado() {
		return flgDisableVisitado;
	}

	public void setFlgDisableVisitado(boolean flgDisableVisitado) {
		this.flgDisableVisitado = flgDisableVisitado;
	}

	public MaeEntidadService getMaeEntidadService() {
		return maeEntidadService;
	}

	public void setMaeEntidadService(MaeEntidadService maeEntidadService) {
		this.maeEntidadService = maeEntidadService;
	}
	
	public EntidadDto getEntidadSeleccionada() {
		return entidadSeleccionada;
	}

	public void setEntidadSeleccionada(EntidadDto entidadSeleccionada) {
		this.entidadSeleccionada = entidadSeleccionada;
	}

	public List<EntidadDto> getListaEntidades() {
		return listaEntidades;
	}

	public void setListaEntidades(List<EntidadDto> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}

	public Integer getLengthNumeroDocumento() {
		return lengthNumeroDocumento;
	}

	public void setLengthNumeroDocumento(Integer lengthNumeroDocumento) {
		this.lengthNumeroDocumento = lengthNumeroDocumento;
	}

	public String getTrabajadorNombreCompleto() {
		return trabajadorNombreCompleto;
	}

	public void setTrabajadorNombreCompleto(String trabajadorNombreCompleto) {
		this.trabajadorNombreCompleto = trabajadorNombreCompleto;
	}

	public String getTrabajadorNumeroDocumento() {
		return trabajadorNumeroDocumento;
	}

	public void setTrabajadorNumeroDocumento(String trabajadorNumeroDocumento) {
		this.trabajadorNumeroDocumento = trabajadorNumeroDocumento;
	}

	public TrabajadorDto getTrabajadorSeleccionado() {
		return trabajadorSeleccionado;
	}

	public void setTrabajadorSeleccionado(TrabajadorDto trabajadorSeleccionado) {
		this.trabajadorSeleccionado = trabajadorSeleccionado;
	}

	public List<TipoDocumentoDto> getListaTipoDocumentoVisitado() {
		return listaTipoDocumentoVisitado;
	}

	public void setListaTipoDocumentoVisitado(List<TipoDocumentoDto> listaTipoDocumentoVisitado) {
		this.listaTipoDocumentoVisitado = listaTipoDocumentoVisitado;
	}

	public String getFiltroVisitante() {
		return filtroVisitante;
	}

	public void setFiltroVisitante(String filtroVisitante) {
		this.filtroVisitante = filtroVisitante;
	}

	public Date getFiltroFechaInicio() {
		return filtroFechaInicio;
	}

	public void setFiltroFechaInicio(Date filtroFechaInicio) {
		this.filtroFechaInicio = filtroFechaInicio;
	}

	public Date getFiltroFechaFinal() {
		return filtroFechaFinal;
	}

	public void setFiltroFechaFinal(Date filtroFechaFinal) {
		this.filtroFechaFinal = filtroFechaFinal;
	}

	public String getFechaMaxima() {
		return fechaMaxima;
	}

	public void setFechaMaxima(String fechaMaxima) {
		this.fechaMaxima = fechaMaxima;
	}

	public String getFechaMinima() {
		return fechaMinima;
	}

	public void setFechaMinima(String fechaMinima) {
		this.fechaMinima = fechaMinima;
	}

	public VUnidadEjecutoraService getvUnidadEjecutoraService() {
		return vUnidadEjecutoraService;
	}

	public void setvUnidadEjecutoraService(VUnidadEjecutoraService vUnidadEjecutoraService) {
		this.vUnidadEjecutoraService = vUnidadEjecutoraService;
	}

	public TipoMotivoDto getMotivo() {
		return motivo;
	}

	public void setMotivo(TipoMotivoDto motivo) {
		this.motivo = motivo;
	}

	public List<TipoMotivoDto> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivoDto> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	public MaeTipoMotivoService getMaeTipoMotivoService() {
		return maeTipoMotivoService;
	}

	public void setMaeTipoMotivoService(MaeTipoMotivoService maeTipoMotivoService) {
		this.maeTipoMotivoService = maeTipoMotivoService;
	}
	
	public String getEquivalencia() {
		return equivalencia;
	}

	public void setEquivalencia(String equivalencia) {
		this.equivalencia = equivalencia;
	}

	public List<TipoDocumentoDto> getListaTipoDocumentoTrabajador() {
		return listaTipoDocumentoTrabajador;
	}

	public void setListaTipoDocumentoTrabajador(List<TipoDocumentoDto> listaTipoDocumentoTrabajador) {
		this.listaTipoDocumentoTrabajador = listaTipoDocumentoTrabajador;
	}

	public VisitaDto getVisitaSeleccionada() {
		return visitaSeleccionada;
	}

	public void setVisitaSeleccionada(VisitaDto visitaSeleccionada) {
		this.visitaSeleccionada = visitaSeleccionada;
	}

	public PideWsService getPideWsService() {
		return pideWsService;
	}

	public void setPideWsService(PideWsService pideWsService) {
		this.pideWsService = pideWsService;
	}

	public String getFiltroRazonSocialEntidad() {
		return filtroRazonSocialEntidad;
	}

	public void setFiltroRazonSocialEntidad(String filtroRazonSocialEntidad) {
		this.filtroRazonSocialEntidad = filtroRazonSocialEntidad;
	}

	public String getFiltroRucEntidad() {
		return filtroRucEntidad;
	}

	public void setFiltroRucEntidad(String filtroRucEntidad) {
		this.filtroRucEntidad = filtroRucEntidad;
	}

	public String getFiltroRazonSocialEntidadPide() {
		return filtroRazonSocialEntidadPide;
	}

	public void setFiltroRazonSocialEntidadPide(String filtroRazonSocialEntidadPide) {
		this.filtroRazonSocialEntidadPide = filtroRazonSocialEntidadPide;
	}

	public String getFiltroRucEntidadPide() {
		return filtroRucEntidadPide;
	}

	public void setFiltroRucEntidadPide(String filtroRucEntidadPide) {
		this.filtroRucEntidadPide = filtroRucEntidadPide;
	}

	public EntidadDto getEntidadPide() {
		return entidadPide;
	}

	public void setEntidadPide(EntidadDto entidadPide) {
		this.entidadPide = entidadPide;
	}

	public boolean isDatosVisitanteDesHabilitado() {
		return datosVisitanteDesHabilitado;
	}

	public void setDatosVisitanteDesHabilitado(boolean datosVisitanteDesHabilitado) {
		this.datosVisitanteDesHabilitado = datosVisitanteDesHabilitado;
	}

	public boolean isDocumentoVisitanteDesHabilitado() {
		return documentoVisitanteDesHabilitado;
	}

	public void setDocumentoVisitanteDesHabilitado(boolean documentoVisitanteDesHabilitado) {
		this.documentoVisitanteDesHabilitado = documentoVisitanteDesHabilitado;
	}

	public String getTrabajadorCargo() {
		return trabajadorCargo;
	}

	public void setTrabajadorCargo(String trabajadorCargo) {
		this.trabajadorCargo = trabajadorCargo;
	}

	public Integer getLengthNumeroDocumentoTrabajador() {
		return lengthNumeroDocumentoTrabajador;
	}

	public void setLengthNumeroDocumentoTrabajador(Integer lengthNumeroDocumentoTrabajador) {
		this.lengthNumeroDocumentoTrabajador = lengthNumeroDocumentoTrabajador;
	}

	public String getObservacionesSalida() {
		return observacionesSalida;
	}

	public void setObservacionesSalida(String observacionesSalida) {
		this.observacionesSalida = observacionesSalida;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getUrlConsultaSUNAT() {
		return urlConsultaSUNAT;
	}

	public void setUrlConsultaSUNAT(String urlConsultaSUNAT) {
		this.urlConsultaSUNAT = urlConsultaSUNAT;
	}

	public String getMensajeNoEncontrado() {
		return mensajeNoEncontrado;
	}

	public void setMensajeNoEncontrado(String mensajeNoEncontrado) {
		this.mensajeNoEncontrado = mensajeNoEncontrado;
	}

	public PersonaReniecBean getVisitanteSeleccionado() {
		return visitanteSeleccionado;
	}

	public void setVisitanteSeleccionado(PersonaReniecBean visitanteSeleccionado) {
		this.visitanteSeleccionado = visitanteSeleccionado;
	}

	public String getVisitanteNumeroDocumento() {
		return visitanteNumeroDocumento;
	}

	public void setVisitanteNumeroDocumento(String visitanteNumeroDocumento) {
		this.visitanteNumeroDocumento = visitanteNumeroDocumento;
	}

	public String getVisitanteNombres() {
		return visitanteNombres;
	}

	public void setVisitanteNombres(String visitanteNombres) {
		this.visitanteNombres = visitanteNombres;
	}

	public String getVisitanteApellidoPaterno() {
		return visitanteApellidoPaterno;
	}

	public void setVisitanteApellidoPaterno(String visitanteApellidoPaterno) {
		this.visitanteApellidoPaterno = visitanteApellidoPaterno;
	}

	public String getVisitanteApellidoMaterno() {
		return visitanteApellidoMaterno;
	}

	public void setVisitanteApellidoMaterno(String visitanteApellidoMaterno) {
		this.visitanteApellidoMaterno = visitanteApellidoMaterno;
	}

	public String getTipoBusqVisitante() {
		return tipoBusqVisitante;
	}

	public void setTipoBusqVisitante(String tipoBusqVisitante) {
		this.tipoBusqVisitante = tipoBusqVisitante;
	}

	public List<PersonaReniecBean> getListaVisitantes() {
		return listaVisitantes;
	}

	public void setListaVisitantes(List<PersonaReniecBean> listaVisitantes) {
		this.listaVisitantes = listaVisitantes;
	}

	public String getFiltroNroDocumento() {
		return filtroNroDocumento;
	}

	public void setFiltroNroDocumento(String filtroNroDocumento) {
		this.filtroNroDocumento = filtroNroDocumento;
	}

	
	
	
}
