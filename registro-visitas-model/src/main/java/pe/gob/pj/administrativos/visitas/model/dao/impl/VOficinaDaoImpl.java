package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;


import pe.gob.pj.administrativos.visitas.entity.VOficina;
import pe.gob.pj.administrativos.visitas.model.dao.VOficinaDao;

import pe.gob.pj.administrativos.visitas.model.dto.OficinaDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.api.commons.utility.sicau.ManejadorError;

@Repository
public class VOficinaDaoImpl  extends BaseDaoImpl<Long, VOficina> implements VOficinaDao{

	@Override
	public List<OficinaDto> listar(String anio, String local, String unidadEjecutora, String corte, String ubigeo) throws Exception {

				List<OficinaDto> resultado = new ArrayList<OficinaDto>();
				
				try {
				StringBuilder hql = new StringBuilder();
				hql.append("SELECT o ");
				hql.append(" FROM VOficina o");
				hql.append(" where o.anioProceso=:").append("anioProceso");
				//hql.append(" and   o.idUniEje=:").append("idUniEje");
				hql.append(" and   o.idCorte=:").append("idCorte");
				hql.append(" and   o.ubigeo=:").append("ubigeo");
				hql.append(" and   o.idLocal=:").append("idLocal");
				Query query =  getSession().createQuery(hql.toString());
				query.setParameter( "anioProceso", anio);
				//query.setParameter( "idUniEje", unidadEjecutora);
				query.setParameter( "idCorte", corte);
				query.setParameter( "ubigeo", ubigeo);
				query.setParameter( "idLocal", local);
				List<VOficina> list = query.list();
				
				if(list!=null && !list.isEmpty()) 
					for (VOficina obj : list) {
						OficinaDto oficina = new OficinaDto();
						oficina.setNomDistrito(obj.getNomDistrito());
						
						oficina.setIdOficina(obj.getIdOficina());
						oficina.setNomOficina(obj.getNomOficina());
						oficina.setOficinaDireccion(obj.getOficinaDireccion());
						oficina.setOficinaDistrito(obj.getOficinaDistrito());
						oficina.setDireccion(obj.getDireccion());
						oficina.setUbigeo(obj.getUbigeo());
						oficina.setNomDistrito(obj.getNomDistrito());
						oficina.setNomProvincia(obj.getNomProvincia());
						oficina.setNomDepartamento(obj.getNomDepartamento());
						oficina.setAnioProceso(obj.getAnioProceso());
						oficina.setIdUniEje(obj.getIdUniEje());
						oficina.setNomUniEje(obj.getNomUniEje());
						oficina.setIdCorte(obj.getIdCorte());
						oficina.setNomCorte(obj.getNomCorte());
						oficina.setIdTipDep(obj.getIdTipDep());
						oficina.setNomTipDep(obj.getNomTipDep());
						oficina.setIdEspDep(obj.getIdEspDep());
						oficina.setNomEspDep(obj.getNomEspDep());
						oficina.setIdSubEspDep(obj.getIdSubEspDep());
						oficina.setNomSubEspDep(obj.getNomSubEspDep());
						oficina.setIdLocal(obj.getIdLocal());
						oficina.setIdOfiEstDep(obj.getIdOfiEstDep());
						resultado.add(oficina);
					}
				
				
				
				
				}catch (Exception e) {
					throw new Exception(
							ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(),
									ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
				}
				
				return resultado;
			
	}



}
