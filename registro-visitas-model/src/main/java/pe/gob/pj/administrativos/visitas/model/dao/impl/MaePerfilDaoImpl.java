package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pe.gob.pj.administrativos.visitas.entity.MaePerfil;
import pe.gob.pj.administrativos.visitas.model.dao.MaePerfilDao;
import pe.gob.pj.administrativos.visitas.model.dto.PerfilDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.api.commons.utility.ConvertirUtil;

@Repository
public class MaePerfilDaoImpl extends BaseDaoImpl<Long, MaePerfil> implements MaePerfilDao {

	private static final Logger Logger = LoggerFactory.getLogger(MaePerfilDaoImpl.class);
	
	

	
	@Override
	public List<PerfilDto> buscarPerfil() throws Exception {

		List<PerfilDto> resultado = new ArrayList<PerfilDto>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT o.nIdPerfil, o.xDescripcion ");
		hql.append(" FROM MaePerfil o");
		hql.append(" where o.lFlagActivo=:").append("estado");
		// Alexis Távara VPP v.1.0.7
		hql.append(" and o.lCitas=:").append("estadoCitas");
		Query query =  getSession().createQuery(hql.toString());
		query.setParameter( "estado", ConstantesVisitas.ACTIVO);
		// Alexis Távara VPP v.1.0.7
		query.setParameter( "estadoCitas", ConstantesVisitas.INACTIVO);
	    
		List<Object[]> list = query.list();
		
		if(list!=null && !list.isEmpty())
			for (Object[] obj : list) {
			    PerfilDto perfilDto = new PerfilDto();
			    perfilDto.setnIdPerfil(ConvertirUtil.toLong(obj[0]));
			    perfilDto.setxDescripcion(ConvertirUtil.toString(obj[1]));
			    resultado.add(perfilDto);
			}
		
		return resultado;

	}
	
}