package pe.gob.pj.administrativos.visitas.model.dao;

import java.util.List;

import pe.gob.pj.administrativos.visitas.entity.VProvincia;
import pe.gob.pj.administrativos.visitas.model.dto.ProvinciaDto;




public interface VProvinciaDao extends BaseDao<Long, VProvincia>{

	
	public List<ProvinciaDto> listar(String idDepartamento) throws Exception;
}

