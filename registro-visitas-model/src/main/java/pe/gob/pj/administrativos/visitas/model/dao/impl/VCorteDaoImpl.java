package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import pe.gob.pj.administrativos.visitas.entity.VCorte;
import pe.gob.pj.administrativos.visitas.model.dao.VCortesDao;
import pe.gob.pj.administrativos.visitas.model.dto.CorteDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.api.commons.utility.sicau.ManejadorError;

/**
* Objeto : VCorteDaoImpl
* Descripción : Es una clase de acceso a datos que permite obtener informacion de cortes o distritos judiciales. 
* Fecha de Creación : 02/01/2019
* Autor : Carlos Arroyo
* ------------------------------------------------------------------------
* Modificaciones
* Fecha                  Nombre                     Descripción
* ------------------------------------------------------------------------
* 08/05/2019			Carlos Arroyo				CRC: Cambio por Reseteo de Clave
*/
@Repository
public class VCorteDaoImpl extends BaseDaoImpl<Long, VCorte> implements VCortesDao{

	private static final Logger Logger = LoggerFactory.getLogger(VCorteDaoImpl.class);
	
	@Override
	public CorteDto obtenerCorte(String anio, String unidadEjecutora, String idCorte) throws Exception {

		CorteDto resultado = new CorteDto();
		
		try {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT c ");
		hql.append(" FROM VCorte c");
		hql.append(" where c.anioProceso=:").append("anio");
		hql.append(" and c.idUniEje=:").append("idUniEje");
		hql.append(" and c.idCorte=:").append("idCorte");
		Query query =  getSession().createQuery(hql.toString());
		query.setParameter( "anio", anio);
		query.setParameter( "idUniEje", unidadEjecutora);
		query.setParameter( "idCorte", idCorte);
		List<VCorte> list = query.list();
		
		if(list!=null && !list.isEmpty())
			for (VCorte obj : list) {
				CorteDto corte = new CorteDto();
				corte.setAnioProceso(obj.getAnioProceso());
				corte.setIdUniEje(obj.getIdUniEje());
				corte.setIdCorte(obj.getIdCorte());
				corte.setNombreCorte(obj.getNomCorte());
				
			   resultado=corte;
			}
		
		
		}catch (Exception e) {
			throw new Exception(
					ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
							ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
		}
		
		return resultado;
	}
	
	/** 
	* Descripción: Consulta los distritos judiciales 
	* @param anio: es el anio actual para consultar los distritos judiciales
	* @return Retorna la lista distritos judiciales.
	* @exception Manejo de errores no clasificados.
	*/	
	@Override
	public List<CorteDto> listarCortes(String anio) throws Exception {

		List<CorteDto> resultado = new ArrayList<CorteDto>();
		try {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT c ");
		hql.append(" FROM VCorte c");
		hql.append(" where c.anioProceso=:").append("anio");
		hql.append(" ORDER BY c.idCorte ");
		Query query =  getSession().createQuery(hql.toString());
		query.setParameter( "anio", anio);
		List<VCorte> list = query.list();
		if(list!=null && !list.isEmpty())
			for (VCorte obj : list) {
				CorteDto corte = new CorteDto();
				corte.setAnioProceso(obj.getAnioProceso());
				corte.setIdUniEje(obj.getIdUniEje());
				corte.setIdCorte(obj.getIdCorte());
				corte.setNombreCorte(obj.getNomCorte());
			    resultado.add(corte);
			}
		}catch (Exception e) {
			throw new Exception(
					ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
							ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
		}
		return resultado;
	}


}
