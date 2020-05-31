package com.taller.castillo.felipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class TsscAdminController {
	
	@GetMapping("/login")
	public String log() {
		return "tsscadmin/login";
	}

}
