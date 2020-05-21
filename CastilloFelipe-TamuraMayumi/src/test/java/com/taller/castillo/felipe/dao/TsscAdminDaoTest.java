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
import com.taller.castillo.felipe.model.TsscAdmin;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTopic;

@TestMethodOrder(Alphanumeric.class)
@SpringBootTest("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
class TsscAdminDaoTest {
	
	@Autowired
	private TsscAdminDao adminDao;	
	
	public TsscAdmin setUp() {
		TsscAdmin admin = new TsscAdmin();   
		admin.setUsername("felipe");
		admin.setPassword("1234");
		adminDao.save(admin);
		
		return admin;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Crear admin")
	@Test
	public void testACreateStory() {
		TsscAdmin admin = new TsscAdmin();   
		admin.setUsername("felipe");
		admin.setPassword("1234");
		adminDao.save(admin);
		
		assertEquals(adminDao.findAll().size(), 1);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar story por el id")
	@Test
	public void testBFindStoryById() {		
		TsscAdmin admin =setUp();

			
		assertEquals(adminDao.findById(2).getUsername(), admin.getUsername());
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Editar admin")
	@Test
	public void testCUpdateStory() {		
		setUp();
		TsscAdmin admin = adminDao.findById(1);
		admin.setUsername("felipeEdited");
		TsscAdmin update = adminDao.update(admin);
		assertEquals(update.getUsername(), admin.getUsername());
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar todas las historias")
	@Test
	public void testDFindAllStory() {		
		List<TsscAdmin> admin = adminDao.findAll();
			
		assertEquals(admin.size(), 3);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Eliminar una historia")
	@Test
	public void testEDeleteStory() {		
		List<TsscAdmin> list = adminDao.findAll();
		TsscAdmin admin = list.get(0);
		adminDao.delete(admin);
		assertEquals(adminDao.findAll().size(), list.size()-1);
	}
	
	
	
	
	
	

	
}
