package pe.gob.pj.administrativos.visitas.service;

import java.util.List;
import java.util.Map;

import pe.gob.pj.administrativos.visitas.entity.MovVisita;
import pe.gob.pj.administrativos.visitas.model.dto.VisitaDto;

public interface MovVisitaService  extends BaseService<Long, MovVisita>{

	public List<VisitaDto> listar(Map<String, Object> params) throws Exception;
	public MovVisita ingresar(VisitaDto visita) throws Exception;
	public MovVisita actualizar(VisitaDto visita) throws Exception;
}
