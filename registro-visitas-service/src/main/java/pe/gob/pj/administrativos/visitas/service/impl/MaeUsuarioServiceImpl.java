package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.pj.administrativos.visitas.entity.MaePerfil;
import pe.gob.pj.administrativos.visitas.entity.MaeTipoDocumento;
import pe.gob.pj.administrativos.visitas.entity.MaeUsuario;
import pe.gob.pj.administrativos.visitas.model.dao.MaeUsuarioDao;
import pe.gob.pj.administrativos.visitas.model.dto.UsuarioDto;
import pe.gob.pj.administrativos.visitas.service.MaeUsuarioService;
import pe.gob.pj.administrativos.visitas.model.util.Utilitarios;
import pe.gob.pj.api.commons.utility.CryptoUtil;

/**
* Resumen.
* Objeto : MaeUsuarioServiceImpl
* Descripción : Es una clase de servicio que permite gestionar los usuarios. 
* Fecha de Creación : 02/01/2019
* Autor : Carlos Arroyo
* ------------------------------------------------------------------------
* Modificaciones
* Fecha                  Nombre                     Descripción
* ------------------------------------------------------------------------
* 08/05/2019			Carlos Arroyo				CRC: Cambio por Reseteo de Clave
*/
@Service("maeUsuarioService")
public class MaeUsuarioServiceImpl extends BaseServiceImpl<Long, MaeUsuario> implements MaeUsuarioService {
	
	private static final Logger Logger = LoggerFactory.getLogger(MaeUsuarioServiceImpl.class);
	
	@Autowired
	private MaeUsuarioDao usuarioDao;

	@Transactional(readOnly = true, value = "transactionManager")
	@Override
	public List<UsuarioDto> buscar(Map<String, Object> params) {
		return usuarioDao.buscar(params);
	}
		
	@Override
	@Transactional(readOnly = false, value = "transactionManager")
	public MaeUsuario guardar(UsuarioDto usuario) throws Exception {

		Utilitarios.ponerEnMayuscula(usuario);
		MaeUsuario maeUsuario = new MaeUsuario();
		maeUsuario.setnIdUsuario(usuario.getnIdUsuario());
		maeUsuario.setMaeTipoDocumento(new MaeTipoDocumento(usuario.getTipoDocumentoDto().getIdTipoDocumento()));
		maeUsuario.setxNroDocumento(usuario.getxNroDocumento());
		maeUsuario.setxUsuario(usuario.getxNroDocumento());
		maeUsuario.setxNombre(usuario.getxNombre());
		maeUsuario.setxApellidoPaterno(usuario.getxApellidoPaterno());
		maeUsuario.setxApellidoMaterno(usuario.getxApellidoMaterno());
		maeUsuario.setxCelular(usuario.getxCelular());
		maeUsuario.setxTelefono(usuario.getxTelefono());
		maeUsuario.setxAnexo(usuario.getxAnexo());
		maeUsuario.setxPassword(CryptoUtil.encriptar( usuario.getxPassword() ));
		
		maeUsuario.setlFlagActivo(usuario.getlFlagActivo());
		maeUsuario.setMaePerfil(new MaePerfil(usuario.getPerfilDto().getnIdPerfil()));
		maeUsuario.setxCodigoSiga(usuario.getxCodigoSiga());
		
		// Datos de auditoría
		maeUsuario.setxIpOperacion(usuario.getxIpOperacion());
		maeUsuario.setfOperacion(usuario.getfOperacion());
		maeUsuario.setnUsuarioOperacion(usuario.getnUsuarioOperacion());
		maeUsuario.setnIdCorte(usuario.getIdCorte());
		maeUsuario.setnIdUniEje(usuario.getIdUnidadEjecutora());
		maeUsuario.setxCorreo(usuario.getCorreo());
		
		// Procede a guardar a usuario
		usuarioDao.save(maeUsuario);

		
		return maeUsuario;
	}
	
	@Override
	@Transactional(readOnly = false, value = "transactionManager")
	public void modificar(UsuarioDto usuario) throws Exception {

		/*String contrasenia = usuario.isFlagCambiarClave() ? usuario.getxPassword().trim() : null;
		Utilitarios.ponerEnMayuscula(usuario);*/
		MaeUsuario maeUsuario = usuarioDao.getByKey(usuario.getnIdUsuario());
		maeUsuario.setlFlagActivo(usuario.getlFlagActivo());
		maeUsuario.setxCelular(usuario.getxCelular());
		maeUsuario.setxTelefono(usuario.getxTelefono());
		maeUsuario.setxAnexo(usuario.getxAnexo());
		maeUsuario.setnIdCorte(usuario.getIdCorte());
		maeUsuario.setxCorreo(usuario.getCorreo());
		if(maeUsuario.getMaePerfil().getnIdPerfil()!=usuario.getPerfilDto().getnIdPerfil()) {
			MaePerfil nuevoPerfil = new MaePerfil();
		    nuevoPerfil.setnIdPerfil(usuario.getPerfilDto().getnIdPerfil());
		    maeUsuario.setMaePerfil(nuevoPerfil);
		}
			
		// Datos de auditoría
		maeUsuario.setxIpOperacion(usuario.getxIpOperacion());
		maeUsuario.setfOperacion(usuario.getfOperacion());
		maeUsuario.setnUsuarioOperacion(usuario.getnUsuarioOperacion());
		maeUsuario.setxMacOperacion(usuario.getxMacOperacion());
		maeUsuario.setxIpWanOperacion(usuario.getxIpWanOperacion());
		// Procede a modificar a usuario
		usuarioDao.update(maeUsuario);

	}

	
	@Override
	@Transactional(readOnly = false, value = "transactionManager")
	public void actualizarClave(UsuarioDto usuario) throws Exception {

		String claveEncriptada = CryptoUtil.encriptar(usuario.getxPassword());
		
		MaeUsuario maeUsuario = usuarioDao.getByKey(usuario.getnIdUsuario());
		maeUsuario.setxPassword(claveEncriptada);
		maeUsuario.setFechaCambioClave(usuario.getFechaCambioClave());
		// Datos de auditoría
		maeUsuario.setfOperacion(usuario.getFechaCambioClave());
		maeUsuario.setnUsuarioOperacion(usuario.getnIdUsuario());

		usuarioDao.update(maeUsuario);
	}
	
	/**
     * Registra solcitud de recuperar contraseña de usuario.     *
     * @param correo electronico    
     * @return  Número de operacion
     */
    @Override
    @Transactional(readOnly = false)
    public int solicitarRecuperacionClave(UsuarioDto bean) throws Exception{
    		return usuarioDao.solicitarRecuperacionClave(bean);		
	}

	/** TODO CRC Metodo resetearClave
	* Descripción: Permite resetear la clave.
	* @param UsuarioDto: Contiene la informacion del usuario 
	* @return No aplica.
	* @exception No aplica.
	*/
	@Override
	@Transactional(readOnly = false, value = "transactionManager")
	public void resetearClave(UsuarioDto usuario) throws Exception {

		String claveEncriptada = CryptoUtil.encriptar(usuario.getxPassword());
		MaeUsuario maeUsuario = usuarioDao.getByKey(usuario.getnIdUsuario());
		maeUsuario.setxPassword(claveEncriptada);
		maeUsuario.setFechaReseteoClave(usuario.getFechaReseteoClave());
		maeUsuario.setlFlagReseteo(usuario.getlFlagReseteo());
		// Datos de auditoría
		maeUsuario.setfOperacion(usuario.getFechaReseteoClave());
		maeUsuario.setnUsuarioOperacion(usuario.getnIdUsuario());
		usuarioDao.update(maeUsuario);
	}
	
	/** TODO CRC Metodo levantarReseteo
	* Descripción: Permite levantar el Reseteo de Clave.
	* @param UsuarioDto: Contiene la informacion del usuario 
	* @return No aplica.
	* @exception No aplica.
	*/
	@Override
	@Transactional(readOnly = false, value = "transactionManager")
	public void levantarReseteo(UsuarioDto usuario) throws Exception {

		String claveEncriptada = CryptoUtil.encriptar(usuario.getxPassword());
		MaeUsuario maeUsuario = usuarioDao.getByKey(usuario.getnIdUsuario());
		maeUsuario.setxPassword(claveEncriptada);
		maeUsuario.setFechaCambioClave(usuario.getFechaCambioClave());
		maeUsuario.setlFlagReseteo(usuario.getlFlagReseteo());
		// Datos de auditoría
		maeUsuario.setfOperacion(usuario.getFechaReseteoClave());
		maeUsuario.setnUsuarioOperacion(usuario.getnIdUsuario());
		usuarioDao.update(maeUsuario);
	}

}