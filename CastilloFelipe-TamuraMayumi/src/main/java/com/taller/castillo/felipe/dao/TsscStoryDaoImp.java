package com.taller.castillo.felipe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
@Repository
public class TsscStoryDaoImp implements TsscStoryDao{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void save(TsscStory story) {
		entityManager.persist(story);
				
	}

	@Override
	public TsscStory update(TsscStory story) {
		return entityManager.merge(story);
		
	}

	@Override
	public void delete(TsscStory story) {
		entityManager.remove(story);
		
	}

	@Override
	public List<TsscStory> findAll() {
		String query = "SELECT story from TsscStory story";
		TypedQuery<TsscStory> tQuery = entityManager.createQuery(query, TsscStory.class);
		return tQuery.getResultList();
	}

	@Override
	public TsscStory findById(long id) {
		TsscStory story = null;
		String query = "SELECT story from TsscStory story WHERE story.id = :id";
		TypedQuery<TsscStory> tQuery = entityManager.createQuery(query, TsscStory.class).setParameter("id", id);
		List<TsscStory> list = tQuery.getResultList();
		if (!list.isEmpty()) {
			story = list.get(0);
		}
		return story;
	}

	@Override
	public void deleteAll() {
		String query = "DELETE FROM TsscStory";	
		entityManager.createQuery(query).executeUpdate();
	}

	

}
