package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import pe.gob.pj.administrativos.visitas.entity.VCorte;
import pe.gob.pj.administrativos.visitas.entity.VUnidadEjecutora;
import pe.gob.pj.administrativos.visitas.model.dao.VUnidadEjecutoraDao;
import pe.gob.pj.administrativos.visitas.model.dto.CorteDto;
import pe.gob.pj.administrativos.visitas.model.dto.UnidadEjecutoraDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.api.commons.utility.sicau.ManejadorError;

@Repository
public class VUnidadEjecutoraDaoImpl extends BaseDaoImpl<Long, VUnidadEjecutora> implements VUnidadEjecutoraDao{

	private static final Logger Logger = LoggerFactory.getLogger(VUnidadEjecutoraDaoImpl.class);

	
	@Override
	public List<UnidadEjecutoraDto> listar(String anio) throws Exception {

		List<UnidadEjecutoraDto> resultado = new ArrayList<UnidadEjecutoraDto>();
		
		try {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT ue ");
		hql.append(" FROM VUnidadEjecutora ue");
		hql.append(" where ue.anioProceso=:").append("anio");
		Query query =  getSession().createQuery(hql.toString());
		query.setParameter( "anio", anio);
	    
		List<VUnidadEjecutora> list = query.list();
		
		if(list!=null && !list.isEmpty())
			for (VUnidadEjecutora obj : list) {
				UnidadEjecutoraDto unidadEjecutoraDto = new UnidadEjecutoraDto();
				unidadEjecutoraDto.setAnioProceso(obj.getAnioProceso());
				unidadEjecutoraDto.setIdUniEje(obj.getIdUniEje());
				unidadEjecutoraDto.setNombreUniEje(obj.getNomUniEje());
			    resultado.add(unidadEjecutoraDto);
			}
		
		
		}catch (Exception e) {
			throw new Exception(
					ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
							ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
		}
		

		
		return resultado;
		

	}


	@Override
	public UnidadEjecutoraDto obtenerUnidadEjecutora(String anio, String idUnidadEjecutora) throws Exception {

		UnidadEjecutoraDto resultado = new UnidadEjecutoraDto();
		
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT ue ");
			hql.append(" FROM VUnidadEjecutora ue");
			hql.append(" where ue.anioProceso=:").append("anio");
			hql.append(" and ue.idUniEje=:").append("idUniEje");
			Query query =  getSession().createQuery(hql.toString());
			query.setParameter( "anio", anio);
			query.setParameter( "idUniEje", idUnidadEjecutora);
			
			List<VUnidadEjecutora> list = query.list();
			
			if(list!=null && !list.isEmpty())
				for (VUnidadEjecutora obj : list) {
					UnidadEjecutoraDto unidadEjecutoraDto = new UnidadEjecutoraDto();
					unidadEjecutoraDto.setAnioProceso(obj.getAnioProceso());
					unidadEjecutoraDto.setIdUniEje(obj.getIdUniEje());
					unidadEjecutoraDto.setNombreUniEje(obj.getNomUniEje());
				    resultado=unidadEjecutoraDto;
		}
		
		
		}catch (Exception e) {
			throw new Exception(
					ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
							ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
		}
		
		return resultado;
	}
	

}
