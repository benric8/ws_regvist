package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.pj.administrativos.visitas.entity.MaeTipoMotivo;
import pe.gob.pj.administrativos.visitas.entity.MaeUsuario;
import pe.gob.pj.administrativos.visitas.model.dao.MaeTipoMotivoDao;
import pe.gob.pj.administrativos.visitas.model.dto.TipoMotivoDto;
import pe.gob.pj.administrativos.visitas.service.MaeTipoMotivoService;

@Service("tipoMotivoService")
public class MaeTipoMotivoServiceImpl extends BaseServiceImpl<Long, MaeTipoMotivo> implements MaeTipoMotivoService  {

	@Autowired
	private MaeTipoMotivoDao maeTipoMotivoDao;
	
	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public List<TipoMotivoDto> listar(String estado) throws Exception {
		return maeTipoMotivoDao.listar(estado);
	}

	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public List<TipoMotivoDto> listarFiltros(Map<String, Object> params) throws Exception {
		return maeTipoMotivoDao.listarFiltros(params);
	}

	@Override
	@Transactional(readOnly = false, value = "transactionManager")
	public TipoMotivoDto guardar(TipoMotivoDto tipoMotivo) throws Exception {
		
		MaeTipoMotivo maeTipoMotivo = new MaeTipoMotivo();
		maeTipoMotivo.setxDescripcion(tipoMotivo.getDescripcion());
		maeTipoMotivo.setlFlagActivo(tipoMotivo.getEstado());
		maeTipoMotivo.setfRegistro(tipoMotivo.getFechaRegistro());
		maeTipoMotivo.setnUsuarioOperacion(tipoMotivo.getnUsuarioOperacion());
		maeTipoMotivo.setfOperacion(tipoMotivo.getfOperacion());
		maeTipoMotivo.setxIpOperacion(tipoMotivo.getxIpOperacion());
		maeTipoMotivo.setxMacOperacion(tipoMotivo.getxMacOperacion());
		maeTipoMotivo.setxIpWanOperacion(tipoMotivo.getxIpWanOperacion());
		
		maeTipoMotivoDao.save(maeTipoMotivo);
		
		tipoMotivo.setIdTipoMotivo(maeTipoMotivo.getnIdTipoMotivo());
		
		return tipoMotivo;
	}

	@Override
	@Transactional(readOnly = false, value = "transactionManager")
	public void modificar(TipoMotivoDto tipoMotivo) throws Exception {
		
		
		MaeTipoMotivo maeTipoMotivo = maeTipoMotivoDao.getByKey(tipoMotivo.getIdTipoMotivo());
		
		maeTipoMotivo.setxDescripcion(tipoMotivo.getDescripcion());
		maeTipoMotivo.setlFlagActivo(tipoMotivo.getEstado());
		maeTipoMotivo.setnUsuarioOperacion(tipoMotivo.getnUsuarioOperacion());
		maeTipoMotivo.setfOperacion(tipoMotivo.getfOperacion());
		maeTipoMotivo.setxIpOperacion(tipoMotivo.getxIpOperacion());
		maeTipoMotivo.setxMacOperacion(tipoMotivo.getxMacOperacion());
		maeTipoMotivo.setxIpWanOperacion(tipoMotivo.getxIpWanOperacion());
		
		maeTipoMotivoDao.update(maeTipoMotivo);
		
	}
	

}
