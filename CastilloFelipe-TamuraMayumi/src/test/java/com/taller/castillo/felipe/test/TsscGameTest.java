package com.taller.castillo.felipe.test;

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

import com.taller.castillo.felipe.dao.TsscGameDao;
import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.NullTopicException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.repository.TsscGameRepository;
import com.taller.castillo.felipe.repository.TsscTopicRepository;
import com.taller.castillo.felipe.service.TsscGameService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
class TsscGameTest {
	
	@Mock
	private TsscGameDao gameDao;
	
	@Mock
	private TsscGameRepository gameRepository;

	@Mock
	private TsscTopicRepository topicRepository;

	@InjectMocks
	private TsscGameService gameService;

	@Autowired
	public TsscGameTest(TsscGameService gameService) {
		super();
		this.gameService = gameService;
	}

	@DisplayName("Crear juego correcto sin tema")
	@Test
	public void testCreateGame() {
		TsscGame game = new TsscGame();
		game.setNGroups(5);
		game.setNSprints(7);
		try {
			when(gameRepository.save(game)).thenReturn(game);
			assertTrue(gameService.createGame(game) != null);
		} catch (ZeroGroupSprintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DisplayName("Crear juego correcto sin tema, con 0 grupos  y  0 sprints")
	@Test
	public void testCreateGameZeroGroupSprint() {
		TsscGame game = new TsscGame();
		game.setNGroups(0);
		game.setNSprints(0);
		when(gameRepository.save(game)).thenReturn(null);
		assertThrows(ZeroGroupSprintException.class, () -> gameService.createGame(game));

	}

	@DisplayName("Crear juego sin tema, con 0 grupos")
	@Test
	public void testCreateGameZeroGroup() {
		TsscGame game = new TsscGame();
		game.setNGroups(0);
		game.setNSprints(7);
		when(gameRepository.save(game)).thenReturn(null);
		assertThrows(ZeroGroupSprintException.class, () -> gameService.createGame(game));

	}

	@DisplayName("Crear juego sin tema, con 0 sprints")
	@Test
	public void testCreateGameZeroSprint() {
		TsscGame game = new TsscGame();
		game.setNGroups(5);
		game.setNSprints(0);
		when(gameRepository.save(game)).thenReturn(null);
		assertThrows(ZeroGroupSprintException.class, () -> gameService.createGame(game));

	}

	@DisplayName("Crear juego correcto con tema existente")
	@Test
	public void testCreateGameWithTopic() {
		TsscGame game = new TsscGame();
		TsscTopic topic = new TsscTopic();
		game.setNGroups(5);
		game.setNSprints(7);
		try {
			long id = 1;
			Optional<TsscTopic> topic2 = Optional.of(topic);
			when(topicRepository.findById(id)).thenReturn(topic2);
			when(gameRepository.save(game)).thenReturn(game);
			assertTrue(gameService.createGame(game) != null);
		} catch (ZeroGroupSprintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DisplayName("Crear juego correcto con tema inexistente")
	@Test
	public void testCreateGameWithTopicNull() {
		TsscGame game = new TsscGame();
		TsscTopic topic = new TsscTopic();
		game.setNGroups(5);
		game.setNSprints(7);
		long id = 1;
		when(topicRepository.findById(id)).thenReturn(Optional.empty());
		when(gameRepository.save(game)).thenReturn(null);
		assertThrows(NullTopicException.class, () -> gameService.createGameWithTopic(game, topic.getId()));

	}

	@DisplayName("Crear juego correcto con tema existente,  con 0 grupos  y  0 sprints")
	@Test
	public void testCreateGameWithTopicZeroGroupSprint() {
		TsscGame game = new TsscGame();
		game.setNGroups(0);
		game.setNSprints(0);
		TsscTopic topic = new TsscTopic();
		Optional<TsscTopic> topic2 = Optional.of(topic);
		long id = 1;
		when(topicRepository.findById(id)).thenReturn(topic2);
		when(gameRepository.save(game)).thenReturn(null);
		assertThrows(ZeroGroupSprintException.class, () -> gameService.createGame(game));

	}

	@DisplayName("Crear juego correcto con tema existente,  con 0 grupos ")
	@Test
	public void testCreateGameWithTopicZeroGroup() {
		TsscGame game = new TsscGame();
		game.setNGroups(0);
		game.setNSprints(7);
		TsscTopic topic = new TsscTopic();
		Optional<TsscTopic> topic2 = Optional.of(topic);
		when(topicRepository.findById(topic.getId())).thenReturn(topic2);
		when(gameRepository.save(game)).thenReturn(null);
		assertThrows(ZeroGroupSprintException.class, () -> gameService.createGame(game));

	}

	@DisplayName("Crear juego correcto con tema existente,  con 0 sprints")
	@Test
	public void testCreateGameWithTopicZeroSprint() {
		TsscGame game = new TsscGame();
		game.setNGroups(7);
		game.setNSprints(0);
		TsscTopic topic = new TsscTopic();
		Optional<TsscTopic> topic2 = Optional.of(topic);
		long id = 1;
		when(topicRepository.findById(id)).thenReturn(topic2);
		when(gameRepository.save(game)).thenReturn(null);
		assertThrows(ZeroGroupSprintException.class, () -> gameService.createGame(game));

	}

	@DisplayName("Editar juego correcto")
	@Test
	public void testEditGame() {
		TsscGame game = new TsscGame();
		game.setNGroups(5);
		game.setNSprints(7);
		try {
			Optional<TsscGame> game2 = Optional.of(new TsscGame());
			when(gameRepository.findById(game.getId())).thenReturn(game2);
			when(gameRepository.save(game)).thenReturn(game);
			assertTrue(gameService.editGame(game) != null);
		} catch (ZeroGroupSprintException | EditException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@DisplayName("Editar juego, grupos 0")
	@Test
	public void testEditGameZeroGroup() {
		TsscGame game =  new TsscGame();
		game.setNGroups(0);
		game.setNSprints(7);
		Optional<TsscGame> game2 = Optional.of(new TsscGame());

		when(gameDao.findById(game.getId())).thenReturn(game);
		//when(gameRepository.save(game)).thenReturn(game);
		assertThrows(ZeroGroupSprintException.class, () -> gameService.editGame(game));

	}
	@DisplayName("Editar juego, sprint 0")
	@Test
	public void testEditGameZeroSprint() {
		TsscGame game =  new TsscGame();
		game.setNGroups(5);
		game.setNSprints(0);
		Optional<TsscGame> game2 = Optional.of(new TsscGame());

		when(gameRepository.findById(game.getId())).thenReturn(game2);
		when(gameRepository.save(game)).thenReturn(null);
		assertThrows(ZeroGroupSprintException.class, () -> gameService.editGame(game));

	}

}
