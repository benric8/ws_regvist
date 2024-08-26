package pe.gob.pj.administrativos.visitas.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 @WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml","*.css","*.js"})	
public class SecurityAccessGrantedFilter implements Filter{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityAccessGrantedFilter.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {        
        
        HttpServletRequest httpRequest = (HttpServletRequest)request;        
		HttpServletResponse res= (HttpServletResponse) response;        
        HttpSession session = httpRequest.getSession();
		
		if (httpRequest.getRequestURI().contains("/templates/")  
				|| httpRequest.getRequestURI().contains("/resourses/css/") 
				|| httpRequest.getRequestURI().contains("/resourses/js/") ) {			
			try {				 
				if( session!=null ){
	    			session.invalidate();
	    		} 
			    httpRequest.getRequestDispatcher("/error404.xhtml").forward(request, res);
			} catch (Exception e) {					
					LOGGER.info("- [ACCESO DENEGADO] - ["+httpRequest.getRequestURL().toString()+"]");	
					httpRequest.getRequestDispatcher("/error403.xhtml").forward(request, res);
			}
		} else {
			chain.doFilter(httpRequest, res);
		}
			 
    }
 
    public void init(FilterConfig filterConfig) {}
 
    public void destroy() {
    	
    	LOGGER.info("destroy method");
    
    }
}
