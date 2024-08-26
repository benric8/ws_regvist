package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.pj.administrativos.visitas.entity.MaePerfil;
import pe.gob.pj.administrativos.visitas.model.dao.MaePerfilDao;
import pe.gob.pj.administrativos.visitas.model.dto.PerfilDto;
import pe.gob.pj.administrativos.visitas.service.MaePerfilService;


@Service("maePerfilService")
public class MaePerfilServiceImpl extends BaseServiceImpl<Long, MaePerfil> implements MaePerfilService {
 
	@Autowired
	private MaePerfilDao perfilDao;
	
	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public List<PerfilDto> buscarPerfil() throws Exception {
		 return perfilDao.buscarPerfil();
	}
	
}