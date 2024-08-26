package pe.gob.pj.administrativos.visitas.service;

import pe.gob.pj.ws.client.reniec.consultas.wsreniec.ConsultaReniec;
import pe.gob.pj.ws.client.reniec.consultas.wsreniec.ConsultaReniecPortType;
import pe.gob.pj.ws.client.reniec.consultas.wsreniec.ConsultaReniecResponse;

public interface ReniecWsService {

	public ConsultaReniecResponse consultaReniec(ConsultaReniecPortType port, ConsultaReniec consultaReniecRequest)throws Exception;
}
