package pe.gob.pj.administrativos.visitas.model.client;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Configuration {

	Properties settings = null;

	public final static String PROPERTIES_FILENAME = "setting-visitas.properties";

	private Configuration() {

        settings = new Properties();

        try {
            settings.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILENAME));
        } catch (IOException ex) {
        	ex.printStackTrace();
        }

    }

    public static Configuration getInstance() {
        return ConfigurationHolder.INSTANCE;
    }

    private static class ConfigurationHolder {
        private static final Configuration INSTANCE = new Configuration();
    }

    public String getProperty(String key) {
        if(settings == null) {
            return null;
        } else {
            return settings.getProperty(key);
        }

    }

    public Set<Object> getKeys() {
        if(settings == null) {
            return null;
        } else {
            return settings.keySet();
        }

    }

}
