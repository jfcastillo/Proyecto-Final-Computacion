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
import com.taller.castillo.felipe.model.TsscTimecontrol;
import com.taller.castillo.felipe.model.TsscTopic;

@TestMethodOrder(Alphanumeric.class)
@SpringBootTest("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
class TsscTimeControlDaoTest {
	
	@Autowired
	private TsscTimeControlDao timeControlDao;	
	
	public TsscTimecontrol setUp() {
		TsscTimecontrol timeControl = new TsscTimecontrol();   
		timeControl.setAutostart("yes");
		timeControl.setName("Control");
		timeControlDao.save(timeControl);
		
		return timeControl;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Crear time control")
	@Test
	public void testACreateTimeControl() {
		TsscTimecontrol timeControl = new TsscTimecontrol();   
		timeControl.setAutostart("yes");
		timeControl.setName("created");
		timeControlDao.save(timeControl);
		
		assertEquals(timeControlDao.findAll().size(), 1);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar time control por el id")
	@Test
	public void testBFindStoryById() {		
		TsscTimecontrol timeControl =setUp();

			
		assertEquals(timeControlDao.findById(2).getName(), timeControl.getName());
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Editar time control")
	@Test
	public void testCUpdateStory() {		
		setUp();
		TsscTimecontrol timeControl = timeControlDao.findById(1);
		timeControl.setName("edited");
		TsscTimecontrol update = timeControlDao.update(timeControl);
		assertEquals(update.getName(), timeControl.getName());
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar todas las time control")
	@Test
	public void testDFindAllStory() {		
		List<TsscTimecontrol> timeControl =timeControlDao.findAll();
			
		assertEquals(timeControl.size(), 3);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Eliminar una time control")
	@Test
	public void testEDeleteStory() {		
		List<TsscTimecontrol> list = timeControlDao.findAll();
		TsscTimecontrol timeControl = list.get(0);
		timeControlDao.delete(timeControl);
		assertEquals(timeControlDao.findAll().size(), list.size()-1);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Eliminar todas las time control")
	@Test
	public void testFDeletAll() {		
		timeControlDao.deleteAll();
			
		assertTrue(timeControlDao.findAll().isEmpty());
	}
	
	
	
	

	
}
