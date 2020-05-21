package com.taller.castillo.felipe.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taller.castillo.felipe.Taller2Application;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTimecontrol;
import com.taller.castillo.felipe.model.TsscTopic;


@SpringBootTest("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
class TsscGameDaoTest {
	
	@Autowired
	private TsscGameDao gameDao;
	@Autowired
	private TsscTopicDao topicDao;	
	@Autowired
	private TsscStoryDao storyDao;	
	@Autowired
	private TsscTimeControlDao timeControlDao;
	
	public TsscGame setUp() {
		TsscGame game = new TsscGame();        
		game.setNGroups(8);
		game.setNSprints(10);
		game.setName("game");
		game.setScheduledDate(LocalDate.of(2019, Month.JANUARY, 14));
		game.setScheduledTime(LocalTime.of(01,00));
		TsscTopic topic = new TsscTopic();
		topic.setDescription("Topic description");
		topic.setGroupPrefix("TD");
		topic.setName("Topic");
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		topicDao.save(topic);
		game.setTsscTopic(topic);
		gameDao.save(game);
		return game;
	}
	public TsscGame setUp1() {
		TsscGame game = new TsscGame();        
		game.setNGroups(8);
		game.setNSprints(10);
		game.setName("game1");
		game.setScheduledDate(LocalDate.of(2019, Month.JANUARY, 14));
		game.setScheduledTime(LocalTime.of(05,00));
		TsscTopic topic = new TsscTopic();
		topic.setDescription("Topic description");
		topic.setGroupPrefix("TD");
		topic.setName("Topic");
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		topicDao.save(topic);
		game.setTsscTopic(topic);
		gameDao.save(game);
		return game;
	}
	public TsscGame setUp2() {
		TsscGame game = new TsscGame();        
		game.setNGroups(8);
		game.setNSprints(10);
		game.setName("game2");
		game.setScheduledDate(LocalDate.of(2019, Month.JANUARY, 30));
		game.setScheduledTime(LocalTime.of(23,00));
		TsscTopic topic = new TsscTopic();
		topic.setDescription("Topic description");
		topic.setGroupPrefix("TD");
		topic.setName("Topic");
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		topicDao.save(topic);
		game.setTsscTopic(topic);
		gameDao.save(game);
		return game;
	}
	public TsscGame setUp3() {
		TsscGame game = new TsscGame();        
		game.setNGroups(8);
		game.setNSprints(10);
		game.setName("game3");
		game.setScheduledDate(LocalDate.of(2019, Month.DECEMBER, 04));
		game.setScheduledTime(LocalTime.of(01,00));
		TsscTopic topic = new TsscTopic();
		topic.setDescription("Topic description");
		topic.setGroupPrefix("TD");
		topic.setName("Topic1");
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		topicDao.save(topic);
		game.setTsscTopic(topic);
		gameDao.save(game);
		TsscGame game1 = new TsscGame();        
		game1.setNGroups(8);
		game1.setNSprints(10);
		game1.setName("game3");
		game1.setScheduledDate(LocalDate.of(2019, Month.DECEMBER, 04));
		game1.setScheduledTime(LocalTime.of(02,00));
		TsscTopic topic1 = new TsscTopic();
		topic1.setDescription("Topic description");
		topic1.setGroupPrefix("TD");
		topic1.setName("Topic2");
		topic1.setDefaultGroups(1);
		topic1.setDefaultSprints(1);
		topicDao.save(topic1);
		game1.setTsscTopic(topic1);
		gameDao.save(game1);
		TsscGame game2 = new TsscGame();        
		game2.setNGroups(8);
		game2.setNSprints(10);
		game2.setName("game3");
		game2.setScheduledDate(LocalDate.of(2019, Month.DECEMBER, 04));
		game2.setScheduledTime(LocalTime.of(04,00));
		TsscTopic topic2 = new TsscTopic();
		topic2.setDescription("Topic description");
		topic2.setGroupPrefix("TD");
		topic2.setName("Topic3");
		topic2.setDefaultGroups(1);
		topic2.setDefaultSprints(1);
		topicDao.save(topic2);
		game2.setTsscTopic(topic2);
		gameDao.save(game2);
		TsscGame game3 = new TsscGame();        
		game3.setNGroups(8);
		game3.setNSprints(10);
		game3.setName("game3");
		game3.setScheduledDate(LocalDate.of(2019, Month.DECEMBER, 04));
		game3.setScheduledTime(LocalTime.of(03,00));
		TsscTopic topic3 = new TsscTopic();
		topic3.setDescription("Topic description");
		topic3.setGroupPrefix("TD");
		topic3.setName("Topic4");
		topic3.setDefaultGroups(1);
		topic3.setDefaultSprints(1);
		topicDao.save(topic3);
		game3.setTsscTopic(topic3);
		gameDao.save(game3);
		return game;
	}
	public TsscGame setUp4() {
		TsscGame game = new TsscGame();       
		List <TsscStory> listStories = new ArrayList<>();
		List <TsscTimecontrol> listTime = new ArrayList<>();
		game.setNGroups(8);
		game.setNSprints(10);
		game.setName("Game with story");
		game.setScheduledDate(LocalDate.of(2019, Month.JANUARY, 01));
		game.setScheduledTime(LocalTime.of(23,00));
		TsscStory story = new TsscStory();
		story.setDescription("Topic description");
		story.setBusinessValue(new BigDecimal(4));
		story.setInitialSprint(new BigDecimal(4));
		story.setPriority(new BigDecimal(4));		
		storyDao.save(story);
		story = storyDao.update(story);
		listStories.add(story);
		listStories.add(story);
		listStories.add(story);
		listStories.add(story);
		listStories.add(story);
		listStories.add(story);
		listStories.add(story);
		listStories.add(story);
		
		gameDao.save(game);
		

		
		
		
		return game;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar juego por el nombre")
	@Test
	public void testFindGameByName() {
		TsscGame game =setUp();
		String name = game.getName();		
		assertEquals(gameDao.findByName(name).get(0).getName(), name);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar juego por la descripcion del tema")
	@Test
	public void testFindGameByDescription() {
		TsscGame game =setUp();
		String description = game.getTsscTopic().getDescription();		
		assertEquals(gameDao.findByDescription(description).get(0).getName(), game.getName());
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar juego por el id del tema")
	@Test
	public void testFindGameById() {
		TsscGame game =setUp();
		long id = game.getTsscTopic().getId();		
		assertEquals(gameDao.findByIdTopic(id).get(0).getName(), game.getName());
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar juego por un rango de fecha")
	@Test
	public void testFindGameByDateRange() {		
		gameDao.deleteAll();
		setUp();
		setUp1();
		setUp2();		
		assertEquals(gameDao.findByDateRange(LocalDate.of(2019, Month.JANUARY, 13), LocalDate.of(2019, Month.JANUARY, 29)).size(), 2);		
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar juego por una fecha y rango de horas")
	@Test
	public void testFindGameByDateHourRange() {		
		gameDao.deleteAll();
		setUp();
		setUp1();
		setUp2();		
		assertEquals(gameDao.findByDateHourRange(LocalDate.of(2019, Month.JANUARY, 14), LocalTime.of(01,00), LocalTime.of(05,00)).size(), 2);		
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar los temas de juegos dada una fecha")
	@Test
	public void testFindTopicByDate() {
		gameDao.deleteAll();
		setUp3();
		List<Object[]> list = gameDao.findTopicByDate(LocalDate.of(2019, Month.DECEMBER, 04));
		long cantidadJuegos =  (long) list.get(0)[1];
		assertEquals(cantidadJuegos, 4);		
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar los juegos para una fecha pero tienen menos de diez historias asociadas")
	@Test
	public void testFindTopicByDateWithStories() {		
		setUp4();
		List<TsscGame> list = gameDao.findGamesWithStories(LocalDate.of(2019, Month.JANUARY, 01));

		assertEquals(1, list.size());
		assertEquals("Game with story", list.get(0).getName());
		
		
	}
	
}
