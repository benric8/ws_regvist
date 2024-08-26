package pe.gob.pj.administrativos.visitas.model.dao;

import java.util.List;
import java.util.Map;


import pe.gob.pj.administrativos.visitas.entity.MovVisita;
import pe.gob.pj.administrativos.visitas.model.dto.VisitaDto;



public interface MovVisitaDao extends BaseDao<Long, MovVisita>{

	public List<VisitaDto> listar(Map<String, Object> params) throws Exception;

	
}
