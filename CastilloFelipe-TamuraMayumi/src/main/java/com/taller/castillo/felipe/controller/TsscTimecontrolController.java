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
import com.taller.castillo.felipe.model.ValidationGroupEdit;
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
	@GetMapping("/tssctimecontrol/edit/{id}")
	public String showEdit(@PathVariable("id") long id, Model model) {
		TsscTimecontrol timecontrol = businessDelegate.getTimeControl(id);
		model.addAttribute("tsscTimecontrol", timecontrol);
		return "tssctimecontrol/edit-timecontrol";
	}

	@PostMapping("/tssctimecontrol/edit/{id}")
	public String editTimecontrol(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@Validated(ValidationGroupEdit.class) @ModelAttribute TsscTimecontrol timecontrol, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "tssctimecontrol/edit-timecontrol";
		}
		if (action != null && !action.equals("Cancelar")) {
			businessDelegate.editTimeControl(id, timecontrol);
		}
		return "redirect:/tsscgames/";
	}
	@GetMapping("/tssctimecontrol/del/{id}")
	public String deleteTimecontrol(@PathVariable("id") long id) {
		businessDelegate.deletTimeControl(id);
		return "redirect:/tsscgames/";
	}
}
