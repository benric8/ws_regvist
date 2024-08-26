package pe.gob.pj.administrativos.visitas.model.client;

import java.io.Serializable;

public class ConstantesConfig implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 3. WEB SERVICE PIDE
	public static final String PARAM_CONFIG_USER_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_USER_PIDE);
	public static final String PARAM_CONFIG_PASS_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_PASS_PIDE);
	public static final String PARAM_CONFIG_ENDPOINT_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_ENDPOINT_PIDE);
	public static final String PARAM_CONFIG_TIMEOUT_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_TIMEOUT_PIDE);
	public static final String PARAM_CONFIG_CODIGO_APLICATIVO_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_CODIGO_APLICATIVO_PIDE);
	public static final String PARAM_CONFIG_CODIGO_ROL_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_CODIGO_ROL_PIDE);
	public static final String PARAM_CONFIG_CODIGO_CLIENTE_PIDE = Configuration.getInstance().getProperty(Property.PARAM_CONFIG_CODIGO_CLIENTE_PIDE);
	
	public static final String SMTP_IP=Configuration.getInstance().getProperty(Property.SMTP_IP);
	public static final String SMTP_PROTOCOLO=Configuration.getInstance().getProperty(Property.SMTP_PROTOCOLO);
	public static final String SMTP_PUERTO= Configuration.getInstance().getProperty(Property.SMTP_PUERTO);
	public static final String SMTP_CORREO_EMISOR= Configuration.getInstance().getProperty(Property.SMTP_CORREO_EMISOR);		

}
