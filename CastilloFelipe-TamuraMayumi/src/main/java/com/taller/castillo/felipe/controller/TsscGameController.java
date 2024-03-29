package com.taller.castillo.felipe.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.taller.castillo.felipe.model.TsscGame;
import com.taller.castillo.felipe.model.TsscTopic;
import com.taller.castillo.felipe.model.ValidationGroupCreate;
import com.taller.castillo.felipe.model.ValidationGroupEdit;
import com.taller.castillo.felipe.service.TsscGameService;

@Controller
public class TsscGameController {

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
	public String saveGame(@Validated(ValidationGroupCreate.class) @ModelAttribute TsscGame game,
			BindingResult bindingResult, @RequestParam String action, Model model) {
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
		TsscGame game = businessDelegate.getGame(idGame);
		model.addAttribute("index", false);
		model.addAttribute("idGame", idGame);
		model.addAttribute("tsscstories", businessDelegate.findStoriesByGameId(idGame));
		return "/tsscstory/index";
	}

	@GetMapping("/tsscgames/topic/{id}")
	public String addTopicToGame(@PathVariable("id") long id, Model model) {
		TsscGame game = businessDelegate.getGame(id);
		idGame = id;
		model.addAttribute("tsscGame", game);
		model.addAttribute("tssctopics", businessDelegate.findAllTopics());
		if (game.getTsscTopic() != null) {
			model.addAttribute("topicActual", game.getTsscTopic().getName());
		} else {
			model.addAttribute("topicActual", null);
		}
		return "tsscgames/add-topicgame";
	}

	@PostMapping("/tsscgames/topic/")
	public String saveTopicToGame(Model model, TsscTopic topic) {
		TsscGame game = businessDelegate.getGame(idGame);
		game.setTsscTopic(topic);

		businessDelegate.editGame(game.getId(), game);

		return "redirect:/tsscgames/";
	}

	@GetMapping("/tsscgames/edit/{id}")
	public String showEdit(@PathVariable("id") long id, Model model) {
		TsscGame game = businessDelegate.getGame(id);
		model.addAttribute("tsscGame", game);
		return "tsscgames/edit-game";
	}

	@PostMapping("/tsscgames/edit/{id}")
	public String editGame(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@Validated(ValidationGroupEdit.class) @ModelAttribute TsscGame game, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "tsscgames/edit-game";
		}
		if (action != null && !action.equals("Cancelar")) {
			businessDelegate.editGame(id, game);
		}
		return "redirect:/tsscgames/";
	}
	
	@GetMapping("/tsscgames/timecontrol/{id}")
	public String indexTimeControl(@PathVariable("id") long idGame, Model model) {
//		TsscGame game = businessDelegate.getGame(idGame);
		model.addAttribute("idGame", idGame);
		model.addAttribute("tssctimecontrols", businessDelegate.findAllTimeControlByGameId(idGame));
		return "/tssctimecontrol/index";
	}
	@GetMapping("/tsscgames/query")
	public String indexQuery(Model model) {
		return "tsscgames/query";
	}
	@GetMapping("/tsscgames/query-topics")
	public String indexQueryTopics(Model model) {
		return "tsscgames/query-topics";
	}
	@GetMapping("/tsscgames/query-games")
	public String indexQueryGames(Model model) {
		return "tsscgames/query-games";
	}
	@PostMapping("/tsscgames/listGames")
	public String queryGames(@RequestParam("startDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate, @RequestParam("endDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate, Model model) {
		model.addAttribute("tsscgames", businessDelegate.findByDateRange(startDate, endDate));
		return "tsscgames/postQuery-games.html";
	}
	@PostMapping("/tsscgames/listTopics")
	public String queryTopics(@RequestParam("startDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,Model model) {
		model.addAttribute("tssctopics", businessDelegate.findTopicByDate(startDate));
		return "tsscgames/postQuery-topics.html";
	}

}
