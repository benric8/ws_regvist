package pe.gob.pj.administrativos.visitas.service;

import java.util.List;
import java.util.Map;

import pe.gob.pj.administrativos.visitas.entity.MaeTipoMotivo;
import pe.gob.pj.administrativos.visitas.model.dto.TipoMotivoDto;


public interface MaeTipoMotivoService extends BaseService<Long, MaeTipoMotivo>{

	
	public List<TipoMotivoDto> listar(String estado) throws Exception;
	public List<TipoMotivoDto> listarFiltros(Map<String, Object> params) throws Exception ;
	public TipoMotivoDto guardar(TipoMotivoDto tipoMotivo) throws Exception ;
	public void modificar(TipoMotivoDto tipoMotivo) throws Exception;
}
