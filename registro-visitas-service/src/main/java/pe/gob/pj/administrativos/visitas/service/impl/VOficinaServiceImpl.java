package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.pj.administrativos.visitas.entity.VOficina;
import pe.gob.pj.administrativos.visitas.model.dao.VOficinaDao;
import pe.gob.pj.administrativos.visitas.model.dto.OficinaDto;
import pe.gob.pj.administrativos.visitas.service.VOficinaService;

@Service("oficinaService")
public class VOficinaServiceImpl extends BaseServiceImpl<Long, VOficina> implements VOficinaService{

	@Autowired
	private VOficinaDao vOficinaDao;

	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public List<OficinaDto> listar(String anio, String local, String unidadEjecutora, String corte,String ubigeo) throws Exception {
		return vOficinaDao.listar(anio, local, unidadEjecutora, corte, ubigeo);
	}
}
