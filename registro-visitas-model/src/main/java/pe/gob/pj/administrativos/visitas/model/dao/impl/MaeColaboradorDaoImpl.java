package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import pe.gob.pj.administrativos.visitas.entity.MaeColaborador;
import pe.gob.pj.administrativos.visitas.model.dao.MaeColaboradorDao;
import pe.gob.pj.administrativos.visitas.model.dto.ColaboradorDto;
import pe.gob.pj.administrativos.visitas.model.dto.TipoDocumentoDto;
import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.api.commons.utility.ConvertirUtil;
import pe.gob.pj.api.commons.utility.sicau.ManejadorError;

@Repository
public class MaeColaboradorDaoImpl extends BaseDaoImpl<Long, MaeColaborador> implements MaeColaboradorDao{
	
	private static final Logger Logger = LoggerFactory.getLogger(MaeColaboradorDaoImpl.class);
	@Override
	public ColaboradorDto buscar(ColaboradorDto personaBuscada) throws Exception {
/*		ColaboradorDto persona= null;
		try {
			String sLog = "MaePersonaDaoImpl.buscar ";
			StringBuffer hql = new StringBuffer();
			hql.append("SELECT p.nIdPersona");
			hql.append(" , p.xNombre");
			hql.append(" , p.xApellidoPaterno");
			hql.append(" , p.xApellidoMaterno");
			hql.append(" , p.xNroDocumento");
			hql.append(" , p.maeTipoDocumento.xAbreviatura");
			hql.append(" , p.xCodigoSiga");
			hql.append(" , p.xCodigoCargoSiga");
			hql.append(" , p.xDescripcionCargoSiga");
			hql.append(" , p.xCodigoLocalSiga");
			hql.append(" , p.xDescripcionLocalSiga");
			hql.append(" FROM MaePersona p ");
			hql.append(" WHERE p.xNroDocumento = :numeroDoc ");
			hql.append(" AND p.maeTipoDocumento.nIdTipoDocumento = :tipoDoc ");
		
			Logger.info(sLog+"INICIO");
			Query query = getSession().createQuery(hql.toString());
			query.setParameter("numeroDoc", personaBuscada.getNumeroDocumento());
			query.setParameter("tipoDoc", personaBuscada.getTipoDocumento().getIdTipoDocumento());
			List<Object[]> resultado = query.list();
			
			if(resultado!=null &&  !resultado.isEmpty()){
				for (Object[] obj : resultado) {
					persona = new ColaboradorDto();
					persona.setnIdPersona(ConvertirUtil.toLong(obj[0]));
					persona.setNombres(ConvertirUtil.toString(obj[1]));
					persona.setApellidoPaterno(ConvertirUtil.toString(obj[2]));
					persona.setApellidoMaterno(ConvertirUtil.toString(obj[3]));
					persona.setNumeroDocumento(ConvertirUtil.toStringAndTrim(obj[4]));
					
					
					TipoDocumentoDto tipoDocumento = new TipoDocumentoDto();
					tipoDocumento.setAbreviatura(ConvertirUtil.toStringAndTrim(obj[5]));
					persona.setTipoDocumento(tipoDocumento);
					persona.setCodigoSiga(ConvertirUtil.toStringAndTrim(obj[6]));
					persona.setCodigoCargoSiga(ConvertirUtil.toStringAndTrim(obj[7]));
					persona.setDescripcionCargoSiga(ConvertirUtil.toStringAndTrim(obj[8]));
					persona.setCodigoLocalSiga(ConvertirUtil.toStringAndTrim(obj[9]));
					persona.setDescripcionLocalSiga(ConvertirUtil.toStringAndTrim(obj[10]));

				}
			}
		} catch (Exception e) {
			throw new Exception(ManejadorError.getGenerarError(Thread.currentThread().getStackTrace()[1].getMethodName(), ConstantesVisitas.NIVEL_APP_DAO, this.getClass().getName(), e.getMessage()));
		}
		return persona;*/
		
		return null;
	}

}
