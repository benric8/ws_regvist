
package pe.gob.pj.administrativos.visitas.service;

import java.util.List;
import pe.gob.pj.administrativos.visitas.entity.MaePerfil;
import pe.gob.pj.administrativos.visitas.model.dto.PerfilDto;


public interface MaePerfilService extends BaseService<Long, MaePerfil>{
	
	public List<PerfilDto> buscarPerfil() throws Exception;
}
