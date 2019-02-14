package br.com.jpautil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static EntityManagerFactory factory = null;
	
	static{
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("projeto-jpa-jsf");
			
		}
	}
	public static EntityManager gEntityManager() {
		return factory.createEntityManager();
	}
	
	//usado no deletePorId
	public static Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
		
	}

}
