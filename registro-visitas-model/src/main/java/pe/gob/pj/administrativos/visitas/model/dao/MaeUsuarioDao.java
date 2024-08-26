package pe.gob.pj.administrativos.visitas.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.pj.administrativos.visitas.entity.MaeUsuario;
import pe.gob.pj.administrativos.visitas.model.dto.UsuarioDto;

public interface MaeUsuarioDao extends BaseDao<Long, MaeUsuario> {

	public UsuarioDto obtenerUsuario(String numeroDoc, long tipoDoc, String password) throws Exception;
	
	public List<UsuarioDto> buscar(Map<String, Object> params);

	public UsuarioDto obtenerUsuarioByIdEcriptado(String id) throws Exception;

	public List<UsuarioDto> obtenerUsuarioByCorreo(String correo) throws Exception;

	int solicitarRecuperacionClave(UsuarioDto bean) throws Exception;

}