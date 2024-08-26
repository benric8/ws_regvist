package pe.gob.pj.administrativos.visitas.model.dao;

import pe.gob.pj.administrativos.visitas.entity.MaeColaborador;
import pe.gob.pj.administrativos.visitas.model.dto.ColaboradorDto;

public interface MaeColaboradorDao extends BaseDao<Long, MaeColaborador>{

	
	public ColaboradorDto buscar(ColaboradorDto persona)  throws Exception;
	
}
