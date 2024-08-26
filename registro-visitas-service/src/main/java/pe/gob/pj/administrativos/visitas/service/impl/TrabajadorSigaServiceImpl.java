package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.pj.administrativos.visitas.model.dao.TrabajadorDao;
import pe.gob.pj.administrativos.visitas.model.dao.impl.TrabajadorDaoImpl;
import pe.gob.pj.administrativos.visitas.model.dto.TrabajadorDto;
import pe.gob.pj.administrativos.visitas.service.TrabajadorSigaService;



@Service("trabajadorSigaService")
public class TrabajadorSigaServiceImpl implements TrabajadorSigaService{
	

	@Autowired
	private TrabajadorDao trabajadorDao;

	public TrabajadorDto consultarTrabajador(String tipo, String dni,String nombres,String anio,String idUnidadEjecutora,String idCorte, String cargo) {
		
		return trabajadorDao.consultarTrabajador(tipo, dni, nombres,anio,idUnidadEjecutora,idCorte,cargo);
		
	}
	
	public List<TrabajadorDto> buscar(String tipo, String dni,String nombres,String anio,String idUnidadEjecutora,String idCorte, String cargo) {
		
		return trabajadorDao.buscar(tipo, dni, nombres,anio,idUnidadEjecutora,idCorte,cargo);
		
	}

	
	
}
