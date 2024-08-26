package pe.gob.pj.administrativos.visitas.model.dao;

import java.util.List;

import pe.gob.pj.administrativos.visitas.entity.VCorte;
import pe.gob.pj.administrativos.visitas.model.dto.CorteDto;

public interface VCortesDao extends BaseDao<Long, VCorte>{

	public CorteDto obtenerCorte(String anio,String unidadEjecutora, String idCorte) throws Exception;
	public List<CorteDto> listarCortes(String anio) throws Exception;
}
