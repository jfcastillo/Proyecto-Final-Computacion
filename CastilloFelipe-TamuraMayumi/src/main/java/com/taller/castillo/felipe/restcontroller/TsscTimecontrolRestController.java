package com.taller.castillo.felipe.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller.castillo.felipe.exception.NullGameException;
import com.taller.castillo.felipe.exception.StoryException;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTimecontrol;
import com.taller.castillo.felipe.service.TsscTimecontrolService;

@RestController
@RequestMapping(value = "/api")
public class TsscTimecontrolRestController {

	@Autowired
	private TsscTimecontrolService timecontrolService;

	@GetMapping("/tssctimecontrol")
	public Iterable<TsscTimecontrol> findAll() {
		return timecontrolService.findAll();
	}

	@GetMapping("/tssctimecontrol/{id}")
	public TsscTimecontrol findById(@PathVariable long id) {
		return timecontrolService.findById(id);
	}

	@GetMapping("/tssctimecontrol/game/{id}")
	public Iterable<TsscTimecontrol> findTimecontrolByGameId(@PathVariable long id) {
		return timecontrolService.findByGameId(id);
	}

	@PostMapping(value = "/tssctimecontrol/game/{gameId}")
	public TsscTimecontrol createTimecontrol(@RequestBody TsscTimecontrol timecontrol, @PathVariable long gameId) {
		TsscTimecontrol createdTimecontrol = null;

		try {
			createdTimecontrol = timecontrolService.createTimecontrol(gameId, timecontrol);
		} catch (NullGameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return createdTimecontrol;
	}
}
