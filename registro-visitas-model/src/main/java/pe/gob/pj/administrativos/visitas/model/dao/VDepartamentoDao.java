package pe.gob.pj.administrativos.visitas.model.dao;

import java.util.List;

import pe.gob.pj.administrativos.visitas.entity.VDepartamento;
import pe.gob.pj.administrativos.visitas.model.dto.DepartamentoDto;



public interface VDepartamentoDao extends BaseDao<Long, VDepartamento>{
	public List<DepartamentoDto> listar() throws Exception;
}
