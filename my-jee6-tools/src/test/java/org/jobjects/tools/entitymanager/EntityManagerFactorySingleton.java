package org.jobjects.tools.entitymanager;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jobjects.tools.AppConstants;

public class EntityManagerFactorySingleton {

	private static Logger LOGGER = Logger.getLogger(EntityManagerFactorySingleton.class.getName());
	static private EntityManagerFactorySingleton instance=null;
	private EntityManagerFactory factory = null;
	private EntityManager manager = null;
	private EntityTransaction trx = null;
	
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
				trx= manager.getTransaction();
				trx.begin();				
			} catch (Throwable t) {
				LOGGER.log(Level.SEVERE, "Erreur au chargement de JPA.", t);
			}
		}
	}
	
	public void stop() {
		try {
		if(factory!=null || manager!=null) {
			trx.commit();
			trx = null;
			manager.close();
			manager=null;
			factory.close();
			factory=null;
		}
		} catch  (Throwable t) {
			if(trx!=null) {
				trx.rollback();
			}
			LOGGER.log(Level.SEVERE, "Erreur au chargement de JPA.", t);
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
