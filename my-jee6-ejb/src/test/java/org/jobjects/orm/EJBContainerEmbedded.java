package org.jobjects.orm;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
/**
 * http://download.oracle.com/docs/cd/E18930_01/html/821-2424/gjlde.html
 * 
 * @author Mickael
 *
 */
public class EJBContainerEmbedded {

	private static EJBContainerEmbedded instance=null;
	
	private EJBContainer ec=null;
	
	public static EJBContainerEmbedded getInstance() {
		if(instance==null) {
			instance=new EJBContainerEmbedded();
		}
		return instance; 
	}
	
	private EJBContainerEmbedded() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(EJBContainer.MODULES, new File("target/classes"));
		
		/*Avec une installation de glassfish afin de facilité la confuiguration pour les tests*/
		properties.put("org.glassfish.ejb.embedded.glassfish.installation.root", "C:/programs/glassfish-3.1.1/glassfish");
		ec = EJBContainer.createEJBContainer(properties);		
	}
	
	public EJBContainer getEJBContainer() {
		return ec;
	}
	
}
