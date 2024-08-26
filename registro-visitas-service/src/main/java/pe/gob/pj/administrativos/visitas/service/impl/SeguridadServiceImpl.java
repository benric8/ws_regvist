package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.gob.pj.administrativos.visitas.model.dao.MaeOpcionDao;
import pe.gob.pj.administrativos.visitas.model.dao.MaeUsuarioDao;
import pe.gob.pj.administrativos.visitas.model.dto.OpcionDto;
import pe.gob.pj.administrativos.visitas.model.dto.UsuarioDto;
import pe.gob.pj.administrativos.visitas.service.SeguridadService;
import pe.gob.pj.api.commons.utility.ValidarUtil;

@Service("seguridadService")
public class SeguridadServiceImpl implements SeguridadService {

	@Autowired
	private MaeUsuarioDao usuarioDao;

	@Autowired
	private MaeOpcionDao maeOpcionDao;

	@Transactional(readOnly = false, value = "transactionManager")
	public UsuarioDto obtenerUsuario(String numeroDoc, Long idDocumento, String password) throws Exception {
		try {
			return usuarioDao.obtenerUsuario(numeroDoc, idDocumento, password);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioDto obtenerUsuarioByIdEcriptado(String check) throws Exception {
		return usuarioDao.obtenerUsuarioByIdEcriptado(check);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioDto> obtenerUsuarioByCorreo(String correo) throws Exception {
		return usuarioDao.obtenerUsuarioByCorreo(correo);

	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly = false, value = "transactionManager")
	public List<OpcionDto> obtenerPermisos(Long nidPerfil) throws Exception {
		List<OpcionDto> listaPadre = maeOpcionDao.buscarOpcionPadre(nidPerfil);
		// listaOtroHijos=null;
		if (listaPadre == null || listaPadre.isEmpty()) {
			return null;
		}
		List<OpcionDto> listaHijosPermiso = maeOpcionDao.buscarOpcionHijosPermiso(nidPerfil);
		if (listaHijosPermiso == null || listaHijosPermiso.isEmpty()) {
			return null;
		}
		List<OpcionDto> listaHijos = maeOpcionDao.buscarOpcionHijos();
		if (listaHijos == null || listaHijos.isEmpty()) {
			return null;
		}
		int pos = -1;
		int posPermiso = -1;
		Iterator it = listaHijos.iterator();
		while (it.hasNext()) {
			OpcionDto opcionHijo = (OpcionDto) it.next();
			pos = -1;
			posPermiso = -1;
			posPermiso = listaHijosPermiso.indexOf(new OpcionDto(opcionHijo.getnIdOpcion()));
			if (posPermiso == -1) {
				// it.remove();
				continue;
			}
			// if(listaOtroHijos==null){
			// listaOtroHijos=new ArrayList<OpcionDto>();
			// listaOtroHijos.addAll(listaHijos);
			// }
			pos = listaPadre.indexOf(new OpcionDto(opcionHijo.getnIdOpcionPadre()));
			if (pos >= 0) {
				OpcionDto opcionPadre = listaPadre.get(pos);
				// if(!ValidarUtil.isNull(opcionPadre.getxMetodo())){
				// continue;
				// }
				if (opcionPadre != null) {
					pos = -1;
					pos = listaHijos.indexOf(new OpcionDto(opcionHijo.getnIdOpcion()));
					// if(listaOtroHijos!=null &&!listaOtroHijos.isEmpty() && pos>=-1){
					// listaOtroHijos.remove(pos);
					// }
					if (opcionPadre.getListaSubMenus() == null) {
						opcionPadre.setListaSubMenus(new ArrayList<OpcionDto>());
					}
					if (opcionPadre.getListaSubMenus().indexOf(new OpcionDto(opcionHijo.getnIdOpcion())) < 0) {
						opcionPadre.getListaSubMenus().add(opcionHijo);
					}
				}

			} else {
				// sino buscar padre entre los hijos restantes
				pos = -1;
				pos = listaHijos.indexOf(new OpcionDto(opcionHijo.getnIdOpcionPadre()));
				if (pos >= 0) {
					OpcionDto opcionPadredelHijo = listaHijos.get(pos);
					if (!ValidarUtil.isNull(opcionPadredelHijo.getxMetodo())) {
						continue;
					}
					boolean existeSuperior = obtenerPadreSuperior(listaHijos, listaPadre, opcionPadredelHijo);
					if (existeSuperior) {
						if (opcionPadredelHijo.getListaSubMenus() == null) {
							opcionPadredelHijo.setListaSubMenus(new ArrayList<OpcionDto>());
						}
						if (opcionPadredelHijo.getListaSubMenus().indexOf(new OpcionDto(opcionHijo.getnIdOpcion())) < 0) {
							opcionPadredelHijo.getListaSubMenus().add(opcionHijo);
						}
					}
				} else {
					it.remove(); // se remueve por incosnsistente no existe su padre
					continue;
				}
			}
		}
		Iterator it2 = listaPadre.iterator();
		while (it2.hasNext()) {
			OpcionDto opcionPadre = (OpcionDto) it2.next();
			if (opcionPadre.getListaSubMenus() == null || opcionPadre.getListaSubMenus().isEmpty()) {
				pos = -1;
				pos = listaHijosPermiso.indexOf(new OpcionDto(opcionPadre.getnIdOpcion()));
				if (pos == -1) {
					it2.remove();
					continue;
				}
				if (ValidarUtil.isNull(opcionPadre.getxMetodo())) {
					it2.remove();
					continue;
				}

			}

		}
		return listaPadre;
	}

	private boolean obtenerPadreSuperior(List<OpcionDto> listaHijos, List<OpcionDto> listaPadre, OpcionDto opcionHijo) throws Exception {
		int pos = -1;
		// if(!ValidarUtil.isNull(opcionHijo.getxMetodo())){
		// return false;
		// }
		if (opcionHijo != null) {

			pos = listaPadre.indexOf(new OpcionDto(opcionHijo.getnIdOpcionPadre()));
			boolean encontro = false;
			if (pos >= 0) {
				OpcionDto opcionPadre = listaPadre.get(pos);
				if (opcionPadre != null) {
					if (opcionPadre.getListaSubMenus() == null) {
						opcionPadre.setListaSubMenus(new ArrayList<OpcionDto>());
					}
					if (opcionPadre.getListaSubMenus().indexOf(new OpcionDto(opcionHijo.getnIdOpcion())) < 0) {
						opcionPadre.getListaSubMenus().add(opcionHijo);
					}
					return true;
				}
			}
			if (!encontro) {
				pos = -1;
				pos = listaHijos.indexOf(new OpcionDto(opcionHijo.getnIdOpcionPadre()));
				if (pos >= 0) {
					if (opcionHijo.getnIdOpcionPadre() == opcionHijo.getnIdOpcion()) {
						return false; // horror -incositencia
					}
					OpcionDto opcionPadredelHijo = listaHijos.get(pos);
					boolean existesuperiror = obtenerPadreSuperior(listaHijos, listaPadre, opcionPadredelHijo);
					if (existesuperiror) {
						if (opcionPadredelHijo.getListaSubMenus() == null) {
							opcionPadredelHijo.setListaSubMenus(new ArrayList<OpcionDto>());
						}
						if (opcionPadredelHijo.getListaSubMenus().indexOf(new OpcionDto(opcionHijo.getnIdOpcion())) < 0) {
							opcionPadredelHijo.getListaSubMenus().add(opcionHijo);
						}
						return true;
					}
				} else {
					return false;
				}

			}
		}
		return false;
	}
}