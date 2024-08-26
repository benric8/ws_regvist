package pe.gob.pj.administrativos.visitas.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.pj.administrativos.visitas.entity.MaeEntidad;
import pe.gob.pj.administrativos.visitas.model.dto.EntidadDto;



public interface MaeEntidadDao extends BaseDao<Long, MaeEntidad>{ 

	public List<EntidadDto> listar(Map<String, Object> params) throws Exception;

}
