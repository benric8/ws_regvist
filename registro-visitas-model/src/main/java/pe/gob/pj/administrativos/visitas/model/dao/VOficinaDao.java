package pe.gob.pj.administrativos.visitas.model.dao;


import java.util.List;

import pe.gob.pj.administrativos.visitas.entity.VOficina;
import pe.gob.pj.administrativos.visitas.model.dto.OficinaDto;

public interface VOficinaDao extends BaseDao<Long, VOficina>{

	public List<OficinaDto> listar(String anio, String local,String unidadEjecutora,String corte,String ubigeo) 
			throws Exception;
}
