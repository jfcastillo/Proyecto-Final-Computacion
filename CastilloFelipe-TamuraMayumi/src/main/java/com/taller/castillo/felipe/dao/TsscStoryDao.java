package com.taller.castillo.felipe.dao;


import java.util.List;

import com.taller.castillo.felipe.model.TsscStory;

public interface TsscStoryDao {
	public void save(TsscStory story);
	public TsscStory update(TsscStory story);
	public void delete(TsscStory story);
	public List<TsscStory> findAll();
	public TsscStory findById(long id);
	public void deleteAll();
}
