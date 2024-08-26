package pe.gob.pj.administrativos.visitas.web.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.api.commons.constants.Constantes;

public class BaseController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{sessionMB}")
	private SessionController sessionController;

	public SessionController getSessionController() {
		return sessionController;
	}

	public void setSessionController(SessionController sessionController) {
		this.sessionController = sessionController;
	}

	public ExternalContext getContext(){
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	private void addMessage(FacesMessage.Severity severity, String message, String detail) {
		FacesMessage facesMessage = new FacesMessage(severity, message, detail);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	public void addInfoMessage(String msg, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, msg, detail);
	}

	public void addWarnMessage(String msg, String detail) {
		addMessage(FacesMessage.SEVERITY_WARN, msg, detail);
	}

	public void addErrorMessage(String msg, String detail) {
		addMessage(FacesMessage.SEVERITY_ERROR, msg, detail);
	}

	public void addFatalMessage(String msg, String detail) {
		addMessage(FacesMessage.SEVERITY_FATAL, msg, detail);
	}

	
	public String getGenerarError(String nombre,String nivel,String nombreClase,Exception ex){
		StringBuffer error = new StringBuffer();
		if( nivel.equals(Constantes.NIVEL_APP_MAGBEAN) ){
			error.append(Constantes.DIVISOR_ERROR_4);
		}
		error.append(Constantes.DIVISOR_ERROR_1);
		error.append(nombreClase);
		error.append(" - ");
		error.append(nombre);
		error.append(Constantes.DIVISOR_ERROR_2);
		if( nivel.equals(Constantes.NIVEL_APP_DAO) ){
			error.append(Constantes.DIVISOR_ERROR_3);
		}

		String mensajeError = "";
		if( ex != null &&  ex.getCause() != null && ex.getMessage().indexOf("JTA transaction unexpectedly rolled back (maybe due to a timeout)") != -1 ){
			if( ex.getCause().getCause() != null ){
				if( ex.getCause().getCause().getCause() != null ){
					if( ex.getCause().getCause().getCause().getCause() != null ){
						mensajeError = ex.getCause().getCause().getCause().getCause().getMessage();
					}else{
						mensajeError = ex.getCause().getCause().getCause().getMessage();
					}
				}else{
					mensajeError = ex.getCause().getCause().getMessage();
				}
			}else{
				mensajeError = ex.getCause().getMessage();
			}
		}else{
			mensajeError = ex.getMessage();
		}

		error.append(mensajeError);
		addErrorMessage(null, Constantes.MSG_GENERO_ERROR_OPERACION);

		return error.toString();
	}
	
	
	public void irInicio(){
		try {
			redirectPage("/index.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void irPrincipal(){
		try {
			redirectPage("/pages/inicio.xhtml");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void redirectPage(String redirectPage) throws Exception{
		FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext extContext = fc.getExternalContext();
        String url = extContext.encodeActionURL(fc.getApplication().getViewHandler().getActionURL(fc, redirectPage));
        extContext.redirect(url);
	}

	public void navigate(PhaseEvent event, String outcome) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
        navigationHandler.handleNavigation(facesContext, null, outcome);
        facesContext.renderResponse();
    }
	
	@SuppressWarnings("unchecked")
	public <T> T obtenerBean(String nombre) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    Application aplicacion = context.getApplication();
	    return (T) aplicacion.evaluateExpressionGet(context,"#{"+nombre+"}", Object.class);
	}
	
	public boolean verificarPermiso( String opcion ){
		for ( String action : getSessionController().getListaActions() ) {
			if( action != null && action.trim().equals( opcion.trim() ) ){
				return true;
			}
		}
		return false;
	}
	public void noTienePermisos() throws Exception{
		this.getSessionController().setUsuarioSession(null);
		this.getSessionController().setListaActions(null);
		redirectPage(ConstantesVisitas.ACTION_REDIRECT_NO_PAGE);
	}
	
	
	public boolean validarNavegador() throws Exception{
		
		HttpServletRequest request = (HttpServletRequest)getContext().getRequest();
		
		String userAgent= request.getHeader("user-agent");
		String browserName = "";
		String browserVer = "";
		String browserNameVer = "";
	  
		if(userAgent.contains("Chrome")){ //checking if Chrome
			browserNameVer=userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0];
			browserName=browserNameVer.split("/")[0];
	        browserVer=browserNameVer.split("/")[1];
	    }
	    else if(userAgent.contains("Firefox")){  //Checking if Firefox
	    	browserNameVer=userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0];
	    	browserName=browserNameVer.split("/")[0];
	        browserVer=browserNameVer.split("/")[1];
	    }
	    else if(userAgent.contains("MSIE")){  //Checking if Intenert Explorer
	    	browserName=userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0];
	    	browserVer=userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[1].replace(';',' ').trim();	    	
	    	
	    	if( browserName.equals("MSIE") && 
	    		Double.valueOf(browserVer).intValue()< 10 ) {	        	
	    		redirectPage(ConstantesVisitas.ACTION_ACTUALIZA_NAVEGADOR);
	    		return false;
	        }
	    }
		
		return true;
		
	}

	/** 
	* Descripción: Permite obtener el anio actual
	* @param  No aplica
	* @return String: Retorna el anio actual.
	* @exception No aplica.
	*/
	public String obtenerAnioActual() {
		Calendar cal= Calendar.getInstance();
		Integer anio = cal.get(Calendar.YEAR);
		String cadenaAnio =anio.toString();
		return cadenaAnio;
	}

	/** 
	* Descripción: Permite obtener la ruta del logo de reportes
	* @param  No aplica
	* @return String: Retorna el anio actual.
	* @exception No aplica.
	*/
	public String getLogoReportes(){
		ExternalContext context = getContext();
		StringBuilder image_path = new StringBuilder();
		image_path.append(context.getRealPath("/"));
		image_path.append(ConstantesVisitas.IMAGE_REPORT_PATH);
		image_path.append(ConstantesVisitas.LOGO_PJ);
		return image_path.toString();
	}

	/** 
	* Descripción: Permite obtener el reporte en PDF mediante un flujo de datos
	* @param  	String nombreReporte: Nombre del reporte a generar
	* 			Map<String, Object> parameters: Parametros de ingreso del reporte 
	* 			List<?> lista: Lista de datos del reporte
	* @return StreamedContent: Retorna el reporte en un flujo de datos.
	* @exception No aplica.
	*/
	@SuppressWarnings({ "static-access" })
	public StreamedContent generarPDF(String nombreReporte, Map<String, Object> parameters, List<?> lista){
		ExternalContext context = getContext();
		StringBuilder jasperFile = new StringBuilder();
		StringBuilder reporte = new StringBuilder();
		jasperFile.append(context.getRealPath("/"));
		jasperFile.append(ConstantesVisitas.REPORT_PATH);
		jasperFile.append(nombreReporte);
		jasperFile.append(".jrxml");
	
		try{
			byte[] array = this.exportPdf(jasperFile.toString(), parameters, lista);
			InputStream stream = new ByteArrayInputStream(array);
			reporte.append(nombreReporte);
			reporte.append(".");
			reporte.append(ConstantesVisitas.EXTENSION_FORMATO_PDF);
			return new DefaultStreamedContent(stream,  ConstantesVisitas.APPLICATION_PDF, reporte.toString());
		}catch(Exception e){
			addErrorMessage("ERROR", e.getMessage());
			return null;
		}
	}

	/** 
	* Descripción: Permite generar el reporte en PDF
	* @param  	String jasperFile: Nombre del reporte a generar
	* 			Map<String, Object> parameters: Parametros de ingreso del reporte 
	* 			List<?> dataList: Lista de datos del reporte
	* @return byte[]: Retorna el reporte en bytes.
	* @exception No aplica.
	*/
	@SuppressWarnings("deprecation")
	public byte[] exportPdf(String jasperFile, Map<String, Object> parameters, List<?> dataList) throws Exception {
		parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
	    JasperReport report = JasperCompileManager.compileReport(jasperFile);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(dataList));
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    JRPdfExporter jRPdfExporter = new JRPdfExporter();
	    jRPdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
	    jRPdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
	    jRPdfExporter.exportReport();
	    byte[] bytes = byteArrayOutputStream.toByteArray();
	    jRPdfExporter = null;
	    return bytes;
	}

	/** 
	* Descripción: Permite obtener el reporte en EXCEL mediante un flujo de datos
	* @param  	String nombreReporte: Nombre del reporte a generar
	* 			Map<String, Object> parameters: Parametros de ingreso del reporte 
	* 			List<?> lista: Lista de datos del reporte
	* @return StreamedContent: Retorna el reporte en un flujo de datos.
	* @exception No aplica.
	*/
	@SuppressWarnings({ "static-access" })
	public StreamedContent generarExcel(String nombreReporte, Map<String, Object> parameters,List<?> lista){
		ExternalContext context = getContext();
		StringBuilder jasperFile = new StringBuilder();
		StringBuilder reporte = new StringBuilder();
		jasperFile.append(context.getRealPath("/"));
		jasperFile.append(ConstantesVisitas.REPORT_PATH);
		jasperFile.append(nombreReporte);
		jasperFile.append(".jrxml");
		try{
			byte[] array = exportXls(jasperFile.toString(), parameters, lista, false);
			InputStream stream = new ByteArrayInputStream(array);
			reporte.append(nombreReporte);
			reporte.append(".");
			reporte.append(ConstantesVisitas.EXTENSION_FORMATO_XLS);
			return new DefaultStreamedContent(stream,  ConstantesVisitas.APPLICATION_XLS, reporte.toString());
		}catch(Exception e){
			addErrorMessage("ERROR", e.getMessage());
			return null;
		}
	}

	/** 
	* Descripción: Permite generar el reporte en EXCEL
	* @param  	String jasperFile: Nombre del reporte a generar
	* 			Map<String, Object> parameters: Parametros de ingreso del reporte 
	* 			List<?> dataList: Lista de datos del reporte
	* 			boolean isOnePagePerSheet: indicador sobre restriccion de una pagina por hoja
	* @return byte[]: Retorna el reporte en bytes.
	* @exception No aplica.
	*/
	@SuppressWarnings("deprecation")
	public static byte[] exportXls(String jasperFile, Map<String, Object> parameters, List<?> dataList, boolean isOnePagePerSheet) throws Exception {
	    parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
	    JasperReport report = JasperCompileManager.compileReport(jasperFile);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(dataList));
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    JExcelApiExporter jExcelApiExporter = new JExcelApiExporter();
	    jExcelApiExporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, jasperPrint);
	    jExcelApiExporter.setParameter(JExcelApiExporterParameter.IS_ONE_PAGE_PER_SHEET, new Boolean(isOnePagePerSheet));
	    jExcelApiExporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
	    jExcelApiExporter.setParameter(JExcelApiExporterParameter.CREATE_CUSTOM_PALETTE, Boolean.TRUE);
	    jExcelApiExporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
	    jExcelApiExporter.exportReport();
	    byte[] bytes = byteArrayOutputStream.toByteArray();
	    byteArrayOutputStream.flush();
	    byteArrayOutputStream.close();
	    jExcelApiExporter = null;
	    return bytes;
	}

}