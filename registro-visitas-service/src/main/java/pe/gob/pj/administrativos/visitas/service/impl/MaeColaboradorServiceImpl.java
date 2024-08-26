package pe.gob.pj.administrativos.visitas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.pj.administrativos.visitas.entity.MaeColaborador;
import pe.gob.pj.administrativos.visitas.model.dao.MaeColaboradorDao;
import pe.gob.pj.administrativos.visitas.model.dto.ColaboradorDto;
import pe.gob.pj.administrativos.visitas.service.MaeColaboradorService;

@Service("colaboradorService")
public class MaeColaboradorServiceImpl extends BaseServiceImpl<Long, MaeColaborador> implements MaeColaboradorService{

	@Autowired
	MaeColaboradorDao maeColaboradorDao;
	

	@Override
	public ColaboradorDto buscar(ColaboradorDto persona) throws Exception {
		return maeColaboradorDao.buscar(persona);
	}
}
