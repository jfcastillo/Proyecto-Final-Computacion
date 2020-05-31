package com.taller.castillo.felipe.controller;

import com.taller.castillo.felipe.delegate.BusinessDelegate;
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
import com.taller.castillo.felipe.exception.ZeroGroupSprintException;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.model.ValidationGroupCreate;
import com.taller.castillo.felipe.model.ValidationGroupEdit;
import com.taller.castillo.felipe.service.TsscTopicService;

@Controller
public class TsscTopicController {
	@Autowired
	private TsscTopicService topicService;

	@Autowired
	private BusinessDelegate businessDelegate;
	
	@GetMapping("/tssctopics/")
	public String indexTopic(Model model) {
		model.addAttribute("tssctopics", businessDelegate.findAllTopics());
		return "tssctopics/index";
	}
	
	@GetMapping("/tssctopics/add")
	public String addTopic(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		
		return "tssctopics/add-topic";
	}


	@PostMapping("/tssctopics/add")
	public String saveTopic(@Validated(ValidationGroupCreate.class) @ModelAttribute TsscTopic topic,	BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model model) {
		if (bindingResult.hasErrors()) {			
			return "tssctopics/add-topic";
			//return addTopic(model);
		} 
		
		if (!action.equals("cancel")) {
			try {
				topicService.createTopic(topic);
			} catch (ZeroGroupSprintException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/tssctopics/";
		}	
		else {
			return "redirect:/tssctopics/";
		}		
		
	}
	
	@GetMapping("/tssctopics/del/{id}")
	public String deleteTopic(@PathVariable("id") long id) {
		
		TsscTopic topic = topicService.findById(id).get();
		if (topic == null) {
			throw new IllegalArgumentException("Tema inválido");
		}
		else {
			topicService.deleteTopic(topic);
		}
		return "redirect:/tssctopics/";		
	}
	
	@GetMapping("tssctopics/edit/{id}")
	public String showEdit(@PathVariable("id") long id, Model model) {
		TsscTopic topic = topicService.findById(id).get();
		model.addAttribute("tsscTopic", topic);
		return "tssctopics/edit-topic";		
	}
	@PostMapping("tssctopics/edit/{id}")
	public String editTopic(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action, 
			@Validated(ValidationGroupEdit.class) @ModelAttribute TsscTopic topic, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "tssctopics/edit-topic";	
		}
		if (action != null && !action.equals("Cancelar")) {
			try {
				topicService.editTopic(topic);
			} catch (EditException | ZeroGroupSprintException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return "redirect:/tssctopics/";	
	}
}
