package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.pj.administrativos.visitas.entity.CfgPuntoControl;
import pe.gob.pj.administrativos.visitas.entity.MaeColaborador;
import pe.gob.pj.administrativos.visitas.entity.MaeEntidad;
import pe.gob.pj.administrativos.visitas.entity.MaeTipoDocumento;
import pe.gob.pj.administrativos.visitas.entity.MaeTipoMotivo;
import pe.gob.pj.administrativos.visitas.entity.MaeUsuario;
import pe.gob.pj.administrativos.visitas.entity.MovVisita;
import pe.gob.pj.administrativos.visitas.model.dao.MaeColaboradorDao;
import pe.gob.pj.administrativos.visitas.model.dao.MovVisitaDao;
import pe.gob.pj.administrativos.visitas.model.dto.VisitaDto;
import pe.gob.pj.administrativos.visitas.service.MovVisitaService;

@Service("visitaService")
public class MovVisitaServiceImpl extends BaseServiceImpl<Long, MovVisita> implements MovVisitaService {

	@Autowired
	MovVisitaDao movVisitaDao;
	@Autowired
	MaeColaboradorDao maeColaboradorDao;

	@Override
	@Transactional(readOnly = true)
	public List<VisitaDto> listar(Map<String, Object> params) throws Exception {
		
		return movVisitaDao.listar(params);
	}

	@Override
	@Transactional(readOnly = false, value = "transactionManager" )
	public MovVisita ingresar(VisitaDto visita) throws Exception {
		
			MovVisita movVisita = new MovVisita();
			MaeColaborador maeColaborador = new MaeColaborador();
			maeColaborador.setnIdColaborador(visita.getVisitado().getIdColaborador());

			maeColaborador.setxNombre(visita.getVisitado().getNombres());
			maeColaborador.setxApellidoPaterno(visita.getVisitado().getApellidoPaterno());
			maeColaborador.setxApellidoMaterno(visita.getVisitado().getApellidoMaterno());
			maeColaborador.setnNroDocumento(visita.getVisitado().getNumeroDocumento());

			MaeTipoDocumento maeTipoDocumento = new MaeTipoDocumento();
			maeTipoDocumento.setnIdTipoDocumento(visita.getVisitado().getTipoDocumento().getIdTipoDocumento());
			maeColaborador.setMaeTipoDocumento(maeTipoDocumento);
			maeColaborador.setxCodigoSiga(visita.getVisitado().getCodigoSiga());
			maeColaborador.setxCodigoCargoSiga(visita.getVisitado().getCodigoCargoSiga());
			maeColaborador.setxDescripcionCargoSiga(visita.getVisitado().getDescripcionCargoSiga());
			maeColaborador.setxCodigoOficinaSiga(visita.getVisitado().getCodigoOficinaSiga());
			maeColaborador.setxDescripcionOficinaSiga(visita.getVisitado().getDescripcionOficinaSiga());
			maeColaborador.setxCodigoLocalSiga(visita.getVisitado().getCodigoLocalSiga());
			maeColaborador.setxDescripcionLocalSiga(visita.getVisitado().getDescripcionLocalSiga());

			maeColaborador.setfRegistro(visita.getVisitado().getfOperacion());
			maeColaborador.setxIpOperacion(visita.getVisitado().getxIpOperacion());
			maeColaborador.setfOperacion(visita.getVisitado().getfOperacion());
			maeColaborador.setnUsuarioOperacion(visita.getVisitado().getnUsuarioOperacion());
			// Procede a guardar
			maeColaboradorDao.save(maeColaborador);

			
			movVisita.setVisitado(maeColaborador);
			movVisita.setfHIngreso(visita.getFechaHoraIngreso());
			movVisita.setfHSalida(null);
			
			MaeTipoMotivo maeTipoMotivo = new MaeTipoMotivo();
			maeTipoMotivo.setnIdTipoMotivo(visita.getTipoMotivo().getIdTipoMotivo());
			movVisita.setMaeTipoMotivo(maeTipoMotivo);

			movVisita.setxDescripcionMotivo(visita.getDescripcionMotivo());
			movVisita.setxGrupoVisita(visita.getGrupoVisita());
			movVisita.setxLugarDescripcion(visita.getLugar());
			
			MaeEntidad maeEntidad = new MaeEntidad();
			maeEntidad.setnIdEntidad(visita.getEntidad().getIdEntidad());
			movVisita.setMaeEntidad(maeEntidad);

			MaeUsuario maeUsuario = new MaeUsuario();
			maeUsuario.setnIdUsuario(visita.getUsuario().getnIdUsuario());
			movVisita.setMaeUsuario(maeUsuario);
			
			CfgPuntoControl cfgPuntoControl = new CfgPuntoControl();
			cfgPuntoControl.setnIdPuntoControl(visita.getPuntoControl().getnIdPuntoControl());
			movVisita.setCfgPuntoControl(cfgPuntoControl);

			movVisita.setxObservaciones(visita.getObservaciones());

			movVisita.setfRegistro(visita.getfOperacion());
			movVisita.setxIpOperacion(visita.getxIpOperacion());
			movVisita.setfOperacion(visita.getfOperacion());
			movVisita.setnUsuarioOperacion(visita.getnUsuarioOperacion());

			movVisita.setxNombres(visita.getNombres());
			movVisita.setxApellidoPaterno(visita.getApellidoPaterno());
			movVisita.setxApellidoMaterno(visita.getApellidoMaterno());
			movVisita.setxNumeroDocumento(visita.getNumeroDocumento());

			MaeTipoDocumento maeTipoDocumentoVisita = new MaeTipoDocumento();
			maeTipoDocumentoVisita.setnIdTipoDocumento(visita.getTipoDocumento().getIdTipoDocumento());
			movVisita.setMaeTipoDocumento(maeTipoDocumentoVisita);
			movVisita.setVisitado(maeColaborador);
			
			movVisita.setlFueraHorario(visita.getlFueraHorario());
			movVisita.setnPlataforma(visita.getnPlataforma());
			movVisitaDao.save(movVisita);

		return movVisita;

	}

	@Override
	@Transactional(readOnly = false, value = "transactionManager" )
	public MovVisita actualizar(VisitaDto visita) throws Exception {
		
		MovVisita movVisita = new MovVisita();
		movVisita=movVisitaDao.getByKey(visita.getNumeroVisita());
		movVisita.setxObservaciones(visita.getObservaciones());
		
		movVisita.setxIpOperacion(visita.getxIpOperacion());
		movVisita.setfOperacion(visita.getfOperacion());
		movVisita.setnUsuarioOperacion(visita.getnUsuarioOperacion());
		movVisita.setxMacOperacion(visita.getxMacOperacion());
		movVisita.setxIpWanOperacion(visita.getxIpWanOperacion());
		
		movVisita.setfHSalida(visita.getFechaHoraSalida());
		movVisitaDao.update(movVisita);
		return movVisita;
	}

}
