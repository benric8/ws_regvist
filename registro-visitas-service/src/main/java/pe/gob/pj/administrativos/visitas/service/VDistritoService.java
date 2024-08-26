package pe.gob.pj.administrativos.visitas.service;


import java.util.List;

import pe.gob.pj.administrativos.visitas.entity.VDistrito;
import pe.gob.pj.administrativos.visitas.model.dao.BaseDao;
import pe.gob.pj.administrativos.visitas.model.dto.DistritoDto;

public interface VDistritoService extends BaseService<Long, VDistrito>{
	public List<DistritoDto> listar(String idDepartamento,String idProvincia) throws Exception;
}
