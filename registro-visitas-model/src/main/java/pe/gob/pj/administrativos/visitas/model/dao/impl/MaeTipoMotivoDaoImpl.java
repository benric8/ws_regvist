package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pe.gob.pj.administrativos.visitas.entity.MaeTipoMotivo;
import pe.gob.pj.administrativos.visitas.model.dao.MaeTipoMotivoDao;
import pe.gob.pj.administrativos.visitas.model.dto.TipoMotivoDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.model.util.ParametrosDeBusqueda;
import pe.gob.pj.api.commons.utility.ConvertirUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;
import pe.gob.pj.api.commons.utility.sicau.ManejadorError;

@Repository
public class MaeTipoMotivoDaoImpl extends BaseDaoImpl<Long, MaeTipoMotivo> implements MaeTipoMotivoDao {



	@SuppressWarnings("unchecked")
	@Override
	public List<TipoMotivoDto> listar(String estado) throws Exception {

		List<TipoMotivoDto> resultado = new ArrayList<TipoMotivoDto>();
		try {

			StringBuilder hql = new StringBuilder();
			hql.append("SELECT tm.nIdTipoMotivo, tm.xDescripcion");
			hql.append("  FROM MaeTipoMotivo tm ");
			hql.append("  WHERE tm.lFlagActivo=:estado");

			Query query = getSession().createQuery(hql.toString());
			query.setParameter("estado", estado);

			List<Object[]> list = query.list();
			if (list != null && !list.isEmpty()) {
				for (Object[] obj : list) {
					TipoMotivoDto tipoMotivo = new TipoMotivoDto();
					tipoMotivo.setIdTipoMotivo(ConvertirUtil.toLong(obj[0]));
					tipoMotivo.setDescripcion(ConvertirUtil.toString(obj[1]));
					resultado.add(tipoMotivo);
				}
			}

		} catch (Exception e) {
			throw new Exception(
					ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
							ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
		}
		return resultado;
	}

	@Override
	public List<TipoMotivoDto> listarFiltros(Map<String, Object> params) throws Exception {
		
		List<TipoMotivoDto> lista = new ArrayList<>();
			
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT ");
		hql.append(" tm.nIdTipoMotivo,");
		hql.append(" tm.xDescripcion,");
		hql.append(" tm.lFlagActivo");
		hql.append(" FROM MaeTipoMotivo tm ");
		

		if( !ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.TM_DESCRIPCION)) ){
			hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
			hql.append(" tm.xDescripcion LIKE :").append(ParametrosDeBusqueda.TM_DESCRIPCION);
		}
		
		if( !ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.TM_ESTADO)) ){
			hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
			hql.append(" tm.lFlagActivo=:").append(ParametrosDeBusqueda.TM_ESTADO);
		}
	
		hql.append(" ORDER BY tm.xDescripcion desc");
					
		Query query = getSession().createQuery(hql.toString());
					
		if( !ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.TM_DESCRIPCION)) ){
			query.setParameter(ParametrosDeBusqueda.TM_DESCRIPCION,"%"+params.get(ParametrosDeBusqueda.TM_DESCRIPCION)+"%");
		}
		if( !ValidarUtil.isNullOrEmpty(params.get(ParametrosDeBusqueda.TM_ESTADO)) ){
			query.setParameter(ParametrosDeBusqueda.TM_ESTADO,params.get(ParametrosDeBusqueda.TM_ESTADO));
		}
		
		List<Object[]> resultado = query.list();
		
		if (resultado != null && !resultado.isEmpty()) {
			for (Object[] obj : resultado) {
				TipoMotivoDto tipoMotivo = new TipoMotivoDto();
				tipoMotivo.setIdTipoMotivo(ConvertirUtil.toLong(obj[0]));
				tipoMotivo.setDescripcion(ConvertirUtil.toString(obj[1]));
				tipoMotivo.setEstado(ConvertirUtil.toString(obj[2]));
				lista.add(tipoMotivo);
			}
		}

		
	return lista;
		
	}

}
