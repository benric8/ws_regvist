package pe.gob.pj.administrativos.visitas.service.impl;

import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import pe.gob.pj.administrativos.visitas.service.ReniecWsService;
import pe.gob.pj.ws.client.reniec.consultas.wsreniec.ConsultaReniec;
import pe.gob.pj.ws.client.reniec.consultas.wsreniec.ConsultaReniecPortType;
import pe.gob.pj.ws.client.reniec.consultas.wsreniec.ConsultaReniecResponse;

@Service("reniecWsService")
public class ReniecWsServiceImpl implements ReniecWsService {
	
	private static final Logger Logger = LoggerFactory.getLogger(ReniecWsServiceImpl.class);

	public ConsultaReniecResponse consultaReniec(ConsultaReniecPortType port, ConsultaReniec consultaReniecRequest)throws Exception{
		ConsultaReniecResponse response = new ConsultaReniecResponse();
		
		try{
			
			Holder<String> resTrama = new Holder <String>() ;
			Holder<String> resCodigo = new Holder <String>() ;
			Holder<String> resDescripcion = new Holder <String>();
			Holder<String> resTotalRegistros = new Holder <String>();
			Holder<String> resPersona= new Holder <String>();
			Holder<byte[]> resFoto = new Holder <byte[]>();
			Holder<byte[]> resFirma =  new Holder <byte[]>();
			Holder<String> resListaPersonas = new Holder <String>();
			
			port.consultaReniec(consultaReniecRequest.getReqTrama(), consultaReniecRequest.getReqDniConsultante(), consultaReniecRequest.getReqTipoConsulta(), consultaReniecRequest.getReqUsuario(), consultaReniecRequest.getReqIp(), consultaReniecRequest.getReqDni(), consultaReniecRequest.getReqNombres(), 
					consultaReniecRequest.getReqApellidoPaterno(), consultaReniecRequest.getReqApellidoMaterno(), consultaReniecRequest.getReqNroRegistros(), consultaReniecRequest.getReqGrupo(), consultaReniecRequest.getReqDniApoderado(), consultaReniecRequest.getReqTipoVinculoApoderado(), 
					resTrama, resCodigo, resDescripcion, resTotalRegistros, resPersona, resFoto, resFirma, resListaPersonas);
			
			response.setResCodigo(resCodigo.value);
			response.setResDescripcion(resDescripcion.value);
			response.setResFirma(resFirma.value);
			response.setResFoto(resFoto.value);
			response.setResListaPersonas(resListaPersonas.value);
			response.setResPersona(resPersona.value);
			response.setResTotalRegistros(resTotalRegistros.value);
			response.setResTrama(resTrama.value);
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
    		throw new Exception(e.getMessage(), e);
		}
		
		return response;
	}
}
