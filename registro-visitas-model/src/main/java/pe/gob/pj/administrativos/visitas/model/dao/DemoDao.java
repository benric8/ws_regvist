package pe.gob.pj.administrativos.visitas.model.dao;

import java.util.List;

import pe.gob.pj.administrativos.visitas.model.bean.Car;

public interface DemoDao extends BaseDao<Long, Car>{

	List<String> findYears() throws Exception;

	String[] getColors();

	String[] getBrands();

}
