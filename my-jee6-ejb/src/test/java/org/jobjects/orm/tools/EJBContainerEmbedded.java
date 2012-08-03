package org.jobjects.orm.tools;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

/**
 * http://download.oracle.com/docs/cd/E18930_01/html/821-2424/gjlde.html
 * 
 * @author Mickael
 * 
 */
public class EJBContainerEmbedded {

	private static EJBContainerEmbedded instance = null;

	// ======================================
	// = Attributes =
	// ======================================
	private static EJBContainer ec;
	private static Context ctx;

	public static EJBContainerEmbedded getInstance() {
		if (instance == null) {
			instance = new EJBContainerEmbedded();
		}
		return instance;
	}

	private EJBContainerEmbedded() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(EJBContainer.APP_NAME, "my-jee-app");
		properties.put(EJBContainer.MODULES, new File("target/classes"));
		properties.put(EJBContainer.PROVIDER,
				"org.glassfish.ejb.embedded.EJBContainerProviderImpl");

		/*
		 * Avec une installation de glassfish afin de facilité la confuiguration
		 * pour les tests
		 */
		/* http://download.oracle.com/docs/cd/E18930_01/html/821-2424/gjlde.html */
		properties.put(
				"org.glassfish.ejb.embedded.glassfish.installation.root",
				"/home/mickael/programs/glassfish");
		properties.put("org.glassfish.ejb.embedded.glassfish.instance.root",
				"/home/mickael/programs/glassfish/glassfish/domains/domain1");
		properties
				.put("org.glassfish.ejb.embedded.glassfish.configuration.file",
						"/home/mickael/programs/glassfish/glassfish/domains/domain1/config/domain.xml");
		properties.put(
				"org.glassfish.ejb.embedded.glassfish.keep-temporary-files",
				"true");

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
