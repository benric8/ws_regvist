package pe.gob.pj.administrativos.visitas.model.bean;

import java.io.Serializable;

public class Car implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String brand;
	private int year;
	private String color;
	
	public Car(String id, String brand, int year, String color) {
		this.id = id;
		this.brand = brand;
		this.year = year;
		this.color = color;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}
