package org.jobjects.derby;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jobjects.orm.tools.AppConstants;

public class EntityManagerFactorySingleton {

	private static Logger LOGGER = Logger.getLogger(EntityManagerFactorySingleton.class.getName());
	static private EntityManagerFactorySingleton instance=null;
	private EntityManagerFactory factory = null;
	private EntityManager manager = null;
	
	private EntityManagerFactorySingleton(){		
	}
	
	static public EntityManagerFactorySingleton getInstance() {
		if(instance==null) {
			instance=new EntityManagerFactorySingleton();
		}
		return instance;
	}
	
	public void start() {
		if(factory==null || manager==null) {
			try {
				factory = Persistence.createEntityManagerFactory(AppConstants.PERSISTENCE_UNIT_NAME);
				manager = factory.createEntityManager();
			} catch (Throwable t) {
				LOGGER.log(Level.SEVERE, "Erreur au chargement de JPA.", t);
			}
		}
	}
	
	public void stop() {
		if(factory!=null || manager!=null) {
			manager.close();
			manager=null;
			factory.close();
			factory=null;
		}
	}
	
	/**
	 * @return the factory
	 */
	public EntityManagerFactory getFactory() {
		return factory;
	}

	/**
	 * @return the manager
	 */
	public EntityManager getManager() {
		return manager;
	}	
}
