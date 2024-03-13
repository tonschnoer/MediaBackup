package de.kmt.MediaBackup;

import java.io.IOException;
import java.util.Timer;
import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

// changed to log4j2
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main extends GenericServlet {

	private static final long serialVersionUID = 1L;
	protected static final Logger logger = LogManager.getLogger();
	
	private static Timer timer = new Timer();
	
	
	// called by the Servlet Container at shutdown
	@Override
	public void destroy() {
		logger.info("Finishing Application and deleting timer service.");
		timer.cancel();	
		timer=null;
		logger.info("Goodbye.");
	}
			
	
	// called by the Servlet Container at startup
	public void init(ServletConfig servletConfig) throws ServletException {
		int errorcode=0;
		super.init(servletConfig);
		logger.info("Initializing Java MediaServBackup for dira (c) 2024 by kmt");
		
		try
		{
			logger.info("loading properties.");
			errorcode=0;
			
			if (errorcode==0) {
				
				logger.info("Loading props successful. Initalizing timer services...");
				//timer.schedule(new TimerElapsedAction(),Props.TimerDelay,Props.TimerInterval);	
			} else {
				logger.fatal("No props available. Application halted.");
			}
			
				
		} catch (Exception e) {
			logger.fatal("Error initializing Application: "+e.getCause());
			
		}
		
	}

	// wird nicht benoetigt, muss aber drin sein
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
	throws ServletException, IOException {
		
	}
		
}