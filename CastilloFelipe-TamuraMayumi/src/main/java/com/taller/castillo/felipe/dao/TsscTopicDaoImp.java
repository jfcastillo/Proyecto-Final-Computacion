package com.taller.castillo.felipe.dao;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscTopic;

@Repository
public class TsscTopicDaoImp implements TsscTopicDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscTopic topic) {		
		entityManager.persist(topic);
	}

	@Override
	public void update(TsscTopic topic) {
		entityManager.merge(topic);
	}

	@Override
	public void delete(TsscTopic topic) {
		entityManager.remove(topic);
	}
	@Override
	public List<TsscTopic> findByName(String name) {
		String query = "SELECT topic from TsscTopic topic WHERE topic.name = :name";
		TypedQuery<TsscTopic> tQuery = entityManager.createQuery(query, TsscTopic.class).setParameter("name", name);
		return tQuery.getResultList();
	}

	@Override
	public List<TsscTopic> findByDescription(String description) {
		String query = "SELECT topic from TsscTopic topic WHERE topic.description = :description";
		TypedQuery<TsscTopic> tQuery = entityManager.createQuery(query, TsscTopic.class).setParameter("description", description);
		return tQuery.getResultList();		
	}

	@Override
	public Iterable<TsscTopic> findAll() {
		String query = "SELECT topic from TsscTopic topic";
		TypedQuery<TsscTopic> tQuery = entityManager.createQuery(query, TsscTopic.class);
		return tQuery.getResultList();	
	}

	@Override
	public TsscTopic findById(long id) {
		TsscTopic topic = null;
		String query = "SELECT topic from TsscTopic topic WHERE topic.id = '"+id+"'";
		List<TsscTopic> list = entityManager.createQuery(query).getResultList();
		if (!list.isEmpty()) {
			topic = list.get(0);
		}
		return topic;
	}

	



}
