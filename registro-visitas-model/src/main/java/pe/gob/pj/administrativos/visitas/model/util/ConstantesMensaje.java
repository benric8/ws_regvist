package pe.gob.pj.administrativos.visitas.model.util;

public class ConstantesMensaje {

	public static final String registroCorrecto = "Registro de información satisfactoria.";
	public static final String modificacionCorrecto = "Modificación de información satisfactoria.";
	public static final String operacionFallida = "No se pudo procesar la información.";

	public static final String registroSeleccionadoInactivo = "El registro seleccionado ya se encuenta inactivo.";
	public static final String registroNoSeleccionado = "Seleccione un registro del listado.";

	public static final String noInformacionOpcionPadre = "No se pudo obtener información de la opción padre.";
	public static final String noInformacionUsurioLogeado = "No se pudo obtener información del usuario.\nSe recomienda cerrar sesión y volver a ingresar.\nGracias.";

	public static final String registroPerfilDuplicado = "El perfil ya se encuentra registrado.";
	public static final String registroEntidadDuplicado = "La entidad ya se encuentra registrado.";
	public static final String registroLugarDuplicado = "El lugar ya se encuentra registrado.";

	public static final String noInformacionEntidadRuc = "No se encontró información asociada al RUC.";
	public static final String cantidadCuotaMayorCero = "La cuota de acciones debe ser mayor a cero.";
	public static final String ipPublicaRequerida = "Ingrese un dirección IP Pública.";
	public static final String listaIpPublicaVacia = "Ingrese por lo menos una dirección IP Pública.";
	public static final String direccionIpNoValida = "Ingrese una dirección IP válida con formato IPv4 o IPv6.";
	public static final String registroIpPublicaDuplicado = "La dirección IP ya se encuentra registrada.";

	public static final String registroGrupoDuplicado = "El grupo ya se encuentra registrado.";
	public static final String requiereAsignarEntidadUsuario = "El usuario debe tener una entidad asignada.";
	public static final String limiteEntidadAsignada = "El usuario ya tiene un entidad asignada.";
	public static final String entidadRequerida = "Seleccione una entidad.";
	public static final String asignaEntidadDuplicada = "La entidad ya fue asociada al usuario.";
	public static final String faltaDatosDocumento = "Debe seleccionar datos requeridos para busqueda.";

	public static final String faltaTipoDocumento = "Debe seleccionar el tipo de documento para busqueda.";
	public static final String faltaNumeroDocumento = "Debe ingresar el número de documento para busqueda.";
	
	public static final String faltaBuscarUsuario = "Debe realizar la busqueda de datos de usuario.";
	
	public static final String existeUsuario = "Existe un usuario registrado con el número de documento ingresado.";
	public static final String noExisteUsuarioBuscado = "El usuario buscado no coincide o no existe.";
	public static final String existeCorreoUsuario = "Existe un usuario registrado con el correo electrónico ingresado.";

	public static final String ingreseCriterioBusqueda = "Ingrese al menos un criterio de busqueda.";
	public static final String ingresePuntoControl = "Ingrese al menos un punto de control.";
	
	public static final String existeLugar = "Existe un lugar registrado con la descripcion ingresada.";
	public static final String lugarVacio = "El lugar debe tener una descripción.";

	public static final String registroExitosoVisita = "Se realizó la operacion satisfactoriamente.";
	public static final String datosDNIDeVisitanteInvalidos = "El DNI debe tener máximo 8 caracteres.";
	public static final String datosOtrosDocumentosDeVisitanteInvalidos = "El Nro. de documento debe tener máximo 20 caracteres.";
	public static final String visitaObservacionesInvalida = "La observacion debe tener máximo 100 caracteres.";
	public static final String visitaDescripcionInvalida = "La descripción debe tener máximo 100 caracteres y mínimo 5 caracteres.";
	public static final String dniNoEncontradoReniec = "DNI no encontrado en RENIEC.";
	public static final String tipoDocumentoNoValidoReniec = "El tipo de documento ingresado no es valido para la busqueda en RENIEC.";
	
	public static final String errorBuscarVisitas = "Fallo en la operación de buscar las visitas.";
	public static final String errorBuscarTipoDocumento = "Fallo en la operación de busqueda del tipo de documento.";
	public static final String errorBuscarTipoMotivo = "Fallo en la operación de busqueda del tipo de motivo.";
	public static final String errorBuscarReniec = "Fallo en la operación de busqueda en la RENIEC.";
	public static final String errorCargarFoto = "Fallo en la operación de cargar la foto.";
	public static final String errorBuscarEntidad = "Fallo en la operación de busqueda de la entidad.";
	public static final String errorBuscarLugar = "Fallo en la operación de busqueda del lugar.";
	public static final String errorBuscarTrabajador = "Fallo en la operación de busqueda del trabajador.";
	public static final String errorGrabarVisita = "Fallo en la operación de grabar visita.";
	public static final String errorRegistrarSalida = "Fallo en la operación de registrar salida.";

	
	public static final String advertenciaBuscarEntidad = "El valor de búsqueda debe tener al menos 3 caracteres.";
	public static final String advertenciaBuscarEntidadPide = "No se encontro a la entidad, puede agregarla pulsando el boton nuevo";
	public static final String advertenciaEntidadExiste = "Ya existe una entidad registrada con el mismo número de ruc.";
	public static final String advertenciaAlMenosUnFiltroEntidad = "Debe ingresar por lo menos un campo de búsqueda";
	
	
	public static final String advertenciaBuscarLugar = "El valor de búsqueda debe tener al menos 3 caracteres.";
	public static final String advertenciaBuscarTrabajadorAlMenosUnValor= "Debe ingresar al menos un parámetro de búsqueda.";
	public static final String advertenciaBuscarTrabajadorDocumento= "El Nro. de documento debe tener al menos 3 caracteres.";
	public static final String advertenciaBuscarTrabajadorNombres = "El Nro. completo debe tener al menos 3 caracteres.";
	public static final String advertenciaElDNIVisitadoVisitanteCoincide = "El Nro. de documento del visitante y el colaborador es el mismo.";
	public static final String advertenciaNoTrabajador = "No se encontro trabajadores con los parámetros ingresados.";
	
	public static final String advertenciaVisitanteTipoDocumento = "El tipo de documento del visitante es necesario para el registro.";
	public static final String advertenciaSiIngresaDocumentoIngreseTipo= "Debe seleccionar un tipo de documento.";
	public static final String advertenciaSiIngresaTipoIngreseDocumento= "Debe ingresar un número de documento.";
	public static final String advertenciaDNI8Caracteres= "El DNI debe contener 8 caracteres.";
	public static final String advertenciaPersonaFallecida= "El DNI ingresado es de una persona fallecida.";

	
	public static final String advertenciaVisitanteNumeroDocumento = "El Nro. de documento del visitante es necesario para el registro.";
	public static final String advertenciaVisitanteNombre = "El nombre del visitante es necesario para el registro.";
	public static final String advertenciaVisitanteEntidad = "La entidad es necesaria para el registro.";
	public static final String advertenciaVisitantePaterno = "El apellido paterno del visitante es necesario para el registro.";

	public static final String advertenciaVisitadoTipoDocumento = "El tipo de documento del visitado es necesario para el registro.";
	public static final String advertenciaVisitadoNumeroDocumento = "El Nro. de documento del visitado es necesario para el registro.";
	public static final String advertenciaVisitadoNombre = "El nombre del visitado es necesario para el registro.";
	public static final String advertenciaVisitadoCargo = "El cargo del visitado es necesario para el registro.";
	public static final String advertenciaVisitadoOficina = "La oficina del visitado es necesario para el registro.";

	public static final String advertenciaVisitaTipoMotivo = "El tipo de motivo es necesario para el registro.";
	public static final String advertenciaVisitadoDescripcionMotivo = "La descripción del motivo es necesario para el registro.";
	public static final String advertenciaVisitaLugar = "El lugar es necesario para el registro.";

	public static final String exitoRegistroVisita = "Visita registrada exitosamente.";
	public static final String exitoRegistroSalidaVisita = "Salida registrada exitosamente.";

	public static final String advertenciaNoSeleccionoLocal = "Se debe seleccionar un local.";
	public static final String advertenciaIngreseNombrePuntoControl = "Se debe ingresar un nombre.";
	public static final String advertenciaIngreseUnLocal = "Se debe ingresar un local.";
	public static final String advertenciaSeleccionarUnidadEjecutora= "Debe seleccionar una Unidad Ejecutora.";
	public static final String advertenciaSeleccionarCorte= "Debe seleccionar una Corte";
	public static final String advertenciaSeleccionarDepartamento= "Debe seleccionar un Departamento.";
	public static final String advertenciaSeleccionarProvincia= "Debe seleccionar una Provincia.";
	public static final String advertenciaSeleccionarDistrito= "Debe seleccionar un Distrito";
	
	public static final String advertenciaUsuarioBuscadoIgualLogueado= "Usuario logueado no puede ser igual al usuario buscado";
	public static final String errorConsultaCorte = "Fallo en operación : Consulta de corte";
	public static final String errorConsultaUnidadEjecutora = "Fallo en operación : Consulta de unidad ejecutora";
	
	public static final String plazoUrlActivaHrs = "24";
	public static final String advertenciaTipoMotivoDescripcion = "La descripción del motivo es necesario para el registro.";
	public static final String advertenciaYaExisteTipoMotivo = "Ya existe un tipo de motivo con la misma descripción.";
	
	public static final String advertenciaIngresarRazonSocial = "Se necesita la Razón Social para el registro.";
	public static final String advertenciaIngresarRucBusqueda = "Ingresar el número de RUC para la búsqueda.";
	public static final String advertenciaIngresarRuc = "Se necesita el RUC para el registro.";
	public static final String advertenciaYaExisteEntidad = "Ya existe una entidad registrada con el mismo número de RUC.";

	public static final String advertenciaSelCorteIncorrecta = "El usuario esta asignado a una Corte que no pertenece.";
	public static final String errorBuscarVisitante = "Fallo en la operación de busqueda del visitante.";
	public static final String advertenciaNoSeleccionarPersona= "No se pudo seleccionar el Visitante";
	public static final String advertenciaVisitanteFallecido= "El visitante seleccionado es de una persona fallecida.";
	
	public static final String advertenciaNombreMenor3Caracteres= "Si ingresa un nombre, debe tener minimo 3 caracteres.";
	public static final String advertenciaApePaternoMenor3Caracteres= "Si ingresa un apellido paterno, debe tener minimo 3 caracteres.";
	public static final String advertenciaApeMaternoMenor3Caracteres= "Si ingresa un apellido materno, debe tener minimo 3 caracteres.";
	
	public static final String advertenciaNombreNoIngresado= "Debe ingresar el Nombre del Visitante.";
	public static final String advertenciaApePaternoNoIngresado= "Debe ingresar el Apellido Paterno del Visitante.";
	public static final String advertenciaApeMaternoNoIngresado= "Debe ingresar el Apellido Materno del Visitante.";
	
}


