package com.taller.castillo.felipe.delegate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTopic;

@Component
public class BusinessDelegateImp implements BusinessDelegate{
	public static final String LOCAL_URL = "http://localhost:8085/";
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	//TsscGame ---------------------------------------------------------------------------------------------------
	@Override
	public TsscGame getGame(long idGame) {
		TsscGame game = null;
		ResponseEntity<TsscGame> responseGame = restTemplate.getForEntity(LOCAL_URL + "api/tsscgames/"+ idGame, TsscGame.class);
		if (responseGame.getStatusCode() == HttpStatus.OK) 
			game = responseGame.getBody();
		return game;
		
	}

	@Override
	public Iterable<TsscGame> findAllGames() {
		TsscGame[] game = null;
		List<TsscGame> gameIt;
		ResponseEntity<TsscGame[]> responseGames = restTemplate.getForEntity(LOCAL_URL + "api/tsscgames/" , TsscGame[].class);
		if (responseGames.getStatusCode() == HttpStatus.OK) {
			game = responseGames.getBody();
		}		
		gameIt = Arrays.asList(game);	
		
		return gameIt;
		
	}

	@Override
	public TsscGame saveGame(TsscGame game) {
		TsscGame response = null;
		ResponseEntity<TsscGame> responseGame = restTemplate.postForEntity(LOCAL_URL + "api/tsscgames/", game, TsscGame.class);
		System.out.println(responseGame.getStatusCode());
		if ( responseGame.getStatusCode() == HttpStatus.OK) {
			response = responseGame.getBody();
		}	
		
		return response;
	}

	// Falta los metodos de editar
	@Override
	public TsscGame editGame(TsscGame game) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//TsscTopic ---------------------------------------------------------------------------------------------------
	@Override
	public TsscTopic getTopic(long idTopic) {
		TsscTopic topic = null;
		ResponseEntity<TsscTopic> responseTopic = restTemplate.getForEntity(LOCAL_URL + "api/tssctopics/"+ idTopic, TsscTopic.class);
		if (responseTopic.getStatusCode() == HttpStatus.OK) 
			topic = responseTopic.getBody();
		return topic;
	}

	@Override
	public Iterable<TsscTopic> findAllTopics() {
		TsscTopic[] topics = null;
		List<TsscTopic> topicsIt;
		ResponseEntity<TsscTopic[]> responseTopics = restTemplate.getForEntity(LOCAL_URL + "api/tssctopics/" , TsscTopic[].class);
		if (responseTopics.getStatusCode() == HttpStatus.OK) {
			topics = responseTopics.getBody();
		}		
		topicsIt = Arrays.asList(topics);	
		
		return topicsIt;
	}

	@Override
	public TsscTopic saveTopic(TsscTopic topic) {
		TsscTopic response = null;
		ResponseEntity<TsscTopic> responseTopic = restTemplate.postForEntity(LOCAL_URL + "api/tssctopics/", topic, TsscTopic.class);
		if ( responseTopic.getStatusCode() == HttpStatus.OK) {
			response = responseTopic.getBody();
		}	
		return response;
	}
	
	@Override
	public TsscTopic editTopic(TsscTopic topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTopic(TsscTopic topic) {
		restTemplate.delete(LOCAL_URL + topic.getId());
		
	}
	//TsscStory ---------------------------------------------------------------------------------------------------
	@Override
	public TsscStory getStory(long idStory) {
		TsscStory story = null;
		ResponseEntity<TsscStory> responseStory = restTemplate.getForEntity(LOCAL_URL + "api/tsscstories/"+ idStory, TsscStory.class);
		if (responseStory.getStatusCode() == HttpStatus.OK) 
			story = responseStory.getBody();
		return story;
	}

	@Override
	public Iterable<TsscStory> findAllStories() {
		TsscStory[] stories = null;
		List<TsscStory> storiesIt;
		ResponseEntity<TsscStory[]> responseStories = restTemplate.getForEntity(LOCAL_URL + "api/tsscstories/" , TsscStory[].class);
		if (responseStories.getStatusCode() == HttpStatus.OK) {
			stories = responseStories.getBody();
		}		
		storiesIt = Arrays.asList(stories);	
		
		return storiesIt;
	}

	@Override
	public TsscStory saveStory(TsscStory story) {
		TsscStory response = null;
		ResponseEntity<TsscStory> responseStory = restTemplate.postForEntity(LOCAL_URL + "api/tsscstories/", story, TsscStory.class);
		if ( responseStory.getStatusCode() == HttpStatus.OK) {
			response = responseStory.getBody();
		}	
		
		return response;
	}

	@Override
	public TsscStory editStory(TsscStory story) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStory(TsscStory story) {
		restTemplate.delete(LOCAL_URL + story.getId());
		
	}

}