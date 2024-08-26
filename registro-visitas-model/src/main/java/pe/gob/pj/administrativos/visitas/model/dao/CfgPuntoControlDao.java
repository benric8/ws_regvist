package pe.gob.pj.administrativos.visitas.model.dao;

import java.util.List;
import java.util.Map;
import pe.gob.pj.administrativos.visitas.entity.CfgPuntoControl;
import pe.gob.pj.administrativos.visitas.model.dto.LocalDto;
import pe.gob.pj.administrativos.visitas.model.dto.PuntoControlDto;

public interface CfgPuntoControlDao extends BaseDao<Long, CfgPuntoControl>{

	public List<PuntoControlDto> buscar(Map<String, Object> params);
	public List<PuntoControlDto> listarTodos();
	public List<LocalDto> listarLocales(String idUnidadEjecutora, String idCorte);
	public List<PuntoControlDto> listarPuntoControlLocal(String idLocal, String unidadEjecutora, String corte);
	public List<PuntoControlDto> listarPuntoControl(String unidadEjecutora, String corte);

}
