package com.taller.castillo.felipe.integration;

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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.repository.TsscTopicRepository;
import com.taller.castillo.felipe.service.TsscTopicService;

@SpringBootTest
@RunWith(SpringRunner.class)
class TsscTopicTest {


    @Autowired
    private TsscTopicService topicService;

    private TsscTopic topic;


//	public TsscTopicTest(TsscTopicService topicService) {
//		this.topicService = topicService;
//	}

    public void setUp() {
        topic = new TsscTopic();
        topic.setDescription("Topic description");
        topic.setGroupPrefix("TD");
        topic.setName("Topic");
        topic.setDefaultGroups(1);
        topic.setDefaultSprints(1);
        try {
            topic = topicService.createTopic(topic);
        } catch (ZeroGroupSprintException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @DisplayName("Crear topic")
    @Test
    public void testCreateTopic() {
        setUp();
        TsscTopic ntopic = new TsscTopic();
        ntopic.setDefaultGroups(12);
        ntopic.setDefaultSprints(8);
        try {
            TsscTopic newTopic = topicService.createTopic(ntopic);
            assertTrue(newTopic == ntopic);
        } catch (ZeroGroupSprintException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @DisplayName("Editar topic")
    @Test
    public void testEditTopic() {
        setUp();
        topic.setName("Topic edited");
        try {
            TsscTopic ntopic = topicService.editTopic(topic);
            assertTrue(topic.getName().equals(ntopic.getName()));
        } catch (EditException | ZeroGroupSprintException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
