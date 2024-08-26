package pe.gob.pj.administrativos.visitas.service;

import java.util.List;
import java.util.Map;

import pe.gob.pj.administrativos.visitas.entity.MaeUsuario;
import pe.gob.pj.administrativos.visitas.model.dto.UsuarioDto;

public interface MaeUsuarioService extends BaseService<Long, MaeUsuario> {

	public List<UsuarioDto> buscar(Map<String, Object> params);
	public MaeUsuario guardar(UsuarioDto usuario) throws Exception;
	public void modificar(UsuarioDto usuario) throws Exception;
	public int solicitarRecuperacionClave(UsuarioDto bean) throws Exception;
	void actualizarClave(UsuarioDto usuario) throws Exception;
	public void resetearClave(UsuarioDto usuario) throws Exception;
	public void levantarReseteo(UsuarioDto usuario) throws Exception;

}