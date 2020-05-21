package com.taller.castillo.felipe.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taller.castillo.felipe.Taller2Application;
import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.repository.TsscTopicRepository;
import com.taller.castillo.felipe.service.TsscTopicService;

@SpringBootTest("/applicationContext.xml")
@RunWith(SpringRunner.class)
@Rollback(false)
class TsscTopicDaoTest {

	
	
	@Autowired
	private TsscTopicDao topicDao;

	public TsscTopic setUp() {
		TsscTopic topic = new TsscTopic();
		topic.setDescription("Topic description");
		topic.setGroupPrefix("TD");
		topic.setName("Topic");
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		topicDao.save(topic);
		return topic;		
	}
	public TsscTopic setUp1() {
		TsscTopic topic = new TsscTopic();
		topic.setDescription("Topic description");
		topic.setGroupPrefix("TD");
		topic.setName("Topic");
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		topicDao.save(topic);
		return topic;		
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Crear topic")
	@Test
	public void testCreateTopic() {			
		TsscTopic ntopic = new TsscTopic();	
		ntopic.setDescription("Topic description created");
		ntopic.setGroupPrefix("TD");
		ntopic.setName("New topic");
		ntopic.setDefaultGroups(1);
		ntopic.setDefaultSprints(1);
		ntopic.setDefaultGroups(12);
		ntopic.setDefaultSprints(8);		
		topicDao.save(ntopic);
		assertNotNull(topicDao.findByName(ntopic.getName()));

	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Editar topic")
	@Test
	public void testEditTopic() {
		TsscTopic topic = setUp();
		topic.setName("Topic edited");
		topicDao.update(topic);
		assertEquals(topicDao.findByName(topic.getName()).get(0).getName(), "Topic edited");
		

	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Eliminar topic")
	@Test
	public void testDeleteTopic() {
		TsscTopic topic = setUp();
		topicDao.delete(topic);
		
		assertTrue(topicDao.findByName(topic.getName()).isEmpty());
		

	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar topic por el nombre")
	@Test
	public void testFindTopicByName() {
		TsscTopic created = setUp();
		
		assertEquals(topicDao.findByName(created.getName()).get(0).getName(), created.getName());

	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@DisplayName("Buscar topic por la descripción")
	@Test
	public void testFindTopicByDescription() {
		TsscTopic created = setUp();
		
		assertEquals(topicDao.findByDescription(created.getDescription()).get(0).getDescription(), created.getDescription());

	}
	
	

}
