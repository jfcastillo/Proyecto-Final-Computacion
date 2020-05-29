package com.taller.castillo.felipe.delegate;

import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTopic;

public interface BusinessDelegate {

	//TsscGame ----------------------------
	public TsscGame getGame(long idGame);
	public Iterable<TsscGame> findAllGames(); 
	public TsscGame saveGame(TsscGame game);
	public TsscGame editGame(TsscGame game);
	
	//TsscTopic ----------------------------
	public TsscTopic getTopic(long idTopic);
	public Iterable<TsscTopic> findAllTopics();
	public TsscTopic saveTopic(TsscTopic topic);
	public TsscTopic editTopic(TsscTopic topic);
	public void deleteTopic(TsscTopic topic);
	
	//TsscStory ----------------------------
	public TsscStory getStory(long idTopic);
	public Iterable<TsscStory> findAllStories();
	public TsscStory saveStory(TsscTopic topic);
	public TsscStory editStory(TsscTopic topic);
	public void deleteStory(TsscTopic topic);

}
