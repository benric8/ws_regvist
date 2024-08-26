package pe.gob.pj.administrativos.visitas.model.util;

import java.util.HashMap;
import java.util.Map;


public class ParametrosDeBusqueda {
	/*======================= GENERAL ==========================*/
	public static final String INDICADOR_ACTIVO				= "1";
	public static final String INDICADOR_INACTIVO			= "0";
	
	/*======================= CARACTERES ESPECIALES ==========================*/
	public static char SIMBOLO_OR = (char) 124; // |
	public static char SIMBOLO_NUMERO = (char) 176;// °
	public static char SIMBOLO_APERTURA_ADMIRACION = (char) 161;// ¡
	public static char SIMBOLO_CIERRE_ADMIRACION = (char) 33;// !
	public static char SIMBOLO_COMILLA_DOBLE = (char) 34; // "
	public static char SIMBOLO_NUMERAL = (char) 35;// #
	public static char SIMBOLO_DOLAR = (char) 36;// $
	public static char SIMBOLO_PORCENTAJE = (char) 37;// %
	public static char SIMBOLO_AND = (char) 38; // &

	public static final String ADD_LIKE = "ADD_LIKE";
	/*======================= TABLA USUARIO ==========================*/
	public static final String USUA_ID = "USUA_ID";
	public static final String USUA_TIPO_DOC = "USUA_TIPO_DOC";
	public static final String NOT_USUA_TIPO_DOC = "NOT_USUA_TIPO_DOC";
	public static final String USUA_NRO_DOC = "USUA_NRO_DOC";
	public static final String USUA_NOMBRES = "USUA_NOMBRES";
	public static final String USUA_PATERNO = "USUA_PATERNO";
	public static final String USUA_MATERNO = "USUA_MATERNO";
	public static final String USUA_ESTADO = "USUA_ESTADO";
	public static final String USUA_REGISTRO = "USUA_REGISTRO";
	public static final String USUA_CORTE = "USUA_CORTE";
	public static final String USUAR_UNIDAD_EJECUTORA = "USUAR_UNIDAD_EJECUTORA";
	public static final String USUA_PERFIL = "USUA_PERFIL";
	public static final String USUA_NOMBRE_COMPLETO = "USUA_NOMBRE_COMPLETO";
	public static final String USUA_CORREO = "USUA_CORREO";
	public static final String USUA_ID_ENCRYPT = "USUA_ID_ENCRYPT";

	/*======================= TABLA LUGAR ==========================*/
	public static final String LURG_NOMBRES = "LURG_NOMBRES";
	public static final String LURG_ESTADO = "LURG_ESTADO";
	public static final String LURG_ID = "LURG_ID";

	/*======================= TABLA PUNTO DE CONTROL ==========================*/
	public static final String PTCT_LOCAL = "PTCT_LOCAL";
	public static final String PTCT_OFICINA = "PTCT_OFICINA";
	public static final String PTCT_INGRESO = "PTCT_INGRESO";
	public static final String PTCT_ESTADO = "PTCT_ESTADO";
	public static final String PTCT_ID = "PTCT_ID";
	public static final String PTCT_NOMBRE = "PTCT_NOMBRE";
	public static final String PTCT_UNI_EJE = "PTCT_UNI_EJE";
	public static final String PTCT_CORTE = "PTCT_CORTE";
	
	/*======================= TABLA PERFIL ==========================*/
	public static final String PERF_NOMBRE = "PERF_NOMBRE";
	public static final String PERF_ID = "PERF_ID";
	public static final String PERF_ESTADO = "PERF_ESTADO";
	public static final String PERF_ENTIDAD = "PERF_ENTIDAD";
	public static final String PERF_SISTEMA = "PERF_SISTEMA";
	public static final String PERF_SIST_ESTADO = "PERF_SIST_ESTADO";
	public static final String PERF_USUA_ESTADO = "PERF_USUA_ESTADO";
	public static final String NOT_PERFIL_ID = "NOT_PERFIL_ID";
	/*======================= TABLA OPCION ==========================*/
	public static final String OPCION_ID = "OPCION_ID";
	public static final String OPCION_NOMBRE = "OPCION_NOMBRE";
	public static final String OPCION_ESTADO = "OPCION_ESTADO";
	public static final String OPCION_ESTADO_SISTEMA = "OPCION_ESTADO_SISTEMA";
	public static final String OPCION_PERFIL = "OPCION_PERFIL";
	public static final String OPCION_PERFIL_ESTADO = "OPCION_PERFIL_ESTADO";
	public static final String OPCION_METODO_NOVACIO = "OPCION_METODO_NOVACIO";
	public static final String OPCION_SISTEMA = "OPCION_SISTEMA";
	public static final String OPCION_PRINCIPAL="OPCION_PRINCIPAL";
	public static final String OPCION_NO_PRINCIPAL="OPCION_NO_PRINCIPAL";
	/*======================= TABLA PETICIONES ==========================*/
	public static final String TIPO_MENSAJE = "TIPO_MENSAJE";
	public static final String ORIGEN_MENSAJE = "ORIGEN_MENSAJE";
	public static final String NO_ORIGEN_MENSAJE = "NO_ORIGEN_MENSAJE";	
	/*======================= VISITAS ==========================*/
	public static final String VIS_ID_UNI_EJE = "VIS_ID_UNI_EJE";
	public static final String VIS_ID_CORTE = "VIS_ID_CORTE";
	public static final String VIS_ID_PUNTO_CONTROL = "VIS_ID_PUNTO_CONTROL";
	public static final String VIS_ID_LOCAL = "VIS_ID_LOCAL";
	public static final String VIS_USUARIO = "VIS_USUARIO";
	/*======================= General ==========================*/
	public static final String FEC_INICIO = "FEC_INICIO";
	public static final String FEC_FIN = "FEC_FIN";
	public static final String PETICION_ACTIVA = "PETICION_ACTIVA";
	public static final String CUOTA_ACTIVA = "CUOTA_ACTIVA";
	public static final String TIPO_DOCU_LIST = "TIPO_DOCU_LIST";
	public static final String ESTADO_REGISTRO = "ESTADO_REGISTRO";
	
	/*======================= TIPO MOTIVO ==========================*/
	public static final String TM_DESCRIPCION = "TM_DESCRIPCION";
	public static final String TM_ESTADO = "TM_ESTADO";
	
	/*======================= ENTIDAD ==========================*/
	public static final String EN_RAZSO = "EN_RAZSO";
	public static final String EN_RUC = "EN_RUC";
	public static final String EN_ESTADO = "EN_ESTADO";
	
	public static final Map<String, String> casillaColumns;
			
    static
    {
    	casillaColumns = new HashMap<String, String>();
    	casillaColumns.put("idUsuario", "Id. usuario");
    	casillaColumns.put("usuario", "Usuario");
    }
}
