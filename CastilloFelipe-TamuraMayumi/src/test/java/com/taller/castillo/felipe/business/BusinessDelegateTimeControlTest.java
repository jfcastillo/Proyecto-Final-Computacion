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
import com.taller.castillo.felipe.model.TsscTimecontrol;

@RunWith(MockitoJUnitRunner.class)
public class BusinessDelegateTimeControlTest {
	
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BusinessDelegate businessDelegate = new BusinessDelegateImp();

    @Test
    public void successOnFindAllTimeControlByGameId() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void serverErrorOnFindAllTimeControlByGameId() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void successOnGetTimeControl() {
    	// TODO Auto-generated method stub
    	
    }
    
    @Test
    public void serverErrorOnGetTimeControl() {
    	//Given
        ResponseEntity responseEntity = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

        //When
        when(restTemplate.getForEntity(anyString(), Mockito.eq(TsscTimecontrol.class))).thenReturn(responseEntity);
        TsscTimecontrol timecontrolResponse = businessDelegate.getTimeControl(1);
        
        //Then
        Assert.assertNull(timecontrolResponse);
    }
    
    @Test
    public void successOnCreateTimecontrol() {
    	//Given
    	TsscTimecontrol timecontrol = new TsscTimecontrol();
    	timecontrol.setName("PruebaName");
    	ResponseEntity<TsscTimecontrol> responseEntity = new ResponseEntity<TsscTimecontrol>(timecontrol, HttpStatus.OK);
    	
    	//When
    	when(restTemplate.postForEntity(anyString(), any(TsscTimecontrol.class), Mockito.eq(TsscTimecontrol.class))).thenReturn(responseEntity);
    	TsscTimecontrol timecontrolResponse = businessDelegate.saveTimeControl(timecontrol, 1);
    	
    	//Then
    	Assert.assertNotNull(timecontrolResponse);
    	Assert.assertEquals(timecontrolResponse.getName(), "PruebaName");
    }
    
    @Test
    public void serverErrorOnCreateTimecontrol() {
    	//Given
    	ResponseEntity responseEntity = new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

    	//When
    	when(restTemplate.postForEntity(anyString(), any(TsscTimecontrol.class), Mockito.eq(TsscTimecontrol.class))).thenReturn(responseEntity);
    	TsscTimecontrol timecontrolResponse = businessDelegate.saveTimeControl(new TsscTimecontrol(), 1);
    	
    	//Then
    	Assert.assertNull(timecontrolResponse);
    }
    
    @Test
    public void successOnEditTimeControl() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void serverErrorOnEditTimeControl() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void successOnDeletTimeControl() {
    	// TODO Auto-generated method stub
    }
    
    @Test
    public void serverErrorOnDeletTimeControl() {
    	// TODO Auto-generated method stub
    }
    
}