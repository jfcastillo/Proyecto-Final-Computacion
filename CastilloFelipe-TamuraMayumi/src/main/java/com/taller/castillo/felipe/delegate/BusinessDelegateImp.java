package com.taller.castillo.felipe.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTopic;

@Component
public class BusinessDelegateImp implements BusinessDelegate{
	public static final String LOCAL_URL = "http://localhost:8085/";
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public TsscGame getGame(long idGame) {
		TsscGame game = null;
		ResponseEntity<TsscGame> responseGame = restTemplate.getForEntity(LOCAL_URL + "api/games"+ idGame, TsscGame.class);
		if (responseGame.getStatusCode() == HttpStatus.OK) 
			game = responseGame.getBody();
		return game;
		
	}

	@Override
	public Iterable<TsscGame> findAllGames() {
		TsscGame[] game = null;
		List<TsscGame> gameIt;
		ResponseEntity<TsscGame[]> responseGames = restTemplate.getForEntity(LOCAL_URL + "api/games" , TsscGame[].class);
		if (responseGames.getStatusCode() == HttpStatus.OK) 
			game = responseGames.getBody();
		gameIt = Arrays.asList(game);
		return gameIt;
	}

	@Override
	public TsscGame saveGame(TsscGame game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TsscGame editGame(TsscGame game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TsscTopic getTopic(long idTopic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<TsscTopic> findAllTopics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TsscTopic saveTopic(TsscTopic topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TsscTopic editTopic(TsscTopic topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTopic(TsscTopic topic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TsscStory getStory(long idTopic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<TsscStory> findAllStories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TsscStory saveStory(TsscTopic topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TsscStory editStory(TsscTopic topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStory(TsscTopic topic) {
		// TODO Auto-generated method stub
		
	}

}
