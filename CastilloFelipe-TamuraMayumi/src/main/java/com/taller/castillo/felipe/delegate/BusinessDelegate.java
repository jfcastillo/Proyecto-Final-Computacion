package com.taller.castillo.felipe.delegate;

import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTimecontrol;
import com.taller.castillo.felipe.model.TsscTopic;

public interface BusinessDelegate {

	//TsscGame ----------------------------
	public TsscGame getGame(long idGame);
	public Iterable<TsscGame> findAllGames(); 
	public TsscGame saveGame(TsscGame game);
	public void editGame(long id, TsscGame game);
	public Iterable<TsscTimecontrol> findAllTimeControlByGameId(long gameId); 
	
	//TsscTopic ----------------------------
	public TsscTopic getTopic(long idTopic);
	public Iterable<TsscTopic> findAllTopics();
	public TsscTopic saveTopic(TsscTopic topic);
	public void editTopic(long id, TsscTopic topic);
	public void deleteTopic(long id);
	
	//TsscStory ----------------------------
	public TsscStory getStory(long idStory);
	public Iterable<TsscStory> findAllStories();
	public Iterable<TsscStory> findStoriesByGameId(long gameId);
	public TsscStory saveStory(TsscStory story, long gameId);
	public TsscStory editStory(TsscStory story);
	public void deleteStory(TsscStory story);

}
