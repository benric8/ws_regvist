package pe.gob.pj.administrativos.visitas.model.dao.impl;

import pe.gob.pj.administrativos.visitas.entity.MaeTipoDocumento;
import pe.gob.pj.administrativos.visitas.model.dao.MaeTipoDocumentoDao;
import pe.gob.pj.administrativos.visitas.model.dto.TipoDocumentoDto;
import pe.gob.pj.administrativos.visitas.model.util.ParametrosDeBusqueda;
import pe.gob.pj.administrativos.visitas.model.util.Utilitarios;
import pe.gob.pj.api.commons.utility.ConvertirUtil;
import pe.gob.pj.api.commons.utility.ValidarUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MaeTipoDocumentoDaoImpl extends BaseDaoImpl<Long, MaeTipoDocumento> implements MaeTipoDocumentoDao {
	
	private static final Logger Logger = LoggerFactory.getLogger(MaeTipoDocumentoDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoDocumentoDto> buscar(Map<String, Object> params) {
				
		List<TipoDocumentoDto> resultado = new ArrayList<TipoDocumentoDto>();
		StringBuilder hql = new StringBuilder();
		
		hql.append(" SELECT nIdTipoDocumento, xDescripcion, xAbreviatura, lFlagActivo,xEquivalencia ");
		hql.append(" FROM MaeTipoDocumento");
		
		if ( !ValidarUtil.isNullOrEmpty( params.get(ParametrosDeBusqueda.TIPO_DOCU_LIST) ) ) {
			hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
			hql.append(" nIdTipoDocumento in ( :").append(ParametrosDeBusqueda.TIPO_DOCU_LIST).append(" )");
		}
		
		if ( !ValidarUtil.isNullOrEmpty( params.get(ParametrosDeBusqueda.ESTADO_REGISTRO) ) ) {
			hql.append((hql.indexOf(" WHERE") > 0) ? " AND" : " WHERE");
			hql.append(" lFlagActivo=:").append(ParametrosDeBusqueda.ESTADO_REGISTRO);
		}
				
	    
		Query query =  getSession().createQuery(hql.toString());
		
		if ( !ValidarUtil.isNullOrEmpty( params.get(ParametrosDeBusqueda.TIPO_DOCU_LIST) ) ) {
			query.setParameterList( ParametrosDeBusqueda.TIPO_DOCU_LIST , (Collection)params.get(ParametrosDeBusqueda.TIPO_DOCU_LIST));
		}
		if ( !ValidarUtil.isNullOrEmpty( params.get(ParametrosDeBusqueda.ESTADO_REGISTRO) ) ) {
			query.setParameter( ParametrosDeBusqueda.ESTADO_REGISTRO, params.get(ParametrosDeBusqueda.ESTADO_REGISTRO));
		}
		
	    List<Object[]> list = query.list();	    

		for (Object[] row : list) {
		    TipoDocumentoDto tdDto = new TipoDocumentoDto();
		    tdDto.setIdTipoDocumento(ConvertirUtil.toLong(row[0]));
		    tdDto.setDescripcion(ConvertirUtil.toString(row[1]));
		    tdDto.setAbreviatura(ConvertirUtil.toString(row[2]));
		    tdDto.setEstado(ConvertirUtil.toString(row[3]));
		    tdDto.setEquivalencia(ConvertirUtil.toString(row[4]));
		    resultado.add(tdDto);
		}
		
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TipoDocumentoDto buscarXEquivalencia(String equivalencia) throws Exception {

		String sLog = "MaeTipoDocumentoDaoImpl.buscar ";
		TipoDocumentoDto resultado = null;
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT nIdTipoDocumento, xDescripcion, xAbreviatura, lFlagActivo,xEquivalencia ");
		hql.append(" FROM MaeTipoDocumento");
		hql.append(" WHERE xEquivalencia=:equivalencia"); 
	    
		Logger.info(sLog+"INICIO");
		Query query =  getSession().createQuery(hql.toString());
		query.setParameter( "equivalencia", equivalencia);
	    List<Object[]> list = query.list();

		for (Object[] row : list) {
			resultado = new TipoDocumentoDto();
		    resultado.setIdTipoDocumento(ConvertirUtil.toLong(row[0]));
		    resultado.setDescripcion(ConvertirUtil.toString(row[1]));
		    resultado.setAbreviatura(ConvertirUtil.toString(row[2]));
		    resultado.setEstado(ConvertirUtil.toString(row[3]));
		    resultado.setEquivalencia(ConvertirUtil.toString(row[4]));
		    break;
		}
		return resultado;
	}
}