package pe.gob.pj.administrativos.visitas.web.handler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class ExpiredExceptionExceptionHandlerFactory extends ExceptionHandlerFactory{
	 private ExceptionHandlerFactory parent;

	    public ExpiredExceptionExceptionHandlerFactory(ExceptionHandlerFactory parent) {
	        this.parent = parent;
	    }

	    @Override
	    public ExceptionHandler getExceptionHandler() {
	        return new ExpiredExceptionExceptionHandler(parent.getExceptionHandler());
	    }
}