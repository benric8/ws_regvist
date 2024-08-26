package pe.gob.pj.administrativos.visitas.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.pj.administrativos.visitas.entity.MaeTipoMotivo;
import pe.gob.pj.administrativos.visitas.model.dto.TipoMotivoDto;


public interface MaeTipoMotivoDao extends BaseDao<Long , MaeTipoMotivo>{

	public List<TipoMotivoDto> listar(String estado) throws Exception;
	public List<TipoMotivoDto> listarFiltros(Map<String, Object> params) throws Exception;
	
}
