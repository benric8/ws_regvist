package pe.gob.pj.administrativos.visitas.service;

import java.util.List;

import pe.gob.pj.administrativos.visitas.model.bean.Car;

public interface DemoService{

	List<Car> listaDemo(int size);

	List<String> listaAnios() throws Exception;
}
