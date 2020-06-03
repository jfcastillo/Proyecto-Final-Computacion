package com.taller.castillo.felipe.business;

import com.taller.castillo.felipe.delegate.BusinessDelegate;
import com.taller.castillo.felipe.delegate.BusinessDelegateImp;
import com.taller.castillo.felipe.model.TsscTopic;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusinessDelegateTopicTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BusinessDelegate businessDelegate = new BusinessDelegateImp();

    @Test
    public void successOnGetTopic() {
        //Given
        TsscTopic topic = new TsscTopic();
        topic.setName("PruebaName");
        ResponseEntity<TsscTopic> responseEntity = new ResponseEntity<TsscTopic>(topic, HttpStatus.OK);

        //When
        when(restTemplate.getForEntity(anyString(), Mockito.eq(TsscTopic.class))).thenReturn(responseEntity);
        TsscTopic topicResponse = businessDelegate.getTopic(2);
        
        //Then
        Assert.assertNotNull(topicResponse);
        Assert.assertEquals(topicResponse.getName(), "PruebaName");
    }

    @Test
    public void ServerErrorOnGetTopic() {
        //Given
        ResponseEntity responseEntity = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

        //When
        when(restTemplate.getForEntity(anyString(), Mockito.eq(TsscTopic.class))).thenReturn(responseEntity);
        TsscTopic topicResponse = businessDelegate.getTopic(2);
        
        //Then
        Assert.assertNull(topicResponse);
    }

    @Test
    public void successOnFindAllTopics() {
        //Given
        TsscTopic topics[] = new TsscTopic[2];
        topics[0] = new TsscTopic();
        topics[1] = new TsscTopic();
        topics[0].setName("PruebaName");
        ResponseEntity<TsscTopic[]> responseEntity = new ResponseEntity<TsscTopic[]>(topics, HttpStatus.OK);

        //When
        when(restTemplate.getForEntity(anyString(), Mockito.eq(TsscTopic[].class))).thenReturn(responseEntity);
        Iterable<TsscTopic> topicsResponse = businessDelegate.findAllTopics();
        
        //Then
        Assert.assertNotNull(topicsResponse);
        Assert.assertEquals(topicsResponse.iterator().next().getName(), "PruebaName");
    }

    @Test
    public void ServerErrorOnFindAllTopics() {
        //Given
        ResponseEntity<TsscTopic[]> responseEntity = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

        //When
        when(restTemplate.getForEntity(anyString(), Mockito.eq(TsscTopic[].class))).thenReturn(responseEntity);
        Iterable<TsscTopic> topicResponse = businessDelegate.findAllTopics();
        
        //Then
        Assert.assertNull(topicResponse);
    }

    @Test
    public void successOnCreateTopic() {
        //Given
        TsscTopic topic = new TsscTopic();
        topic.setName("PruebaName");
        ResponseEntity<TsscTopic> responseEntity = new ResponseEntity<TsscTopic>(topic, HttpStatus.OK);

        //When
        when(restTemplate.postForEntity(anyString(), any(TsscTopic.class), Mockito.eq(TsscTopic.class))).thenReturn(responseEntity);
        TsscTopic topicResponse = businessDelegate.saveTopic(topic);
        
        //Then
        Assert.assertNotNull(topicResponse);
        Assert.assertEquals(topicResponse.getName(), "PruebaName");
    }

    @Test
    public void ServerErrorOnCreateTopic() {
        //Given
        ResponseEntity responseEntity = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

        //When
        when(restTemplate.postForEntity(anyString(), any(TsscTopic.class), Mockito.eq(TsscTopic.class))).thenReturn(responseEntity);
        TsscTopic topicResponse = businessDelegate.saveTopic(new TsscTopic());
        
        //Then
        Assert.assertNull(topicResponse);
    }

    @Test
    public void successOnEditTopic() {
        //Given
        TsscTopic topic = new TsscTopic();
        topic.setName("PruebaName");
        ResponseEntity<TsscTopic> responseEntity = new ResponseEntity<TsscTopic>(topic, HttpStatus.OK);

        // When
        // TODO Auto-generated method stub
        //Then
        Assertions.assertThatCode(() -> businessDelegate.editTopic(1, topic)).doesNotThrowAnyException();
    }

    @Test(expected = HttpClientErrorException.class)
    public void ErrorOnEditTopic() {
    	// TODO Auto-generated method stub
        //Given
        TsscTopic topic = new TsscTopic();
        topic.setName("PruebaName");
        ResponseEntity<TsscTopic> responseEntity = new ResponseEntity<TsscTopic>(topic, HttpStatus.OK);

        //When
        doThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR)).when(restTemplate).put(anyString(), any(TsscTopic.class));

        //Then
        businessDelegate.editTopic(1, topic);
    }

    @Test
    public void successOnDeleteTopic() {
    	// TODO Auto-generated method stub
        Assertions.assertThatCode(() -> businessDelegate.deleteTopic(1)).doesNotThrowAnyException();
    }

    @Test(expected = HttpClientErrorException.class)
    public void ErrorOnDeleteTopic() {
    	// TODO Auto-generated method stub
        doThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR)).when(restTemplate).delete(anyString());

        //Then
        businessDelegate.deleteTopic(1);
    }
}