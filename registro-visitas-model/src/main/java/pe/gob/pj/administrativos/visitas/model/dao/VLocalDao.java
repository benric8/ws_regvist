package pe.gob.pj.administrativos.visitas.model.dao;


import java.util.List;

import pe.gob.pj.administrativos.visitas.entity.VLocal;
import pe.gob.pj.administrativos.visitas.model.dto.LocalDto;

public interface VLocalDao extends BaseDao<Long, VLocal>{
	
	public List<LocalDto> listar(String unidadEjecutora,String corte,
			String departamento, String provincia, String distrito) throws Exception;
}
