package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import pe.gob.pj.administrativos.visitas.entity.MaeUsuario;
import pe.gob.pj.administrativos.visitas.model.dao.MaeUsuarioDao;
import pe.gob.pj.administrativos.visitas.model.dto.PerfilDto;
import pe.gob.pj.administrativos.visitas.model.dto.TipoDocumentoDto;
import pe.gob.pj.administrativos.visitas.model.dto.UsuarioDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.model.util.ParametrosDeBusqueda;
import pe.gob.pj.administrativos.visitas.model.util.Utilitarios;
import pe.gob.pj.api.commons.utility.ConvertirUtil;
import pe.gob.pj.api.commons.utility.CryptoUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;
import pe.gob.pj.api.commons.utility.sicau.ManejadorError;

/**
* Resumen.
* Objeto : MaeUsuarioDaoImpl
* Descripción : Es una clase de accedo a datos para gestionar la informacion del usuario 
* Fecha de Creación : 02/01/2019
* Autor : Carlos Arroyo
* ------------------------------------------------------------------------
* Modificaciones
* Fecha                  Nombre                     Descripción
* ------------------------------------------------------------------------
* 08/05/2019			Carlos Arroyo				CRC: Cambio por Reseteo de Clave
* 15/06/2022			Alexis Távara				VPP: Validar solo Perfiles Permitidos v.1.0.7
*/
@Repository
public class MaeUsuarioDaoImpl extends BaseDaoImpl<Long, MaeUsuario> implements MaeUsuarioDao {

	private static final Logger Logger = LoggerFactory.getLogger(MaeUsuarioDaoImpl.class);

	/** TODO CRC Metodo obtenerUsuario
	* Descripción: Permite obtener la informacion del usuario.
	* @param numeroDoc	: numero del documento del usuario
	* 		 tipoDoc	: tipo de documento de identidad
	* 		 password	: clave del usuario
	* @return Retorna al formulario de Bienvenida del sistema.
	* @exception Manejo de errores no clasificados.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public UsuarioDto obtenerUsuario(String numeroDoc, long tipoDoc, String password) throws Exception {
		UsuarioDto usuario = null;
		try {
			String sLog = "MaeUsuarioDaoImpl.obtenerUsuario ";
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT u.nIdUsuario");
			hql.append(" , u.xNombre");
			hql.append(" , u.xApellidoPaterno");
			hql.append(" , u.xApellidoMaterno");
			hql.append(" , u.xCelular");
			hql.append(" , u.xTelefono");
			hql.append(" , u.xAnexo");
			hql.append(" , u.lFlagActivo");
			hql.append(" , u.maePerfil.nIdPerfil");
			hql.append(" , u.maePerfil.xDescripcion");
			hql.append(" , u.nIdUniEje");
			hql.append(" , u.nIdCorte");
			hql.append(" , u.xNroDocumento");
			hql.append(" , u.xCorreo");
			hql.append(" , u.maeTipoDocumento.nIdTipoDocumento");
			hql.append(" , u.maeTipoDocumento.xEquivalencia");
			//TODO CRC Agrega el flag de Reseteo de Clave a la consulta
			hql.append(" , u.lFlagReseteo ");
			hql.append(" FROM MaeUsuario u ");
			hql.append(" WHERE u.xNroDocumento = :numeroDoc ");
			hql.append(" AND u.xPassword = :password ");
			hql.append(" AND u.maeTipoDocumento.nIdTipoDocumento = :tipoDoc ");
			// Alexis Távara VPP v.1.0.7
			hql.append(" AND u.lCitas IS NULL ");
			Logger.info(sLog+"INICIO");
			Query query = getSession().createQuery(hql.toString());
			query.setParameter("numeroDoc", numeroDoc);
			query.setParameter("password", CryptoUtil.encriptar(password));
			query.setParameter("tipoDoc", tipoDoc);
			List<Object[]> resultado = query.list();
			
			if(resultado!=null &&  !resultado.isEmpty()){
				for (Object[] obj : resultado) {
					usuario = new UsuarioDto();
					usuario.setnIdUsuario(ConvertirUtil.toLong(obj[0]));
					usuario.setxNombre(ConvertirUtil.toString(obj[1]));
					usuario.setxApellidoPaterno(ConvertirUtil.toString(obj[2]));
					usuario.setxApellidoMaterno(ConvertirUtil.toString(obj[3]));
					usuario.setxCelular(ConvertirUtil.toStringAndTrim(obj[4]));
					usuario.setxTelefono(ConvertirUtil.toStringAndTrim(obj[5]));
					usuario.setxAnexo(ConvertirUtil.toStringAndTrim(obj[6]));
					usuario.setlFlagActivo(ConvertirUtil.toStringAndTrim(obj[7]));
					PerfilDto perfilDto = new PerfilDto();
					perfilDto.setnIdPerfil(ConvertirUtil.toLong(obj[8]));
					perfilDto.setxDescripcion(ConvertirUtil.toString(obj[9]));
					usuario.setIdUnidadEjecutora(ConvertirUtil.toStringAndTrim(obj[10]));
					usuario.setIdCorte(ConvertirUtil.toStringAndTrim(obj[11]));
					
					usuario.setxNroDocumento(ConvertirUtil.toStringAndTrim(obj[12]));
					usuario.setCorreo(ConvertirUtil.toStringAndTrim(obj[13]));
					
					TipoDocumentoDto tipoDocumentoDto = new TipoDocumentoDto();
					tipoDocumentoDto.setIdTipoDocumento(ConvertirUtil.toLong(obj[14]));
					tipoDocumentoDto.setEquivalencia(ConvertirUtil.toStringAndTrim(obj[15]));
					
					usuario.setTipoDocumentoDto(tipoDocumentoDto);
					usuario.setPerfilDto(perfilDto);
					usuario.setxNombreCompleto(usuario.getxNombre()+" "+usuario.getxApellidoPaterno()+" "+usuario.getxApellidoMaterno());
					//TODO CRC Agrega el flag de Reseteo de Clave a la consulta
					usuario.setlFlagReseteo(ConvertirUtil.toStringAndTrim(obj[16]));
				}
			}
		} catch (Exception e) {
			throw new Exception(ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
		}
		return usuario;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<UsuarioDto> buscar(Map<String, Object> params) {
		
		List<UsuarioDto> resultado = new ArrayList<UsuarioDto>();
		StringBuilder hql = new StringBuilder(createBasicQuery());
		List<Entry<String, Object>> listaParams = new ArrayList<Entry<String, Object>>();
		
		for (Map.Entry<String, Object> map : params.entrySet()) {
			if (Utilitarios.validaValor(map, ParametrosDeBusqueda.USUA_ID)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" o.nIdUsuario=:").append(ParametrosDeBusqueda.USUA_ID);
				listaParams.add(map);
			} else if (Utilitarios.validaValor(map, ParametrosDeBusqueda.USUA_TIPO_DOC)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" o.maeTipoDocumento.nIdTipoDocumento=:").append(ParametrosDeBusqueda.USUA_TIPO_DOC);
				listaParams.add(map);
			} else if (Utilitarios.validaValor(map, ParametrosDeBusqueda.NOT_USUA_TIPO_DOC)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" o.maeTipoDocumento.nIdTipoDocumento in (:").append(ParametrosDeBusqueda.NOT_USUA_TIPO_DOC).append(")");
				listaParams.add(map);
			} else if (Utilitarios.validaValor(map, ParametrosDeBusqueda.USUA_NRO_DOC)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" o.xNroDocumento=:").append(ParametrosDeBusqueda.USUA_NRO_DOC);
				listaParams.add(map);
			} else if (Utilitarios.validaValor(map, ParametrosDeBusqueda.USUA_ESTADO)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" o.lFlagActivo=:").append(ParametrosDeBusqueda.USUA_ESTADO);
				listaParams.add(map);
			} else if (Utilitarios.validaValor(map, ParametrosDeBusqueda.USUA_PATERNO)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" o.xApellidoPaterno LIKE CONCAT(:").append(ParametrosDeBusqueda.USUA_PATERNO).append(",'%')");
				listaParams.add(map);
			} else if (Utilitarios.validaValor(map, ParametrosDeBusqueda.USUA_MATERNO)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" o.xApellidoMaterno LIKE CONCAT(:").append(ParametrosDeBusqueda.USUA_MATERNO).append(",'%')");
				listaParams.add(map);
			} else if (Utilitarios.validaValor(map, ParametrosDeBusqueda.USUA_NOMBRES)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" o.xNombre LIKE CONCAT(:").append(ParametrosDeBusqueda.USUA_NOMBRES).append(",'%')");
				listaParams.add(map);
			} else if (Utilitarios.validaValor(map, ParametrosDeBusqueda.USUA_NOMBRE_COMPLETO)) {				
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" UPPER(CONCAT(CONCAT(CONCAT(CONCAT(o.xNombre,' '),o.xApellidoPaterno),' '),o.xApellidoMaterno)) LIKE :").append(ParametrosDeBusqueda.USUA_NOMBRE_COMPLETO);
				listaParams.add(map);
			} else if (Utilitarios.validaValor(map, ParametrosDeBusqueda.USUAR_UNIDAD_EJECUTORA)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" o.nIdUniEje=:").append(ParametrosDeBusqueda.USUAR_UNIDAD_EJECUTORA);
				listaParams.add(map);
			} else if (Utilitarios.validaValor(map, ParametrosDeBusqueda.USUA_CORTE)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" o.nIdCorte=:").append(ParametrosDeBusqueda.USUA_CORTE);
				listaParams.add(map);
			} else if (Utilitarios.validaValor(map, ParametrosDeBusqueda.USUA_PERFIL)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" o.maePerfil.nIdPerfil=:").append(ParametrosDeBusqueda.USUA_PERFIL);
				listaParams.add(map);
			} else if (Utilitarios.validaValor(map, ParametrosDeBusqueda.USUA_CORREO)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" UPPER(o.xCorreo)=:").append(ParametrosDeBusqueda.USUA_CORREO);
				listaParams.add(map);
			} else if( Utilitarios.validaValor(map, ParametrosDeBusqueda.USUA_NOMBRE_COMPLETO)){
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" UPPER(CONCAT(CONCAT(CONCAT(CONCAT(o.xNombre,' '), o.xApellidoPaterno),' '),o.xApellidoMaterno)) LIKE '%")
				.append(ParametrosDeBusqueda.USUA_NOMBRE_COMPLETO).append("%'");
				listaParams.add(map);
			} else if (Utilitarios.validaValor(map, ParametrosDeBusqueda.USUA_ID_ENCRYPT)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" o.codigoEncriptado=:").append(ParametrosDeBusqueda.USUA_ID_ENCRYPT);
				listaParams.add(map);
			}
		}
		
		// Alexis Távara VPP v.1.0.7
		hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
		hql.append(" o.lCitas is null");
		
		Query query = getSession().createQuery(hql.toString());
		for (Map.Entry<String, Object> map : listaParams) {
			if(map.getValue() instanceof Collection)
				query.setParameterList(map.getKey(), (Collection) map.getValue());
			else
				query.setParameter( map.getKey(), map.getValue() );
			
			
		}

		List<MaeUsuario> list = query.list();
		for (MaeUsuario u : list) {
			UsuarioDto userDto = new UsuarioDto();
			userDto.setnIdUsuario(u.getnIdUsuario());
			userDto.setxNroDocumento(u.getxNroDocumento());
			userDto.setxUsuario(u.getxUsuario());
			userDto.setxNombre(u.getxNombre());
			userDto.setxApellidoPaterno(u.getxApellidoPaterno());
			userDto.setxApellidoMaterno(u.getxApellidoMaterno());
			userDto.setxCelular(u.getxCelular()!=null?u.getxCelular().trim():u.getxCelular());
			userDto.setxTelefono(u.getxTelefono()!=null?u.getxTelefono().trim():u.getxTelefono());
			userDto.setxAnexo(u.getxAnexo()!=null?u.getxAnexo().trim():u.getxAnexo());
			userDto.setlFlagActivo(u.getlFlagActivo());
			userDto.setxPassword(u.getxPassword());
			userDto.setxCodigoSiga(u.getxCodigoSiga());
			userDto.setCorreo(u.getxCorreo());
			userDto.setIdUnidadEjecutora(u.getnIdUniEje());
			userDto.setIdCorte(u.getnIdCorte());
			userDto.setToken(u.getToken());
			userDto.setFechaSolicitudCambioClave(u.getFechaSolicitudCambioClave());
			userDto.setFechaCambioClave(u.getFechaCambioClave());
			TipoDocumentoDto tipoDoc =new TipoDocumentoDto();
			tipoDoc.setIdTipoDocumento(u.getMaeTipoDocumento().getnIdTipoDocumento());
			tipoDoc.setAbreviatura(u.getMaeTipoDocumento().getxAbreviatura());
			tipoDoc.setDescripcion(u.getMaeTipoDocumento().getxDescripcion());
			tipoDoc.setEstado(u.getMaeTipoDocumento().getlFlagActivo());
			userDto.setTipoDocumentoDto(tipoDoc);
			PerfilDto perfil = new PerfilDto();
			if(u.getMaePerfil()!=null) {
				perfil.setnIdPerfil(u.getMaePerfil().getnIdPerfil());
				perfil.setxDescripcion(u.getMaePerfil().getxDescripcion());
				perfil.setlFlagActivo(u.getMaePerfil().getlFlagActivo());
			}
			userDto.setPerfilDto(perfil);
			resultado.add(userDto);
		}

		return resultado;
	}
	
	/**
	* Obtiene datos de usuario con id ecriptado para recuperar clave.     *
	* @param id de usuario
	* @return Datos de usuario
	*/
	//@SuppressWarnings("unchecked")
	@Override
	public  UsuarioDto obtenerUsuarioByIdEcriptado(String id) throws Exception {
		 
		UsuarioDto usuario =  null;
	 /*
		try{
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT u.nIdUsuario");
			hql.append(" , u.xNombres");
			hql.append(" , u.xApellidoMaterno");
			hql.append(" , u.xApellidoMaterno");
			hql.append(" , u.xNumeroDoc");
			hql.append(" , u.xCorreoElec");
			hql.append(" , u.xIdusuarioEncriptado");
			hql.append(" , u.xToquen");
			hql.append(" , u.fRecuperacionDatos");
			hql.append(" , u.fCambioClave ");
			hql.append(" ,getdate() as fechaActual ");
			hql.append(" FROM MaeUsuario u ");
			hql.append(" WHERE u.xIdusuarioEncriptado = :id ");
			Query query = getSession().createQuery(hql.toString());
			query.setParameter( "id", id);
		    
		    List<Object[]> resultado = query.list();
		    for (Object[] obj : resultado){
		    	usuario = new UsuarioDto();
		    	usuario.setnIdUsuario( ConvertirUtil.toLong( obj[0] ) );
		    	usuario.setxNombres( ConvertirUtil.toString( obj[1] ) );
		    	usuario.setxApellidoPaterno( ConvertirUtil.toString( obj[2] ) );
		    	usuario.setxApellidoMaterno( ConvertirUtil.toString( obj[3] ) );
		    	usuario.setxNumeroDoc( ConvertirUtil.toString( obj[4] ) );
		    	usuario.setxCorreoElec( ConvertirUtil.toString( obj[5] ) );
		    	usuario.setxIdusuarioEncriptado( ConvertirUtil.toString( obj[6] ) );
		    	usuario.setxToquen( ConvertirUtil.toString( obj[7] ) );
		    	usuario.setFrecuperacionDatos(ConvertirUtil.toTimestamp(obj[8]));
		    	usuario.setFcambioClave(ConvertirUtil.toTimestamp(obj[9]));
		    	usuario.setFechaActual(ConvertirUtil.toTimestamp(obj[10]));
		    }
		
		    
		}catch(Exception e){
			throw new Exception( ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()) );
		}
	*/
		return usuario;
	}
	 
	@Override
	public List<UsuarioDto> obtenerUsuarioByCorreo(String correo) throws Exception {
		 
		List<UsuarioDto> lstUsuarios=null;
		/*
		try{
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT u.nIdUsuario");
			hql.append(" , u.xNombre");
			hql.append(" , u.xApellidoPaterno");
			hql.append(" , u.xApellidoMaterno");
			hql.append(" , u.xNroDocumento");
			hql.append(" , u.xCorreoElec");
			hql.append(" , u.xIdusuarioEncriptado");
			hql.append(" , u.xToquen");
			hql.append(" , u.fRecuperacionDatos");
			hql.append(" , u.fCambioClave ");
			hql.append(" ,getdate() as fechaActual ");
			hql.append(" ,td.nIdTipoDoc ");
			hql.append(" ,td.xNombre ");
			hql.append(" FROM MaeUsuario u ");
			hql.append("inner join u.maeTipoDocumento td ");
			hql.append(" WHERE u.xCorreoElec = :correo ");
			hql.append("and u.lEstadoRegistro=:estado  ");
			Query query = getSession().createQuery(hql.toString());
			query.setParameter( "correo", correo);
			query.setParameter("estado", ConstantesVisitas.ACTIVO);
			
		    UsuarioDto usuario =  null;
		    List<Object[]> resultado = query.list();
		    if(resultado!=null && !resultado.isEmpty()){
		    	for (Object[] obj : resultado){
		    		lstUsuarios= new ArrayList<UsuarioDto>();
			    	usuario = new UsuarioDto();
			    	usuario.setnIdUsuario( ConvertirUtil.toLong( obj[0] ) );
			    	usuario.setxNombres( ConvertirUtil.toString( obj[1] ) );
			    	usuario.setxApellidoPaterno( ConvertirUtil.toString( obj[2] ) );
			    	usuario.setxApellidoMaterno( ConvertirUtil.toString( obj[3] ) );
			    	usuario.setxNumeroDoc( ConvertirUtil.toString( obj[4] ) );
			    	usuario.setxCorreoElec( ConvertirUtil.toString( obj[5] ) );
			    	usuario.setxIdusuarioEncriptado( ConvertirUtil.toString( obj[6] ) );
			    	usuario.setxToquen( ConvertirUtil.toString( obj[7] ) );
			    	usuario.setFrecuperacionDatos(ConvertirUtil.toTimestamp(obj[8]));
			    	usuario.setFcambioClave(ConvertirUtil.toTimestamp(obj[9]));
			    	usuario.setFechaActual(ConvertirUtil.toTimestamp(obj[10]));
			    	usuario.setTipoDocumentoDto(new TipoDocumentoDto());
			    	usuario.getTipoDocumentoDto().setnIdTipoDoc(ConvertirUtil.toLong( obj[11] ));
			    	usuario.getTipoDocumentoDto().setxNombre(ConvertirUtil.toString( obj[12] ));
			    	lstUsuarios.add(usuario);
			    }
		    }
		
		}catch(Exception e){
			throw new Exception( ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
					ConstantesSicau.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()) );
		}
		*/
	    return lstUsuarios;
	}


	/**
     * Registra solcitud para recuperar contraseña
     * @param correo electronico
     * @return  Número de operación
     */
	@Override
	public int solicitarRecuperacionClave(UsuarioDto bean) throws Exception{
		try{

			StringBuffer hql = new StringBuffer();
			hql.append(" UPDATE MaeUsuario u");
			hql.append(" SET u.codigoEncriptado = :codigoEncriptado  ");
			hql.append(", u.token = :token");
			hql.append(", u.fechaSolicitudCambioClave = :fechaRecuperacion");
			hql.append(" WHERE u.nIdUsuario = :idUsuario");

			Query query = getSession().createQuery( hql.toString() );
			query.setParameter("codigoEncriptado", bean.getCodigoEncriptado());
			query.setParameter("token", bean.getToken());
			query.setParameter("fechaRecuperacion", bean.getFechaSolicitudCambioClave());
			query.setParameter("idUsuario", bean.getnIdUsuario());


			return query.executeUpdate();

		}catch(Exception e){    		
    		throw new Exception(ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), 
    				ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
		}
	}
}