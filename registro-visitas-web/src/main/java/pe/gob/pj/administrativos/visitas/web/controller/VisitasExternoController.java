package pe.gob.pj.administrativos.visitas.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.pj.administrativos.visitas.model.bean.CaptchaBean;
import pe.gob.pj.administrativos.visitas.model.dto.CorteDto;
import pe.gob.pj.administrativos.visitas.model.dto.VisitaDto;
import pe.gob.pj.administrativos.visitas.service.MovVisitaService;
import pe.gob.pj.administrativos.visitas.service.VCorteService;
import pe.gob.pj.administrativos.visitas.model.util.*;
import pe.gob.pj.api.commons.constants.Constantes;
import pe.gob.pj.api.commons.constants.sicau.ConstantesSicau;
import pe.gob.pj.api.commons.utility.ConvertirUtil;
import pe.gob.pj.api.commons.utility.FechaUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;

/**
* Resumen.
* Objeto : VisitasExternoController
* Descripción : Es una clase controlador de vistas que permite consultar el registro de visitas. 
* Fecha de Creación : 02/01/2019
* Autor : Carlos Arroyo
* ------------------------------------------------------------------------
* Modificaciones
* Fecha                  Nombre                     Descripción
* ------------------------------------------------------------------------
* 08/05/2019			Carlos Arroyo				CCRV: Cambio en la Consulta del Registro de Visitas
*/
@ViewScoped
@ManagedBean(name = "visitasExternoMB")
public class VisitasExternoController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(VisitasExternoController.class.getName());

	@ManagedProperty(value = "#{visitaService}")
	private MovVisitaService movVisitaService;
	
	@ManagedProperty(value = "#{captchaMB}")
	private CaptchaController captchaMB;
	
	@ManagedProperty(value = "#{corteService}")
	private VCorteService vCorteService;

	private String filtroVisitante;
	private String filtroVisitado;
	private Date filtroFechaInicio;
	private Date filtroFechaFinal;
	private String fechaMaxima;
	private String fechaMinima;
	private List<VisitaDto> listaVisitas;
	private VisitaDto visitaSeleccionada;
	private String captcha;
    private CaptchaBean captchaBean;
    
    //TODO CCRV Declara variables para la modificacion
	///////////////////////////////////////////////////////////////////////////////////
    private List<CorteDto> listaCortes;
    private String filtroDistritoJudicial;
	///////////////////////////////////////////////////////////////////////////////////

	/** TODO CRC Metodo init
	* Descripción: Permite cargar la informacion por defecto de la consulta
	* @param No aplica
	* @return No aplica.
	* @exception Manejo de errores no clasificados.
	*/
	@PostConstruct
	public void init() {
		try {
			boolean navegadorPermitido = validarNavegador();
			if( navegadorPermitido ) {
				filtroFechaInicio = FechaUtil.getSumaHoySinHora(-7);
				filtroFechaFinal = FechaUtil.getHoySinHora();
				filtroVisitante = null;
				filtroVisitado = null;
				
				fechaMaxima = ConvertirUtil.dateToString(new Date(), "dd/MM/yyyy");
				fechaMinima = ConvertirUtil.dateToString(filtroFechaInicio, "dd/MM/yyyy");
				
				Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
				
				parametrosBusqueda.put("fecha-inicio", FechaUtil.irInicioDia(filtroFechaInicio));
				parametrosBusqueda.put("fecha-fin", FechaUtil.irFinalDia(filtroFechaFinal));
								
				//TODO CCRV Carga la lista de distritos judiciales y el anio actual
				///////////////////////////////////////////////////////////////////////////////////
				listaCortes = vCorteService.listarCortes(obtenerAnioActual());
				parametrosBusqueda.put("anio", obtenerAnioActual());
				///////////////////////////////////////////////////////////////////////////////////
				listaVisitas = movVisitaService.listar(parametrosBusqueda);
				
				refrescarCaptcha();
			}
		} catch (Exception e) {
			refrescarCaptcha();
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
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

	/** TODO CRC Metodo buscarVisitas
	* Descripción: Permite consultar el registro de visitas
	* @param No aplica
	* @return No aplica.
	* @exception Manejo de errores no clasificados.
	*/
	public void buscarVisitas() {
		try {
			Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();

			if( ValidarUtil.isNull( this.captcha ) ){
				
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Ingrese el código Captcha." );
				refrescarCaptcha();
				
			}else if( !captchaMB.validarCaptcha( this.captcha, captchaBean.getSessionID() ) ) {
			
				addWarnMessage(Constantes.mensajeAdvertenciaTitulo, "Código captcha incorrecto.");
				refrescarCaptcha();
	            
	        }else{
			
				if (!ValidarUtil.isNullOrEmpty(filtroFechaInicio)) {
					parametrosBusqueda.put("fecha-inicio", FechaUtil.irInicioDia(filtroFechaInicio));
				}
	
				if (!ValidarUtil.isNullOrEmpty(filtroFechaFinal)) {
					parametrosBusqueda.put("fecha-fin", FechaUtil.irFinalDia(filtroFechaFinal));
				}
	
				if (!ValidarUtil.isNullOrEmpty(filtroVisitante)) {
					parametrosBusqueda.put("visitante", filtroVisitante.trim().toUpperCase());
				}
				
				if (!ValidarUtil.isNullOrEmpty(filtroVisitado)) {
					parametrosBusqueda.put("visitado", filtroVisitado.trim().toUpperCase());
				}
				
				//TODO CCRV Filtra por distrito judicial y carga el anio actual
				///////////////////////////////////////////////////////////////////////////////////
				if (!ValidarUtil.isNullOrEmpty(filtroDistritoJudicial)) {
					parametrosBusqueda.put("distritoJud", filtroDistritoJudicial.trim().toUpperCase());
				}
				parametrosBusqueda.put("anio", obtenerAnioActual());
				///////////////////////////////////////////////////////////////////////////////////
	
				listaVisitas = movVisitaService.listar(parametrosBusqueda);
				refrescarCaptcha();			
	        }
		} catch (Exception excepcion) {
			refrescarCaptcha();
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN,
					 this.getClass().getName(),
					 excepcion) );
		}
	}

	public void refrescarCaptcha() {  	
		try {
			captchaBean = captchaMB.generarNuevaImagen();
			captcha = "";
		}catch(Exception excepcion){
			logger.error( getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN,
					 this.getClass().getName(),
					 excepcion) );
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
	
	public String getFiltroVisitante() {
		return filtroVisitante;
	}

	public void setFiltroVisitante(String filtroVisitante) {
		this.filtroVisitante = filtroVisitante;
	}

	public String getFiltroVisitado() {
		return filtroVisitado;
	}

	public void setFiltroVisitado(String filtroVisitado) {
		this.filtroVisitado = filtroVisitado;
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

	public VisitaDto getVisitaSeleccionada() {
		return visitaSeleccionada;
	}

	public void setVisitaSeleccionada(VisitaDto visitaSeleccionada) {
		this.visitaSeleccionada = visitaSeleccionada;
	}

	public CaptchaController getCaptchaMB() {
		return captchaMB;
	}

	public void setCaptchaMB(CaptchaController captchaMB) {
		this.captchaMB = captchaMB;
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

	public List<CorteDto> getListaCortes() {
		return listaCortes;
	}

	public void setListaCortes(List<CorteDto> listaCortes) {
		this.listaCortes = listaCortes;
	}

	public VCorteService getvCorteService() {
		return vCorteService;
	}

	public void setvCorteService(VCorteService vCorteService) {
		this.vCorteService = vCorteService;
	}

	public String getFiltroDistritoJudicial() {
		return filtroDistritoJudicial;
	}

	public void setFiltroDistritoJudicial(String filtroDistritoJudicial) {
		this.filtroDistritoJudicial = filtroDistritoJudicial;
	}


	
	
}
