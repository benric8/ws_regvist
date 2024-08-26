package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.pj.administrativos.visitas.entity.MaeEntidad;
import pe.gob.pj.administrativos.visitas.model.dao.MaeEntidadDao;
import pe.gob.pj.administrativos.visitas.model.dto.EntidadDto;
import pe.gob.pj.administrativos.visitas.service.MaeEntidadService;

@Service("entidadService")
public class MaeEntidadServiceImpl extends BaseServiceImpl<Long, MaeEntidad> implements MaeEntidadService{

	@Autowired
	MaeEntidadDao maeEntidadDao;
	@Override
	@Transactional(readOnly = true, value = "transactionManager")
	public List<EntidadDto> listar(Map<String, Object> params) throws Exception {
		
		return maeEntidadDao.listar(params);
	}
	@Override
	@Transactional(readOnly = false, value = "transactionManager" )
	public EntidadDto ingresar(EntidadDto entidad) throws Exception {

		EntidadDto entidadNueva = new EntidadDto();
		MaeEntidad maeEntidad = new MaeEntidad();
		maeEntidad.setlFlagActivo(entidad.getEstado());
		maeEntidad.setfRegistro(entidad.getfOperacion());
		maeEntidad.setxIpOperacion(entidad.getxIpOperacion());
		maeEntidad.setfOperacion(entidad.getfOperacion());
		maeEntidad.setnUsuarioOperacion(entidad.getnUsuarioOperacion());
		maeEntidad.setxRuc(entidad.getRuc());
		maeEntidad.setxRazonSocial(entidad.getRazonSocial());
		maeEntidadDao.save(maeEntidad);
		
		entidadNueva.setIdEntidad(maeEntidad.getnIdEntidad());
		entidadNueva.setRuc(maeEntidad.getxRuc());
		entidadNueva.setRazonSocial(maeEntidad.getxRazonSocial());
		
		return entidadNueva;
	}

}
