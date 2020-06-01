package com.taller.castillo.felipe.business;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.taller.castillo.felipe.delegate.BusinessDelegate;
import com.taller.castillo.felipe.delegate.BusinessDelegateImp;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTopic;

@RunWith(MockitoJUnitRunner.class)
public class BusinessDelegateStoryTest {
	
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BusinessDelegate businessDelegate = new BusinessDelegateImp();

    @Test
    public void successOnGetStory() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void serverErrorOnGetStory() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void successOnFindAllStories() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void serverErrorOnFindAllStories() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void successOnFindStoriesByGameId() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void serverErrorOnFindStoriesByGameId() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void successOnCreateStory() {
    	//Given
    	TsscStory story = new TsscStory();
    	story.setDescription("PruebaDescription");
    	ResponseEntity<TsscStory> responseEntity = new ResponseEntity<TsscStory>(story, HttpStatus.OK);
    	
    	//When
    	when(restTemplate.postForEntity(anyString(), any(TsscStory.class), Mockito.eq(TsscStory.class))).thenReturn(responseEntity);
    	
    	//Then
    	TsscStory storyResponse = businessDelegate.saveStory(story, 1);
    	Assert.assertNotNull(storyResponse);
    	Assert.assertEquals(storyResponse.getDescription(), "PruebaDescription");
    }
    
    @Test
    public void serverErrorOnCreateStory() {
    	//Given
    	ResponseEntity responseEntity = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

    	//When
    	when(restTemplate.postForEntity(anyString(), any(TsscStory.class), Mockito.eq(TsscStory.class))).thenReturn(responseEntity);
    	
    	//Then
    	TsscStory storyResponse = businessDelegate.saveStory(new TsscStory(), 1);
    	Assert.assertNull(storyResponse);
    }
    
    @Test
    public void successOnEditStory() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void serverErrorOnEditStory() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void successOnDeleteStory() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void serverErrorOnDeleteStory() {
    	// TODO Auto-generated method stub
    }
    
}