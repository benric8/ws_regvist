package pe.gob.pj.administrativos.visitas.service;


import java.util.List;
import java.util.Map;

import pe.gob.pj.administrativos.visitas.entity.MaeEntidad;

import pe.gob.pj.administrativos.visitas.model.dto.EntidadDto;


public interface MaeEntidadService extends BaseService<Long, MaeEntidad>{

	public List<EntidadDto> listar(Map<String, Object> params) throws Exception;
	public EntidadDto ingresar(EntidadDto entidad) throws Exception;
}
