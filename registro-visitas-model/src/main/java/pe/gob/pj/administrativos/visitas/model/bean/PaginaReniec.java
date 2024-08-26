package pe.gob.pj.administrativos.visitas.model.bean;

import java.io.Serializable;

public class PaginaReniec implements Serializable{

	private static final long serialVersionUID = 1L;

	private String size;
	private String page;
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}	
	
	
}
