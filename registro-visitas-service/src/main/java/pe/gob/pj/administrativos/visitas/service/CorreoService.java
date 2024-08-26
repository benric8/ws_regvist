package pe.gob.pj.administrativos.visitas.service;

import pe.gob.pj.api.mail.exception.MailException;
import pe.gob.pj.api.mail.properties.MessageProperties;

public interface CorreoService {

    public void enviar(MessageProperties messageProperties) throws MailException;

}
