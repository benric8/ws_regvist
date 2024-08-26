package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import pe.gob.pj.administrativos.visitas.entity.CfgPuntoControl;
import pe.gob.pj.administrativos.visitas.model.dao.CfgPuntoControlDao;
import pe.gob.pj.administrativos.visitas.model.dto.LocalDto;
import pe.gob.pj.administrativos.visitas.model.dto.PuntoControlDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.model.util.ParametrosDeBusqueda;
import pe.gob.pj.administrativos.visitas.model.util.Utilitarios;
import pe.gob.pj.api.commons.utility.ConvertirUtil;

@Repository("cfgPuntoControlDao")
public class CfgPuntoControlDaoImpl extends BaseDaoImpl<Long, CfgPuntoControl> implements CfgPuntoControlDao{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<PuntoControlDto> buscar(Map<String, Object> params) {

		List<PuntoControlDto> resultado = new ArrayList<PuntoControlDto>();
		StringBuilder hql = new StringBuilder(createBasicQuery());
		List<Entry<String, Object>> listaParams = new ArrayList<Entry<String, Object>>();

		for (Map.Entry<String, Object> map : params.entrySet()) {
			if (Utilitarios.validaValor(map, ParametrosDeBusqueda.PTCT_NOMBRE)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				if (Utilitarios.validaValor(map, ParametrosDeBusqueda.ADD_LIKE))
					hql.append(" UPPER(o.xNombre)=UPPER(:").append(ParametrosDeBusqueda.PTCT_NOMBRE).append(")");
				else
					hql.append(" UPPER(o.xNombre) LIKE UPPER('%'||:").append(ParametrosDeBusqueda.PTCT_NOMBRE).append("||'%')");
				listaParams.add(map);
			} 
			if (Utilitarios.validaValor(map, ParametrosDeBusqueda.PTCT_LOCAL)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" UPPER(o.nIdLocal)=UPPER(:").append(ParametrosDeBusqueda.PTCT_LOCAL).append(")");
				listaParams.add(map);
			} 
			if (Utilitarios.validaValor(map, ParametrosDeBusqueda.PTCT_UNI_EJE)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" UPPER(o.nIdUniEje)=UPPER(:").append(ParametrosDeBusqueda.PTCT_UNI_EJE).append(")");
				listaParams.add(map);
			}
			
			if (Utilitarios.validaValor(map, ParametrosDeBusqueda.PTCT_CORTE)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				//TODO CCRV Modificacion de filtro de didtrito judicial 
				hql.append(" UPPER(o.vCorte.idCorte)=UPPER(:").append(ParametrosDeBusqueda.PTCT_CORTE).append(")");
				//hql.append(" UPPER(o.nIdCorte)=UPPER(:").append(ParametrosDeBusqueda.PTCT_CORTE).append(")");
				listaParams.add(map);
			}
						
			if (Utilitarios.validaValor(map, ParametrosDeBusqueda.PTCT_OFICINA)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				if (Utilitarios.validaValor(map, ParametrosDeBusqueda.ADD_LIKE))
					hql.append(" UPPER(o.xDescripcionOficina)=UPPER(:").append(ParametrosDeBusqueda.PTCT_OFICINA).append(")");
				else
					hql.append(" UPPER(o.xDescripcionOficina) LIKE UPPER('%'||:").append(ParametrosDeBusqueda.PTCT_OFICINA).append("||'%')");
				listaParams.add(map);
			} 
			if (Utilitarios.validaValor(map, ParametrosDeBusqueda.PTCT_INGRESO)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				if (Utilitarios.validaValor(map, ParametrosDeBusqueda.ADD_LIKE))
					hql.append(" UPPER(o.maeIngreso.xDescripcion)=UPPER(:").append(ParametrosDeBusqueda.PTCT_INGRESO).append(")");
				else
					hql.append(" UPPER(o.maeIngreso.xDescripcion) LIKE UPPER('%'||:").append(ParametrosDeBusqueda.PTCT_INGRESO).append("||'%')");
				listaParams.add(map);
			} 
			if (Utilitarios.validaValor(map, ParametrosDeBusqueda.PTCT_ESTADO)) {
				hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
				hql.append(" o.lFlagActivo=:").append(ParametrosDeBusqueda.PTCT_ESTADO);
				listaParams.add(map);
			}

			
		}

		Query query = getSession().createQuery(hql.toString());
		for (Map.Entry<String, Object> map : listaParams) {
			if (map.getValue() instanceof Collection)
				query.setParameterList(map.getKey(), (Collection) map.getValue());
			else
				query.setParameter(map.getKey(), map.getValue());
		}

		List<CfgPuntoControl> list = query.list();
		for (CfgPuntoControl u : list) {
			PuntoControlDto dto = new PuntoControlDto();
			dto.setnIdPuntoControl(u.getnIdPuntoControl());
			dto.setnIdLocal(u.getnIdLocal());
			dto.setxDescripcionLocal(u.getxDescripcionLocal());
			dto.setnIdOficina(u.getnIdOficina());
			dto.setxDescripcionOficina(u.getxDescripcionOficina());
			dto.setIdUnidadEjecutora(u.getnIdUniEje());
			dto.setIdCorte(u.getvCorte().getIdCorte());
			dto.setlFlagActivo(u.getlFlagActivo());
			dto.setfRegistro(u.getfRegistro());
			dto.setNombre(u.getxNombre());
			resultado.add(dto);
		}
		return resultado;
	}
	
	public List<PuntoControlDto> listarTodos(){
		
		List<PuntoControlDto> resultado = new ArrayList<PuntoControlDto>();
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT ");
		hql.append(" pc.nIdPuntoControl,");
		hql.append(" pc.xDescripcionLocal,");
		hql.append(" pc.xDescripcionOficina,");		
		hql.append(" pc.lFlagActivo,");
		hql.append(" pc.xNombre");
		hql.append(" FROM CfgPuntoControl pc ");
		
		Query query = getSession().createQuery(hql.toString());
		
		List<Object[]> list = query.list();
		for (Object[] obj : list) {
			PuntoControlDto dto = new PuntoControlDto();
			dto.setnIdPuntoControl(ConvertirUtil.toLong(obj[0]));
			dto.setxDescripcionLocal(ConvertirUtil.toString(obj[1]));
			dto.setxDescripcionOficina(ConvertirUtil.toString(obj[2]));
			dto.setlFlagActivo(ConvertirUtil.toString(obj[3]));
			dto.setNombre(ConvertirUtil.toString(obj[4]));
			resultado.add(dto);
		}
		return resultado;
	}
	
	public List<LocalDto> listarLocales(String unidadEjecutora, String corte){
		
		List<LocalDto> resultado = new ArrayList<LocalDto>();
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT distinct");
		hql.append(" pc.nIdLocal,");
		hql.append(" pc.xDescripcionLocal");
		hql.append(" FROM CfgPuntoControl pc ");
		hql.append(" where pc.lFlagActivo=:").append("estado");
		//hql.append(" and pc.nIdUniEje=:").append("nIdUniEje");
		hql.append(" and pc.vCorte.idCorte=:").append("nIdCorte");
		//hql.append(" and pc.nIdCorte=:").append("nIdCorte");
		Query query =  getSession().createQuery(hql.toString());
		query.setParameter( "estado", ConstantesVisitas.ACTIVO);
		//query.setParameter( "nIdUniEje", unidadEjecutora);
		query.setParameter( "nIdCorte", corte);
		
		List<Object[]> list = query.list();
		
		if(list!=null && !list.isEmpty())
			for (Object[] obj : list) {
				LocalDto local = new LocalDto();
				local.setIdCodLocal(ConvertirUtil.toString(obj[0]));
				local.setDescripcion(ConvertirUtil.toString(obj[1]));
			    resultado.add(local);
			}
		
		return resultado;
	}
	
	public List<PuntoControlDto> listarPuntoControlLocal(String idLocal, String unidadEjecutora, String corte){
		
		List<PuntoControlDto> resultado = new ArrayList<PuntoControlDto>();
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT ");
		hql.append(" pc.nIdPuntoControl,");
		hql.append(" pc.xNombre");
		hql.append(" FROM CfgPuntoControl pc ");
		hql.append(" where pc.lFlagActivo=:").append("estado");
		hql.append(" and pc.nIdLocal=:").append("nIdLocal");
		//hql.append(" and pc.nIdUniEje=:").append("nIdUniEje");
		hql.append(" and pc.vCorte.idCorte=:").append("nIdCorte");
		//hql.append(" and pc.nIdCorte=:").append("nIdCorte");
		Query query =  getSession().createQuery(hql.toString());
		query.setParameter( "estado", ConstantesVisitas.ACTIVO);
		query.setParameter( "nIdLocal", idLocal);
		//query.setParameter( "nIdUniEje", unidadEjecutora);
		query.setParameter( "nIdCorte", corte);
		
		List<Object[]> list = query.list();
		
		if(list!=null && !list.isEmpty())
			for (Object[] obj : list) {
				PuntoControlDto punto = new PuntoControlDto();
				punto.setnIdPuntoControl(ConvertirUtil.toLong(obj[0]));
				punto.setNombre(ConvertirUtil.toString(obj[1]));
			   
			    resultado.add(punto);
			}
		
		return resultado;
	}

	//TODO RVI
	public List<PuntoControlDto> listarPuntoControl(String unidadEjecutora, String corte){
		
		List<PuntoControlDto> resultado = new ArrayList<PuntoControlDto>();
		
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT ");
		hql.append(" pc.nIdPuntoControl, ");
		hql.append(" pc.xNombre, ");
		hql.append(" pc.xDescripcionLocal ");
		hql.append(" FROM CfgPuntoControl pc ");
		hql.append(" where pc.lFlagActivo=:").append("estado");
		//hql.append(" and pc.nIdUniEje=:").append("nIdUniEje");
		hql.append(" and pc.vCorte.idCorte=:").append("nIdCorte");
		hql.append(" order by pc.xDescripcionLocal, pc.xNombre ");

		Query query =  getSession().createQuery(hql.toString());
		query.setParameter( "estado", ConstantesVisitas.ACTIVO);
		//query.setParameter( "nIdUniEje", unidadEjecutora);
		query.setParameter( "nIdCorte", corte);
		List<Object[]> list = query.list();
		
		if(list!=null && !list.isEmpty())
			for (Object[] obj : list) {
				PuntoControlDto punto = new PuntoControlDto();
				punto.setnIdPuntoControl(ConvertirUtil.toLong(obj[0]));
				punto.setNombre(ConvertirUtil.toString(obj[1]));
			    punto.setxDescripcionLocal(ConvertirUtil.toString(obj[2]));
			    resultado.add(punto);
			}
		
		return resultado;
	}

}
