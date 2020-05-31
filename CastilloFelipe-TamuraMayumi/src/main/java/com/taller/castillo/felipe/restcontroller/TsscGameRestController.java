package com.taller.castillo.felipe.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.service.TsscGameService;

@RestController
@RequestMapping("/api")
public class TsscGameRestController {
	
	@Autowired
	private TsscGameService gameService;
	
	
	@GetMapping("/tsscgames/{id}")
	public TsscGame findById(@PathVariable long id){
		return gameService.findById(id).get();
	}
	@GetMapping("/tsscgames/")
	public Iterable<TsscGame> findAllGames() {
		return gameService.findAll();
	}
	
	@PostMapping("/tsscgames/")
	public TsscGame saveGame(@RequestBody TsscGame game) {
		TsscGame created = null;
		try {
			created = gameService.createGame(game);
		} catch (ZeroGroupSprintException e) {
			e.printStackTrace();
		}
		return created;
	}
	@PutMapping("/tsscgames/")
    public TsscGame editGame(@RequestBody TsscGame game) {
		TsscGame createdGame = null;
        try {
        	createdGame = gameService.editGame(game);
        } catch (ZeroGroupSprintException | EditException e) {
            e.printStackTrace();
        }
        return createdGame;
    }

}
