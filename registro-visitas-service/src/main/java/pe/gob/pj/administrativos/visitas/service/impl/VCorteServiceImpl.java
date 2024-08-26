package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.pj.administrativos.visitas.entity.VCorte;
import pe.gob.pj.administrativos.visitas.model.dao.VCortesDao;
import pe.gob.pj.administrativos.visitas.model.dto.CorteDto;
import pe.gob.pj.administrativos.visitas.service.VCorteService;


@Service("corteService")
public class VCorteServiceImpl  extends BaseServiceImpl<Long, VCorte> implements VCorteService{

	@Autowired
	private VCortesDao vCorteDao;

	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public CorteDto obtenerCorte(String anio, String unidadEjecutora, String idCorte) throws Exception {
		return vCorteDao.obtenerCorte(anio, unidadEjecutora, idCorte);
	}
	
	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public List<CorteDto> listarCortes(String anio) throws Exception {
		return vCorteDao.listarCortes(anio);
	}

}
