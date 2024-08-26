package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import pe.gob.pj.administrativos.visitas.entity.VLocal;
import pe.gob.pj.administrativos.visitas.model.dao.VLocalDao;
import pe.gob.pj.administrativos.visitas.model.dto.LocalDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.api.commons.utility.sicau.ManejadorError;

@Repository
public class VLocalDaoImpl extends BaseDaoImpl<Long, VLocal> implements VLocalDao{

	private static final Logger Logger = LoggerFactory.getLogger(VLocalDaoImpl.class);
	
	@Override
	public List<LocalDto> listar(String unidadEjecutora, String corte, String departamento, String provincia,
			String distrito) throws Exception {

		List<LocalDto> resultado = new ArrayList<LocalDto>();
		
		try {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT l ");
		hql.append(" FROM VLocal l");
		//hql.append(" where l.idUniEje=:").append("idUniEje");
		hql.append(" where l.idCorte=:").append("idCorte");
		hql.append(" and   l.idDepartamento=:").append("idDepartamento");
		hql.append(" and   l.idProvincia=:").append("idProvincia");
		hql.append(" and   l.idDistrito=:").append("idDistrito");
		Query query =  getSession().createQuery(hql.toString());
		//query.setParameter( "idUniEje", unidadEjecutora);
		query.setParameter( "idCorte", corte);
		query.setParameter( "idDepartamento", departamento);
		query.setParameter( "idProvincia", provincia);
		query.setParameter( "idDistrito", distrito);
		List<VLocal> list = query.list();
		
		if(list!=null && !list.isEmpty())
			for (VLocal obj : list) {
				LocalDto local = new LocalDto();
				
				local.setIdCodLocal(obj.getIdCodLocal());
				local.setDescripcion(obj.getmLoDescri());
				local.setDireccion(obj.getmLoDireccion());
				local.setIdUniEje(obj.getIdUniEje());
				local.setIdCorte(obj.getIdCorte());
				local.setIdDepartamento(obj.getIdDepartamento());
				local.setIdProvincia(obj.getIdProvincia());
				local.setIdDistrito(obj.getIdDistrito());
			    resultado.add(local);
			}
		
		
		}catch (Exception e) {
			throw new Exception(
					ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
							ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
		}
		
		return resultado;
	}

}
