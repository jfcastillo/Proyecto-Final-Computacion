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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.repository.TsscTopicRepository;
import com.taller.castillo.felipe.service.TsscTopicService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)

class TsscTopicTest {

	@Mock
	private TsscTopicRepository topicRepository;
	@InjectMocks
	private TsscTopicService topicService;

	@Autowired
	public TsscTopicTest(TsscTopicService topicService) {
		this.topicService = topicService;
	}
	
	
	@DisplayName("Crear topic correcto")
	@Test
	public void testCreateTopic() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(1);
		try {
			when(topicRepository.save(topic)).thenReturn(topic);
			assertTrue(topicService.createTopic(topic) != null);
		} catch (ZeroGroupSprintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@DisplayName("Crear topic con 0 grupos y  0 sprints")
	@Test
	public void testCreateTopicZeroGroupSprint() {
		TsscTopic topic = new TsscTopic();
		when(topicRepository.save(topic)).thenReturn(null);
		assertThrows(ZeroGroupSprintException.class, () -> topicService.createTopic(topic));

	}
	@DisplayName("Crear topic con 0 sprints")
	@Test
	public void testCreateTopicZeroSprint() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(1);
		when(topicRepository.save(topic)).thenReturn(null);
		assertThrows(ZeroGroupSprintException.class, () -> topicService.createTopic(topic));

	}
	@DisplayName("Crear topic con 0 grupos")
	@Test
	public void testCreateTopicZeroGroup() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultSprints(1);
		when(topicRepository.save(topic)).thenReturn(null);
		assertThrows(ZeroGroupSprintException.class, () -> topicService.createTopic(topic));

	}
	@DisplayName("Editar topic correcto")
	@Test
	public void testEditTopic() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(3);
		topic.setDefaultSprints(8);
		try {
			Optional<TsscTopic> topic2 = Optional.of(topic);
			when(topicRepository.save(topic)).thenReturn(topic);
			when(topicRepository.findById(topic.getId())).thenReturn(topic2);
			assertTrue(topicService.editTopic(topic) != null);
		} catch (EditException | ZeroGroupSprintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@DisplayName("Editar topic con 0 grupos y  0 sprints")
	@Test
	public void testEditTopicZeroGroupSprint() {

		TsscTopic topic = new TsscTopic();

		Optional<TsscTopic> topic2 = Optional.of(topic);
		when(topicRepository.save(topic)).thenReturn(topic);
		when(topicRepository.findById(topic.getId())).thenReturn(topic2);
		assertThrows(ZeroGroupSprintException.class, () -> topicService.editTopic(topic));

	}
	@DisplayName("Editar topic con 0 grupos")
	@Test
	public void testEditTopicZeroGroup() {

		TsscTopic topic = new TsscTopic();
		topic.setDefaultSprints(8);
		Optional<TsscTopic> topic2 = Optional.of(topic);
		when(topicRepository.save(topic)).thenReturn(topic);
		when(topicRepository.findById(topic.getId())).thenReturn(topic2);
		assertThrows(ZeroGroupSprintException.class, () -> topicService.editTopic(topic));

	}
	@DisplayName("Editar topic con 0 sprints")
	@Test
	public void testEditTopicZeroSprint() {

		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(8);
		Optional<TsscTopic> topic2 = Optional.of(topic);
		when(topicRepository.save(topic)).thenReturn(topic);
		when(topicRepository.findById(topic.getId())).thenReturn(topic2);
		assertThrows(ZeroGroupSprintException.class, () -> topicService.editTopic(topic));

	}

}
