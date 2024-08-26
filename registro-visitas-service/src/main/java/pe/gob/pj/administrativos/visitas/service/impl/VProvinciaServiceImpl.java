package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.gob.pj.administrativos.visitas.entity.VProvincia;
import pe.gob.pj.administrativos.visitas.model.dao.VProvinciaDao;
import pe.gob.pj.administrativos.visitas.model.dto.ProvinciaDto;

import pe.gob.pj.administrativos.visitas.service.VProvinciaService;

@Service("provinciaService")
public class VProvinciaServiceImpl extends BaseServiceImpl<Long, VProvincia> implements VProvinciaService{

	@Autowired
	private VProvinciaDao vProvinciaDao;

	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public List<ProvinciaDto> listar(String idDepartamento) throws Exception {

		return vProvinciaDao.listar(idDepartamento);
	}




}
