package pe.gob.pj.administrativos.visitas.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.pj.administrativos.visitas.entity.VLocal;
import pe.gob.pj.administrativos.visitas.model.dao.VLocalDao;
import pe.gob.pj.administrativos.visitas.model.dto.LocalDto;
import pe.gob.pj.administrativos.visitas.service.VLocalService;
@Service("localService")
public class VLocalServiceImpl  extends BaseServiceImpl<Long, VLocal> implements VLocalService{

	@Autowired
	private VLocalDao vLocalDao;

	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public List<LocalDto> listar(String unidadEjecutora, String corte, String departamento, String provincia,
			String distrito) throws Exception {
		return vLocalDao.listar(unidadEjecutora, corte, departamento, provincia, distrito);
	}

}
