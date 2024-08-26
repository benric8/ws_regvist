package pe.gob.pj.administrativos.visitas.service;

import java.util.List;

import pe.gob.pj.administrativos.visitas.model.dto.TrabajadorDto;

public interface TrabajadorSigaService {

	public TrabajadorDto consultarTrabajador(String tipo, String dni,String nombres,String anio,String idUnidadEjecutora,String idCorte, String cargo) throws Exception;
	public List<TrabajadorDto> buscar(String tipo, String dni,String nombres,String anio,String idUnidadEjecutora,String idCorte, String cargo) throws Exception;
}
