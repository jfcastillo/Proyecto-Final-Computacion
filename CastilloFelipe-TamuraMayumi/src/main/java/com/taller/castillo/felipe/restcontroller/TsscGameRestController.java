package com.taller.castillo.felipe.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.service.TsscGameService;

@RestController
@RequestMapping("/api")
public class TsscGameRestController {
	
	@Autowired
	private TsscGameService gameService;
	
	
	@GetMapping("/tsscgames/{id}")
	public TsscGame getGame(long idGame){
		return gameService.findById(idGame).get();
	}
	@GetMapping("/tsscgames/")
	public Iterable<TsscGame> findAllGames() {
		return gameService.findAll();
	}

}
