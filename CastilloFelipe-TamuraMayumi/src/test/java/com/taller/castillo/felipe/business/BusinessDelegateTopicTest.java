package com.taller.castillo.felipe.business;

import com.taller.castillo.felipe.delegate.BusinessDelegate;
import com.taller.castillo.felipe.delegate.BusinessDelegateImp;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.service.TsscGameService;
import org.assertj.core.matcher.AssertionMatcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BusinessDelegateTopicTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BusinessDelegate businessDelegate = new BusinessDelegateImp();

    @Test
    public void successOnCreateTopic() {
        //Given
        TsscTopic topic = new TsscTopic();
        topic.setName("PruebaName");
        ResponseEntity<TsscTopic> responseEntity = new ResponseEntity<TsscTopic>(topic, HttpStatus.OK);

        //When
        when(restTemplate.postForEntity(anyString(), any(TsscTopic.class), Mockito.eq(TsscTopic.class))).thenReturn(responseEntity);

        //Then
        TsscTopic topicResponse = businessDelegate.saveTopic(topic);
        Assert.assertNotNull(topicResponse);
        Assert.assertEquals(topicResponse.getName(), "PruebaName");
    }

    @Test
    public void ServerErrorOnCreateTopic() {
        //Given
        ResponseEntity responseEntity = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

        //When
        when(restTemplate.postForEntity(anyString(), any(TsscTopic.class), Mockito.eq(TsscTopic.class))).thenReturn(responseEntity);

        //Then
        TsscTopic topicResponse = businessDelegate.saveTopic(new TsscTopic());
        Assert.assertNull(topicResponse);
    }
}
