package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import pe.gob.pj.administrativos.visitas.entity.MaeOpcion;
import pe.gob.pj.administrativos.visitas.model.dao.MaeOpcionDao;
import pe.gob.pj.administrativos.visitas.model.dto.OpcionDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.api.commons.utility.ConvertirUtil;

@Repository
public class MaeOpcionDaoImpl extends BaseDaoImpl<Long, MaeOpcion> implements MaeOpcionDao {

	private static final Logger Logger = LoggerFactory.getLogger(MaeOpcionDaoImpl.class);

	@Override
	public List<OpcionDto> buscarOpcionPadre(Long nidPerfil) throws Exception {

		List<OpcionDto> resultado = new ArrayList<OpcionDto>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT s.nIdOpcion, s.xDescripcion,");
		hql.append(" s.nIdOpcionPadre, s.xUrl, s.nOrden ");
		hql.append("  FROM MaeOpcion s ");
		hql.append("  Where s.nIdOpcionPadre is null ");
		hql.append("  and s.lFlagActivo=:estado");
		hql.append("  order by s.nOrden ");

		Query query = getSession().createQuery(hql.toString());
		query.setParameter("estado", ConstantesVisitas.ACTIVO);

		List<Object[]> list = query.list();
		if (list != null && !list.isEmpty()) {
			for (Object[] obj : list) {
				OpcionDto opcion = new OpcionDto();
				opcion.setnIdOpcion(ConvertirUtil.toLong(obj[0]));
				opcion.setxNombre(ConvertirUtil.toString(obj[1]));
				opcion.setnIdOpcionPadre(obj[2] != null ? ConvertirUtil.toLong(obj[2]) : null);
				opcion.setxMetodo(ConvertirUtil.toString(obj[3]));
				opcion.setnOrden(ConvertirUtil.toInteger(obj[4]));
				resultado.add(opcion);
			}
		}
		return resultado;
	}

	@Override
	public List<OpcionDto> buscarOpcionHijosPermiso(Long nidPerfil) throws Exception {

		List<OpcionDto> resultado = new ArrayList<OpcionDto>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT s.nIdOpcion, ");
		hql.append(" s.xDescripcion, s.nIdOpcionPadre,");
		hql.append(" s.xUrl, s.nOrden ");
		hql.append(" FROM MaeOpcion s ");
		hql.append(" inner join s.cfgPerfilOpcionList p ");
		hql.append("  Where s.nIdOpcionPadre is not null ");
		hql.append("   and  p.maePerfil.nIdPerfil=:nidPerfil ");
		hql.append("   and  s.lFlagActivo=:estado ");
		hql.append("   and  p.lFlagActivo=:estado1 ");
		hql.append("   order by s.nIdOpcionPadre,s.nOrden ");

		Query query = getSession().createQuery(hql.toString());
		query.setParameter("nidPerfil", nidPerfil);
		query.setParameter("estado", ConstantesVisitas.ACTIVO);
		query.setParameter("estado1", ConstantesVisitas.ACTIVO);
		
		List<Object[]> list = query.list();
		if (list != null && !list.isEmpty()) {
			for (Object[] obj : list) {
				OpcionDto opcion = new OpcionDto();
				opcion.setnIdOpcion(ConvertirUtil.toLong(obj[0]));
				opcion.setxNombre(ConvertirUtil.toString(obj[1]));
				opcion.setnIdOpcionPadre(obj[2] != null ? ConvertirUtil.toLong(obj[2]) : null);
				opcion.setxMetodo(ConvertirUtil.toString(obj[3]));
				opcion.setnOrden(ConvertirUtil.toInteger(obj[4]));
				resultado.add(opcion);
			}
		}
		return resultado;
	}

	@Override
	public List<OpcionDto> buscarOpcionHijos() throws Exception {

		List<OpcionDto> resultado = new ArrayList<OpcionDto>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT s.nIdOpcion, ");
		hql.append(" s.xDescripcion, s.nIdOpcionPadre,");
		hql.append(" s.xUrl, s.nOrden ");
		hql.append(" FROM MaeOpcion s ");
		hql.append("  where  s.lFlagActivo=:estado ");
		hql.append("   and s.nIdOpcionPadre is not null ");
		hql.append("   order by s.nIdOpcionPadre, s.nOrden ");

		Query query = getSession().createQuery(hql.toString());
		query.setParameter("estado", ConstantesVisitas.ACTIVO);
		
		List<Object[]> list = query.list();
		if (list != null && !list.isEmpty()) {
			for (Object[] obj : list) {
				OpcionDto opcion = new OpcionDto();
				opcion.setnIdOpcion(ConvertirUtil.toLong(obj[0]));
				opcion.setxNombre(ConvertirUtil.toString(obj[1]));
				opcion.setnIdOpcionPadre(obj[2] != null ? ConvertirUtil.toLong(obj[2]) : null);
				opcion.setxMetodo(ConvertirUtil.toString(obj[3]));
				opcion.setnOrden(ConvertirUtil.toInteger(obj[4]));
				resultado.add(opcion);
			}
		}
		return resultado;
	}
	
}