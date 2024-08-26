package pe.gob.pj.administrativos.visitas.model.dao;

import java.util.List;

import pe.gob.pj.administrativos.visitas.entity.MaeOpcion;
import pe.gob.pj.administrativos.visitas.model.dto.OpcionDto;

public interface MaeOpcionDao extends BaseDao<Long, MaeOpcion> {
	
	public List<OpcionDto> buscarOpcionPadre(Long nidPerfil) throws Exception;
	public List<OpcionDto> buscarOpcionHijosPermiso(Long nidPerfil) throws Exception;
	public List<OpcionDto> buscarOpcionHijos() throws Exception;

	//public List<OpcionDto> buscar(Map<String, Object> params) throws Exception;
	//public List<OpcionDto> buscarOpcionOrigen(Long nidOpcion) throws Exception;
	//public OpcionDto buscarOpcion(Long nidOpcion, String estadoOpcion) throws Exception;
	//public boolean existeOpcion(Long nidOpcion) throws Exception;
	//public MaeOpcion buscar(Long nidOpcion) throws Exception;
}