package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.pj.administrativos.visitas.entity.VDepartamento;
import pe.gob.pj.administrativos.visitas.model.dao.VDepartamentoDao;
import pe.gob.pj.administrativos.visitas.model.dto.DepartamentoDto;
import pe.gob.pj.administrativos.visitas.service.VDepartamentoService;



@Service("departamentoService")
public class VDepartamentoServiceImpl extends BaseServiceImpl<Long, VDepartamento> implements VDepartamentoService{

	@Autowired
	private VDepartamentoDao vDepartamentoDao;

	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public List<DepartamentoDto> listar() throws Exception {
		return vDepartamentoDao.listar();
	}



}
