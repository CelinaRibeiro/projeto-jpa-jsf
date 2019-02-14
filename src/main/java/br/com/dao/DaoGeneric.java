package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

public class DaoGeneric<E> {

	// metodo apenas salva no bd
	public void salvar(E entidade) {
		EntityManager entityManager = JPAUtil.gEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(entidade);
		entityTransaction.commit();
		entityManager.close();
	}

	// metodo salva e atualiza no bd
	public E merge(E entidade) {
		EntityManager entityManager = JPAUtil.gEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		E retorno = entityManager.merge(entidade);

		entityTransaction.commit();
		entityManager.close();
		return retorno;

	}

	// normalmente ocorre erro, vou utilizar o deletePorId
	public void delete(E entidade) {
		EntityManager entityManager = JPAUtil.gEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.remove(entidade);

		entityTransaction.commit();
		entityManager.close();
	}

	//delete em uso
	public void deletePorId(E entidade) {
		EntityManager entityManager = JPAUtil.gEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		Object id = JPAUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id).executeUpdate();
		
		entityTransaction.commit();
		entityManager.close();
	}
	
	public List<E> getListEntity(Class<E> entidade){
		EntityManager entityManager = JPAUtil.gEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		List<E> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		
		return retorno;
		
		
		
	}
}
