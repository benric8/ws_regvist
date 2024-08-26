package pe.gob.pj.administrativos.visitas.service.impl;

import org.springframework.stereotype.Service;

import pe.gob.pj.administrativos.visitas.model.util.ConstantesVisitas;
import pe.gob.pj.administrativos.visitas.service.CorreoService;
import pe.gob.pj.api.mail.exception.MailException;
import pe.gob.pj.api.mail.properties.MailProperties;
import pe.gob.pj.api.mail.properties.MessageProperties;
import pe.gob.pj.api.mail.stmp.Mail;
import pe.gob.pj.api.mail.stmp.MailBuilder;

import javax.annotation.PostConstruct;
import javax.mail.SendFailedException;

@Service("correoService")
public class CorreoServiceImpl implements CorreoService {

    private MailProperties mailProperties;

    @PostConstruct
    private void init() throws Exception {

        this.mailProperties = new MailProperties();
        this.mailProperties.setSmtpCorreoEmisor(ConstantesVisitas.SMTP_CORREO_EMISOR);
        this.mailProperties.setSmtpLocalHost(ConstantesVisitas.SMTP_LOCALHOST);
        this.mailProperties.setSmtpIp(ConstantesVisitas.SMTP_IP);
        this.mailProperties.setSmtpProtocolo(ConstantesVisitas.SMTP_PROTOCOLO);
        this.mailProperties.setSmtpPuerto(ConstantesVisitas.SMTP_PUERTO);


    }

    private Mail builder() throws MailException {
        MailBuilder mailBuilder = new MailBuilder();
        Mail mail = mailBuilder.build(this.mailProperties);
        return mail;
    }

    @Override
    public void enviar(MessageProperties messageProperties)throws MailException{

        Mail mail;
        try {
            mail = builder();
            mail.enviarCorreo(messageProperties);
        } catch (SendFailedException se){
        	if(se.getValidSentAddresses().length <= 0)
        		throw new MailException(se.getMessage(), se);
        }catch (Exception e) {
        	throw new MailException(e.getMessage(), e);
		}
    }
}
