package pe.gob.pj.administrativos.visitas.web.handler;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class ExpiredExceptionExceptionHandler extends ExceptionHandlerWrapper {
    private ExceptionHandler wrapped;

    public ExpiredExceptionExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
    	FacesContext fc = FacesContext.getCurrentInstance();
        for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
        	ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            String redirectPage = null;
            Throwable t = context.getException();

            try {
                if (t instanceof ViewExpiredException ) {
                	 redirectPage = "/finSesion.xhtml";
                } else {
                	redirectPage = "/error.xhtml";
                }
            } finally {
                i.remove();
            }

            ExternalContext extContext = fc.getExternalContext();
            String url = extContext.encodeActionURL(fc.getApplication().getViewHandler().getActionURL(fc, redirectPage));
            try {
                extContext.redirect(url);
            } catch (IOException ioe) {
                throw new FacesException(ioe);
            } catch (IllegalStateException ioe) {
            	throw new FacesException(ioe);
            }

            break;
        }

        getWrapped().handle();
    }
}
