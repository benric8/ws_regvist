package pe.gob.pj.administrativos.visitas.model.dao;

import java.util.List;

import pe.gob.pj.administrativos.visitas.entity.VDistrito;
import pe.gob.pj.administrativos.visitas.model.dto.DistritoDto;



public interface VDistritoDao  extends BaseDao<Long, VDistrito>{

	public List<DistritoDto> listar(String idDepartamento,String idProvincia) throws Exception;
}
