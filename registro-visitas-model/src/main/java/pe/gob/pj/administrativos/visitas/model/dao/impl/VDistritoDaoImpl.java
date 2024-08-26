package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import pe.gob.pj.administrativos.visitas.entity.VDistrito;
import pe.gob.pj.administrativos.visitas.model.dao.VDistritoDao;
import pe.gob.pj.administrativos.visitas.model.dto.DistritoDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.api.commons.utility.sicau.ManejadorError;

@Repository
public class VDistritoDaoImpl extends BaseDaoImpl<Long, VDistrito> implements VDistritoDao{

	
	private static final Logger Logger = LoggerFactory.getLogger(VDistritoDaoImpl.class);
	
	@Override
	public List<DistritoDto> listar(String idDepartamento,String idProvincia) throws Exception {

		List<DistritoDto> resultado = new ArrayList<DistritoDto>();
		try {
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT d ");
			hql.append(" FROM VDistrito d");
			hql.append(" where d.idProvincia=:").append("idProvincia");
			hql.append(" and d.idDepartamento=:").append("idDepartamento");
			Query query =  getSession().createQuery(hql.toString());
			query.setParameter( "idProvincia", idProvincia);
			query.setParameter( "idDepartamento", idDepartamento);

			List<VDistrito> list = query.list();
			
			if(list!=null && !list.isEmpty())
				for (VDistrito obj : list) {
					DistritoDto distrito = new DistritoDto();
					distrito.setIdDistrito(obj.getIdDistrito());
					distrito.setIdProvincia(obj.getIdProvincia());
					distrito.setIdDepartamento(obj.getIdDepartamento());
					distrito.setNomDistrito(obj.getNomDistrito());
				    resultado.add(distrito);
				}
			
			
			}catch (Exception e) {
				throw new Exception(
						ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
								ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
			}
			
			return resultado;
	}

}
