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
import com.taller.castillo.felipe.model.TsscTimecontrol;
import com.taller.castillo.felipe.model.ValidationGroupCreate;
@Controller
public class TsscTimecontrolController {
	
	@Autowired
	private BusinessDelegate businessDelegate;
	
	private long idGame;
	
	
	@GetMapping("tssctimecontrol/add/{id}")
	public String addTimecontrol(@PathVariable("id") long id, Model model) {
		idGame = id;
		model.addAttribute("tsscTimecontrol", new TsscTimecontrol());
		return "tssctimecontrol/add-timecontrol";
	}
	@PostMapping("tssctimecontrol/add")
	public String saveTimecontrol(@ModelAttribute TsscTimecontrol timecontrol,
			BindingResult bindingResult, @RequestParam String action, Model model) {
		System.out.println(idGame);

		if (bindingResult.hasErrors()) {
			return "tssctimecontrol/add-timecontrol";
		}
		if (!action.equals("cancel")) {
			businessDelegate.saveTimeControl(timecontrol, idGame);
			return "redirect:/tsscgames/";
		}
		return "redirect:/tsscgames/";
	}
}
