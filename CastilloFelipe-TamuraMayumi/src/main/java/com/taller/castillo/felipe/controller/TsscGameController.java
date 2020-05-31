package com.taller.castillo.felipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taller.castillo.felipe.delegate.BusinessDelegate;
import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.model.ValidationGroupCreate;
import com.taller.castillo.felipe.model.ValidationGroupEdit;
import com.taller.castillo.felipe.service.TsscGameService;
import com.taller.castillo.felipe.service.TsscTopicService;

@Controller
public class TsscGameController {
	
	@Autowired
	private TsscGameService gameService;
	@Autowired
	private TsscTopicService topicService;
	@Autowired
	private BusinessDelegate businessDelegate;
	
	private long idGame;
	
	@GetMapping("/tsscgames")
	public String indexGame(Model model) {
		model.addAttribute("tsscgames", businessDelegate.findAllGames());
		return "tsscgames/index";
	}
	
	@GetMapping("/tsscgames/add")
	public String addGame(Model model) {
		model.addAttribute("tsscGame", new TsscGame());
		return "tsscgames/add-game";
	}
	@PostMapping("/tsscgames/add")
	public String saveGame(@Validated(ValidationGroupCreate.class) @ModelAttribute TsscGame game, BindingResult bindingResult,
			@RequestParam String action, Model model) {
		if (bindingResult.hasErrors()) {	
					
			return "tsscgames/add-game";
		} 
		
		if (!action.equals("cancel")) {
			
				businessDelegate.saveGame(game);
			
			return "redirect:/tsscgames/";
		}	
		
		else {
			return "redirect:/tsscgames/";
		}
	}
	
	@GetMapping("/tsscgames/story/{id}")
	public String indexStory(@PathVariable("id") long idGame, Model model) {
		TsscGame game = gameService.findById(idGame).get();
		model.addAttribute("idGame", idGame);
		model.addAttribute("tsscstories", game.getTsscStories());		
		return "/tsscstory/index";
	}
	@GetMapping("/tsscgames/topic/{id}")
	public String addTopicToGame(@PathVariable("id") long id, Model model) {
		TsscGame game = gameService.findById(id).get();
		idGame = id;
		model.addAttribute("tsscGame", game);
		model.addAttribute("tssctopics", topicService.findAll());
		if(game.getTsscTopic() != null) {
			model.addAttribute("topicActual", game.getTsscTopic().getName());
		}
		else {
			model.addAttribute("topicActual", null);
		}
		return "tsscgames/add-topicgame";
	}
	@PostMapping("/tsscgames/topic/")
	public String saveTopicToGame(Model model, TsscTopic topic) {
		TsscGame game = gameService.findById(idGame).get();
		game.setTsscTopic(topic);		
		try {
			gameService.createGame(game);
		} catch (ZeroGroupSprintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/tsscgames/";
	}
	@GetMapping("/tsscgames/edit/{id}")
	public String showEdit(@PathVariable("id") long id, Model model) {
		TsscGame game = gameService.findById(id).get();
		model.addAttribute("tsscGame", game);	
		return "tsscgames/edit-game";
	}
	@PostMapping("/tsscgames/edit/{id}")
	public String editGame(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, 
			@Validated(ValidationGroupEdit.class) @ModelAttribute TsscGame game, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "tsscgames/edit-game";
		}
		if (action != null && !action.equals("Cancelar")) {
			try {
				gameService.editGame(game);
			} catch (EditException | ZeroGroupSprintException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/tsscgames/";
	}
	
	

}
