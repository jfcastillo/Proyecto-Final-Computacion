package com.taller.castillo.felipe.restcontroller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscTopic;
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
	@PutMapping("/tsscgames/{id}")
    public TsscGame editGame(@PathVariable long id, @RequestBody TsscGame game) {
		TsscGame createdGame = null;
		if (gameService.findById(id) != null) {
			try {
	        	createdGame = gameService.editGame(game);
	        } catch (ZeroGroupSprintException | EditException e) {
	            e.printStackTrace();
	        }
		}        
        return createdGame;
    }
	
	@GetMapping("/tsscgames/{startDate}/{endDate}")
	public List<TsscGame> findByDateRange(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,@PathVariable("endDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		
		return gameService.findByDateRange(startDate, endDate);
	}
//	@GetMapping("/tsscgames/{startDate}")
//	public List<TsscTopic> findByDateRange(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate) {
//		List<Object[]> query = (gameService.findTopicByDate(startDate));
//		List<TsscTopic> topics = new ArrayList<>();
//		for (Object[] objects : query) {
//			topics.add((TsscTopic)objects[0]);
//		}
//		return topics;
//	}

}
