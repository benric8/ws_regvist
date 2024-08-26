package pe.gob.pj.administrativos.visitas.web.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.gob.pj.administrativos.visitas.model.dto.DepartamentoDto;
import pe.gob.pj.administrativos.visitas.model.dto.DistritoDto;
import pe.gob.pj.administrativos.visitas.model.dto.LocalDto;
import pe.gob.pj.administrativos.visitas.model.dto.OficinaDto;
import pe.gob.pj.administrativos.visitas.model.dto.ProvinciaDto;
import pe.gob.pj.administrativos.visitas.model.dto.PuntoControlDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesMensaje;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.model.util.ParametrosDeBusqueda;
import pe.gob.pj.administrativos.visitas.service.CfgPuntoControlService;
import pe.gob.pj.administrativos.visitas.service.VCorteService;
import pe.gob.pj.administrativos.visitas.service.VDepartamentoService;
import pe.gob.pj.administrativos.visitas.service.VDistritoService;
import pe.gob.pj.administrativos.visitas.service.VLocalService;
import pe.gob.pj.administrativos.visitas.service.VOficinaService;
import pe.gob.pj.administrativos.visitas.service.VProvinciaService;
import pe.gob.pj.administrativos.visitas.service.VUnidadEjecutoraService;
import pe.gob.pj.api.commons.constants.Constantes;
import pe.gob.pj.api.commons.constants.sicau.ConstantesSicau;
import pe.gob.pj.api.commons.utility.CommonsUtilities;
import pe.gob.pj.api.commons.utility.FechaUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;

@ViewScoped
@ManagedBean(name = "puntoControlMB")
public class PuntoControlController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PuntoControlController.class);

	private String filtroNombre;
	private String filtroLocal;
	private List<LocalDto> filtroListaLocal;
	private String filtroEstado;	

	private List<PuntoControlDto> listaPuntoControl;
	private PuntoControlDto puntoControlSeleccionado;

	private String formulario;
	private PuntoControlDto puntoControl;

	private String filtroDepartamento;
	private String filtroProvincia;
	private String filtroDistrito;
	
	
	private List<LocalDto> listaLocal;
	private LocalDto localSeleccionado;

	private List<OficinaDto> listaOficina;
	private List<OficinaDto> filtroNombreOficina;
	private OficinaDto oficinaSeleccionada;

	private List<DepartamentoDto> listaDepartamento;
	private List<ProvinciaDto> listaProvincia;
	private List<DistritoDto> listaDistrito;
	private boolean deshabilitarBusquedaOficina;

	@ManagedProperty(value = "#{unidadEjecutoraService}")
	private VUnidadEjecutoraService vUnidadEjecutoraService;

	@ManagedProperty(value = "#{corteService}")
	private VCorteService vCorteService;

	@ManagedProperty(value = "#{departamentoService}")
	private VDepartamentoService vDepartamentoService;

	@ManagedProperty(value = "#{provinciaService}")
	private VProvinciaService vProvinciaService;

	@ManagedProperty(value = "#{distritoService}")
	private VDistritoService vDistritoService;

	@ManagedProperty(value = "#{localService}")
	private VLocalService vLocalService;

	@ManagedProperty(value = "#{oficinaService}")
	private VOficinaService vOficinaService;

	@ManagedProperty(value = "#{cfgPuntoControlService}")
	private CfgPuntoControlService puntoControlService;

	@PostConstruct
	private void iniciar() {
		try {			
			boolean navegadorPermitido = validarNavegador();
			if( navegadorPermitido ) {
				if( !verificarPermiso( ConstantesVisitas.ACTION_MANTENIMIENTO_PUNTO_CONTROL)  ){
					this.noTienePermisos();
				}else{
					filtroListaLocal = puntoControlService.listarLocales(this.getSessionController().getUsuarioSession().getIdUnidadEjecutora(), 
																		this.getSessionController().getUsuarioSession().getIdCorte());
					buscarPuntoControl();
				}				
			}			
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	/*
	 * OPCIONES PRINCIPALES
	 * ==========================================================================
	 */
	public void buscarPuntoControl() {
		try {
	
			Map<String, Object> parametrosBusqueda = new HashMap<>();
			
			if( !ValidarUtil.isNullOrEmpty(filtroNombre) ) {
				parametrosBusqueda.put(ParametrosDeBusqueda.PTCT_NOMBRE, filtroNombre.trim().toUpperCase());			
				parametrosBusqueda.put(ParametrosDeBusqueda.ADD_LIKE, ParametrosDeBusqueda.INDICADOR_ACTIVO);
			}
			
			if( !ValidarUtil.isNullOrEmpty(filtroLocal) ) {
				parametrosBusqueda.put(ParametrosDeBusqueda.PTCT_LOCAL, filtroLocal);
			}
			
			if(!ValidarUtil.isNull(filtroEstado)){
    			parametrosBusqueda.put(ParametrosDeBusqueda.PTCT_ESTADO, filtroEstado);	
    		}
			
			//parametrosBusqueda.put(ParametrosDeBusqueda.PTCT_UNI_EJE, getSessionController().getUsuarioSession().getIdUnidadEjecutora());
			parametrosBusqueda.put(ParametrosDeBusqueda.PTCT_CORTE, getSessionController().getUsuarioSession().getIdCorte());
			
			this.listaPuntoControl = puntoControlService.buscar(parametrosBusqueda);
			puntoControlSeleccionado = null;
				
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	public void nuevoPuntoControl() {
		try {
			deshabilitarBusquedaOficina=true;
			this.formulario = "ADD";
			PrimeFaces.current().resetInputs("frmPuntoControl");
			puntoControl = new PuntoControlDto();
			puntoControl.setlFlagActivo(ConstantesVisitas.ACTIVO);

			PrimeFaces.current().executeScript("PF('dlgPuntoControl').show()");
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	public void editarPuntoControl() {
		try {
			deshabilitarBusquedaOficina=false;
			this.formulario = "EDIT";
			PrimeFaces.current().resetInputs("frmPuntoControl");

			if (puntoControlSeleccionado != null) {
				puntoControl = new PuntoControlDto();
				puntoControl.setnIdPuntoControl(puntoControlSeleccionado.getnIdPuntoControl());
				puntoControl.setNombre(puntoControlSeleccionado.getNombre());
				puntoControl.setxDescripcionLocal(puntoControlSeleccionado.getxDescripcionLocal());
				puntoControl.setnIdLocal(puntoControlSeleccionado.getnIdLocal());
				puntoControl.setxDescripcionOficina(puntoControlSeleccionado.getxDescripcionOficina());
				puntoControl.setnIdOficina(puntoControlSeleccionado.getnIdOficina());
				puntoControl.setlFlagActivo(puntoControlSeleccionado.getlFlagActivo());
				puntoControl.setIdUnidadEjecutora(puntoControlSeleccionado.getIdUnidadEjecutora());
				puntoControl.setIdCorte(puntoControlSeleccionado.getIdCorte());
				puntoControl.setLbEstadoRegistro((puntoControl.getlFlagActivo().equals(ParametrosDeBusqueda.INDICADOR_ACTIVO)) ? true : false);
				
				// code here
			} else {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Seleccione un registro del listado.");
			}

			PrimeFaces.current().executeScript("PF('dlgPuntoControl').show()");
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	public void grabarPuntoControl() {
		try {

			if (puntoControl != null  ) {
					
				if( ValidarUtil.isNullOrEmpty(puntoControl.getNombre()) ) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaIngreseNombrePuntoControl);
				}else if( ValidarUtil.isNullOrEmpty(puntoControl.getnIdLocal()) ) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaNoSeleccionoLocal);
				}else {										
					puntoControl.setxIpOperacion(CommonsUtilities.getRemoteIpAddress());
					puntoControl.setfOperacion(FechaUtil.currentJavaUtilDate());
					puntoControl.setfRegistro(FechaUtil.currentJavaUtilDate());
					puntoControl.setnUsuarioOperacion(getSessionController().getUsuarioSession().getnIdUsuario());
				
					if (formulario.equals("ADD")) {						
						Map<String, Object> parametrosBusqueda = new HashMap<>();
						parametrosBusqueda.put(ParametrosDeBusqueda.PTCT_NOMBRE, puntoControl.getNombre().trim().toUpperCase());
						parametrosBusqueda.put(ParametrosDeBusqueda.PTCT_LOCAL, puntoControl.getnIdLocal().trim().toUpperCase());
						if( !ValidarUtil.isNullOrEmpty(puntoControl.getnIdOficina()) ) {
							parametrosBusqueda.put(ParametrosDeBusqueda.PTCT_OFICINA, puntoControl.getnIdOficina().trim().toUpperCase());
						}
						
						List<PuntoControlDto> consultaPC = puntoControlService.buscar(parametrosBusqueda);
						
						if( consultaPC!=null && consultaPC.size()>0 ) {
							addErrorMessage(Constantes.mensajeErrorTitulo, "Ya existe registrado un Punto de Control con los datos proporcionados.");
						}else{						
							puntoControl.setlFlagActivo(ParametrosDeBusqueda.INDICADOR_ACTIVO);
							puntoControl.setIdCorte(getSessionController().getUsuarioSession().getIdCorte());
							puntoControl.setIdUnidadEjecutora(getSessionController().getUsuarioSession().getIdUnidadEjecutora());
							puntoControlService.ingresar(puntoControl);
							addInfoMessage(Constantes.mensajeInformativoTitulo, ConstantesMensaje.registroCorrecto);
						}
					} else {
						puntoControl.setlFlagActivo( (puntoControl.isLbEstadoRegistro())?ParametrosDeBusqueda.INDICADOR_ACTIVO:ParametrosDeBusqueda.INDICADOR_INACTIVO );
						puntoControlService.actualizar(puntoControl);
						addInfoMessage(Constantes.mensajeInformativoTitulo, ConstantesMensaje.modificacionCorrecto);
					}
					
					PrimeFaces.current().executeScript("PF('dlgPuntoControl').hide()");
					filtroNombre=puntoControl.getNombre();
					buscarPuntoControl();
				}
					
			} else {
				addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.operacionFallida);
			}
			
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}
	

	/*
	 * OPCIONES SECUNDARIAS
	 * ==========================================================================
	 */
	public void cerrarRegistrarPuntoControl() {
		try {		
			puntoControl=null;
			puntoControlSeleccionado = null;
			PrimeFaces.current().executeScript("PF('dlgPuntoControl').hide()");
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}
	
	private void limpiarFormularioLocal() {
		filtroDepartamento = null;
		filtroProvincia = null;
		filtroDistrito = null;
		listaLocal = null;
		localSeleccionado = null;
	}
	
	public void iniciarBusquedaLocal() {
		try {
			PrimeFaces.current().resetInputs("frmLocal");			
			limpiarFormularioLocal();
			obtenerDepartamento();
			PrimeFaces.current().executeScript("PF('dlgLocal').show()");
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	public void cerrarBusquedaLocal() {
		try {
			limpiarFormularioLocal();
			PrimeFaces.current().executeScript("PF('dlgLocal').hide()");
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}
	
	private void limpiarFormularioOficina() {
		listaOficina = null;
		oficinaSeleccionada = null;
	}
	
	public void iniciarBusquedaOficina() {
		try {

			listaOficina=null;
			filtroNombreOficina=null;
			if( !ValidarUtil.isNullOrEmpty(puntoControl.getnIdLocal()) ) {			
				PrimeFaces.current().resetInputs("frmOficina");
				limpiarFormularioOficina();
				buscarOficina();
				
				if( listaOficina!=null ) {
					PrimeFaces.current().executeScript("PF('dlgOficina').show()");
				}else {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "No existen oficinas disponibles para el local seleccionado.");
				}
			}else {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaNoSeleccionoLocal);	
			}
			
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	public void cerrarBusquedaOficina() {
		try {
			limpiarFormularioOficina();
			PrimeFaces.current().executeScript("PF('dlgOficina').hide()");
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	public void buscarLocal() {
		try {
			
			// FALTA VALIDAR CAMPOS DE UBIGEO
			if( ValidarUtil.isNull(filtroDepartamento) ) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Debe seleccionar un departamento.");
			}else if( ValidarUtil.isNull(filtroProvincia) ) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Debe seleccionar un distrito.");
			}else if( ValidarUtil.isNull(filtroDistrito) ) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Debe seleccionar un distrito.");
			}else {			
				listaLocal = vLocalService.listar(getSessionController().getUsuarioSession().getIdUnidadEjecutora(), 
													getSessionController().getUsuarioSession().getIdCorte(), 
													filtroDepartamento, 
													filtroProvincia,
													filtroDistrito);
			}
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	private void buscarOficina() {
		try {							
			Calendar cal = Calendar.getInstance();
			Integer anio = cal.get(Calendar.YEAR);
			String ubigeo = filtroDepartamento + filtroProvincia + filtroDistrito;
			listaOficina = vOficinaService.listar(anio.toString(), 
												puntoControl.getnIdLocal(), 
												getSessionController().getUsuarioSession().getIdUnidadEjecutora(), 
												getSessionController().getUsuarioSession().getIdCorte(), 
												ubigeo);	
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	public void seleccionarLocal() {
		try {
			if( localSeleccionado!=null ) {
				puntoControl.setnIdLocal("");
				puntoControl.setxDescripcionLocal("");
				puntoControl.setnIdOficina("");
				puntoControl.setxDescripcionOficina("");
				
				puntoControl.setnIdLocal(localSeleccionado.getIdCodLocal());
				puntoControl.setxDescripcionLocal(localSeleccionado.getDescripcion());
	
				PrimeFaces.current().executeScript("PF('dlgLocal').hide()");
			}else {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Debe seleccionar un local de la lista.");
			}
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	public void seleccionarOficina() {
		try {
			if( localSeleccionado!=null ) {
				puntoControl.setnIdOficina(oficinaSeleccionada.getIdOficina());
				puntoControl.setxDescripcionOficina(oficinaSeleccionada.getNomOficina());
	
				PrimeFaces.current().executeScript("PF('dlgOficina').hide()");
			}else{
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Debe seleccionar una oficina de la lista.");
			}
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}


	public void obtenerDepartamento() {
		try {
			listaDepartamento = vDepartamentoService.listar();
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	public void obtenerProvincia() {
		try {

			listaProvincia = vProvinciaService.listar(filtroDepartamento);

		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	public void obtenerDistrito() {
		try {
			listaDistrito = vDistritoService.listar(filtroDepartamento, filtroProvincia);
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}

	public void restaurarFormulario() {
		try {
			PrimeFaces.current().resetInputs("frmPuntoControl");
			PrimeFaces.current().resetInputs("frmLocal");
			PrimeFaces.current().resetInputs("frmOficina");
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}


	/*
	 * GET Y SET
	 * ==========================================================================
	 */
	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}

	public List<PuntoControlDto> getListaPuntoControl() {
		return listaPuntoControl;
	}

	public void setListaPuntoControl(List<PuntoControlDto> listaPuntoControl) {
		this.listaPuntoControl = listaPuntoControl;
	}

	public PuntoControlDto getPuntoControlSeleccionado() {
		return puntoControlSeleccionado;
	}

	public void setPuntoControlSeleccionado(PuntoControlDto puntoControlSeleccionado) {
		this.puntoControlSeleccionado = puntoControlSeleccionado;
	}

	public String getFormulario() {
		return formulario;
	}

	public void setFormulario(String formulario) {
		this.formulario = formulario;
	}

	public PuntoControlDto getPuntoControl() {
		return puntoControl;
	}

	public void setPuntoControl(PuntoControlDto puntoControl) {
		this.puntoControl = puntoControl;
	}

	public String getFiltroDepartamento() {
		return filtroDepartamento;
	}

	public void setFiltroDepartamento(String filtroDepartamento) {
		this.filtroDepartamento = filtroDepartamento;
	}

	public String getFiltroProvincia() {
		return filtroProvincia;
	}

	public void setFiltroProvincia(String filtroProvincia) {
		this.filtroProvincia = filtroProvincia;
	}

	public String getFiltroDistrito() {
		return filtroDistrito;
	}

	public void setFiltroDistrito(String filtroDistrito) {
		this.filtroDistrito = filtroDistrito;
	}

	public List<LocalDto> getListaLocal() {
		return listaLocal;
	}

	public void setListaLocal(List<LocalDto> listaLocal) {
		this.listaLocal = listaLocal;
	}

	public LocalDto getLocalSeleccionado() {
		return localSeleccionado;
	}

	public void setLocalSeleccionado(LocalDto localSeleccionado) {
		this.localSeleccionado = localSeleccionado;
	}

	public List<OficinaDto> getListaOficina() {
		return listaOficina;
	}

	public void setListaOficina(List<OficinaDto> listaOficina) {
		this.listaOficina = listaOficina;
	}

	public OficinaDto getOficinaSeleccionada() {
		return oficinaSeleccionada;
	}

	public void setOficinaSeleccionada(OficinaDto oficinaSeleccionada) {
		this.oficinaSeleccionada = oficinaSeleccionada;
	}

	public List<DepartamentoDto> getListaDepartamento() {
		return listaDepartamento;
	}

	public void setListaDepartamento(List<DepartamentoDto> listaDepartamento) {
		this.listaDepartamento = listaDepartamento;
	}

	public List<ProvinciaDto> getListaProvincia() {
		return listaProvincia;
	}

	public void setListaProvincia(List<ProvinciaDto> listaProvincia) {
		this.listaProvincia = listaProvincia;
	}

	public List<DistritoDto> getListaDistrito() {
		return listaDistrito;
	}

	public void setListaDistrito(List<DistritoDto> listaDistrito) {
		this.listaDistrito = listaDistrito;
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

	public VDepartamentoService getvDepartamentoService() {
		return vDepartamentoService;
	}

	public void setvDepartamentoService(VDepartamentoService vDepartamentoService) {
		this.vDepartamentoService = vDepartamentoService;
	}

	public VProvinciaService getvProvinciaService() {
		return vProvinciaService;
	}

	public void setvProvinciaService(VProvinciaService vProvinciaService) {
		this.vProvinciaService = vProvinciaService;
	}

	public VDistritoService getvDistritoService() {
		return vDistritoService;
	}

	public void setvDistritoService(VDistritoService vDistritoService) {
		this.vDistritoService = vDistritoService;
	}

	public VLocalService getvLocalService() {
		return vLocalService;
	}

	public void setvLocalService(VLocalService vLocalService) {
		this.vLocalService = vLocalService;
	}

	public VOficinaService getvOficinaService() {
		return vOficinaService;
	}

	public void setvOficinaService(VOficinaService vOficinaService) {
		this.vOficinaService = vOficinaService;
	}

	public CfgPuntoControlService getPuntoControlService() {
		return puntoControlService;
	}

	public void setPuntoControlService(CfgPuntoControlService puntoControlService) {
		this.puntoControlService = puntoControlService;
	}

	public boolean isDeshabilitarBusquedaOficina() {
		return deshabilitarBusquedaOficina;
	}

	public void setDeshabilitarBusquedaOficina(boolean deshabilitarBusquedaOficina) {
		this.deshabilitarBusquedaOficina = deshabilitarBusquedaOficina;
	}

	public String getFiltroLocal() {
		return filtroLocal;
	}

	public void setFiltroLocal(String filtroLocal) {
		this.filtroLocal = filtroLocal;
	}

	public List<LocalDto> getFiltroListaLocal() {
		return filtroListaLocal;
	}

	public void setFiltroListaLocal(List<LocalDto> filtroListaLocal) {
		this.filtroListaLocal = filtroListaLocal;
	}

	public String getFiltroEstado() {
		return filtroEstado;
	}
	
	public void setFiltroEstado(String filtroEstado) {
		this.filtroEstado = filtroEstado;
	}
	
	public List<OficinaDto> getFiltroNombreOficina() {
		return filtroNombreOficina;
	}
	
	public void setFiltroNombreOficina(List<OficinaDto> filtroNombreOficina) {
		this.filtroNombreOficina = filtroNombreOficina;
	}
}