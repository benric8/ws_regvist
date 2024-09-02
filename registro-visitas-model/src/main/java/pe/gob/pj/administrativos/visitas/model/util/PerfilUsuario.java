package pe.gob.pj.administrativos.visitas.model.util;

public enum PerfilUsuario {
	ADMINISTRADOR_SISTEMA(1,"ADMINISTRADOR DE SISTEMA"),
	ADMINISTRADOR_CORTE(2,"ADMINISTRADOR DE CORTE"),
	OPERADOR(3,"OPERADOR");
	
	private int idPerfil;
	private String descripcion;
	
	public int getIdPerfil() {
		return idPerfil;
	}
	public String getDescripcion() {
		return descripcion;
	}
	
	private PerfilUsuario(int idPerfil, String descripcion) {
		this.idPerfil = idPerfil;
		this.descripcion = descripcion;
	}
	
	
}
