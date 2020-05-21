	package com.taller.castillo.felipe.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscTopic;

@Repository
public class TsscGameDaoImp implements TsscGameDao{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscGame game) {
		entityManager.persist(game);
		
	}

	@Override
	public TsscGame update(TsscGame game) {
		return entityManager.merge(game);
	}

	@Override
	public void delete(TsscGame game) {
		entityManager.remove(game);
	}

	@Override
	public List<TsscGame> findByName(String name) {
		String query = "SELECT game FROM TsscGame game WHERE game.name = :name";
		TypedQuery<TsscGame> tQuery = entityManager.createQuery(query, TsscGame.class).setParameter("name", name);
		return tQuery.getResultList();
	}

	@Override
	public List<TsscGame> findByDescription(String description) {
		String query = "SELECT game FROM TsscGame game WHERE game.tsscTopic.description = :description";
		TypedQuery<TsscGame> tQuery = entityManager.createQuery(query, TsscGame.class).setParameter("description", description);
		return tQuery.getResultList();
	}

	@Override
	public List<TsscGame> findByIdTopic(long idTopic) {
		String query = "SELECT game FROM TsscGame game WHERE game.tsscTopic.id = :idTopic";
		TypedQuery<TsscGame> tQuery = entityManager.createQuery(query, TsscGame.class).setParameter("idTopic", idTopic);
		return tQuery.getResultList();
	}

	@Override
	public List<TsscGame> findByDateRange(LocalDate startDate, LocalDate endDate) {
		String query = "SELECT game FROM TsscGame game WHERE game.scheduledDate BETWEEN :sDate AND : eDate";
		TypedQuery<TsscGame> tQuery = entityManager.createQuery(query, TsscGame.class).setParameter("sDate", startDate).setParameter("eDate", endDate);
		return tQuery.getResultList();
	}

	@Override
	public List<TsscGame> findByDateHourRange(LocalDate date, LocalTime startHour, LocalTime endHour) {
		String query = "SELECT game FROM TsscGame game WHERE game.scheduledDate = :date AND game.scheduledTime BETWEEN :sHour AND : eHour";
		TypedQuery<TsscGame> tQuery = entityManager.createQuery(query, TsscGame.class).setParameter("date", date).setParameter("sHour", startHour).setParameter("eHour", endHour);
		return tQuery.getResultList();		
	}

	@Override
	public void deleteAll() {
		String query = "DELETE FROM TsscGame";	
		entityManager.createQuery(query).executeUpdate();
	}

	@Override
	public Iterable<TsscGame> findAll() {
		String query = "SELECT game FROM TsscGame game";
		TypedQuery<TsscGame> tQuery = entityManager.createQuery(query, TsscGame.class);
		return tQuery.getResultList();
	}

	@Override
	public TsscGame findById(long id) {
		TsscGame game = null;
		String query = "SELECT game FROM TsscGame game WHERE game.id = :id";
		TypedQuery<TsscGame> tQuery = entityManager.createQuery(query, TsscGame.class).setParameter("id", id);
		List<TsscGame> list = tQuery.getResultList();
		if (!list.isEmpty()) {
			game = list.get(0);
		}
		return game;
	}
	
	@Override
	public List<Object[]> findTopicByDate(LocalDate date) {
		String query = "SELECT game.tsscTopic, (SELECT COUNT(game) FROM TsscGame game WHERE game.scheduledDate = :date) FROM TsscGame game WHERE game.scheduledDate = :date GROUP BY game.tsscTopic ORDER BY game.scheduledTime" ;
		TypedQuery<Object[]> tQuery = entityManager.createQuery(query, Object[].class).setParameter("date", date);
		return tQuery.getResultList();
	}

	@Override
	public List<TsscGame> findGamesWithStories(LocalDate date) {

		String query = "SELECT game FROM TsscGame game WHERE game.scheduledDate = :date "
				+ "AND ((SELECT COUNT(tControl) FROM TsscTimecontrol tControl WHERE tControl.tsscGame.id = game.id) = 0"
				+ " OR (SELECT COUNT(story) FROM TsscStory story WHERE story.tsscGame.id = game.id ) < 10)";
		
		TypedQuery<TsscGame> tQuery = entityManager.createQuery(query, TsscGame.class).setParameter("date", date);
		return tQuery.getResultList();
	}
	
	

	

}
