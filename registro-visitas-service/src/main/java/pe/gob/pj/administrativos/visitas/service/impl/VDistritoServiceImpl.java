package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.pj.administrativos.visitas.entity.VDistrito;
import pe.gob.pj.administrativos.visitas.model.dao.VDistritoDao;
import pe.gob.pj.administrativos.visitas.model.dto.DistritoDto;
import pe.gob.pj.administrativos.visitas.service.VDistritoService;

@Service("distritoService")
public class VDistritoServiceImpl extends BaseServiceImpl<Long, VDistrito> implements VDistritoService{

	
	@Autowired
	private VDistritoDao vDistritoDao;

	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public List<DistritoDto> listar(String idDepartamento, String idProvincia) throws Exception {
		return vDistritoDao.listar(idDepartamento,idProvincia);
	}

}
