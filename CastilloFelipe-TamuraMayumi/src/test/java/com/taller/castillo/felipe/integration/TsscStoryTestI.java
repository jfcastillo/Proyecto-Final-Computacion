package com.taller.castillo.felipe.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.NullGameException;
import com.taller.castillo.felipe.exception.StoryException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.repository.TsscGameRepository;
import com.taller.castillo.felipe.repository.TsscStoryRepository;
import com.taller.castillo.felipe.repository.TsscTopicRepository;
import com.taller.castillo.felipe.service.TsscGameService;
import com.taller.castillo.felipe.service.TsscStoryService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TsscStoryTestI {
	
	private TsscGameService gameService;
	
	private TsscStoryService storyService;
	
	private TsscStory story;

	@Autowired
	public TsscStoryTestI(TsscGameService gameService, TsscStoryService storyService) {
		super();
		this.gameService = gameService;
		this.storyService = storyService;
	}

	public void setUp() {
		TsscGame game = new TsscGame();
		game.setNGroups(8);
		game.setNSprints(10);
		game.setName("game");
		
		story = new TsscStory();		
		story.setBusinessValue(new BigDecimal(5));
		story.setInitialSprint(new BigDecimal(8));
		story.setPriority(new BigDecimal(11));
		try {
			game = gameService.createGame(game);
			story = storyService.createStory(story, game.getId());
		} catch (ZeroGroupSprintException | StoryException | NullGameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@DisplayName("Crear historia correcta con juego existente")
	@Test
	public void testCreateStoryWithGame() {
		TsscGame game = new TsscGame();
		game.setNGroups(8);
		game.setNSprints(10);
		game.setName("game");
		
		TsscStory story = new TsscStory();		
		story.setBusinessValue(new BigDecimal(5));
		story.setInitialSprint(new BigDecimal(8));
		story.setPriority(new BigDecimal(11));
		story.setDescription("story");

		try {
			TsscGame newGame = gameService.createGame(game);
			TsscStory newStory = storyService.createStory(story, newGame.getId());
			assertTrue(newStory.getTsscGame().getId() == newGame.getId());
		} catch (StoryException | NullGameException | ZeroGroupSprintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	@DisplayName("Editar historia")
	@Test
	public void testEditStory() {
		setUp();
		story.setDescription("description edited");
		
		try {
			TsscStory newStory =  storyService.editStory(story);
			assertTrue(newStory.getDescription().equals(story.getDescription()));
		} catch (EditException | StoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
