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
import javax.faces.event.ActionEvent;

import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.gob.pj.administrativos.visitas.model.dto.PuntoControlDto;
import pe.gob.pj.administrativos.visitas.model.dto.VisitaDto;
import pe.gob.pj.administrativos.visitas.service.CfgPuntoControlService;
import pe.gob.pj.administrativos.visitas.service.MovVisitaService;
import pe.gob.pj.administrativos.visitas.model.util.*;
import pe.gob.pj.api.commons.constants.Constantes;
import pe.gob.pj.api.commons.constants.sicau.ConstantesSicau;
import pe.gob.pj.api.commons.utility.ConvertirUtil;
import pe.gob.pj.api.commons.utility.FechaUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;

/**
* Resumen.
* Objeto : ReporteController.
* Descripción : Es una clase controlador de vistas que permite generar reporte de visitas.
* Fecha de Creación : 30/05/2019
* Autor : Carlos Arroyo
* ------------------------------------------------------------------------
* Modificaciones
* Fecha                  Nombre                     Descripción
* ------------------------------------------------------------------------
*/
@ViewScoped
@ManagedBean(name = "reporteMB")
public class ReporteController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ReporteController.class.getName());

	@ManagedProperty(value = "#{visitaService}")
	private MovVisitaService movVisitaService;
	
	@ManagedProperty(value = "#{cfgPuntoControlService}")
	private CfgPuntoControlService puntoControlService;

	private List<VisitaDto> listaVisitas;
	private VisitaDto visita;
	private String nroDocumentoVisitante;
	private String nroDocumentoVisitado;
	private String nombreCompletoVisitado;
	private String filtroEstado;
	private String equivalencia;
	private String filtroVisitante;
	private Date filtroFechaInicio;
	private Date filtroFechaFinal;
	private String fechaMaxima;
	private String fechaMinima;
	private String mensajes;
	private VisitaDto visitaSeleccionada;
	
	private List<PuntoControlDto> listaPuntoControl;
	private String filtroPuntoControl;
	private String codigoPuntoControl;
	private String descriPuntoControl;
	
	private StreamedContent reporte;
	private short optionExport;
	private boolean formatoExcel;
	private boolean formatoPdf;

	/** 
	* Descripción: Permite inicializar la ventana
	* @param No aplica
	* @return No aplica.
	* @exception Manejo de errores no clasificados.
	*/
	@PostConstruct
	public void init() {
		try {
			boolean navegadorPermitido = validarNavegador();
			if( navegadorPermitido ) {
				if( !verificarPermiso( ConstantesVisitas.ACTION_REPORTE_VISITAS)  ){
					this.noTienePermisos();
				}else{
					inicializarVariables();
					filtroFechaInicio = FechaUtil.getHoySinHora();
					filtroFechaFinal = FechaUtil.getHoySinHora();
					buscarPuntoControl();
					buscarVisitas();
				}
			}
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
		}
	}
	
	/** 
	* Descripción: Permite consultar el registro de visitas`
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
			
			if (!ValidarUtil.isNullOrEmpty(filtroPuntoControl)) {
				String[] punto = filtroPuntoControl.split("\\|");
				codigoPuntoControl = punto[0]; 
				descriPuntoControl = punto[1]; 
				parametrosBusqueda.put(ParametrosDeBusqueda.VIS_ID_PUNTO_CONTROL, new Long(codigoPuntoControl.trim()));
			}			
			
			parametrosBusqueda.put("distritoJud", getSessionController().getUsuarioSession().getIdCorte());
			parametrosBusqueda.put("anio", obtenerAnioActual());
			parametrosBusqueda.put("order", "1");
			listaVisitas = movVisitaService.listar(parametrosBusqueda);
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					Constantes.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
			addErrorMessage(Constantes.mensajeErrorTitulo, ConstantesMensaje.errorBuscarVisitas);
		}
	}

	public void inicializarVariables() {

		visita = new VisitaDto();
	}
	
	/** 
	* Descripción: Permite establecer la fecha minima de consulta
	* @param No aplica
	* @return No aplica.
	* @exception Manejo de errores no clasificados.
	*/
	public void seleccionaFecha() {
		try {
			fechaMinima = ConvertirUtil.dateToString(filtroFechaInicio, "dd/MM/yyyy");
		} catch (Exception e) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), e));
		}
	}
	
	/** 
	* Descripción: Permite buscar los puntos de control
	* @param No aplica
	* @return No aplica.
	* @exception Manejo de errores no clasificados.
	*/	
	public void buscarPuntoControl() {
		try {
			this.listaPuntoControl = puntoControlService.listarPuntoControl(
				getSessionController().getUsuarioSession().getIdUnidadEjecutora(),
				getSessionController().getUsuarioSession().getIdCorte());
				
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
		}
	}
	
	/** 
	* Descripción: Permite generar reporte de visitas
	* @param ActionEvent event permite la capturar evento click 
	* @return No aplica.
	* @exception Manejo de errores no clasificados.
	*/	
	public void generarReporte(ActionEvent event){
		try {
			StringBuilder nombreReporte = new StringBuilder();
			Map<String, Object> parameters = new HashMap<String, Object>();
			List<VisitaDto> lista = listaVisitas;
			optionExport=ConstantesVisitas.FORMATO_REPORTE_PDF;
			
			if( lista.size() > 0 ){
				parameters.put("IMAGE_REPORT_PATH", getLogoReportes() );
				parameters.put("p_usuario", getSessionController().getUsuarioSession().getxNombreCompleto() );
				parameters.put("p_fechaInicial", filtroFechaInicio);
				parameters.put("p_fechaFinal", filtroFechaFinal );
				parameters.put("p_visitante", filtroVisitante);
				if(descriPuntoControl!=null) {
					parameters.put("p_puntoControl", descriPuntoControl.trim());
				}else {
					parameters.put("p_puntoControl", "");
				}
				parameters.put("p_totalRegistros", lista.size());
				parameters.put("p_distritoJud", getSessionController().getUsuarioSession().getDescripcionCorte());
				
				nombreReporte.append(ConstantesVisitas.NOMBRE_REPORTE_VISITAS);
				if( optionExport == ConstantesVisitas.FORMATO_REPORTE_PDF ){
					reporte = generarPDF(nombreReporte.toString(), parameters, lista);
				}else if( optionExport == ConstantesVisitas.FORMATO_REPORTE_XLS ){
					reporte = generarExcel(nombreReporte.toString(), parameters, lista);
				}
			}else{
				addWarnMessage("", "No hay resultados para el reporte." );
			}
			
			
		} catch (Exception excepcion) {
			logger.error(getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_MAGBEAN, this.getClass().getName(), excepcion));
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

	public VisitaDto getVisita() {
		return visita;
	}

	public void setVisita(VisitaDto visita) {
		this.visita = visita;
	}

	public String getMensajes() {
		return mensajes;
	}

	public void setMensajes(String mensajes) {
		this.mensajes = mensajes;
	}

	public String getNroDocumentoVisitante() {
		return nroDocumentoVisitante;
	}

	public void setNroDocumentoVisitante(String nroDocumentoVisitante) {
		this.nroDocumentoVisitante = nroDocumentoVisitante;
	}

	public String getNroDocumentoVisitado() {
		return nroDocumentoVisitado;
	}

	public void setNroDocumentoVisitado(String nroDocumentoVisitado) {
		this.nroDocumentoVisitado = nroDocumentoVisitado;
	}

	public String getNombreCompletoVisitado() {
		return nombreCompletoVisitado;
	}

	public void setNombreCompletoVisitado(String nombreCompletoVisitado) {
		this.nombreCompletoVisitado = nombreCompletoVisitado;
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

	public String getFiltroEstado() {
		return filtroEstado;
	}

	public void setFiltroEstado(String filtroEstado) {
		this.filtroEstado = filtroEstado;
	}

	public String getEquivalencia() {
		return equivalencia;
	}

	public void setEquivalencia(String equivalencia) {
		this.equivalencia = equivalencia;
	}

	public VisitaDto getVisitaSeleccionada() {
		return visitaSeleccionada;
	}

	public void setVisitaSeleccionada(VisitaDto visitaSeleccionada) {
		this.visitaSeleccionada = visitaSeleccionada;
	}

	public CfgPuntoControlService getPuntoControlService() {
		return puntoControlService;
	}

	public void setPuntoControlService(CfgPuntoControlService puntoControlService) {
		this.puntoControlService = puntoControlService;
	}

	public List<PuntoControlDto> getListaPuntoControl() {
		return listaPuntoControl;
	}

	public void setListaPuntoControl(List<PuntoControlDto> listaPuntoControl) {
		this.listaPuntoControl = listaPuntoControl;
	}

	public String getFiltroPuntoControl() {
		return filtroPuntoControl;
	}

	public void setFiltroPuntoControl(String filtroPuntoControl) {
		this.filtroPuntoControl = filtroPuntoControl;
	}

	public String getCodigoPuntoControl() {
		return codigoPuntoControl;
	}

	public void setCodigoPuntoControl(String codigoPuntoControl) {
		this.codigoPuntoControl = codigoPuntoControl;
	}

	public String getDescriPuntoControl() {
		return descriPuntoControl;
	}

	public void setDescriPuntoControl(String descriPuntoControl) {
		this.descriPuntoControl = descriPuntoControl;
	}

	public StreamedContent getReporte() {
		return reporte;
	}

	public void setReporte(StreamedContent reporte) {
		this.reporte = reporte;
	}

	public short getOptionExport() {
		return optionExport;
	}

	public void setOptionExport(short optionExport) {
		this.optionExport = optionExport;
	}

	public boolean isFormatoExcel() {
		return formatoExcel;
	}

	public void setFormatoExcel(boolean formatoExcel) {
		this.formatoExcel = formatoExcel;
	}

	public boolean isFormatoPdf() {
		return formatoPdf;
	}

	public void setFormatoPdf(boolean formatoPdf) {
		this.formatoPdf = formatoPdf;
	}


}
