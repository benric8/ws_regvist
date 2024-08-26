package pe.gob.pj.administrativos.visitas.service;


import java.util.List;

import pe.gob.pj.administrativos.visitas.entity.VProvincia;
import pe.gob.pj.administrativos.visitas.model.dao.BaseDao;
import pe.gob.pj.administrativos.visitas.model.dto.ProvinciaDto;

public interface VProvinciaService extends BaseService<Long, VProvincia>{
	public List<ProvinciaDto> listar(String idDepartamento) throws Exception;
}
