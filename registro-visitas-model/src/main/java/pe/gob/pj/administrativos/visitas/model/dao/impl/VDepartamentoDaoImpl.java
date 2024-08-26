package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import pe.gob.pj.administrativos.visitas.entity.VDepartamento;
import pe.gob.pj.administrativos.visitas.model.dao.VDepartamentoDao;
import pe.gob.pj.administrativos.visitas.model.dto.DepartamentoDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.api.commons.utility.sicau.ManejadorError;

@Repository
public class VDepartamentoDaoImpl extends BaseDaoImpl<Long, VDepartamento> implements VDepartamentoDao{

	private static final Logger Logger = LoggerFactory.getLogger(VDepartamentoDaoImpl.class);
	
	@Override
	public List<DepartamentoDto> listar() throws Exception {

		List<DepartamentoDto> resultado = new ArrayList<DepartamentoDto>();
		
		try {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT d ");
		hql.append(" FROM VDepartamento d");
		Query query =  getSession().createQuery(hql.toString());

		List<VDepartamento> list = query.list();
		
		if(list!=null && !list.isEmpty())
			for (VDepartamento obj : list) {
				DepartamentoDto departamento = new DepartamentoDto();
				
				departamento.setIdDepartamento(obj.getIdDepartamento());
				departamento.setNomDepartamento(obj.getNomDepartamento());
			    resultado.add(departamento);
			}
		
		
		}catch (Exception e) {
			throw new Exception(
					ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
							ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
		}
		
		return resultado;
	}

	
}
