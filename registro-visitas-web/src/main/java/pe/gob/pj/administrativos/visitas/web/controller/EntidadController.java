package pe.gob.pj.administrativos.visitas.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.pj.administrativos.visitas.model.bean.ParamConfigPideBean;
import pe.gob.pj.administrativos.visitas.model.client.ConstantesConfig;
import pe.gob.pj.administrativos.visitas.model.dto.EntidadDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesMensaje;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.model.util.FormularioOpcion;
import pe.gob.pj.administrativos.visitas.model.util.ParametrosDeBusqueda;
import pe.gob.pj.administrativos.visitas.model.util.Utilitarios;
import pe.gob.pj.administrativos.visitas.service.MaeEntidadService;
import pe.gob.pj.administrativos.visitas.service.PideWsService;
import pe.gob.pj.api.commons.constants.Constantes;
import pe.gob.pj.api.commons.constants.sicau.ConstantesSicau;
import pe.gob.pj.api.commons.utility.CommonsUtilities;
import pe.gob.pj.api.commons.utility.FechaUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;
import pe.gob.pj.pidews.type.RequestConsultarDatosPrincipalesRUCType;
import pe.gob.pj.pidews.type.ResponseConsultarDatosPrincipalesRUCType;
import pe.gob.pj.pidews.ws.Auditoria;
import pe.gob.pj.pidews.ws.Seguridad;

@ViewScoped
@ManagedBean(name = "entidadMB")
public class EntidadController  extends BaseController implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(EntidadController.class.getName());
	
	private String filtroRazonSocial;
	
	private String filtroRuc;
	
	private String filtroRucPide;
	
	private EntidadDto entidadSeleccion;
	
	private List<EntidadDto> listaEntidades;
	
	private FormularioOpcion formulario;
	
	private EntidadDto entidad;
	
	@ManagedProperty(value = "#{entidadService}")
	private MaeEntidadService maeEntidadService;
	
	@ManagedProperty(value="#{pideWsService}")
	private PideWsService pideWsService;
	
	
	@PostConstruct
	public void init() {
		try {
			entidad = new EntidadDto();
			iniciarVariables();
			listar();

		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
		}
	}
	
	private void iniciarVariables() {
		entidadSeleccion=null;
		
	}
	
	
	public void buscar() {
		listar();
	}
	
	public void nuevo() {
		try {
			entidad = new EntidadDto();
			filtroRucPide="";
			this.entidad.setLbEstadoRegistro(true);
			this.formulario = FormularioOpcion.ADD;
			
			PrimeFaces.current().resetInputs("frmEntidad");
			PrimeFaces.current().executeScript("PF('dlgEntidad').show();");
		
			
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}
	
	public void listar() {

		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		
			
		if (!ValidarUtil.isNullOrEmpty(filtroRazonSocial)) {
			parametrosBusqueda.put(ParametrosDeBusqueda.EN_RAZSO, "%"+filtroRazonSocial.trim().toUpperCase()+"%");
		}
		
		if (!ValidarUtil.isNullOrEmpty(filtroRuc)) {
			parametrosBusqueda.put(ParametrosDeBusqueda.EN_RUC, "%"+filtroRuc+"%");
		}
		
		parametrosBusqueda.put(ParametrosDeBusqueda.EN_ESTADO, ConstantesVisitas.ACTIVO);
				
		try {
		listaEntidades= maeEntidadService.listar(parametrosBusqueda);
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
			addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorBuscarTipoMotivo);
		}
	}
	
	public void editar() {
		try {
		this.formulario = FormularioOpcion.EDIT;
		PrimeFaces.current().resetInputs("frmEntidad");
		this.entidad = (EntidadDto) BeanUtils.cloneBean(entidadSeleccion);
		entidad.setLbEstadoRegistro((entidad.getEstado().equals(ParametrosDeBusqueda.INDICADOR_ACTIVO)) ? true : false);
		PrimeFaces.current().executeScript("PF('dlgEntidad').show();");
		
		}catch (Exception excepcion) {
			logger.error(excepcion.getMessage());
		}
	}
	
	
	
	public boolean validarExistenciaEntidad(String ruc) {
		boolean val=false;
		for (EntidadDto en: listaEntidades) {
			if (en.getRuc().equals(ruc)) {
				val=true;
			}
		}
		
		return val;
	}
	
	public void buscarPide() {
		
		if( !ValidarUtil.isNullOrEmpty(filtroRucPide) ) {
			
			
			if(validarExistenciaEntidad(filtroRucPide)) {
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaYaExisteEntidad);
				return;
			}
			
			verificarRUC(filtroRucPide);
		
		
		
		}else {
			addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaIngresarRucBusqueda);
			return;
		}
		
	}
	
	public EntidadDto verificarRUC(String ruc) {
		
		try{
			
			

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
					}else{
						addWarnMessage(Constantes.mensajeAdvertenciaTitulo, responsePide.getMensaje().getDescripcion());
					}
				}else{
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.noInformacionEntidadRuc);
				}
			
			
			
			return entidad;
			
		}catch(Exception excepcion){					
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN,
					 this.getClass().getName(),
					 excepcion) );
		}
		
		return entidad;
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
	
	public void guardar() {
		
		try{
		
			if (entidad!=null) {
				
				
				if(ValidarUtil.isNullOrEmpty(entidad.getRuc())) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaIngresarRuc);
					return;
				}
				
				if(ValidarUtil.isNullOrEmpty(entidad.getRazonSocial())) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaIngresarRazonSocial);
					return;
				}
				
				
				completarDatosAuditoria();
				entidad.setEstado(ConstantesVisitas.ACTIVO);
				
				
				if (formulario == FormularioOpcion.ADD) {
					
					
					maeEntidadService.ingresar(entidad);
					addInfoMessage(Constantes.mensajeInformativoTitulo, ConstantesMensaje.registroCorrecto);
					PrimeFaces.current().executeScript("PF('dlgEntidad').hide();");
			
				}
				
				listar();
				
				
			}
			
		}catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}
	
	public void completarDatosAuditoria() {
		entidad.setFechaRegistro(FechaUtil.currentJavaUtilDate());
		entidad.setxIpOperacion(CommonsUtilities.getRemoteIpAddress());
		entidad.setfOperacion(FechaUtil.currentJavaUtilDate());
		entidad.setnUsuarioOperacion(getSessionController().getUsuarioSession().getnIdUsuario());
	}
	
	public void cerrarRegistroUsuario() {
		this.entidad = null;
		PrimeFaces.current().executeScript("PF('dlgEntidad').hide();");
	}

	public String getFiltroRazonSocial() {
		return filtroRazonSocial;
	}

	public void setFiltroRazonSocial(String filtroRazonSocial) {
		this.filtroRazonSocial = filtroRazonSocial;
	}

	public String getFiltroRuc() {
		return filtroRuc;
	}

	public void setFiltroRuc(String filtroRuc) {
		this.filtroRuc = filtroRuc;
	}



	public EntidadDto getEntidadSeleccion() {
		return entidadSeleccion;
	}

	public void setEntidadSeleccion(EntidadDto entidadSeleccion) {
		this.entidadSeleccion = entidadSeleccion;
	}

	public List<EntidadDto> getListaEntidades() {
		return listaEntidades;
	}

	public void setListaEntidades(List<EntidadDto> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}

	public FormularioOpcion getFormulario() {
		return formulario;
	}

	public void setFormulario(FormularioOpcion formulario) {
		this.formulario = formulario;
	}

	public EntidadDto getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadDto entidad) {
		this.entidad = entidad;
	}

	public MaeEntidadService getMaeEntidadService() {
		return maeEntidadService;
	}

	public void setMaeEntidadService(MaeEntidadService maeEntidadService) {
		this.maeEntidadService = maeEntidadService;
	}

	public String getFiltroRucPide() {
		return filtroRucPide;
	}

	public void setFiltroRucPide(String filtroRucPide) {
		this.filtroRucPide = filtroRucPide;
	}

	public PideWsService getPideWsService() {
		return pideWsService;
	}

	public void setPideWsService(PideWsService pideWsService) {
		this.pideWsService = pideWsService;
	}


	
	
	
	
}
