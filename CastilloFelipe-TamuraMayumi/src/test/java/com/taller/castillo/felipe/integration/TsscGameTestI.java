package com.taller.castillo.felipe.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.NullTopicException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.repository.TsscGameRepository;
import com.taller.castillo.felipe.repository.TsscTopicRepository;
import com.taller.castillo.felipe.service.TsscGameService;
import com.taller.castillo.felipe.service.TsscTopicService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TsscGameTestI {

	
	private TsscTopicService topicService;	
	private TsscGameService gameService;
	private TsscGame game;
	private TsscTopic topic;
	@Autowired
	public TsscGameTestI(TsscTopicService topicService, TsscGameService gameService) {
		super();
		this.topicService = topicService;
		this.gameService = gameService;
	}
	
	public void setUp() {
		game = new TsscGame();

		game.setNGroups(8);
		game.setNSprints(10);
		game.setName("game");
		topic = new TsscTopic();		
		topic.setDefaultGroups(12);
		topic.setDefaultSprints(8);
		try {
			game = gameService.createGame(game);
			topic = topicService.createTopic(topic);
		} catch (ZeroGroupSprintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DisplayName("Crear juego sin tema")
	@Test
	public void testCreateGame() {
		TsscGame game = new TsscGame();
		game.setNGroups(5);
		game.setNSprints(7);
		game.setName("game created");
		try {
			assertTrue(gameService.createGame(game) == game);
		} catch (ZeroGroupSprintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@DisplayName("Crear juego con tema")
	@Test
	public void testCreateGameWithTopic() {
//		gameService.deleteAll();
//		setUp();
		TsscGame game= new TsscGame();
		TsscTopic topic = new TsscTopic();
		game.setNGroups(8);
		game.setNSprints(10);
		topic = new TsscTopic();		
		topic.setDefaultGroups(12);
		topic.setDefaultSprints(8);
		game.setName("game created");
		
		try {		
			topic = topicService.createTopic(topic);
			TsscGame cGame = gameService.createGameWithTopic(game, topic.getId());
			assertTrue(cGame.getTsscTopic().getId() == topic.getId());
		} catch (ZeroGroupSprintException | NullTopicException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	


	@DisplayName("Editar juego correcto")
	@Test
	public void testEditGame() {
		setUp();
		try {
			game.setName("game edited");
			assertTrue(gameService.editGame(game).getName() == game.getName());
		} catch (ZeroGroupSprintException | EditException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}
