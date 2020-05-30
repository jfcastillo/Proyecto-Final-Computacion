package com.taller.castillo.felipe.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.taller.castillo.felipe.service.TsscGameService;

@RestController
public class TsscGameRestController {
	
	@Autowired
	private TsscGameService gameService;

}
