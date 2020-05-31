package com.taller.castillo.felipe.restcontroller;

import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.service.TsscStoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	public TsscStory findById(long id) {
		return tsscStoryService.findById(id).get();
	}
	
}