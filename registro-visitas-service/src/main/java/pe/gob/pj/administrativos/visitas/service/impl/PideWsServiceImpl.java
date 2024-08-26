package pe.gob.pj.administrativos.visitas.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pe.gob.pj.administrativos.visitas.service.PideWsService;
import pe.gob.pj.pidews.type.RequestConsultarDatosPrincipalesRUCType;
import pe.gob.pj.pidews.type.ResponseConsultarDatosPrincipalesRUCType;
import pe.gob.pj.pidews.ws.Auditoria;
import pe.gob.pj.pidews.ws.PideServicio;
//import pe.gob.pj.pidews.ws.PideServicio;
//import pe.gob.pj.pidews.ws.RequestConsultarDatosPrincipalesRUCType;
//import pe.gob.pj.pidews.ws.ResponseConsultarDatosPrincipalesRUCType;
import pe.gob.pj.pidews.ws.Seguridad;


@Service("pideWsService")
public class PideWsServiceImpl implements PideWsService {

	private static final Logger Logger = LoggerFactory.getLogger(PideWsServiceImpl.class);
	
	public ResponseConsultarDatosPrincipalesRUCType consultarDatosPrincipalesRUC(PideServicio port, Seguridad seguridad, Auditoria auditoria, RequestConsultarDatosPrincipalesRUCType requestDatosPrincipalesRUC)throws Exception{
		
		try{
			
			ResponseConsultarDatosPrincipalesRUCType response = port.consultarDatosPrincipalesRUC(seguridad, auditoria, requestDatosPrincipalesRUC);
				
			return response;
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
    		throw new Exception(e.getMessage(), e);
		}
	}

	
}
