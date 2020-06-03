package com.taller.castillo.felipe.business;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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

import com.taller.castillo.felipe.delegate.BusinessDelegate;
import com.taller.castillo.felipe.delegate.BusinessDelegateImp;
import com.taller.castillo.felipe.model.TsscStory;

@RunWith(MockitoJUnitRunner.class)
public class BusinessDelegateStoryTest {
	
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BusinessDelegate businessDelegate = new BusinessDelegateImp();

    @Test
    public void successOnGetStory() {
    	//Given
    	TsscStory story = new TsscStory();
    	story.setDescription("PruebaDescription");
    	ResponseEntity<TsscStory> responseEntity = new ResponseEntity<TsscStory>(story, HttpStatus.OK);
    	
    	//When
    	when(restTemplate.getForEntity(anyString(), Mockito.eq(TsscStory.class))).thenReturn(responseEntity);
    	TsscStory storyResponse = businessDelegate.getStory(2);
    	
    	//Then
    	Assert.assertNotNull(storyResponse);
    	Assert.assertEquals(storyResponse.getDescription(), "PruebaDescription");
    }
    
    @Test
    public void serverErrorOnGetStory() {
    	//Given
        ResponseEntity responseEntity = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

        //When
        when(restTemplate.getForEntity(anyString(), Mockito.eq(TsscStory.class))).thenReturn(responseEntity);
        TsscStory storyResponse = businessDelegate.getStory(2);
        
        //Then
        Assert.assertNull(storyResponse);
    }
    
    @Test
    public void successOnFindAllStories() {
    	//Given
    	TsscStory stories[] = new TsscStory[2];
    	stories[0] = new TsscStory();
    	stories[1] = new TsscStory();
    	stories[0].setDescription("PruebaDescription");
    	ResponseEntity<TsscStory[]> responseEntity = new ResponseEntity<TsscStory[]>(stories, HttpStatus.OK);

        //When
        when(restTemplate.getForEntity(anyString(), Mockito.eq(TsscStory[].class))).thenReturn(responseEntity);
        Iterable<TsscStory> storiesResponse = businessDelegate.findAllStories();
        
        //Then
        Assert.assertNotNull(storiesResponse);
        Assert.assertEquals(storiesResponse.iterator().next().getDescription(), "PruebaDescription");
    }
    
    @Test
    public void serverErrorOnFindAllStories() {
    	//Given
    	ResponseEntity<TsscStory[]> responseEntity = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    	
    	//When
        when(restTemplate.getForEntity(anyString(), Mockito.eq(TsscStory[].class))).thenReturn(responseEntity);
        Iterable<TsscStory> storiesResponse = businessDelegate.findAllStories();
        
        //Then
        Assert.assertNull(storiesResponse);
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
    	TsscStory storyResponse = businessDelegate.saveStory(story, 1);
    	
    	//Then
    	Assert.assertNotNull(storyResponse);
    	Assert.assertEquals(storyResponse.getDescription(), "PruebaDescription");
    }
    
    @Test
    public void serverErrorOnCreateStory() {
    	//Given
    	ResponseEntity responseEntity = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

    	//When
    	when(restTemplate.postForEntity(anyString(), any(TsscStory.class), Mockito.eq(TsscStory.class))).thenReturn(responseEntity);
    	TsscStory storyResponse = businessDelegate.saveStory(new TsscStory(), 1);
    	
    	//Then
    	Assert.assertNull(storyResponse);
    }
    
    @Test
    public void successOnEditStory() {
    	// TODO Auto-generated method stub
    	//Given
    	TsscStory story = new TsscStory();
    	story.setDescription("PruebaDescription");
    	ResponseEntity<TsscStory> responseEntity = new ResponseEntity<TsscStory>(story, HttpStatus.OK);
    	
        //Then
    	Assertions.assertThatCode(() -> businessDelegate.editStory(1, story)).doesNotThrowAnyException();
    }
    
    @Test (expected = HttpClientErrorException.class)
    public void serverErrorOnEditStory() {
    	// TODO Auto-generated method stub
    	//Given
    	TsscStory story = new TsscStory();
    	story.setDescription("PruebaDescription");
    	ResponseEntity<TsscStory> responseEntity = new ResponseEntity<TsscStory>(story, HttpStatus.OK);
    	
        //When
        doThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR)).when(restTemplate).put(anyString(), any(TsscStory.class));
        
        //Then
        businessDelegate.editStory(1, story);
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