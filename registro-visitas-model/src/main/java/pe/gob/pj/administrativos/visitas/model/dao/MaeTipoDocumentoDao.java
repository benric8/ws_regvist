package pe.gob.pj.administrativos.visitas.model.dao;

import pe.gob.pj.administrativos.visitas.entity.MaeTipoDocumento;
import pe.gob.pj.administrativos.visitas.model.dto.TipoDocumentoDto;

import java.util.List;
import java.util.Map;

public interface MaeTipoDocumentoDao extends BaseDao<Long, MaeTipoDocumento> {

	List<TipoDocumentoDto> buscar(Map<String, Object> params) throws Exception;;
	TipoDocumentoDto buscarXEquivalencia(String equivalencia) throws Exception;;
}
