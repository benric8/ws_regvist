package pe.gob.pj.administrativos.visitas.model.dao.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Repository;

import pe.gob.pj.administrativos.visitas.model.bean.Car;
import pe.gob.pj.administrativos.visitas.model.dao.DemoDao;

@Repository("demoDao")
public class DemoDaoImpl extends BaseDaoImpl<Long, Car> implements DemoDao{

    private final static String[] colors;
    
    private final static String[] brands;
    
    private final static String[] years;
     
    static {
        colors = new String[10];
        colors[0] = "Black";
        colors[1] = "White";
        colors[2] = "Green";
        colors[3] = "Red";
        colors[4] = "Blue";
        colors[5] = "Orange";
        colors[6] = "Silver";
        colors[7] = "Yellow";
        colors[8] = "Brown";
        colors[9] = "Maroon";
         
        brands = new String[10];
        brands[0] = "BMW";
        brands[1] = "Mercedes";
        brands[2] = "Volvo";
        brands[3] = "Audi";
        brands[4] = "Renault";
        brands[5] = "Fiat";
        brands[6] = "Volkswagen";
        brands[7] = "Honda";
        brands[8] = "Jaguar";
        brands[9] = "Ford";
        
        years = new String[4];
        years[0] = "2015";
        years[1] = "2016";
        years[2] = "2017";
        years[3] = "2018";
        
    }

	@Override
	public List<String> findYears() throws Exception {
		return Arrays.asList(years);
	}

	
	@Override
	public String[] getColors(){
		return colors;
	}
	
	@Override
	public String[] getBrands() {
		return brands;
	}
}
