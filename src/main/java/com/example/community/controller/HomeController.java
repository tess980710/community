package com.example.community.controller;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");

		logger.info("메인페이지 이동");

		return "home";
	}

}
