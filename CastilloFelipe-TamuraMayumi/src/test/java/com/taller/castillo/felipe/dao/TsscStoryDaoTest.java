package com.taller.castillo.felipe.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
import com.taller.castillo.felipe.model.TsscTopic;

@TestMethodOrder(Alphanumeric.class)
@SpringBootTest("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
class TsscStoryDaoTest {
	
	@Autowired
	private TsscStoryDao storyDao;	
	
	public TsscStory setUp() {
		TsscStory story = new TsscStory();   
		story.setDescription("Description story");
		story.setInitialSprint(new BigDecimal(4));
		story.setBusinessValue(new BigDecimal(4));
		story.setPriority(new BigDecimal(4));
		storyDao.save(story);
		
		return story;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Crear story")
	@Test
	public void testACreateStory() {
		TsscStory story = new TsscStory();   
		story.setDescription("Description story created");
		story.setInitialSprint(new BigDecimal(4));
		story.setBusinessValue(new BigDecimal(4));
		story.setPriority(new BigDecimal(4));
		storyDao.save(story);
		
		assertEquals(storyDao.findAll().size(), 1);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar story por el id")
	@Test
	public void testBFindStoryById() {		
		TsscStory story =setUp();

			
		assertEquals(storyDao.findById(2).getDescription(), story.getDescription());
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Editar story")
	@Test
	public void testCUpdateStory() {		
		setUp();
		TsscStory story = storyDao.findById(1);
		story.setDescription("edited");
		TsscStory update = storyDao.update(story);
		assertEquals(update.getDescription(), story.getDescription());
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar todas las historias")
	@Test
	public void testDFindAllStory() {		
		List<TsscStory> story =storyDao.findAll();
			
		assertEquals(story.size(), 3);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Eliminar una historia")
	@Test
	public void testEDeleteStory() {		
		List<TsscStory> list = storyDao.findAll();
		TsscStory story = list.get(0);
		storyDao.delete(story);
		assertEquals(storyDao.findAll().size(), list.size()-1);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Eliminar todas las historias")
	@Test
	public void testFDeletAll() {		
		storyDao.deleteAll();
			
		assertTrue(storyDao.findAll().isEmpty());
	}
	
	
	
	

	
}
