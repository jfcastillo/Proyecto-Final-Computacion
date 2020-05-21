package com.taller.castillo.felipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.taller.castillo.felipe.service.TsscAdminService;
@Controller
public class TsscAdminController {
	@Autowired
	private TsscAdminService adminService;
	
	@GetMapping("/login")
	public String log() {
		
		return "tsscadmin/login";
	}

}
