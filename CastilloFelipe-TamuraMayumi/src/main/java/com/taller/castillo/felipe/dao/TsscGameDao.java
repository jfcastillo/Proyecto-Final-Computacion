package com.taller.castillo.felipe.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscTopic;

public interface TsscGameDao {
	public void save(TsscGame game);
	public TsscGame update(TsscGame game);
	public void delete(TsscGame game);
	public void deleteAll();
	public List<TsscGame> findByName(String name);
	public List<TsscGame> findByDescription(String description);
	public List<TsscGame> findByIdTopic(long idTopic);
	public List<TsscGame> findByDateRange(LocalDate startDate, LocalDate endDate);
	public List<TsscGame> findByDateHourRange(LocalDate date, LocalTime startHour, LocalTime endHour);
	public Iterable<TsscGame> findAll();
	public TsscGame findById(long id);
	public List<Object[]> findTopicByDate(LocalDate startDate);
	public List<TsscGame> findGamesWithStories(LocalDate date);
	
}
