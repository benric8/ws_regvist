package pe.gob.pj.administrativos.visitas.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.pj.administrativos.visitas.model.bean.Car;
import pe.gob.pj.administrativos.visitas.model.dao.DemoDao;
import pe.gob.pj.administrativos.visitas.service.DemoService;

@Service("demoService")
public class DemoServiceImpl implements DemoService {

	private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);
	
	@Autowired
	private DemoDao demoDao;

	@Override
	public List<String> listaAnios() throws Exception {		
        return demoDao.findYears();
	}
	
	@Override
	public List<Car> listaDemo(int size) {
		String[] colors = demoDao.getColors();
		String[] brands = demoDao.getBrands();
		
		List<Car> list = new ArrayList<Car>();
        for(int i = 0 ; i < size ; i++) {
            list.add(new Car(getRandomId(), getRandomBrand(brands), getRandomYear(), getRandomColor(colors)));
        }
         
        logger.info("Registros encontrados: " + list.size());
        return list;
	}
	
	private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private int getRandomYear() {
        return (int) (Math.random() * 50 + 1960);
    }
     
    private String getRandomColor(String[] colors) {
        return colors[(int) (Math.random() * 10)];
    }
     
    private String getRandomBrand(String[] brands) {
        return brands[(int) (Math.random() * 10)];
    }			

}
