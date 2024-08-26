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
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import pe.gob.pj.administrativos.visitas.model.dto.TipoMotivoDto;
import pe.gob.pj.administrativos.visitas.model.dto.UsuarioDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesMensaje;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.model.util.FormularioOpcion;
import pe.gob.pj.administrativos.visitas.model.util.ParametrosDeBusqueda;
import pe.gob.pj.administrativos.visitas.service.MaeTipoMotivoService;
import pe.gob.pj.api.commons.constants.Constantes;
import pe.gob.pj.api.commons.utility.CommonsUtilities;
import pe.gob.pj.api.commons.utility.CryptoUtil;
import pe.gob.pj.api.commons.utility.FechaUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;

@ViewScoped
@ManagedBean(name = "tipoMotivoMB")
public class TipoMotivoController  extends BaseController implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(TipoMotivoController.class.getName());
	
	private String filtroDescripcion;
	
	private String filtroEstado;
	
	private TipoMotivoDto tipoMotivoSeleccion;
	
	private List<TipoMotivoDto> listaTipoMotivo;
	
	private FormularioOpcion formulario;
	
	private TipoMotivoDto tipoMotivo;
	
	@ManagedProperty(value = "#{tipoMotivoService}")
	private MaeTipoMotivoService maeTipoMotivoService;
	
	
	
	@PostConstruct
	public void init() {
		try {
			tipoMotivo = new TipoMotivoDto();
			iniciarVariables();
			listar();

		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
		}
	}
	
	private void iniciarVariables() {
		tipoMotivoSeleccion=null;
	}
	
	
	public void buscar() {
		listar();
	}
	
	public void nuevo() {
		try {
			tipoMotivo = new TipoMotivoDto();
			this.tipoMotivo.setLbEstadoRegistro(true);
			this.formulario = FormularioOpcion.ADD;
			PrimeFaces.current().resetInputs("frmTipoMotivo");
			PrimeFaces.current().executeScript("PF('dlgTipoMotivo').show();");
		
			
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}
	
	public void listar() {

		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		
			
		if (!ValidarUtil.isNullOrEmpty(filtroDescripcion)) {
			parametrosBusqueda.put(ParametrosDeBusqueda.TM_DESCRIPCION, filtroDescripcion.trim().toUpperCase());
		}
		
		if (!ValidarUtil.isNullOrEmpty(filtroEstado)) {
			parametrosBusqueda.put(ParametrosDeBusqueda.TM_ESTADO, filtroEstado);
		}
				
		try {
		listaTipoMotivo= maeTipoMotivoService.listarFiltros(parametrosBusqueda);
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
			addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorBuscarTipoMotivo);
		}
	}
	
	public void editar() {
		try {
		this.formulario = FormularioOpcion.EDIT;
		PrimeFaces.current().resetInputs("frmTipoMotivo");
		this.tipoMotivo = (TipoMotivoDto) BeanUtils.cloneBean(tipoMotivoSeleccion);
		tipoMotivo.setLbEstadoRegistro((tipoMotivo.getEstado().equals(ParametrosDeBusqueda.INDICADOR_ACTIVO)) ? true : false);
		PrimeFaces.current().executeScript("PF('dlgTipoMotivo').show();");
		
		}catch (Exception excepcion) {
			logger.error(excepcion.getMessage());
		}
	}
	
	public boolean validarExisteTipoMotivo(TipoMotivoDto tipoMotivo) {
		boolean val=false;
		for (TipoMotivoDto tm : listaTipoMotivo) {
			if (	tm.getIdTipoMotivo()!=tipoMotivo.getIdTipoMotivo()&&
					tm.getDescripcion().trim().toUpperCase().equals(tipoMotivo.getDescripcion().trim().toUpperCase())) {
				val=true;
			}
		}
		
		return val;
	}

	
	public void guardar() {
		
		try{
		
			if (tipoMotivo!=null) {
				
				if(ValidarUtil.isNullOrEmpty(tipoMotivo.getDescripcion())) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaTipoMotivoDescripcion);
					return;
				}
				
				if (validarExisteTipoMotivo(tipoMotivo)) {
					addWarnMessage(Constantes.mensajeAdvertenciaTitulo, ConstantesMensaje.advertenciaYaExisteTipoMotivo);
					return ;
				}
				
				
				completarDatosAuditoria();
				tipoMotivo.setEstado((tipoMotivo.isLbEstadoRegistro()) ? ParametrosDeBusqueda.INDICADOR_ACTIVO : ParametrosDeBusqueda.INDICADOR_INACTIVO);
				tipoMotivo.setDescripcion(tipoMotivo.getDescripcion().toUpperCase());
				
				if (formulario == FormularioOpcion.ADD) {
					
					
					
					maeTipoMotivoService.guardar(tipoMotivo);
					addInfoMessage(Constantes.mensajeInformativoTitulo, ConstantesMensaje.registroCorrecto);
					PrimeFaces.current().executeScript("PF('dlgTipoMotivo').hide();");
				
					
					

				} else if (formulario == FormularioOpcion.EDIT) {
					
					
				
					maeTipoMotivoService.modificar(tipoMotivo);
					addInfoMessage(Constantes.mensajeInformativoTitulo, ConstantesMensaje.modificacionCorrecto);
					PrimeFaces.current().executeScript("PF('dlgTipoMotivo').hide();");
					

				}
				
				buscar();
				
				
			}
			
		}catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}
	
	public void completarDatosAuditoria() {
		tipoMotivo.setFechaRegistro(FechaUtil.currentJavaUtilDate());
		tipoMotivo.setxIpOperacion(CommonsUtilities.getRemoteIpAddress());
		tipoMotivo.setfOperacion(FechaUtil.currentJavaUtilDate());
		tipoMotivo.setnUsuarioOperacion(getSessionController().getUsuarioSession().getnIdUsuario());
	}
	
	public void cerrarRegistroUsuario() {
		this.tipoMotivo = null;
		PrimeFaces.current().executeScript("PF('dlgTipoMotivo').hide();");
	}

	public String getFiltroDescripcion() {
		return filtroDescripcion;
	}

	public void setFiltroDescripcion(String filtroDescripcion) {
		this.filtroDescripcion = filtroDescripcion;
	}

	public String getFiltroEstado() {
		return filtroEstado;
	}

	public void setFiltroEstado(String filtroEstado) {
		this.filtroEstado = filtroEstado;
	}

	public TipoMotivoDto getTipoMotivoSeleccion() {
		return tipoMotivoSeleccion;
	}

	public void setTipoMotivoSeleccion(TipoMotivoDto tipoMotivoSeleccion) {
		this.tipoMotivoSeleccion = tipoMotivoSeleccion;
	}

	public List<TipoMotivoDto> getListaTipoMotivo() {
		return listaTipoMotivo;
	}

	public void setListaTipoMotivo(List<TipoMotivoDto> listaTipoMotivo) {
		this.listaTipoMotivo = listaTipoMotivo;
	}

	public FormularioOpcion getFormulario() {
		return formulario;
	}

	public void setFormulario(FormularioOpcion formulario) {
		this.formulario = formulario;
	}

	public MaeTipoMotivoService getMaeTipoMotivoService() {
		return maeTipoMotivoService;
	}

	public void setMaeTipoMotivoService(MaeTipoMotivoService maeTipoMotivoService) {
		this.maeTipoMotivoService = maeTipoMotivoService;
	}

	public TipoMotivoDto getTipoMotivo() {
		return tipoMotivo;
	}

	public void setTipoMotivo(TipoMotivoDto tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}
	
	
	
	
}
