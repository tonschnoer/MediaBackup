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

	// Das Überladen der Klasse GenericServlet verlangt eine Variable serialVersionUID.
	private static final long serialVersionUID = 1L;
	protected static final Logger logger = LogManager.getLogger();
	
	
	
	
	// called by the Servlet Container at shutdown
	@Override
	public void destroy() {
		logger.info("Finishing Application and deleting timer service.");
		logger.info("Goodbye.");
	}
			
	
	// called automatically by the Servlet Container at startup
	public void init(ServletConfig servletConfig) throws ServletException {
		
		super.init(servletConfig);
		logger.info("Initializing Java MediaServBackup for dira (c) 2024 by kmt");
		
		try
		{
			logger.info("loading properties.");
			Config.parseConfig();
				
				logger.info("Loading config successful. Initalizing timer services...");
				TCPClient tc = new TCPClient();
				tc.main(null);
		
		} catch (Exception e) {
			logger.fatal("Error initializing Application: "+e.getCause());
			
		}
		
	}

	// wird funktional nicht benoetigt, muss aber drin sein, da wir die Klasse GenericServlet extenden.
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
	throws ServletException, IOException {
		
	}
		
}