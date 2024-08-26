package pe.gob.pj.administrativos.visitas.model.util;

import java.io.Serializable;
/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright (C) 2015 Poder Judicial (Lima - Peru)
 * GERENCIA DE INFORMATICA
 * SUB GERENCIA DE  DESARROLLO DE SISTEMAS DE INFORMACION
 * PROYECTO SINOE - WJAV015 - WJAV016: SICAU

 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 **/
/**
 * @objetivo: Implementar la clase NavigationConstantes
 * @autor: 
 * @fecha: 11/07/2017
 **/
public class NavigationVisitasConstantes implements Serializable {
	private static final long serialVersionUID = -2330044987484001707L;

	/*********************** PRINCIPALES *******************************/
	public static final String LOGIN = "login";
	public static final String CAMBIO_CLAVE = "seg-cambio-clave";
	public static final String PAGINA_BIENVENIDA = "bienvenida";
	public static final String PAGINA_SELECCION_LOCAL = "seleccion-local";
	public static final String PAGINA_INICIO= "inicio";
	
	public static final String ERROR_PAGINA = "errorPagina";
	public static final String SESSION_FINALIZADA = "sesionFinalizada";
	

	

	
    
    /*********************** MANTENIMIENTOS *******************************/
    public static final String MANTENIMIENTO_CAMBIO_CLAVE 		= "cambiar-clave";
//    public static final String MANTENIMIENTO_USUARIO 			= "buscar-usuario";
    public static final String MANTENIMIENTO_USUARIO 			= "buscarUsuario";
    
    /*********************** REPORTES Y/O NOTIFICACIONES *******************************/
    public static final String REPORTE_CAMBIO_CLAVE 			= "reporte-cambio-clave";
	public static final String REPORTE_CREACION_USUARIO 		= "reporte-creacion-usuario";


	/*********************** ACTUALIZAR DE DATOS EN LOGIN *******************************/
	public static final String SEGURIDAD_ACTULIZAR_CORREOS	 		= "seguridad-actualizar-correos";
	public static final String SEGURIDAD_ACTULIZAR_ZONA_RECOJO	 	= "seguridad-actualizar-zonaRecojo";

	/*********************** EGURIDAD - SSO *******************************/
	public static final String SSO_MENU_APP		 		= "sso-menu-app";
	public static final String SSO_SESSIONES_ACTIVAS	= "sso-session-activa";
	public static final String SSO_VALIDAR_ERROR		= "/sso-validar.xhtml";
	
	/*********************** VISITAS *******************************/
	public static final String VISITAS_PRINCIPAL		= "visitas-principal";

	public static final String VISITAS_AGREGAR		= "visitas-agregar";


}
