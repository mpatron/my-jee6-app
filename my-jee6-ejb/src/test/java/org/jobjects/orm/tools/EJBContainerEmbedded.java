package org.jobjects.orm.tools;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.apache.commons.io.FileUtils;

/**
 * http://download.oracle.com/docs/cd/E18930_01/html/821-2424/gjlde.html
 * 
 * @author Mickael
 * 
 */
public class EJBContainerEmbedded {
	private static Logger LOGGER = Logger.getLogger(EJBContainerEmbedded.class.getName());

	private static EJBContainerEmbedded instance = null;
	//public static final String MODULE_NAME = "my-jee-app";  
	//public static final String TARGET_DIR = "target/" + MODULE_NAME;  
	

	// ======================================
	// = Attributes =
	// ======================================
	private static EJBContainer ec;
	private static Context ctx;

	public static EJBContainerEmbedded getInstance() {
		if (instance == null) {
			try {
				instance = new EJBContainerEmbedded();
			} catch (Throwable t) {
				LOGGER.log(Level.SEVERE, "internal error.", t);
			}
		}
		return instance;
	}

//	private static File prepareModuleDirectory() throws IOException {  
//	    File result = new File(EJBContainerEmbedded.TARGET_DIR);  
//	    FileUtils.copyDirectory(new File("target/classes"), result);  
//	    FileUtils.copyFile(new File("target/test-classes/META-INF/persistence.xml"),   
//	            new File(EJBContainerEmbedded.TARGET_DIR + "/META-INF/persistence.xml"));  
//	    return result;  
//	}

	private EJBContainerEmbedded() throws IOException {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(EJBContainer.APP_NAME, "my-jee-ejb");
		//properties.put(EJBContainer.MODULES, new File[] { new File("target/test-classes"), new File("target/classes")});
		//LOGGER.log(Level.INFO, ">>>>"+(new File("../my-jee6-persistence/target/classes")).getAbsolutePath());
		//properties.put(EJBContainer.MODULES, new File[] { prepareModuleDirectory(), new File("../my-jee6-persistence/target/classes")});
		//properties.put(EJBContainer.PROVIDER, "org.glassfish.ejb.embedded.EJBContainerProviderImpl");

		
		properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.core.LocalInitialContextFactory");
        properties.put(EJBContainer.PROVIDER, "org.apache.openejb.OpenEjbContainer");
        //properties.put("openejb.deployments.classpath.ear", "true");
        properties.put("openejb.validation.output.level", "VERBOSE");
        
        String dbName="movieDatabase";
        properties.put(dbName, "new://Resource?type=DataSource");
        dbName += ".";
        properties.put(dbName + "JdbcDriver", "org.apache.derby.jdbc.EmbeddedDriver");
        properties.put(dbName + "JdbcUrl", "jdbc:derby:memory:MyDerbyDB;create=true");
        properties.put(dbName + "UserName", "APP");
        properties.put(dbName + "Password", "APP");
        properties.put(dbName + "JtaManaged", "true");
        
		
		/*
		 * Avec une installation de glassfish afin de facilité la configuration pour les tests
		 * http://download.oracle.com/docs/cd/E18930_01/html/821-2424/gjlde.html
		 */
		//String glassFishMainPath = "/home/mickael/programs/glassfish3";
//		String glassFishMainPath = "C:/programs/glassfish-3.1.2.2-ml/glassfish";
//		String damineName = "domain1";
//		properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", glassFishMainPath);
//		properties.put("org.glassfish.ejb.embedded.glassfish.instance.root", glassFishMainPath+"/glassfish/domains/"+damineName);
//		properties.put("org.glassfish.ejb.embedded.glassfish.configuration.file", glassFishMainPath+"/glassfish/domains/"+damineName+"/config/domain.xml");
//		properties.put("org.glassfish.ejb.embedded.glassfish.keep-temporary-files", "true");		

		ec = EJBContainer.createEJBContainer(properties);
		ctx = ec.getContext();
	}

	public EJBContainer getEJBContainer() {
		return ec;
	}

	public Context getContext() {
		return ctx;
	}

	public void shutdown() throws Exception {
		if (ctx != null)
			ctx.close();
		if (ec != null)
			ec.close();
	}
}
