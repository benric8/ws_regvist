package pe.gob.pj.administrativos.visitas.model.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Registro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	protected ArrayList<Persona> personas;
	protected int totalRegistros;
	public ArrayList<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}
	public int getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	@Override
	public String toString() {
		return "Registro [personas=" + personas + ", totalRegistros=" + totalRegistros + "]";
	}
	
	
}
