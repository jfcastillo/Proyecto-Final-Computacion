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
import com.taller.castillo.felipe.model.TsscStory;
import com.taller.castillo.felipe.model.ValidationGroupCreate;
import com.taller.castillo.felipe.model.ValidationGroupEdit;

@Controller
public class TsscStoryController {

	@Autowired
	private BusinessDelegate businessDelegate;
	
	private boolean index = false;

	private long idGame;

	@GetMapping("/tsscstory/")
	public String indexStory(Model model) {
		index = true;
		model.addAttribute("index", index);
		model.addAttribute("tsscstories", businessDelegate.findAllStories());
		return "tsscstory/index";
	}
	@GetMapping("tsscstory/add")
	public String addStoryWithGame(Model model) {			
		index = true;
		model.addAttribute("tsscgames", businessDelegate.findAllGames());
		model.addAttribute("index", index);
		model.addAttribute("tsscStory", new TsscStory());
		return "tsscstory/add-story";
	}
	
	@GetMapping("tsscstory/add/{id}")
	public String addStory(@PathVariable("id") long idGame, Model model) {
		index = false;
		this.idGame = idGame;
		model.addAttribute("index", index);
		model.addAttribute("tsscStory", new TsscStory());
		return "tsscstory/add-story";
	}

	@PostMapping("tsscstory/add")
	public String saveStory(@Validated(ValidationGroupCreate.class) @ModelAttribute TsscStory story,
			BindingResult bindingResult, @RequestParam String action, Model model) {

		if (bindingResult.hasErrors()) {
			return "tsscstory/add-story";
		}
		if (!action.equals("cancel")) {
			if (index) {
				businessDelegate.saveStory(story);
				return "redirect:/tsscstory/";
			}
			else {
				businessDelegate.saveStory(story, this.idGame);
				return "redirect:/tsscgames/";
			}
			
			
		}
		return "redirect:/tsscgames/";
	}

	@GetMapping("/tsscstory/edit/{id}")
	public String showEdit(@PathVariable("id") long id, Model model) {
		TsscStory story = businessDelegate.getStory(id);
		model.addAttribute("tsscStory", story);
		return "tsscstory/edit-story";
	}

	@PostMapping("/tsscstory/edit/{id}")
	public String editStory(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@Validated(ValidationGroupEdit.class) @ModelAttribute TsscStory story, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "tsscstory/edit-story";
		}
		if (action != null && !action.equals("Cancelar")) {
			businessDelegate.editStory(id, story);
		}
		return "redirect:/tsscstory/";
	}

	@GetMapping("/tsscstory/del/{id}")
	public String deleteStory(@PathVariable("id") long id) {
		businessDelegate.deleteStory(id);
		return "redirect:/tsscstory/";
	}
}