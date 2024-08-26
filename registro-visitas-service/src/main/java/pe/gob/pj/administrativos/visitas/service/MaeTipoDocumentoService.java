package pe.gob.pj.administrativos.visitas.service;

import java.util.List;
import java.util.Map;

import pe.gob.pj.administrativos.visitas.entity.MaeTipoDocumento;
import pe.gob.pj.administrativos.visitas.model.dto.TipoDocumentoDto;

public interface MaeTipoDocumentoService extends BaseService<Long, MaeTipoDocumento>{

	List<TipoDocumentoDto> buscar(Map<String, Object> params) throws Exception;
	TipoDocumentoDto buscarXEquivalencia(String equivalencia) throws Exception;;
}
