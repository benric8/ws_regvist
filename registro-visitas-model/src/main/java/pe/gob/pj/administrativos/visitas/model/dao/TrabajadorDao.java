package pe.gob.pj.administrativos.visitas.model.dao;



import java.util.List;

import pe.gob.pj.administrativos.visitas.model.dto.TrabajadorDto;

public interface TrabajadorDao{
	
	public TrabajadorDto consultarTrabajador(String tipo, String dni,String nombres,String anio,String idUnidadEjecutora,String idCorte, String cargo);
	public List<TrabajadorDto> buscar(String tipo, String dni,String nombres,String anio,String idUnidadEjecutora,String idCorte, String cargo);
}
