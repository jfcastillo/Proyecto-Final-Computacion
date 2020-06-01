package com.taller.castillo.felipe.restcontroller;

import com.taller.castillo.felipe.exception.NullGameException;
import com.taller.castillo.felipe.exception.StoryException;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.service.TsscStoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class TsscStoryRestController {

	@Autowired
	private TsscStoryService tsscStoryService;
	
	@GetMapping(value = "/tsscstories")
	public Iterable<TsscStory> findAll(){
		return tsscStoryService.findAll();
	}
	
	@GetMapping(value = "/tsscstories/{id}")
	public TsscStory findById(@PathVariable long id) {
		return tsscStoryService.findById(id).get();
	}

	@GetMapping(value = "/tsscstories/game/{id}")
	public Iterable<TsscStory> findStoryByGameId(@PathVariable long id) {
		return tsscStoryService.findByGameId(id);
	}

	@PostMapping(value = "/tsscstories/game/{gameId}")
	public TsscStory createStory(@RequestBody TsscStory tsscStory, @PathVariable long gameId) {
		TsscStory createdStory = null;
		try {
			createdStory = tsscStoryService.createStory(tsscStory, gameId);
		} catch (StoryException | NullGameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createdStory;
	}
	
}