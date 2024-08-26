package pe.gob.pj.administrativos.visitas.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.primefaces.PrimeFaces;

import pe.gob.pj.administrativos.visitas.model.dto.CorteDto;
import pe.gob.pj.administrativos.visitas.model.dto.LocalDto;
import pe.gob.pj.administrativos.visitas.model.dto.PerfilDto;
import pe.gob.pj.administrativos.visitas.model.dto.PuntoControlDto;
import pe.gob.pj.administrativos.visitas.model.dto.TipoDocumentoDto;
import pe.gob.pj.administrativos.visitas.model.dto.TrabajadorDto;
import pe.gob.pj.administrativos.visitas.model.dto.UnidadEjecutoraDto;
import pe.gob.pj.administrativos.visitas.model.dto.UsuarioDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesMensaje;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.model.util.FormularioOpcion;
import pe.gob.pj.administrativos.visitas.model.util.ParametrosDeBusqueda;
import pe.gob.pj.administrativos.visitas.service.CfgPuntoControlService;
import pe.gob.pj.administrativos.visitas.service.MaePerfilService;
import pe.gob.pj.administrativos.visitas.service.MaeTipoDocumentoService;
import pe.gob.pj.administrativos.visitas.service.MaeUsuarioService;
import pe.gob.pj.administrativos.visitas.service.SeguridadService;
import pe.gob.pj.administrativos.visitas.service.TrabajadorSigaService;
import pe.gob.pj.administrativos.visitas.service.VCorteService;
import pe.gob.pj.administrativos.visitas.service.VUnidadEjecutoraService;
import pe.gob.pj.api.commons.constants.Constantes;
import pe.gob.pj.api.commons.utility.CommonsUtilities;
import pe.gob.pj.api.commons.utility.CryptoUtil;
import pe.gob.pj.api.commons.utility.FechaUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;
import org.apache.commons.beanutils.BeanUtils;

/**
* Objeto : UsuarioController
* Descripción : Es una clase controlador de vistas que permite gestionar la informacion del usuario. 
* Fecha de Creación : 02/01/2019
* Autor : Carlos Arroyo
* ------------------------------------------------------------------------
* Modificaciones
* Fecha                  Nombre                     Descripción
* ------------------------------------------------------------------------
* 08/05/2019			Carlos Arroyo				CRC: Cambio por Reseteo de Clave
*/
@ManagedBean(name = "usuarioMB")
@ViewScoped
public class UsuarioController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class.getName());

	// SERVICIOS
	@ManagedProperty(value = "#{maeUsuarioService}")
	private MaeUsuarioService maeUsuarioService;

	@ManagedProperty(value = "#{maeTipoDocumentoService}")
	private MaeTipoDocumentoService maeTipoDocumentoService;

	@ManagedProperty(value = "#{maePerfilService}")
	private MaePerfilService maePerfilService;

	@ManagedProperty(value = "#{seguridadService}")
	private SeguridadService seguridadService;
	
	@ManagedProperty(value = "#{cfgPuntoControlService}")
	private CfgPuntoControlService cfgPuntoControlService;
	
	@ManagedProperty(value = "#{trabajadorSigaService}")
	private TrabajadorSigaService trabajadorSigaService;
	
	@ManagedProperty(value = "#{unidadEjecutoraService}")
	private VUnidadEjecutoraService vUnidadEjecutoraService;
	
	@ManagedProperty(value = "#{corteService}")
	private VCorteService vCorteService;
	
	private Long filtroTipoDocumento;
	private String filtroNumeroDocumento;
	private String filtroNombres;
	private String filtroPaterno;
	private String filtroMaterno;
	
	private String filtroEstado;
	private Long paramTipoDocumento;
	private String paramNumeroDocumento;
	
	private Long tamanio;
	private boolean numeroDocumentoHabilitado;

	private List<UsuarioDto> listaUsuario;
	private UsuarioDto usuarioSeleccion;
	private FormularioOpcion formulario;
	private UsuarioDto usuario;

	private List<TipoDocumentoDto> listaTipoDocumento;
	private List<PerfilDto> listaPerfil;
	private PuntoControlDto puntoSeleccionada;
	private List<PuntoControlDto> listaPuntos;
	
	private List<LocalDto> listaLocal;
	
	private String filtroNombrePuntoControl;
	private String filtroLocal;
	private String filtroOficina;
	private String filtroIngreso;
	private Long filtroPerfil;
	private TrabajadorDto trabajador; 
	private UsuarioDto usuarioLogueado;
	private String filtroNombreCompleto;
	private List<CorteDto> listaCortes;
	private String estiloCorte;
	
	@PostConstruct
	public void init() {
		try {
			
			boolean navegadorPermitido = validarNavegador();
			if( navegadorPermitido ) {
				if( !verificarPermiso( ConstantesVisitas.ACTION_MANTENIMIENTO_USUARIO)  ){
					this.noTienePermisos();
				}else{
				
					usuarioLogueado=getSessionController().getUsuarioSession();
					filtroEstado = ParametrosDeBusqueda.INDICADOR_ACTIVO;
					
					cargarTipoDocumento();
					//TODO 20180621 MEU Agrega lista de Cortes
					listaCortes = vCorteService.listarCortes(obtenerAnioActual());
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					this.listaPerfil = maePerfilService.buscarPerfil();
					this.listaLocal=cfgPuntoControlService.listarLocales
							(this.getSessionController().getUsuarioSession().getIdUnidadEjecutora(),
									this.getSessionController().getUsuarioSession().getIdCorte());
					buscar();
					
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
			e.printStackTrace();
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
			this.listaTipoDocumento = maeTipoDocumentoService.buscar(parametrosBusqueda);
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
		}
	}
	
	public void buscar() {
		try {
			
			boolean minimoFiltro = false;
			
			Map<String, Object> parametrosBusqueda = new HashMap<>();
			if (filtroTipoDocumento != null && filtroTipoDocumento.longValue() > 0) {
				parametrosBusqueda.put(ParametrosDeBusqueda.USUA_TIPO_DOC, filtroTipoDocumento);
				minimoFiltro = true;
			} else {
				List<Long> tipoDocumentoPermitido = new ArrayList<Long>();
				tipoDocumentoPermitido.add(ConstantesVisitas.NUM_DOC_DNI);
				tipoDocumentoPermitido.add(ConstantesVisitas.NUM_DOC_CE);
				parametrosBusqueda.put(ParametrosDeBusqueda.NOT_USUA_TIPO_DOC, tipoDocumentoPermitido);
			}			
			if (!ValidarUtil.isNull(filtroNumeroDocumento)) {
				parametrosBusqueda.put(ParametrosDeBusqueda.USUA_NRO_DOC, filtroNumeroDocumento.trim());
				minimoFiltro = true;
			}
			if (!ValidarUtil.isNull(filtroNombreCompleto)) {
				parametrosBusqueda.put(ParametrosDeBusqueda.USUA_NOMBRE_COMPLETO, "%"+filtroNombreCompleto.trim().toUpperCase()+"%");
				minimoFiltro = true;
			}
			if (!ValidarUtil.isNull(filtroEstado)) {
				parametrosBusqueda.put(ParametrosDeBusqueda.USUA_ESTADO, filtroEstado);
				minimoFiltro = true;
			}

			if (Long.compare( this.getSessionController().getUsuarioSession().getPerfilDto().getnIdPerfil(), ConstantesVisitas.PERFIL_ADMINISTRADOR_SISTEMA)==0) {
				if (filtroPerfil != null && filtroPerfil.longValue() > 0) {
					parametrosBusqueda.put(ParametrosDeBusqueda.USUA_PERFIL, filtroPerfil);
					minimoFiltro = true;
				}
			}
			
			//parametrosBusqueda.put(ParametrosDeBusqueda.USUAR_UNIDAD_EJECUTORA, getSessionController().getUsuarioSession().getIdUnidadEjecutora());
			parametrosBusqueda.put(ParametrosDeBusqueda.USUA_CORTE, getSessionController().getUsuarioSession().getIdCorte());
			
			if (Long.compare( this.getSessionController().getUsuarioSession().getPerfilDto().getnIdPerfil(), ConstantesVisitas.PERFIL_ADMINISTRADOR_CORTE)==0) {
				parametrosBusqueda.put(ParametrosDeBusqueda.USUA_PERFIL, ConstantesVisitas.PERFIL_OPERADOR);
			}
			minimoFiltro = true;
			
			if( minimoFiltro ) {
				this.listaUsuario = maeUsuarioService.buscar(parametrosBusqueda);
			}else {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Proporcione por lo menos un filtro de búsqueda");
			}
			
			this.usuarioSeleccion = null;
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
			e.printStackTrace();
		}
	}

	public void nuevo() {
		try {
			usuario=null;
			this.formulario = FormularioOpcion.ADD;
			PrimeFaces.current().resetInputs("frmUsuario");

			this.usuario = new UsuarioDto();
			
			if( Long.compare( this.getSessionController().getUsuarioSession().getPerfilDto().getnIdPerfil(), ConstantesVisitas.PERFIL_ADMINISTRADOR_CORTE)==0 ){
				this.usuario.setDescripcionPerfilRegistro(ConstantesVisitas.DESCRIPCION_PERFIL_OPERADOR);
			}else if( Long.compare( this.getSessionController().getUsuarioSession().getPerfilDto().getnIdPerfil(), ConstantesVisitas.PERFIL_ADMINISTRADOR_SISTEMA)==0 ){
				this.usuario.setDescripcionPerfilRegistro(ConstantesVisitas.DESCRIPCION_PERFIL_ADMINISTRADOR_CORTE);
			}
			
			this.usuario.setLbEstadoRegistro(true);
			this.usuario.setTipoDocumentoDto(new TipoDocumentoDto());
			this.usuario.setPerfilDto(new PerfilDto());
			this.trabajador = new TrabajadorDto();
			this.estiloCorte="";
			
			PrimeFaces.current().executeScript("PF('dlgUsuario').show();");
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}
	
	public void editar() {
		try {
			this.formulario = FormularioOpcion.EDIT;
			PrimeFaces.current().resetInputs("frmUsuario");
			
			if (usuarioSeleccion != null) {				
				this.usuario = (UsuarioDto) BeanUtils.cloneBean(usuarioSeleccion);
				UnidadEjecutoraDto unidadEjecutora=null;
				CorteDto corte=null;
				try {
					 unidadEjecutora = vUnidadEjecutoraService.obtenerUnidadEjecutora(obtenerAnioActual(), this.usuario.getIdUnidadEjecutora());
				}
				catch(Exception e) {
					addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorConsultaUnidadEjecutora);
					return;
				}
				
				try {
					 corte=vCorteService.obtenerCorte(obtenerAnioActual(), this.usuario.getIdUnidadEjecutora(), this.usuario.getIdCorte());
				}
				catch(Exception e) {
					addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorConsultaCorte);
					return;
				}
				
				usuario.setDescripcionUnidadEjecutora(unidadEjecutora.getNombreUniEje());
				usuario.setDescripcionCorte(corte.getNombreCorte());
				
				if( Long.compare( this.getSessionController().getUsuarioSession().getPerfilDto().getnIdPerfil(), ConstantesVisitas.PERFIL_ADMINISTRADOR_CORTE)==0 ){
					this.usuario.setDescripcionPerfilRegistro(ConstantesVisitas.DESCRIPCION_PERFIL_OPERADOR);
				}else if( Long.compare( this.getSessionController().getUsuarioSession().getPerfilDto().getnIdPerfil(), ConstantesVisitas.PERFIL_ADMINISTRADOR_SISTEMA)==0 ){
					this.usuario.setDescripcionPerfilRegistro(ConstantesVisitas.DESCRIPCION_PERFIL_ADMINISTRADOR_CORTE);
				}
				
				usuario.setLbEstadoRegistro((usuario.getlFlagActivo().equals(ParametrosDeBusqueda.INDICADOR_ACTIVO)) ? true : false);
				usuario.setFlagCambiarClave(false);
				validarCorte();
				
				PrimeFaces.current().executeScript("PF('dlgUsuario').show();");
			} else {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.registroNoSeleccionado);
			}
		} catch (Exception excepcion) {
			logger.error(excepcion.getMessage());
		}
	}
	
	private boolean existeUsuario() {
		boolean respuesta = false;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put(ParametrosDeBusqueda.USUA_TIPO_DOC, this.usuario.getTipoDocumentoDto().getIdTipoDocumento());
			map.put(ParametrosDeBusqueda.USUA_NRO_DOC, this.usuario.getxNroDocumento());
			map.put(ParametrosDeBusqueda.USUA_ESTADO, "");
			List<UsuarioDto> list = maeUsuarioService.buscar(map);
			if (list != null && list.size() > 0) {
				respuesta = Boolean.TRUE;
			}
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
		return respuesta;
	}
		
	public void listenerTipoDocumento(){

		this.usuario.setxNroDocumento("");
		this.usuario.setxNombre("");
		this.usuario.setxApellidoPaterno("");
		this.usuario.setxApellidoMaterno("");
		this.usuario.setxCodigoSiga("");
		
		if(!ValidarUtil.isNullOrEmpty(this.usuario.getTipoDocumentoDto().getEquivalencia())){
			if (this.usuario.getTipoDocumentoDto().getEquivalencia().equals(ConstantesVisitas.DNI_SIGA) ){
				tamanio = ConstantesVisitas.tamanioDNI;
				numeroDocumentoHabilitado = true;
			} else if ( this.usuario.getTipoDocumentoDto().getEquivalencia().equals(ConstantesVisitas.CARNET_EXTRANJERIA_SIGA) ){
				tamanio = ConstantesVisitas.tamanioCE;
				numeroDocumentoHabilitado = true;
			} else if ( this.usuario.getTipoDocumentoDto().getEquivalencia().equals(ConstantesVisitas.PASAPORTE_SIGA) ){
				tamanio = ConstantesVisitas.tamanioPAS;
				numeroDocumentoHabilitado = true;
			} else{
				tamanio = ConstantesVisitas.LONG_CERO;
				numeroDocumentoHabilitado = false;
			}
		}else {
			tamanio = ConstantesVisitas.LONG_CERO;
			numeroDocumentoHabilitado = false;
		}
	}

	public void buscarUsuarioSiga() {
		try {
			PrimeFaces.current().resetInputs("frmUsuario");
			
			if( ValidarUtil.isNull(usuario.getTipoDocumentoDto().getEquivalencia()) ) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.faltaTipoDocumento);
			}else if( ValidarUtil.isNullOrEmpty(usuario.getxNroDocumento()) ) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.faltaNumeroDocumento);
			}else{
			
				if(!validarBusquedaUsuarioSiga(usuario)) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaUsuarioBuscadoIgualLogueado);
					return;
				}
				if (existeUsuario()) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.existeUsuario);
					return;
				}
				//TODO 20180621 MEU Carga de trabadores de todas las cortes 
				/*	trabajador = trabajadorSigaService.consultarTrabajador
							(usuario.getTipoDocumentoDto().getEquivalencia()
							,usuario.getxNroDocumento()
							,(usuario.getxNombre()==null ? "" : usuario.getxNombre())
							,obtenerAnioActual()
							,(getSessionController().getUsuarioSession().getIdUnidadEjecutora()==null ? "" :getSessionController().getUsuarioSession().getIdUnidadEjecutora()) 
							,(getSessionController().getUsuarioSession().getIdCorte()==null ? "" : getSessionController().getUsuarioSession().getIdCorte())
							,(usuario.getCargo()==null?"":usuario.getCargo())); */
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					trabajador = trabajadorSigaService.consultarTrabajador
							(usuario.getTipoDocumentoDto().getEquivalencia()
							,usuario.getxNroDocumento()
							,(usuario.getxNombre()==null ? "" : usuario.getxNombre())
							,obtenerAnioActual()
							,"" ,"" ,(usuario.getCargo()==null?"":usuario.getCargo()));
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////					
					
					if (trabajador != null) {
						
						TipoDocumentoDto tipoDocumento= maeTipoDocumentoService.buscarXEquivalencia(usuario.getTipoDocumentoDto().getEquivalencia());
						usuario.setTipoDocumentoDto(tipoDocumento);
						usuario.setxCodigoSiga(trabajador.getIdppicodigo());
						usuario.setxNombre(trabajador.getMppinombres());
						usuario.setxApellidoPaterno(trabajador.getMppiappater());
						usuario.setxApellidoMaterno(trabajador.getMppiapmater());
						
						UnidadEjecutoraDto unidadEjecutora = vUnidadEjecutoraService.obtenerUnidadEjecutora(obtenerAnioActual(), trabajador.getId_uni_eje());
						CorteDto corte=vCorteService.obtenerCorte(obtenerAnioActual(), trabajador.getId_uni_eje(), trabajador.getIdCorte());
						usuario.setDescripcionUnidadEjecutora(unidadEjecutora.getNombreUniEje());
						usuario.setDescripcionCorte(corte.getNombreCorte());
						usuario.setIdCorte(trabajador.getIdCorte());
						usuario.setIdUnidadEjecutora(trabajador.getId_uni_eje());
						usuario.setCorreo(trabajador.getCorreo());
						
						if (trabajador.getVlpestado().equals("V")) {
							usuario.setlFlagActivo("1");
						} else {
							usuario.setlFlagActivo("0");
						}
						validarCorte();
					} else {
						usuario.setxCodigoSiga("");
						usuario.setxNombre("");
						usuario.setxApellidoPaterno("");
						usuario.setxApellidoMaterno("");
						usuario.setlFlagActivo("0");
						usuario.setDescripcionUnidadEjecutora("");
						usuario.setDescripcionCorte("");
						usuario.setCorreo("");
						addInfoMessage(Constantes.mensajeInformativoTitulo, ConstantesMensaje.noExisteUsuarioBuscado);
					}
					usuario.setLbEstadoRegistro((usuario.getlFlagActivo().equals(ParametrosDeBusqueda.INDICADOR_ACTIVO)) ? true : false);
				
			}
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}
	
	private boolean validarBusquedaUsuarioSiga(UsuarioDto usu) {
		
		if (getSessionController().getUsuarioSession().getTipoDocumentoDto().getEquivalencia().equals(usu.getTipoDocumentoDto().getEquivalencia())&&
			getSessionController().getUsuarioSession().getxNroDocumento().equals(usu.getxNroDocumento())){
			return false;
		}
		return true;
	}
	
	public void guardar() {
		try {
			if (usuario != null) {

				if (formulario == FormularioOpcion.ADD) {
					if (ValidarUtil.isNullOrEmpty(this.usuario.getxNroDocumento())) {
						addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.faltaBuscarUsuario);
						return;
					}
				}
				completarDatosAuditoria();
				usuario.setlFlagActivo((usuario.isLbEstadoRegistro()) ? ParametrosDeBusqueda.INDICADOR_ACTIVO : ParametrosDeBusqueda.INDICADOR_INACTIVO);
				
				if( Long.compare( this.getSessionController().getUsuarioSession().getPerfilDto().getnIdPerfil(), ConstantesVisitas.PERFIL_ADMINISTRADOR_CORTE)==0 ){
					PerfilDto perfil = new PerfilDto();
					perfil.setnIdPerfil( ConstantesVisitas.PERFIL_OPERADOR );
					usuario.setPerfilDto(perfil);
					String idCorteActual = this.getSessionController().getUsuarioSession().getIdCorte();
					String idCorteSeleccionada = this.usuario.getIdCorte();
					if(!idCorteSeleccionada.equals(idCorteActual)) {
						addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaSelCorteIncorrecta);
						return;
					}
					
				}else if( Long.compare( this.getSessionController().getUsuarioSession().getPerfilDto().getnIdPerfil(), ConstantesVisitas.PERFIL_ADMINISTRADOR_SISTEMA)==0 ){
					PerfilDto perfil = new PerfilDto();
					perfil.setnIdPerfil( ConstantesVisitas.PERFIL_ADMINISTRADOR_CORTE );
					usuario.setPerfilDto(perfil);
				}
				
				if (formulario == FormularioOpcion.ADD) {
					usuario.setxPassword( CryptoUtil.generarClaveVisual(8) );					
					maeUsuarioService.guardar(usuario);
					addInfoMessage(Constantes.mensajeInformativoTitulo, ConstantesMensaje.registroCorrecto);
					PrimeFaces.current().executeScript("PF('dlgConstancia').show();");
					buscar();

				} else if (formulario == FormularioOpcion.EDIT) {
					maeUsuarioService.modificar(usuario);
					addInfoMessage(Constantes.mensajeInformativoTitulo, ConstantesMensaje.modificacionCorrecto);
					PrimeFaces.current().executeScript("PF('dlgUsuario').hide();");
					buscar();
				}
			} else {
				addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.operacionFallida);
			}
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}
	
	public void reiniciarValores() {
		filtroPerfil=0L;
		filtroEstado="";
		filtroNombreCompleto="";
		filtroTipoDocumento=0L;
		filtroNumeroDocumento="";
	}
	
	public void aceptarConstancia(){		
		PrimeFaces.current().executeScript("PF('dlgUsuario').hide();");
		PrimeFaces.current().executeScript("PF('dlgConstancia').hide();");
		buscar();
	}
	
	public void cerrarRegistroUsuario() {
		this.usuario = null;
		PrimeFaces.current().executeScript("PF('dlgUsuario').hide();");
	}
	
	public void completarDatosAuditoria() {
		usuario.setxIpOperacion(CommonsUtilities.getRemoteIpAddress());
		usuario.setfOperacion(FechaUtil.currentJavaUtilDate());
		usuario.setnUsuarioOperacion(getSessionController().getUsuarioSession().getnIdUsuario());
	}

	public void restaurarFormulario() {
		PrimeFaces.current().resetInputs("frmUsuario");
	}

	/** 
	* Descripción: Permite abrir ventana de Confirmacion Reseteo de Clave.
	* @param No aplica
	* @return No aplica.
	* @exception No aplica.
	*/
	public void abrirConfirmacionRC() {
		logger.info("ingreso abrirConfirmacionRC");
		PrimeFaces.current().executeScript("PF('dlgConfirmacionRC').show();");
	}
	
	/** 
	* Descripción: Permite resetear la clave y mostrar popup con el resultado.
	* @param No aplica
	* @return No aplica.
	* @exception Manejo de errores no clasificados.
	*/
	public void resetearClave() {
		if (usuarioSeleccion != null) {				
			try {
				this.usuario = (UsuarioDto) BeanUtils.cloneBean(usuarioSeleccion);
				this.usuario.setxPassword(ConstantesVisitas.RESETEO_CLAVE);
				this.usuario.setFechaReseteoClave(Calendar.getInstance().getTime());
				this.usuario.setlFlagReseteo(ConstantesVisitas.ACTIVO);
				maeUsuarioService.resetearClave(usuario);
				addInfoMessage(Constantes.mensajeInformativoTitulo, ConstantesMensaje.modificacionCorrecto);
				logger.info("Revision IdUsuario="+this.usuario.getnIdUsuario());
				PrimeFaces.current().executeScript("PF('dlgResetearClave').show();");
				//buscar();
			} catch (Exception e) {
				logger.error("Problema confirmarResetearClave mensaje="+e.getMessage());
			} 
			
		} else {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.registroNoSeleccionado);
		}
	}
	
	public void validarCorte() {
		Long idPerfil = this.getSessionController().getUsuarioSession().getPerfilDto().getnIdPerfil();
		String idCorteActual = this.getSessionController().getUsuarioSession().getIdCorte();
		String idCorteSeleccionada = this.usuario.getIdCorte();
		estiloCorte = "";
		
		if( Long.compare( idPerfil, ConstantesVisitas.PERFIL_ADMINISTRADOR_CORTE)==0 ){
			if(!idCorteSeleccionada.equals(idCorteActual)) {
				estiloCorte = "red-text-custom";
			}
		}
	}
	

	public MaeUsuarioService getMaeUsuarioService() {
		return maeUsuarioService;
	}

	public void setMaeUsuarioService(MaeUsuarioService maeUsuarioService) {
		this.maeUsuarioService = maeUsuarioService;
	}

	public MaeTipoDocumentoService getMaeTipoDocumentoService() {
		return maeTipoDocumentoService;
	}

	public void setMaeTipoDocumentoService(MaeTipoDocumentoService maeTipoDocumentoService) {
		this.maeTipoDocumentoService = maeTipoDocumentoService;
	}

	public String getFiltroNumeroDocumento() {
		return filtroNumeroDocumento;
	}

	public void setFiltroNumeroDocumento(String filtroNumeroDocumento) {
		this.filtroNumeroDocumento = filtroNumeroDocumento;
	}

	public String getFiltroEstado() {
		return filtroEstado;
	}

	public void setFiltroEstado(String filtroEstado) {
		this.filtroEstado = filtroEstado;
	}

	public List<UsuarioDto> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<UsuarioDto> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public UsuarioDto getUsuarioSeleccion() {
		return usuarioSeleccion;
	}

	public void setUsuarioSeleccion(UsuarioDto usuarioSeleccion) {
		this.usuarioSeleccion = usuarioSeleccion;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public List<TipoDocumentoDto> getListaTipoDocumento() {
		return listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<TipoDocumentoDto> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}

	public List<PerfilDto> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<PerfilDto> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public Long getFiltroTipoDocumento() {
		return filtroTipoDocumento;
	}

	public void setFiltroTipoDocumento(Long filtroTipoDocumento) {
		this.filtroTipoDocumento = filtroTipoDocumento;
	}

	public FormularioOpcion getFormulario() {
		return formulario;
	}

	public void setFormulario(FormularioOpcion formulario) {
		this.formulario = formulario;
	}

	public Long getParamTipoDocumento() {
		return paramTipoDocumento;
	}

	public void setParamTipoDocumento(Long paramTipoDocumento) {
		this.paramTipoDocumento = paramTipoDocumento;
	}

	public String getParamNumeroDocumento() {
		return paramNumeroDocumento;
	}

	public void setParamNumeroDocumento(String paramNumeroDocumento) {
		this.paramNumeroDocumento = paramNumeroDocumento;
	}

	public MaePerfilService getMaePerfilService() {
		return maePerfilService;
	}

	public void setMaePerfilService(MaePerfilService maePerfilService) {
		this.maePerfilService = maePerfilService;
	}

	public SeguridadService getSeguridadService() {
		return seguridadService;
	}

	public void setSeguridadService(SeguridadService seguridadService) {
		this.seguridadService = seguridadService;
	}

	public List<PuntoControlDto> getListaPuntos() {
		return listaPuntos;
	}

	public void setListaPuntos(List<PuntoControlDto> listaPuntos) {
		this.listaPuntos = listaPuntos;
	}

	public PuntoControlDto getPuntoSeleccionada() {
		return puntoSeleccionada;
	}

	public void setPuntoSeleccionada(PuntoControlDto puntoSeleccionada) {
		this.puntoSeleccionada = puntoSeleccionada;
	}

	public CfgPuntoControlService getCfgPuntoControlService() {
		return cfgPuntoControlService;
	}

	public void setCfgPuntoControlService(CfgPuntoControlService cfgPuntoControlService) {
		this.cfgPuntoControlService = cfgPuntoControlService;
	}

	public TrabajadorSigaService getTrabajadorSigaService() {
		return trabajadorSigaService;
	}

	public void setTrabajadorSigaService(TrabajadorSigaService trabajadorSigaService) {
		this.trabajadorSigaService = trabajadorSigaService;
	}

	public String getFiltroLocal() {
		return filtroLocal;
	}

	public void setFiltroLocal(String filtroLocal) {
		this.filtroLocal = filtroLocal;
	}

	public String getFiltroOficina() {
		return filtroOficina;
	}

	public void setFiltroOficina(String filtroOficina) {
		this.filtroOficina = filtroOficina;
	}

	public String getFiltroIngreso() {
		return filtroIngreso;
	}

	public void setFiltroIngreso(String filtroIngreso) {
		this.filtroIngreso = filtroIngreso;
	}

	public String getFiltroNombrePuntoControl() {
		return filtroNombrePuntoControl;
	}

	public void setFiltroNombrePuntoControl(String filtroNombrePuntoControl) {
		this.filtroNombrePuntoControl = filtroNombrePuntoControl;
	}

	public String getFiltroNombres() {
		return filtroNombres;
	}

	public void setFiltroNombres(String filtroNombres) {
		this.filtroNombres = filtroNombres;
	}

	public String getFiltroPaterno() {
		return filtroPaterno;
	}

	public void setFiltroPaterno(String filtroPaterno) {
		this.filtroPaterno = filtroPaterno;
	}

	public String getFiltroMaterno() {
		return filtroMaterno;
	}

	public void setFiltroMaterno(String filtroMaterno) {
		this.filtroMaterno = filtroMaterno;
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

	public UsuarioDto getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(UsuarioDto usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}
	

	public List<LocalDto> getListaLocal() {
		return listaLocal;
	}
	
	public void setListaLocal(List<LocalDto> listaLocal) {
		this.listaLocal = listaLocal;
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

	public Long getFiltroPerfil() {
		return filtroPerfil;
	}

	public void setFiltroPerfil(Long filtroPerfil) {
		this.filtroPerfil = filtroPerfil;
	}

	public String getFiltroNombreCompleto() {
		return filtroNombreCompleto;
	}

	public void setFiltroNombreCompleto(String filtroNombreCompleto) {
		this.filtroNombreCompleto = filtroNombreCompleto;
	}

	public List<CorteDto> getListaCortes() {
		return listaCortes;
	}

	public void setListaCortes(List<CorteDto> listaCortes) {
		this.listaCortes = listaCortes;
	}

	public String getEstiloCorte() {
		return estiloCorte;
	}

	public void setEstiloCorte(String estiloCorte) {
		this.estiloCorte = estiloCorte;
	}
	
	
	
		
}
