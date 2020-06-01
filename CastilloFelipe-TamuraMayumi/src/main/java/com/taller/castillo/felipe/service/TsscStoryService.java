package com.taller.castillo.felipe.service;

import java.util.Optional;

import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.NullGameException;
import com.taller.castillo.felipe.exception.StoryException;
import com.taller.castillo.felipe.model.TsscStory;

public interface TsscStoryService {
	
	public TsscStory createStory(TsscStory story, long idGame) throws StoryException, NullGameException;
	public TsscStory editStory(TsscStory story) throws StoryException, EditException;
	public Iterable<TsscStory> findAll();
	public Optional<TsscStory> findById(long id);
	public Iterable<TsscStory> findByGameId(long id);
	public void deleteStory(TsscStory actualStory);
	
}