package com.taller.castillo.felipe.delegate;

import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTimecontrol;
import com.taller.castillo.felipe.model.TsscTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class BusinessDelegateImp implements BusinessDelegate {
    public static final String LOCAL_URL = "http://localhost:8085/";

    @Autowired
    private RestTemplate restTemplate;


    //TsscGame ---------------------------------------------------------------------------------------------------
    @Override
    public TsscGame getGame(long idGame) {
        TsscGame game = null;
        ResponseEntity<TsscGame> responseGame = restTemplate.getForEntity(LOCAL_URL + "api/tsscgames/" + idGame, TsscGame.class);
        if (responseGame.getStatusCode() == HttpStatus.OK)
            game = responseGame.getBody();
        return game;

    }

    @Override
    public Iterable<TsscGame> findAllGames() {
        TsscGame[] game = null;
        List<TsscGame> gameIt;
        ResponseEntity<TsscGame[]> responseGames = restTemplate.getForEntity(LOCAL_URL + "api/tsscgames/", TsscGame[].class);
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
        if (responseGame.getStatusCode() == HttpStatus.OK) {
            response = responseGame.getBody();
        }

        return response;
    }

    @Override
    public void editGame(long id, TsscGame game) {
        restTemplate.put(LOCAL_URL + "api/tsscgames/" + id, game);
    }
    
    //TsscTopic ---------------------------------------------------------------------------------------------------
    @Override
    public TsscTopic getTopic(long idTopic) {
        TsscTopic topic = null;
        ResponseEntity<TsscTopic> responseTopic = restTemplate.getForEntity(LOCAL_URL + "api/tssctopics/" + idTopic, TsscTopic.class);
        if (responseTopic.getStatusCode() == HttpStatus.OK)
            topic = responseTopic.getBody();
        return topic;
    }

    @Override
    public Iterable<TsscTopic> findAllTopics() {
        TsscTopic[] topics = null;
        List<TsscTopic> topicsIt = null;
        ResponseEntity<TsscTopic[]> responseTopics = restTemplate.getForEntity(LOCAL_URL + "api/tssctopics/", TsscTopic[].class);
        if (responseTopics.getStatusCode() == HttpStatus.OK) {
            topics = responseTopics.getBody();
            topicsIt = Arrays.asList(topics);
        }
        return topicsIt;
    }

    @Override
    public TsscTopic saveTopic(TsscTopic topic) {
        TsscTopic response = null;
        ResponseEntity<TsscTopic> responseTopic = restTemplate.postForEntity(LOCAL_URL + "api/tssctopics/", topic, TsscTopic.class);
        if (responseTopic.getStatusCode() == HttpStatus.OK) {
            response = responseTopic.getBody();
        }

        return response;
    }

    @Override
    public void editTopic(long id, TsscTopic topic) {
        restTemplate.put(LOCAL_URL + "api/tssctopics/"+id, topic);
    }

    @Override
    public void deleteTopic(long id) {
        restTemplate.delete(LOCAL_URL + "api/tssctopics/" + id);
    }

    //TsscStory ---------------------------------------------------------------------------------------------------
    @Override
    public TsscStory getStory(long idStory) {
        TsscStory story = null;
        ResponseEntity<TsscStory> responseStory = restTemplate.getForEntity(LOCAL_URL + "api/tsscstories/" + idStory, TsscStory.class);
        if (responseStory.getStatusCode() == HttpStatus.OK)
            story = responseStory.getBody();
        return story;
    }

    @Override
    public Iterable<TsscStory> findAllStories() {
        TsscStory[] stories = null;
        List<TsscStory> storiesIt;
        ResponseEntity<TsscStory[]> responseStories = restTemplate.getForEntity(LOCAL_URL + "api/tsscstories/", TsscStory[].class);
        if (responseStories.getStatusCode() == HttpStatus.OK) {
            stories = responseStories.getBody();
        }
        storiesIt = Arrays.asList(stories);

        return storiesIt;
    }

    @Override
    public Iterable<TsscStory> findStoriesByGameId(long gameId) {
        TsscStory[] stories = null;
        List<TsscStory> storiesIt;
        ResponseEntity<TsscStory[]> responseStories = restTemplate.getForEntity(LOCAL_URL + "api/tsscstories/game/"+gameId, TsscStory[].class);
        if (responseStories.getStatusCode() == HttpStatus.OK) {
            stories = responseStories.getBody();
        }
        storiesIt = Arrays.asList(stories);

        return storiesIt;
    }

    @Override
    public TsscStory saveStory(TsscStory story, long id) {
        TsscStory response = null;
        ResponseEntity<TsscStory> responseStory = restTemplate.postForEntity(LOCAL_URL + "api/tsscstories/game/"+id, story, TsscStory.class);
        if (responseStory.getStatusCode() == HttpStatus.OK) {
            response = responseStory.getBody();
        }

        return response;
    }

    @Override
    public void editStory(long id, TsscStory story) {
    	restTemplate.put(LOCAL_URL + "api/tsscstories/"+id, story);
    }

    @Override
    public void deleteStory(long id) {
        restTemplate.delete(LOCAL_URL + "api/tsscstories/"+ id);
    }

    //TsscTimecontrol---------------------------------------------------------------------------------------------------

    @Override
    public Iterable<TsscTimecontrol> findAllTimeControlByGameId(long gameId) {
    	TsscTimecontrol[] timecontrols = null;
        List<TsscTimecontrol> timecontrolIt;
        ResponseEntity<TsscTimecontrol[]> responseTimecontrol = restTemplate.getForEntity(LOCAL_URL + "api/tssctimecontrol/game/"+gameId, TsscTimecontrol[].class);
        if (responseTimecontrol.getStatusCode() == HttpStatus.OK) {
        	timecontrols = responseTimecontrol.getBody();
        }
        timecontrolIt = Arrays.asList(timecontrols);

        return timecontrolIt;
    }
	@Override
	public TsscTimecontrol getTimeControl(long idTimecontrol) {
		TsscTimecontrol timecontrol = null;
        ResponseEntity<TsscTimecontrol> responseTimecontrol = restTemplate.getForEntity(LOCAL_URL + "api/tssctimecontrol/" + idTimecontrol, TsscTimecontrol.class);
        if (responseTimecontrol.getStatusCode() == HttpStatus.OK)
        	timecontrol = responseTimecontrol.getBody();
        return timecontrol;
	}

	@Override
	public TsscTimecontrol saveTimeControl(TsscTimecontrol timecontrol, long gameId) {
		TsscTimecontrol response = null;
        ResponseEntity<TsscTimecontrol> responseStory = restTemplate.postForEntity(LOCAL_URL + "api/tssctimecontrol/game/"+gameId, timecontrol, TsscTimecontrol.class);
        if (responseStory.getStatusCode() == HttpStatus.OK) {
            response = responseStory.getBody();
        }

        return response;
	}

	@Override
	public void editTimeControl(long id, TsscTimecontrol timecontrol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletTimeControl(long id) {
		// TODO Auto-generated method stub
		
	}

}