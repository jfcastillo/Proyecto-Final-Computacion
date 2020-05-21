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

import com.taller.castillo.felipe.exception.EditException;
import com.taller.castillo.felipe.exception.NullGameException;
import com.taller.castillo.felipe.exception.StoryException;
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.ValidationGroupCreate;
import com.taller.castillo.felipe.model.ValidationGroupEdit;
import com.taller.castillo.felipe.service.TsscGameService;
import com.taller.castillo.felipe.service.TsscStoryService;
@Controller
public class TsscStoryController {
	@Autowired
	private TsscStoryService storyService;
	
	private long idGame;
	
//	@GetMapping("/tsscstory/")
//	public String indexStory(@PathVariable("id") long id, Model model) {
//		model.addAttribute("tsscstories", storyService.findAll());
//		
//		
//		return "tsscstory/index";
//		
//	}

	@GetMapping("tsscstory/add/{id}")
	public String addStory(@PathVariable("id") long idGame, Model model) {
		this.idGame = idGame;
		model.addAttribute("tsscStory", new TsscStory());
		return "tsscstory/add-story";
	}
	
	@PostMapping("tsscstory/add")
	public String saveStory(@Validated(ValidationGroupCreate.class) @ModelAttribute TsscStory story,
			BindingResult bindingResult,@RequestParam String action, Model model) {
		
		if (bindingResult.hasErrors()) {	
			
			return "tsscstory/add-story";
		} 
		if (!action.equals("cancel")) {
			try {
				storyService.createStory(story, this.idGame);
			} catch (StoryException | NullGameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/tsscgames/";
		}
		
		return "redirect:/tsscgames/";
		
	}		
	
	@GetMapping("/tsscstory/edit/{id}")
	public String showEdit(@PathVariable("id") long id, Model model) {
		TsscStory story = storyService.findById(id).get();
		model.addAttribute("tsscStory", story);
		return "tsscstory/edit-story";
	}
	
	@PostMapping("/tsscstory/edit/{id}")
	public String editStory(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, 
			@Validated(ValidationGroupEdit.class) @ModelAttribute TsscStory story, BindingResult bindingResult, Model model) {
		System.out.println("hola");
		if (bindingResult.hasErrors()) {
			return "tsscstory/edit-story";
		}
		if (action != null && !action.equals("Cancelar")) {
			try {
				storyService.createStory(story, this.idGame);
			} catch (StoryException  | NullGameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/tsscgames/";
	}

}
