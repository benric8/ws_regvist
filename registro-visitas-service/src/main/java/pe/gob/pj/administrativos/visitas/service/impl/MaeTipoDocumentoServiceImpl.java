package pe.gob.pj.administrativos.visitas.service.impl;

import pe.gob.pj.administrativos.visitas.entity.MaeTipoDocumento;
import pe.gob.pj.administrativos.visitas.model.dao.MaeTipoDocumentoDao;
import pe.gob.pj.administrativos.visitas.model.dto.TipoDocumentoDto;
import pe.gob.pj.administrativos.visitas.service.MaeTipoDocumentoService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("maeTipoDocumentoService")
public class MaeTipoDocumentoServiceImpl extends BaseServiceImpl<Long, MaeTipoDocumento>
		implements MaeTipoDocumentoService {

	@Autowired
	MaeTipoDocumentoDao maeTipoDocumentoDao;
	
	@Transactional(readOnly = false, value = "transactionManager")
	@Override
	public List<TipoDocumentoDto> buscar(Map<String, Object> params) throws Exception {
		return maeTipoDocumentoDao.buscar(params);
	}

	@Transactional(readOnly = false, value = "transactionManager")
	@Override
	public TipoDocumentoDto buscarXEquivalencia(String equivalencia) throws Exception {
		return maeTipoDocumentoDao.buscarXEquivalencia(equivalencia);
	}
}
