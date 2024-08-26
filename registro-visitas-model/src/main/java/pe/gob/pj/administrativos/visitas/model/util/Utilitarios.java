package pe.gob.pj.administrativos.visitas.model.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import pe.gob.pj.administrativos.visitas.model.bean.ComboWebBean;
import pe.gob.pj.administrativos.visitas.model.bean.ParamConfigPideBean;
import pe.gob.pj.administrativos.visitas.model.bean.ParamConfigReniecBean;
import pe.gob.pj.administrativos.visitas.model.bean.PersonaReniecBean;
import pe.gob.pj.api.commons.utility.ValidarUtil;
import pe.gob.pj.pidews.handler.PideHandler;
import pe.gob.pj.pidews.ws.PideServicio;
import pe.gob.pj.pidews.ws.PideServicio_Service;
import pe.gob.pj.ws.client.reniec.consultas.wsreniec.ConsultaReniecPortType;
import pe.gob.pj.ws.client.reniec.consultas.wsreniec.ConsultaReniecResponse;
import pe.gob.pj.ws.client.reniec.consultas.wsreniec.ConsultaReniecService;


public class Utilitarios {
	
	/*public static void main (String [ ] args) {
		String valor ="16637695	1	F	ARROYO	LOPEZ	CARLOS ALBERTO	S	S	\r\n" + 
				"40427145	3	F	ARROYO	LOPEZ	CARLOS ANTONIO	S	S	\r\n" + 
				"74985291	2	W	ARROYO	LOPEZ	CARLOS DANIEL	S	S	\r\n" + 
				"08061129	9	F	ARROYO	LOPEZ	JUAN CARLOS	S	S	\r\n" + 
				"10710448	3	F	ARROYO	LOPEZ	JUAN CARLOS	S	S	\r\n" + 
				"02861374	7	F	ARROYO	LOPEZ	MARCIAL CARLOS	S	S	\r\n" + 
				"08021757	4	F	ARROYO	LOPEZ	MARCIAL CARLOS	N	N";
		
		ConsultaReniecResponse consulta = new ConsultaReniecResponse();
		consulta.setResListaPersonas(valor);
		ArrayList<PersonaReniecBean> lista;
		lista = cargarListaPersonaReniecResponseBean(consulta);
		
		for(PersonaReniecBean persona : lista) {
			System.out.println(" PERSONA "+persona.getNroDNI());
		}
	}*/
	
	public static ComboWebBean bucarListPorCodigo(Collection<ComboWebBean> coleccion, String codigo) {
		
		ComboWebBean comboWebBean = null;
		
		if(StringUtils.isBlank(codigo)){
			return comboWebBean;
		}
				
		for (ComboWebBean elemento : coleccion) {
			if (codigo.trim().equals(elemento.getCodigo())) {
				comboWebBean = new ComboWebBean(elemento.getId().trim(), elemento.getCodigo().trim(), elemento.getNombre().trim());
				break;
			}
		}
		return comboWebBean;
	}
	
	
	public static ConsultaReniecPortType getPortReniec(ParamConfigReniecBean paramConfigReniecBean ) {
		
		ConsultaReniecService service = new ConsultaReniecService();
		ConsultaReniecPortType port = service.getConsultaReniec();
		
        BindingProvider bp = (BindingProvider)port;
        
        // Se asigna el endpoint del WS d
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, paramConfigReniecBean.getEndpoint());
		// Se asigna los tiempos de timeout de la consulta al WS 
		bp.getRequestContext().put("com.sun.xml.ws.connect.timeout", Integer.parseInt(paramConfigReniecBean.getTimeout()) * 1000);
		bp.getRequestContext().put("com.sun.xml.ws.request.timeout", Integer.parseInt(paramConfigReniecBean.getTimeout()) * 1000);
				
		return port;
	}
	
	
	public static PersonaReniecBean cargarPersonaReniecResponseBean(ConsultaReniecResponse consultaReniecResponse) {

		String[] arr_pers = consultaReniecResponse.getResPersona().split("\t");
		PersonaReniecBean persona = new PersonaReniecBean();
		
		int i = 0;

		try {
			persona.setNroDocumentoIdentidad(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodigoVerificacion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setApellidoPaterno(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setApellidoMaterno(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setApellidoCasado(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNombres(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodigoUbigeoDepartamentoDomicilio(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodigoUbigeoProvinciaDomicilio(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodigoUbigeoDistritoDomicilio(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodigoUbigeoLocalidadDomicilio(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDepartamentoDomicilio(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setProvinciaDomicilio(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDistritoDomicilio(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLocalidadDomicilio(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setEstadoCivil(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setGradoInstruccion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setEstatura(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setSexo(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocumentoSustentatorioTipoDocumento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocumentoSustentatorioNroDocumento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodigoUbigeoDepartamentoNacimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodigoUbigeoProvinciaNacimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodigoUbigeoDistritoNacimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodigoUbigeoLocalidadNacimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDepartamentoNacimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setProvinciaNacimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDistritoNacimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLocalidadNacimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFechaNacimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocumentoPadreTipDocumento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocumentoPadreNumDocumento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNombrePadre(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocumentoMadreTipoDocumento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocumentoMadreNumeroDocumento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNombreMadre(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFechaInscripcion(arr_pers[i++]);
		} catch (Exception e) {
		}
		;
		try {
			persona.setFechaEmision(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFechaFallecimiento(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setConstanciaVotacion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFechaCaducidad(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setRestricciones(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPrefijoDireccion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDireccion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNroDireccion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setBlockOChalet(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setInterior(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setUrbanizacion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setEtapa(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setManzana(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLote(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPreBlockOChalet(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPreDptoPisoInterior(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPreUrbCondResid(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setReservado(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLongitudFoto(Integer.parseInt(arr_pers[i++]));
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLongitudFirma(Integer.parseInt(arr_pers[i++]));
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setReservadoFotoFirma1(Integer.parseInt(arr_pers[i++]));
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setReservadoFotoFirma2(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFoto(consultaReniecResponse.getResFoto());
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFirma(consultaReniecResponse.getResFirma());
		} catch (Throwable ex) {
		}
		;
		return persona;
	}
	
	
	/*
	public static ConsultaReniecPortType getPortReniec(ParamConfigReniecBean paramConfigReniecBean ) {
		
		ConsultaReniecService service = new ConsultaReniecService();
		ConsultaReniecPortType port = service.getConsultaReniec();
		
        BindingProvider bp = (BindingProvider)port;
        
        // Se asigna el endpoint del WS d
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, paramConfigReniecBean.getEndpoint());
		// Se asigna los tiempos de timeout de la consulta al WS 
		bp.getRequestContext().put("com.sun.xml.ws.connect.timeout", Integer.parseInt(paramConfigReniecBean.getTimeout()) * 1000);
		bp.getRequestContext().put("com.sun.xml.ws.request.timeout", Integer.parseInt(paramConfigReniecBean.getTimeout()) * 1000);
				
		return port;
	}
	*/

	@SuppressWarnings("rawtypes")
	public static PideServicio getPortPide(ParamConfigPideBean paramConfigPideBean ) {
		
		PideServicio_Service pideService = new PideServicio_Service();
		PideServicio port = pideService.getPideServicioSOAP();
			
		List<Handler> handlerChain = new ArrayList<Handler>(); // preguntar a oscar
        handlerChain.add(new PideHandler(paramConfigPideBean.getUser(), paramConfigPideBean.getPass()));
					
        BindingProvider bp = (BindingProvider)port;
        bp.getBinding().setHandlerChain(handlerChain);
        // Se asigna el endpoint del WS d
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, paramConfigPideBean.getEndpoint());
		// Se asigna los tiempos de timeout de la consulta al WS 
		bp.getRequestContext().put("com.sun.xml.ws.connect.timeout", Integer.parseInt(paramConfigPideBean.getTimeout()) * 1000);
		bp.getRequestContext().put("com.sun.xml.ws.request.timeout", Integer.parseInt(paramConfigPideBean.getTimeout()) * 1000);
				
		return port;
	}
	/*
	public static PersonaReniecBean cargarPersonaReniecResponseBean(ConsultaReniecResponse consultaReniecResponse) {

		String[] arr_pers = consultaReniecResponse.getResPersona().split("\t");
		PersonaReniecBean persona = new PersonaReniecBean();
		
		int i = 0;

		try {
			persona.setNroDNI(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFlgVerif(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setApePat(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setApeMat(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setApeCas(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNombres(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiDepDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiProDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiDisDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiLocDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDepDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setProDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDisDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLocDom(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setEstCiv(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setGraInst(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setEstatura(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setSexo(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocSustTipDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocSustNroDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiDepNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiProNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiDisNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setCodUbiLocNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDepNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setProNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDisNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLocNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFecNac(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocPadTipDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocPadNumDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNomPad(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocMadTipDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDocMadNumDoc(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNomMad(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFecIns(arr_pers[i++]);
		} catch (Exception e) {
		}
		;
		try {
			persona.setFecExp(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFecFall(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setConsVot(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFecCad(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setRestric(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPrefDir(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setDireccion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setNroDir(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setBlocOChal(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setInterior(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setUrbanizacion(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setEtapa(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setManzana(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLote(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPreBlocOChal(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPreDptoPisoInt(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setPreUrbCondResid(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setReservado(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLongitudFoto(Integer.parseInt(arr_pers[i++]));
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setLongitudFirma(Integer.parseInt(arr_pers[i++]));
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setReservadoFotoFirma1(Integer.parseInt(arr_pers[i++]));
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setReservadoFotoFirma2(arr_pers[i++]);
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFoto(consultaReniecResponse.getResFoto());
		} catch (Throwable ex) {
		}
		;
		try {
			persona.setFirma(consultaReniecResponse.getResFirma());
		} catch (Throwable ex) {
		}
		;
		return persona;
	}
	*/
	
	@SuppressWarnings("unchecked")
	public static <T> T initializeAndUnproxy(T entity) {
	    if (entity == null) {
	        throw new 
	           NullPointerException("Entity passed for initialization is null");
	    }

	    Hibernate.initialize(entity);
	    if (entity instanceof HibernateProxy) {
	        entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
	    }
	    return entity;
	}
	
	public static boolean validaValor(Map.Entry<String, Object> map, String campo){
		boolean estado = false;
		if( map.getKey().equals(campo) ){
			if( map.getValue() instanceof String  && !ValidarUtil.isNull( (String)map.getValue() )){
				estado = true;
			}else if( map.getValue() instanceof Long  && !ValidarUtil.isNull( map.getValue().toString() )){
				estado = true;
			}else if( map.getValue() instanceof Collection && !ValidarUtil.isNullOrEmpty(map.getValue())){
				estado = true;
			} //EJHM
			else if(  !ValidarUtil.isNullOrEmpty(map.getValue())){
				estado = true;
			}
		}
		return estado;
	}	
	
	public static <T> void ponerEnMayuscula(T o) {
      for (Field f : o.getClass().getDeclaredFields()) {
          f.setAccessible(true);
          try {
          	Object value = f.get(o);
              if (value != null && value instanceof String) {
                  f.set(o, ((String) value).toUpperCase().trim());
              }
          } catch (IllegalAccessException e) { // shouldn't happen because I used setAccessible
          }
      }
	}
	
	public static String obtenerFechaFormateada(Date fecha, String formato) {
	    SimpleDateFormat sdf = new SimpleDateFormat(formato);
	    return sdf.format(fecha);
	}
	
	public static ArrayList<PersonaReniecBean> cargarListaPersonaReniecResponseBean(String tramaInicial) {

		ArrayList<PersonaReniecBean> lista = new ArrayList<PersonaReniecBean>();
		String[] arr_pers =null;
		PersonaReniecBean persona = null;
		String trama = tramaInicial.replace(",", "").replace("[", "").replace("]", "");
		String[] lineas = trama.split("\n");
		try {
			for(int i=0; i<=lineas.length; i++) {
				arr_pers = lineas[i].split("\t");
				persona = new PersonaReniecBean();
				int j = 0;
				try {
					persona.setNroDocumentoIdentidad(arr_pers[j++]);
				} catch (Throwable ex) {
				}
				try {
					persona.setCodigoVerificacion(arr_pers[j++]);
				} catch (Throwable ex) {
				}
				try {
					persona.setTipoFichaRegistral(arr_pers[j++]);
				} catch (Throwable ex) {
				}
				try {
					persona.setApellidoPaterno(arr_pers[j++]);
				} catch (Throwable ex) {
				}
				try {
					persona.setApellidoMaterno(arr_pers[j++]);
				} catch (Throwable ex) {
				}
				try {
					persona.setNombres(arr_pers[j++]);
				} catch (Throwable ex) {
				}
				try {
					persona.setDatos(arr_pers[j++]);
				} catch (Throwable ex) {
				}
				try {
					persona.setDatos(arr_pers[j++]);
				} catch (Throwable ex) {
				}
				try {
					persona.setFlagImagen(arr_pers[j++]);
				} catch (Throwable ex) {
				}
				if(persona.getDatos()!=null && !persona.getDatos().equals(ConstantesVisitas.RESTRICCION)) {
					lista.add(persona);
				}
			}
		} catch (Throwable ex) {
		}
		return lista;
	}
}
