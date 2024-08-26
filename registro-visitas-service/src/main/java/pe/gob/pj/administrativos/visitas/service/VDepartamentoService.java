package pe.gob.pj.administrativos.visitas.service;


import java.util.List;

import pe.gob.pj.administrativos.visitas.entity.VDepartamento;
import pe.gob.pj.administrativos.visitas.model.dao.BaseDao;
import pe.gob.pj.administrativos.visitas.model.dto.DepartamentoDto;

public interface VDepartamentoService extends BaseService<Long, VDepartamento> {
	public List<DepartamentoDto> listar() throws Exception;
}
