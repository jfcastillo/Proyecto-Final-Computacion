package com.taller.castillo.felipe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;


import com.taller.castillo.felipe.model.TsscTimecontrol;
@Repository
public class TsscTimeControlDaoImp implements TsscTimeControlDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscTimecontrol timeControl) {
		entityManager.persist(timeControl);
				
	}

	@Override
	public TsscTimecontrol update(TsscTimecontrol timeControl) {
		return entityManager.merge(timeControl);
		
	}

	@Override
	public void delete(TsscTimecontrol timeControl) {
		entityManager.remove(timeControl);
		
	}

	@Override
	public List<TsscTimecontrol> findAll() {
		String query = "SELECT timeControl from TsscTimecontrol timeControl";
		TypedQuery<TsscTimecontrol> tQuery = entityManager.createQuery(query, TsscTimecontrol.class);
		return tQuery.getResultList();
	}

	@Override
	public TsscTimecontrol findById(long id) {
		TsscTimecontrol story = null;
		String query = "SELECT timeControl from TsscTimecontrol timeControl WHERE timeControl.id = :id";
		TypedQuery<TsscTimecontrol> tQuery = entityManager.createQuery(query, TsscTimecontrol.class).setParameter("id", id);
		List<TsscTimecontrol> list = tQuery.getResultList();
		if (!list.isEmpty()) {
			story = list.get(0);
		}
		return story;
	}

	@Override
	public void deleteAll() {
		String query = "DELETE FROM TsscTimecontrol";	
		entityManager.createQuery(query).executeUpdate();
		
	}

	@Override
	public List<TsscTimecontrol> findByGameId(long id) {
		String query = "SELECT timecontrol from TsscTimecontrol timecontrol WHERE timecontrol.tsscGame.id = :id";
		TypedQuery<TsscTimecontrol> tQuery = entityManager.createQuery(query, TsscTimecontrol.class).setParameter("id", id);
		List<TsscTimecontrol> list = tQuery.getResultList();
		return list;
	}

}
