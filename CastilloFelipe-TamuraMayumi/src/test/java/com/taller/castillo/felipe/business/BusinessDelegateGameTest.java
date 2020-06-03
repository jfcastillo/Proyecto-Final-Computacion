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
import com.taller.castillo.felipe.model.TsscGame;

@RunWith(MockitoJUnitRunner.class)
public class BusinessDelegateGameTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BusinessDelegate businessDelegate = new BusinessDelegateImp();

    @Test
    public void successOnGetGame() {
    	//Given
    	TsscGame game = new TsscGame();
    	game.setName("PruebaName");
    	ResponseEntity<TsscGame> responseEntity = new ResponseEntity<TsscGame>(game, HttpStatus.OK);
    	
    	//When
    	when(restTemplate.getForEntity(anyString(), Mockito.eq(TsscGame.class))).thenReturn(responseEntity);
    	TsscGame gameResponse = businessDelegate.getGame(1);
    	
    	//Then
    	Assert.assertNotNull(gameResponse);
    	Assert.assertEquals(gameResponse.getName(), "PruebaName");
    }
    
    @Test
    public void serverErrorOnGetGame() {
    	//Given
        ResponseEntity responseEntity = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

        //When
        when(restTemplate.getForEntity(anyString(), Mockito.eq(TsscGame.class))).thenReturn(responseEntity);
        TsscGame gameResponse = businessDelegate.getGame(1);
        
        //Then
        Assert.assertNull(gameResponse);
    }
    
    @Test
    public void successOnFindAllGames() {
    	//Given
    	TsscGame games[] = new TsscGame[2];
    	games[0] = new TsscGame();
    	games[1] = new TsscGame();
    	games[0].setName("PruebaName");
    	ResponseEntity<TsscGame[]> responseEntity = new ResponseEntity<TsscGame[]>(games, HttpStatus.OK);

        //When
        when(restTemplate.getForEntity(anyString(), Mockito.eq(TsscGame[].class))).thenReturn(responseEntity);
        Iterable<TsscGame> gamesResponse = businessDelegate.findAllGames();
        
        //Then
        Assert.assertNotNull(gamesResponse);
        Assert.assertEquals(gamesResponse.iterator().next().getName(), "PruebaName");
    }
    
    @Test
    public void serverErrorOnFindAllGames() {
    	//Given
    	ResponseEntity<TsscGame[]> responseEntity = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    	
    	//When
    	when(restTemplate.getForEntity(anyString(), Mockito.eq(TsscGame[].class))).thenReturn(responseEntity);
    	Iterable<TsscGame> gamesResponse = businessDelegate.findAllGames();
        
        //Then
    	Assert.assertNull(gamesResponse);
    }
    
    @Test
    public void successOnCreateGame() {
    	//Given
    	TsscGame game = new TsscGame();
    	game.setName("PruebaName");
    	ResponseEntity<TsscGame> responseEntity = new ResponseEntity<TsscGame>(game, HttpStatus.OK);
    	
    	//When
    	when(restTemplate.postForEntity(anyString(), any(TsscGame.class), Mockito.eq(TsscGame.class))).thenReturn(responseEntity);
    	TsscGame gameResponse = businessDelegate.saveGame(game);
    	
    	//Then
    	Assert.assertNotNull(gameResponse);
    	Assert.assertEquals(gameResponse.getName(), "PruebaName");
    }
    
    @Test
    public void serverErrorOnCreateGame() {
    	//Given
    	ResponseEntity responseEntity = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    	
    	//When
    	when(restTemplate.postForEntity(anyString(), any(TsscGame.class), Mockito.eq(TsscGame.class))).thenReturn(responseEntity);
    	TsscGame gameResponse = businessDelegate.saveGame(new TsscGame());
    	
    	//Then
    	Assert.assertNull(gameResponse);
    }
    
    @Test
    public void successOnEditGame() {
    	// TODO Auto-generated method stub
    	//Given
    	TsscGame game = new TsscGame();
    	game.setName("PruebaName");
    	ResponseEntity<TsscGame> responseEntity = new ResponseEntity<TsscGame>(game, HttpStatus.OK);

        //Then
    	Assertions.assertThatCode(() -> businessDelegate.editGame(1, game)).doesNotThrowAnyException();
    }
    
    @Test (expected = HttpClientErrorException.class)
    public void serverErrorOnEditGame() {
    	// TODO Auto-generated method stub
    	//Given
    	TsscGame game = new TsscGame();
    	game.setName("PruebaName");
    	ResponseEntity<TsscGame> responseEntity = new ResponseEntity<TsscGame>(game, HttpStatus.OK);

        //When
        doThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR)).when(restTemplate).put(anyString(), any(TsscGame.class));

        //Then
        businessDelegate.editGame(1, game);
    }
    
}