package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.pj.administrativos.visitas.entity.VUnidadEjecutora;
import pe.gob.pj.administrativos.visitas.model.dao.VUnidadEjecutoraDao;
import pe.gob.pj.administrativos.visitas.model.dto.UnidadEjecutoraDto;
import pe.gob.pj.administrativos.visitas.service.VUnidadEjecutoraService;

@Service("unidadEjecutoraService")
public class VUnidadEjecutoraServiceImpl extends BaseServiceImpl<Long, VUnidadEjecutora> implements VUnidadEjecutoraService{

	
	
	@Autowired
	private VUnidadEjecutoraDao vUnidadEjecutoraDao;

	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public List<UnidadEjecutoraDto> listar(String anio) throws Exception {
		return vUnidadEjecutoraDao.listar(anio);
	}

	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public UnidadEjecutoraDto obtenerUnidadEjecutora(String anio, String idUnidadEjecutora) throws Exception {
		return vUnidadEjecutoraDao.obtenerUnidadEjecutora(anio, idUnidadEjecutora);
	}

	
}
