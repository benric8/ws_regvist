package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pe.gob.pj.administrativos.visitas.entity.MaeEntidad;
import pe.gob.pj.administrativos.visitas.model.dao.MaeEntidadDao;
import pe.gob.pj.administrativos.visitas.model.dto.EntidadDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.model.util.ParametrosDeBusqueda;
import pe.gob.pj.api.commons.utility.ConvertirUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;
import pe.gob.pj.api.commons.utility.sicau.ManejadorError;


@Repository
public class MaeEntidadDaoImpl extends BaseDaoImpl<Long, MaeEntidad> implements MaeEntidadDao{

	private static final Logger Logger = LoggerFactory.getLogger(MaeEntidadDaoImpl.class);
	
	
	@Override
	public List<EntidadDto> listar(Map<String, Object> params) throws Exception {
	
		EntidadDto ent= null;
		List<EntidadDto> lista = new ArrayList<>();
		try {
			String sLog = "MaeEntidadDaoImpl.listar ";
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT e.nIdEntidad");
			hql.append(" , e.xRuc");
			hql.append(" , e.xRazonSocial");
			hql.append(" , e.lFlagActivo");
			hql.append(" FROM MaeEntidad e ");
			hql.append(" WHERE e.lFlagActivo = :").append(ParametrosDeBusqueda.EN_ESTADO);
			
			if (!ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.EN_RUC))) {
			hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
			hql.append(" e.xRuc LIKE :").append(ParametrosDeBusqueda.EN_RUC);
			}
			
			if (ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.EN_RUC)) && (!ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.EN_RAZSO)))){

			hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
			hql.append(" e.xRazonSocial LIKE :").append(ParametrosDeBusqueda.EN_RAZSO);
			}
			
			Query query = getSession().createQuery(hql.toString());
			
			query.setParameter(ParametrosDeBusqueda.EN_ESTADO,params.get(ParametrosDeBusqueda.EN_ESTADO));
			
			if (ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.EN_RUC)) && (!ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.EN_RAZSO)))){
			query.setParameter(ParametrosDeBusqueda.EN_RAZSO,params.get(ParametrosDeBusqueda.EN_RAZSO));
			}
			
			if (!ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.EN_RUC))) {
			query.setParameter(ParametrosDeBusqueda.EN_RUC,params.get(ParametrosDeBusqueda.EN_RUC));
			}
			
			List<Object[]> resultado = query.list();
			
			if(resultado!=null &&  !resultado.isEmpty()){
				for (Object[] obj : resultado) {
					ent = new EntidadDto();
					ent.setIdEntidad(ConvertirUtil.toLong(obj[0]));
					ent.setRuc(ConvertirUtil.toString(obj[1]));
					ent.setRazonSocial(ConvertirUtil.toString(obj[2]));
					ent.setEstado(ConvertirUtil.toString(obj[3]));
					lista.add(ent);

				}
			}
		} catch (Exception e) {
			throw new Exception(ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
		}
		return lista;
	}


	



}
