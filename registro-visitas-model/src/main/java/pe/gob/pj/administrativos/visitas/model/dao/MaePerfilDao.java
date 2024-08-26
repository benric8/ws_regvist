package pe.gob.pj.administrativos.visitas.model.dao;

import java.util.List;
import pe.gob.pj.administrativos.visitas.entity.MaePerfil;
import pe.gob.pj.administrativos.visitas.model.dto.PerfilDto;

public interface MaePerfilDao extends BaseDao<Long , MaePerfil> {

	//List<PerfilDto> buscar(Map<String, Object> params);
	//List<PerfilDto> buscarAsignadosPorEntidad(Map<String, Object> params);
	//List<PerfilDto> buscarPorSistema(Map<String, Object> params);
	//public List<PerfilDto> buscarPerfilUsuario(Long idUsuario) throws Exception;
	//List<PerfilDto> buscarPorUsuario(Map<String, Object> params);
	//public List<PerfilDto> buscarPerfilUsuario(Long nidUsuario,Long nidSistema) throws Exception;
	public List<PerfilDto> buscarPerfil() throws Exception;
}
