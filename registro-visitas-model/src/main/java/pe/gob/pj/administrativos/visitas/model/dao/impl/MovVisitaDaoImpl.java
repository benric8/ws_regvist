package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import pe.gob.pj.administrativos.visitas.entity.MovVisita;
import pe.gob.pj.administrativos.visitas.model.dao.MovVisitaDao;
import pe.gob.pj.administrativos.visitas.model.dto.EntidadDto;
import pe.gob.pj.administrativos.visitas.model.dto.PuntoControlDto;
import pe.gob.pj.administrativos.visitas.model.dto.ColaboradorDto;
import pe.gob.pj.administrativos.visitas.model.dto.TipoDocumentoDto;
import pe.gob.pj.administrativos.visitas.model.dto.TipoMotivoDto;
import pe.gob.pj.administrativos.visitas.model.dto.VisitaDto;
import pe.gob.pj.administrativos.visitas.model.util.ParametrosDeBusqueda;
import pe.gob.pj.api.commons.utility.ConvertirUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;

import java.util.Map;

/**
* Objeto : MovVisitaDaoImpl
* Descripción : Es una clase de acceso a datos que consulta y registra las visitas. 
* Fecha de Creación : 02/01/2019
* Autor : Carlos Arroyo
* ------------------------------------------------------------------------
* Modificaciones
* Fecha                  Nombre                     Descripción
* ------------------------------------------------------------------------
* 08/05/2019			Carlos Arroyo				CCRV: Cambio en la Consulta del Registro de Visitas
* 26/03/2021			Ricardo Ruiz				CCRV: Cambio en la Consulta del Registro de Visitas (add/filtro_visitado)
*/
@Repository
public class MovVisitaDaoImpl extends BaseDaoImpl<Long, MovVisita> implements MovVisitaDao {
	
	/** TODO CCRV Metodo listar
	* Descripción: Permite consultar las visitas.
	* @param Map<String, Object>: filtros de la consulta de visitas
	* @return List<VisitaDto>: Lista de visatas
	* @exception No aplica.
	*/
	@Override
	public List<VisitaDto> listar(Map<String, Object> params) throws Exception {
		
		//Faltan los filtros.
		
		List<VisitaDto> lista = new ArrayList<>();

			StringBuffer hql = new StringBuffer();
			hql.append("SELECT ");
			hql.append(" vi.nIdVisita,");
			hql.append(" vi.fHIngreso,");
			hql.append(" to_char(vi.fHIngreso, 'DD/MM/YYYY'),");
			hql.append(" to_char(vi.fHIngreso, 'HH24:MI:SS'),");
			hql.append(" vi.fHSalida,");
			hql.append(" to_char(vi.fHSalida, 'DD/MM/YYYY'),");
			hql.append(" to_char(vi.fHSalida, 'HH24:MI:SS'),");
			hql.append(" vi.xNombres,");
			hql.append(" vi.xApellidoPaterno,");
			hql.append(" vi.xApellidoMaterno,");
			hql.append(" vi.xNumeroDocumento,");
			hql.append(" vi.maeTipoDocumento.xAbreviatura,");
			hql.append(" vi.maeEntidad.xRazonSocial,");			
			hql.append(" vi.maeTipoMotivo.xDescripcion,");
			hql.append(" vi.xDescripcionMotivo,");
			hql.append(" vi.visitado.xNombre,");
			hql.append(" vi.visitado.xApellidoPaterno,");
			hql.append(" vi.visitado.xApellidoMaterno,");
			hql.append(" vi.visitado.nNroDocumento,");
			hql.append(" vi.visitado.xDescripcionCargoSiga,");
			hql.append(" vi.visitado.maeTipoDocumento.xAbreviatura,");
			hql.append(" vi.xLugarDescripcion,");
			hql.append(" pc.xNombre,");
			hql.append(" vi.xObservaciones,");
			//TODO CCRV Modificar consulta para enlazar con la Corte - Distrito Judicial
			hql.append(" vc.nomCorte, ");
			//TODO RVI
			hql.append(" pc.xDescripcionLocal,");
			hql.append(" vi.lFueraHorario,");
			hql.append(" vi.nPlataforma,  ");
			hql.append(" mpp.xDescripcion  ");
			hql.append(" FROM MovVisita vi, MaePlataforma mpp  ");
//			hql.append(" inner join MaePlataforma mp on mp.nPlataforma=vi.nPlataforma " );
			hql.append(" inner join vi.cfgPuntoControl pc ");
			hql.append(" inner join pc.vCorte vc ");
			hql.append(" WHERE vi.nPlataforma=mpp.nPlataforma ");
			hql.append(" AND vc.anioProceso= :anio ");
			
			if( !ValidarUtil.isNullOrEmpty(params.get("fecha-inicio")) && !ValidarUtil.isNullOrEmpty(params.get("fecha-fin")) ){
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" vi.fHIngreso BETWEEN :fechaInicio and :fechaFin");
			}
						
			if( !ValidarUtil.isNullOrEmpty(params.get("visitante")) ){
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" UPPER(CONCAT(CONCAT(CONCAT(CONCAT(vi.xNombres,' '),vi.xApellidoPaterno),' '),vi.xApellidoMaterno)) LIKE :visitante");				  
			}
			
			if( !ValidarUtil.isNullOrEmpty(params.get("visitado")) ){
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" UPPER(CONCAT(CONCAT(CONCAT(CONCAT(vi.visitado.xNombre,' '),vi.visitado.xApellidoPaterno),' '),vi.visitado.xApellidoMaterno)) LIKE :visitado");				  
			}

			if( !ValidarUtil.isNullOrEmpty(params.get("nrodocumento")) ){
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" vi.xNumeroDocumento=:").append("nrodocumento");
			}
			
			if( !ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.VIS_ID_PUNTO_CONTROL)) ){
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" vi.cfgPuntoControl.nIdPuntoControl=:").append(ParametrosDeBusqueda.VIS_ID_PUNTO_CONTROL);
			}
			
			if( !ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.VIS_ID_LOCAL)) ){
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" vi.cfgPuntoControl.nIdLocal=:").append(ParametrosDeBusqueda.VIS_ID_LOCAL);
			}
			
			if( !ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.VIS_USUARIO)) ){
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" vi.maeUsuario.nIdUsuario=:").append(ParametrosDeBusqueda.VIS_USUARIO);
			}
			
			//TODO CCRV Agregar el filtro de Distrito Judicial
			if( !ValidarUtil.isNullOrEmpty(params.get("distritoJud")) ){
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" vc.idCorte=:").append("distritoJud");
			}
			
			//TODO RVI
			if( !ValidarUtil.isNullOrEmpty(params.get("order")) ){
				hql.append(" ORDER BY pc.xDescripcionLocal, pc.xNombre ");
			}else {
				hql.append(" ORDER BY vi.fHIngreso desc");
			}
			
			Query query = getSession().createQuery(hql.toString());
			
			//TODO CCRV Agregar el filtro de Distrito Judicial
			query.setParameter("anio", params.get("anio"));
						
			if( !ValidarUtil.isNullOrEmpty(params.get("fecha-inicio")) && !ValidarUtil.isNullOrEmpty(params.get("fecha-fin")) ){
				query.setParameter("fechaInicio", params.get("fecha-inicio"));
				query.setParameter("fechaFin", params.get("fecha-fin"));
			}
			
			if( !ValidarUtil.isNullOrEmpty(params.get("visitante")) ){
				query.setParameter("visitante", "%"+params.get("visitante")+"%");
			}

			if( !ValidarUtil.isNullOrEmpty(params.get("visitado")) ){
				query.setParameter("visitado", "%"+params.get("visitado")+"%");
			}
			
			if( !ValidarUtil.isNullOrEmpty(params.get("nrodocumento")) ){
				query.setParameter("nrodocumento", params.get("nrodocumento"));
			}
			
			if( !ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.VIS_ID_PUNTO_CONTROL)) ){
				query.setParameter(ParametrosDeBusqueda.VIS_ID_PUNTO_CONTROL, params.get(ParametrosDeBusqueda.VIS_ID_PUNTO_CONTROL));
			}
			
			if( !ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.VIS_ID_LOCAL)) ){
				query.setParameter(ParametrosDeBusqueda.VIS_ID_LOCAL, params.get(ParametrosDeBusqueda.VIS_ID_LOCAL));
			}
			
			if( !ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.VIS_USUARIO)) ){
				query.setParameter(ParametrosDeBusqueda.VIS_USUARIO, ConvertirUtil.toLong(params.get(ParametrosDeBusqueda.VIS_USUARIO)));
			}
			
			//TODO CCRV Agregar el filtro de Distrito Judicial
			if( !ValidarUtil.isNullOrEmpty(params.get("distritoJud")) ){
				query.setParameter("distritoJud", params.get("distritoJud"));
			}			
			
			List<Object[]> resultado = query.list();

			if (resultado != null && !resultado.isEmpty()) {
				for (Object[] obj : resultado) {
					VisitaDto visita = new VisitaDto();
					visita.setNumeroVisita(ConvertirUtil.toLong(obj[0]));
					
					visita.setFechaHoraIngreso(ConvertirUtil.toDate(obj[1]));
					visita.setFechaIngreso(ConvertirUtil.toString(obj[2]));
					visita.setHoraIngreso(ConvertirUtil.toString(obj[3]));
					
					visita.setFechaHoraSalida(ConvertirUtil.toDate(obj[4]));
					visita.setFechaSalida(ConvertirUtil.toString(obj[5]));
					visita.setHoraSalida(ConvertirUtil.toString(obj[6]));

					visita.setNombres(ConvertirUtil.toString(obj[7]));
					visita.setApellidoPaterno(ConvertirUtil.toString(obj[8]));
					visita.setApellidoMaterno(ConvertirUtil.toString(obj[9]));
					visita.setNumeroDocumento(ConvertirUtil.toString(obj[10]));					
					TipoDocumentoDto tipoDocumentoVisitante = new TipoDocumentoDto();
					tipoDocumentoVisitante.setAbreviatura(ConvertirUtil.toString(obj[11]));
					visita.setTipoDocumento(tipoDocumentoVisitante);
					
					EntidadDto entidad = new EntidadDto();
					entidad.setRazonSocial(ConvertirUtil.toString(obj[12]));
					visita.setEntidad(entidad);
					
					TipoMotivoDto tipoMotivo = new TipoMotivoDto();
					tipoMotivo.setDescripcion(ConvertirUtil.toString(obj[13]));
					
					visita.setTipoMotivo(tipoMotivo);
					visita.setDescripcionMotivo(ConvertirUtil.toString(obj[14]));
					
					ColaboradorDto visitado = new ColaboradorDto();
					visitado.setNombres(ConvertirUtil.toString(obj[15]));
					visitado.setApellidoPaterno(ConvertirUtil.toString(obj[16]));
					visitado.setApellidoMaterno(ConvertirUtil.toString(obj[17]));
					visitado.setNumeroDocumento(ConvertirUtil.toString(obj[18]));
					visitado.setDescripcionCargoSiga(ConvertirUtil.toString(obj[19]));
					TipoDocumentoDto tipoDocumentoVisitado = new TipoDocumentoDto();
					tipoDocumentoVisitado.setAbreviatura(ConvertirUtil.toString(obj[20]));
					visitado.setTipoDocumento(tipoDocumentoVisitado);
										
					visita.setLugar(ConvertirUtil.toString(obj[21]));
					PuntoControlDto puntoControl = new PuntoControlDto();
					puntoControl.setNombre(ConvertirUtil.toString(obj[22]));
					visita.setObservaciones(ConvertirUtil.toString(obj[23]));
					//TODO CCRV Agregar el filtro de Distrito Judicial
					puntoControl.setDescripcionCorte(ConvertirUtil.toString(obj[24]));
					puntoControl.setxDescripcionLocal(ConvertirUtil.toString(obj[25]));			
					visita.setVisitado(visitado);
					visita.setPuntoControl(puntoControl);
					visita.setlFueraHorario(ConvertirUtil.toString(obj[26]));
					visita.setnPlataforma(ConvertirUtil.toInteger(obj[27]));
					visita.setPlataforma(ConvertirUtil.toString(obj[28]));
					
					lista.add(visita);
				}
			}
		
		return lista;
	}

}
