package com.taller.castillo.felipe.dao;

import java.time.LocalDate;
import java.util.List;

import com.taller.castillo.felipe.model.TsscTopic;

public interface TsscTopicDao {
	public void save(TsscTopic topic);
	public void update(TsscTopic topic);
	public void delete(TsscTopic topic);
	public List<TsscTopic> findByName(String name);
	public List<TsscTopic> findByDescription(String description);
	public Iterable<TsscTopic> findAll();
	public TsscTopic findById(long id);

}
