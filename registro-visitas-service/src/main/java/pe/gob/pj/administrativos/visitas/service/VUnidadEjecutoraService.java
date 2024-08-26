package pe.gob.pj.administrativos.visitas.service;

import java.util.List;

import pe.gob.pj.administrativos.visitas.entity.VUnidadEjecutora;
import pe.gob.pj.administrativos.visitas.model.dto.UnidadEjecutoraDto;

public interface VUnidadEjecutoraService extends BaseService<Long, VUnidadEjecutora>{
	public List<UnidadEjecutoraDto> listar(String anio) throws Exception;
	public UnidadEjecutoraDto obtenerUnidadEjecutora(String anio,String idUnidadEjecutora) throws Exception;
}
