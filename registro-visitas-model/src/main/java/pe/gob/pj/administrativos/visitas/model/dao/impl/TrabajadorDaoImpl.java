package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pe.gob.pj.administrativos.visitas.model.dao.TrabajadorDao;
import pe.gob.pj.administrativos.visitas.model.dto.TrabajadorDto;
import pe.gob.pj.api.commons.utility.ConvertirUtil;

@Repository
public class TrabajadorDaoImpl implements TrabajadorDao {

	private static final Logger Logger = LoggerFactory.getLogger(TrabajadorDaoImpl.class);

	@Autowired
	private SimpleJdbcCall sjcVisitas;

	public TrabajadorDaoImpl() {
	}
	
	public TrabajadorDto consultarTrabajador(String tipo, String dni,String nombres,String anio,String idUnidadEjecutora,String idCorte, String cargo) {
		sjcVisitas.withSchemaName("ADMINPJ").withProcedureName("SP_DATOS_TRABAJADOR");
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("P_TIPO_DOC", tipo)
				.addValue("P_NRO_DOC",dni)
				.addValue("P_NOMBRES",nombres)
				.addValue("P_ANIO", anio)
			    .addValue("P_ID_UNI_UEJE", idUnidadEjecutora)
			    .addValue("P_ID_CORTE", idCorte)
			    .addValue("P_CARGO", cargo);
		Map<String, Object> out = sjcVisitas.execute(in);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> rs = (List<Map<String, Object>>) out.get("P_CURSOR");
		Map<String, Object> m = null;
		TrabajadorDto dto = null;
		if (rs != null && !rs.isEmpty()) {
			m = rs.get(0);
			dto = new TrabajadorDto();
			dto.setIdppicodigo(ConvertirUtil.toString(m.get("IDPPICODIGO")));
			dto.setPercodold(ConvertirUtil.toString(m.get("PERCODOLD")));
			dto.setIddidcodigo(ConvertirUtil.toString(m.get("IDDIDCODIGO")));
			dto.setDipnumero(ConvertirUtil.toString(m.get("DIPNUMERO")));
			dto.setMppiappater(ConvertirUtil.toString(m.get("MPPIAPPATER")));
			dto.setMppiapmater(ConvertirUtil.toString(m.get("MPPIAPMATER")));
			dto.setMppinombres(ConvertirUtil.toString(m.get("MPPINOMBRES")));
			dto.setVlpestado(ConvertirUtil.toString(m.get("VLPESTADO")));
			dto.setIdtdocodigo(ConvertirUtil.toString(m.get("IDTDOCODIGO")));
			dto.setNom_local(ConvertirUtil.toString(m.get("NOM_LOCAL")));
			dto.setIdcodlocal_ub(ConvertirUtil.toString(m.get("IDCODLOCAL_UB")));
			dto.setIdanoproc(ConvertirUtil.toString(m.get("IDANOPROC")));
			dto.setId_uni_eje(ConvertirUtil.toString(m.get("ID_UNI_EJE")));
			dto.setIdoficodigo(ConvertirUtil.toString(m.get("IDOFICODIGO")));
			dto.setOficina(ConvertirUtil.toString(m.get("OFICINA")));
			dto.setIdCorte(ConvertirUtil.toString(m.get("ID_CORTE")));
			dto.setIdcargo(ConvertirUtil.toString(m.get("IDCARGO")));
			dto.setCargo(ConvertirUtil.toString(m.get("CARGO")));
			dto.setCargo(ConvertirUtil.toString(m.get("CARGO")));
			dto.setCorreo(ConvertirUtil.toString(m.get("CXEDESCRIP")));

		}
		return dto;
	}
	
	public List<TrabajadorDto> buscar(String tipo, String dni,String nombres,String anio,String idUnidadEjecutora,String idCorte, String cargo) {

		List<TrabajadorDto> listaTrabajadores = new ArrayList<>();

		sjcVisitas.withSchemaName("ADMINPJ").withProcedureName("SP_DATOS_TRABAJADOR");
		
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("P_TIPO_DOC", tipo)
				.addValue("P_NRO_DOC",dni)
				.addValue("P_NOMBRES",nombres)
				.addValue("P_ANIO", anio)
			    .addValue("P_ID_UNI_UEJE", idUnidadEjecutora)
			    .addValue("P_ID_CORTE", idCorte)
			    .addValue("P_CARGO", cargo);
		
		Map<String, Object> out = sjcVisitas.execute(in);
		List<Map<String, Object>> rs = (List<Map<String, Object>>) out.get("P_CURSOR");
		Map<String, Object> m = null;
		TrabajadorDto dto = null;
		
		if(rs!=null && !rs.isEmpty()) {
			for (int i=0;i<rs.size();i++) {
			m = rs.get(i);
			dto = new TrabajadorDto();
			dto.setIdppicodigo(ConvertirUtil.toString(m.get("IDPPICODIGO")));
			dto.setPercodold(ConvertirUtil.toString(m.get("PERCODOLD")));
			dto.setIddidcodigo(ConvertirUtil.toString(m.get("IDDIDCODIGO")));
			dto.setDipnumero(ConvertirUtil.toString(m.get("DIPNUMERO")));
			dto.setMppiappater(ConvertirUtil.toString(m.get("MPPIAPPATER")));
			dto.setMppiapmater(ConvertirUtil.toString(m.get("MPPIAPMATER")));
			dto.setMppinombres(ConvertirUtil.toString(m.get("MPPINOMBRES")));
			dto.setVlpestado(ConvertirUtil.toString(m.get("VLPESTADO")));
			dto.setIdtdocodigo(ConvertirUtil.toString(m.get("IDTDOCODIGO")));
			dto.setNom_local(ConvertirUtil.toString(m.get("NOM_LOCAL")));
			dto.setIdcodlocal_ub(ConvertirUtil.toString(m.get("IDCODLOCAL_UB")));
			dto.setIdanoproc(ConvertirUtil.toString(m.get("IDANOPROC")));
			dto.setId_uni_eje(ConvertirUtil.toString(m.get("ID_UNI_EJE")));
			dto.setIdoficodigo(ConvertirUtil.toString(m.get("IDOFICODIGO")));
			dto.setOficina(ConvertirUtil.toString(m.get("OFICINA")));
			dto.setIdCorte(ConvertirUtil.toString(m.get("ID_CORTE")));
			dto.setIdcargo(ConvertirUtil.toString(m.get("IDCARGO")));
			dto.setCargo(ConvertirUtil.toString(m.get("CARGO")));
			dto.setCorreo(ConvertirUtil.toString(m.get("CXEDESCRIP")));
			listaTrabajadores.add(dto);
			}
		}
		return listaTrabajadores;
	}
	
	public SimpleJdbcCall getSjcVisitas() {
		return sjcVisitas;
	}

	public void setSjcVisitas(SimpleJdbcCall sjcVisitas) {
		this.sjcVisitas = sjcVisitas;
	}
}
