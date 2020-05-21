package com.taller.castillo.felipe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.taller.castillo.felipe.model.TsscAdmin;
import com.taller.castillo.felipe.model.TsscStory;
@Repository
public class TsscAdminDaoImp implements TsscAdminDao{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void save(TsscAdmin admin) {
		entityManager.persist(admin);		
	}

	@Override
	public TsscAdmin update(TsscAdmin admin) {
		return entityManager.merge(admin);
	}

	@Override
	public void delete(TsscAdmin admin) {
		entityManager.remove(admin);
	}

	@Override
	public List<TsscAdmin> findAll() {
		String query = "SELECT admin from TsscAdmin admin";
		TypedQuery<TsscAdmin> tQuery = entityManager.createQuery(query, TsscAdmin.class);
		return tQuery.getResultList();
	}

	@Override
	public TsscAdmin findById(long id) {
		TsscAdmin admin = null;
		String query = "SELECT admin from TsscAdmin admin WHERE admin.id = :id";
		TypedQuery<TsscAdmin> tQuery = entityManager.createQuery(query, TsscAdmin.class).setParameter("id", id);
		List<TsscAdmin> list = tQuery.getResultList();
		if (!list.isEmpty()) {
			admin = list.get(0);
		}
		return admin;
	}

}
