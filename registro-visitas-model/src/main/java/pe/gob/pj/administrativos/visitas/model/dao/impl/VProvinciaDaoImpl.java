package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import pe.gob.pj.administrativos.visitas.entity.VProvincia;
import pe.gob.pj.administrativos.visitas.model.dao.VProvinciaDao;
import pe.gob.pj.administrativos.visitas.model.dto.ProvinciaDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.api.commons.utility.sicau.ManejadorError;
@Repository
public class VProvinciaDaoImpl extends BaseDaoImpl<Long, VProvincia> implements VProvinciaDao{

	private static final Logger Logger = LoggerFactory.getLogger(VProvinciaDaoImpl.class);

	@Override
	public List<ProvinciaDto> listar(String idDepartamento) throws Exception {

		List<ProvinciaDto> resultado = new ArrayList<ProvinciaDto>();
		
		try {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT p ");
		hql.append(" FROM VProvincia p");
		hql.append(" where p.idDepartamento=:").append("idDepartamento");
		Query query =  getSession().createQuery(hql.toString());
		query.setParameter( "idDepartamento", idDepartamento);

		List<VProvincia> list = query.list();
		
		if(list!=null && !list.isEmpty())
			for (VProvincia obj : list) {
				ProvinciaDto provincia = new ProvinciaDto();
				provincia.setIdProvincia(obj.getIdProvincia());
				provincia.setIdDepartamento(obj.getIdDepartamento());
				provincia.setNomProvincia(obj.getNomProvincia());
			    resultado.add(provincia);
			}
		
		
		}catch (Exception e) {
			throw new Exception(
					ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
							ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
		}
		
		return resultado;
	}

	
	


}
