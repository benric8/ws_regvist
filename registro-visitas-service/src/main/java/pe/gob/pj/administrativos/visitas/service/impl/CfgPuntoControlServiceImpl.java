package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.pj.administrativos.visitas.entity.CfgPuntoControl;
import pe.gob.pj.administrativos.visitas.entity.VCorte;
import pe.gob.pj.administrativos.visitas.model.dao.CfgPuntoControlDao;
import pe.gob.pj.administrativos.visitas.model.dto.LocalDto;
import pe.gob.pj.administrativos.visitas.model.dto.PuntoControlDto;
import pe.gob.pj.administrativos.visitas.service.CfgPuntoControlService;

/**
* Resumen.
* Objeto : CfgPuntoControlServiceImpl
* Descripción : Es una clase de servicio que permite gestionar los puntos de control. 
* Fecha de Creación : 02/01/2019
* Autor : Carlos Arroyo
* ------------------------------------------------------------------------
* Modificaciones
* Fecha                  Nombre                     Descripción
* ------------------------------------------------------------------------
* 08/05/2019			Carlos Arroyo				CRC: Cambio por Reseteo de Clave
*/
@Service("cfgPuntoControlService")
public class CfgPuntoControlServiceImpl extends BaseServiceImpl<Long, CfgPuntoControl> implements CfgPuntoControlService {

	@Autowired
	private CfgPuntoControlDao cfgPuntoControlDao;

	@Override
	@Transactional(readOnly = true, value = "transactionManager")
	public List<PuntoControlDto> buscar(Map<String, Object> params) {
		return cfgPuntoControlDao.buscar(params);
	}

	/** TODO CRC Metodo ingresar
	* Descripción: Permite registrar el punto de control
	* @param PuntoControlDto: Contiene la informacion del punto de control 
	* @return CfgPuntoControl: Contiene una entidad del punto de control.
	* @exception No aplica.
	*/
	@Override
	@Transactional(readOnly = false, value = "transactionManager" )
	public CfgPuntoControl ingresar(PuntoControlDto puntoControl) {

		CfgPuntoControl cfgPuntoControl = new CfgPuntoControl();
		cfgPuntoControl.setlFlagActivo(puntoControl.getlFlagActivo());
		cfgPuntoControl.setnIdLocal(puntoControl.getnIdLocal());
		cfgPuntoControl.setxDescripcionLocal(puntoControl.getxDescripcionLocal());
		cfgPuntoControl.setnIdOficina(puntoControl.getnIdOficina());
		cfgPuntoControl.setxDescripcionOficina(puntoControl.getxDescripcionOficina());
		cfgPuntoControl.setxNombre(puntoControl.getNombre());
		
		//TODO CRC Permite agragar el Id de corte
		//cfgPuntoControl.setnIdCorte(puntoControl.getIdCorte());
		VCorte corte = new VCorte();
		corte.setIdCorte(puntoControl.getIdCorte());
		cfgPuntoControl.setvCorte(corte);
		//cfgPuntoControl.setnIdCorte(puntoControl.getIdCorte());
		cfgPuntoControl.setnIdUniEje(puntoControl.getIdUnidadEjecutora());
		cfgPuntoControl.setfRegistro(puntoControl.getfOperacion());
		cfgPuntoControl.setxIpOperacion(puntoControl.getxIpOperacion());
		cfgPuntoControl.setfOperacion(puntoControl.getfOperacion());
		cfgPuntoControl.setnUsuarioOperacion(puntoControl.getnUsuarioOperacion());
		
		// Procede a guardar
		cfgPuntoControlDao.save(cfgPuntoControl);
		
		return cfgPuntoControl;
		
	}

	@Override
	@Transactional(readOnly = false, value = "transactionManager" )
	public CfgPuntoControl actualizar(PuntoControlDto puntoControl) {
		
		CfgPuntoControl cfgPuntoControl = new CfgPuntoControl();
		cfgPuntoControl=cfgPuntoControlDao.getByKey(puntoControl.getnIdPuntoControl());
		cfgPuntoControl.setlFlagActivo(puntoControl.getlFlagActivo());		
		cfgPuntoControl.setxIpOperacion(puntoControl.getxIpOperacion());
		cfgPuntoControl.setfOperacion(puntoControl.getfOperacion());
		cfgPuntoControl.setnUsuarioOperacion(puntoControl.getnUsuarioOperacion());
		cfgPuntoControl.setxMacOperacion(puntoControl.getxMacOperacion());
		cfgPuntoControl.setxIpWanOperacion(puntoControl.getxIpWanOperacion());
		cfgPuntoControlDao.update(cfgPuntoControl);
		return cfgPuntoControl;
	}

	@Override
	@Transactional(readOnly = true, value = "transactionManager")
	public List<PuntoControlDto> listarTodos() {
		return cfgPuntoControlDao.listarTodos();
	}

	@Override
	@Transactional(readOnly = true, value = "transactionManager")
	public List<LocalDto> listarLocales(String unidadEjecutora, String corte) {
		return cfgPuntoControlDao.listarLocales(unidadEjecutora,corte);
	}
	
	@Override
	@Transactional(readOnly = true, value = "transactionManager")
	public List<PuntoControlDto> listarPuntoControlLocal(String idLocal, String unidadEjecutora, String corte){
		return cfgPuntoControlDao.listarPuntoControlLocal(idLocal, unidadEjecutora,corte);
	}
	
	/** 
	* Descripción: Permite listar los puntos de control
	* @param 	String unidadEjecutora: codigo de la unidad ejecutoria 
	* 			String corte: codigo de la corte 
	* @return  List<PuntoControlDto>: Retorna la lista puntos de control.
	* @exception No aplica.
	*/
	@Override
	@Transactional(readOnly = true, value = "transactionManager")
	public List<PuntoControlDto> listarPuntoControl(String unidadEjecutora, String corte){
		return cfgPuntoControlDao.listarPuntoControl(unidadEjecutora,corte);
	}	
}
