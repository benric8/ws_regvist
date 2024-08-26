package pe.gob.pj.administrativos.visitas.service;


import pe.gob.pj.pidews.ws.Auditoria;
import pe.gob.pj.pidews.ws.PideServicio;
import pe.gob.pj.pidews.type.RequestConsultarDatosPrincipalesRUCType;
import pe.gob.pj.pidews.type.ResponseConsultarDatosPrincipalesRUCType;


public interface PideWsService {

	public ResponseConsultarDatosPrincipalesRUCType consultarDatosPrincipalesRUC(PideServicio port, pe.gob.pj.pidews.ws.Seguridad seguridad, Auditoria auditoria, RequestConsultarDatosPrincipalesRUCType requestDatosPrincipalesRUC)throws Exception;
}
