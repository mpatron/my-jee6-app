package org.jobjects.derby;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jobjects.orm.tools.AppConstants;

public class EntityManagerFactorySingleton {

	static private EntityManagerFactorySingleton instance=null;
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	
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
			factory = Persistence.createEntityManagerFactory(AppConstants.PERSISTENCE_UNIT_NAME);
			manager = factory.createEntityManager();
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
}
