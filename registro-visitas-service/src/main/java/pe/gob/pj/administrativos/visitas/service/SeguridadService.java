package pe.gob.pj.administrativos.visitas.service;

import java.util.List;
import pe.gob.pj.administrativos.visitas.model.dto.OpcionDto;
import pe.gob.pj.administrativos.visitas.model.dto.UsuarioDto;

public interface SeguridadService {

	public UsuarioDto obtenerUsuario(String numeroDoc, Long idDocumento, String password) throws Exception;

	public UsuarioDto obtenerUsuarioByIdEcriptado(String check) throws Exception;

	public List<UsuarioDto> obtenerUsuarioByCorreo(String correo) throws Exception;

	public List<OpcionDto> obtenerPermisos(Long nidPerfil) throws Exception;

}