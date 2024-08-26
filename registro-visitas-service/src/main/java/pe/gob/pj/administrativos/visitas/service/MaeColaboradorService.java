package pe.gob.pj.administrativos.visitas.service;

import pe.gob.pj.administrativos.visitas.entity.MaeColaborador;
import pe.gob.pj.administrativos.visitas.model.dto.ColaboradorDto;

public interface MaeColaboradorService extends BaseService<Long, MaeColaborador>{
	public ColaboradorDto buscar(ColaboradorDto persona)  throws Exception;
	
}
