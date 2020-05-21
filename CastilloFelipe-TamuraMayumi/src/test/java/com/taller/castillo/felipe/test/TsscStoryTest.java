package com.taller.castillo.felipe.test;

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
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
class TsscStoryTest {
	@Mock
	private TsscStoryRepository storyRepository;

	@Mock
	private TsscGameRepository gameRepository;

	@InjectMocks
	private TsscStoryService storyService;

	@Autowired
	public TsscStoryTest(TsscStoryService storyService) {
		super();
		this.storyService = storyService;
	}

	@DisplayName("Crear historia correcta con juego existente")
	@Test
	public void testCreateStory() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(5));
		story.setInitialSprint(new BigDecimal(8));
		story.setPriority(new BigDecimal(11));
		long id = 1;

		try {
			Optional<TsscGame> game = Optional.of(new TsscGame());
			when(gameRepository.findById(id)).thenReturn(game);
			when(storyRepository.save(story)).thenReturn(story);
			assertTrue(storyService.createStory(story, id) != null);
		} catch (StoryException | NullGameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@DisplayName("Crear historia correcta con juego inexistente")
	@Test
	public void testCreateStoryNullGame() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(5));
		story.setInitialSprint(new BigDecimal(8));
		story.setPriority(new BigDecimal(11));
		long id = 1;
		when(gameRepository.findById(id)).thenReturn(Optional.empty());
		when(storyRepository.save(story)).thenReturn(story);
		assertThrows(NullGameException.class, () -> storyService.createStory(story, id));

	}

	@DisplayName("Crear historia correcta con juego existente y negocio 0")
	@Test
	public void testCreateStoryZeroBussiness() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(0));
		story.setInitialSprint(new BigDecimal(8));
		story.setPriority(new BigDecimal(11));
		long id = 1;

		Optional<TsscGame> game = Optional.of(new TsscGame());
		when(gameRepository.findById(id)).thenReturn(game);
		when(storyRepository.save(story)).thenReturn(null);
		assertThrows(StoryException.class, () -> storyService.createStory(story, id));

	}

	@DisplayName("Crear historia correcta con juego existente y sprit inicial 0")
	@Test
	public void testCreateStoryZeroSprint() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(6));
		story.setInitialSprint(new BigDecimal(0));
		story.setPriority(new BigDecimal(11));
		long id = 1;

		Optional<TsscGame> game = Optional.of(new TsscGame());
		when(gameRepository.findById(id)).thenReturn(game);
		when(storyRepository.save(story)).thenReturn(null);
		assertThrows(StoryException.class, () -> storyService.createStory(story, id));

	}

	@DisplayName("Crear historia correcta con juego existente y prioridad 0")
	@Test
	public void testCreateStoryZeroPriority() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(6));
		story.setInitialSprint(new BigDecimal(11));
		story.setPriority(new BigDecimal(0));
		long id = 1;

		Optional<TsscGame> game = Optional.of(new TsscGame());
		when(gameRepository.findById(id)).thenReturn(game);
		when(storyRepository.save(story)).thenReturn(null);
		assertThrows(StoryException.class, () -> storyService.createStory(story, id));

	}
	
	@DisplayName("Editar historia correcta")
	@Test
	public void testEditGame() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(5));
		story.setInitialSprint(new BigDecimal(8));
		story.setPriority(new BigDecimal(11));
		try {
			Optional<TsscStory> story2 = Optional.of(story);
			when(storyRepository.findById(story.getId())).thenReturn(story2);
			when(storyRepository.save(story)).thenReturn(story);
			assertTrue(storyService.editStory(story) != null);
		} catch (EditException | StoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@DisplayName("Editar historia correcta con juego existente y negocio 0")
	@Test
	public void testEditStoryZeroBussiness() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(0));
		story.setInitialSprint(new BigDecimal(8));
		story.setPriority(new BigDecimal(11));
		long id = 1;

		Optional<TsscGame> game = Optional.of(new TsscGame());
		when(gameRepository.findById(id)).thenReturn(game);
		when(storyRepository.save(story)).thenReturn(null);
		assertThrows(StoryException.class, () -> storyService.editStory(story));

	}

	@DisplayName("Editar historia correcta con juego existente y sprit inicial 0")
	@Test
	public void testEditStoryZeroSprint() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(6));
		story.setInitialSprint(new BigDecimal(0));
		story.setPriority(new BigDecimal(11));
		long id = 1;

		Optional<TsscGame> game = Optional.of(new TsscGame());
		when(gameRepository.findById(id)).thenReturn(game);
		when(storyRepository.save(story)).thenReturn(null);
		assertThrows(StoryException.class, () -> storyService.editStory(story));

	}

	@DisplayName("Editar historia correcta con juego existente y prioridad 0")
	@Test
	public void testEditStoryZeroPriority() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(new BigDecimal(6));
		story.setInitialSprint(new BigDecimal(11));
		story.setPriority(new BigDecimal(0));
		long id = 1;

		Optional<TsscGame> game = Optional.of(new TsscGame());
		when(gameRepository.findById(id)).thenReturn(game);
		when(storyRepository.save(story)).thenReturn(null);
		assertThrows(StoryException.class, () -> storyService.editStory(story));

	}

}
