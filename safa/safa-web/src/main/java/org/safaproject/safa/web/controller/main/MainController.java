package org.safaproject.safa.web.controller.main;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {

	private static Logger logger = Logger.getLogger("MainController");

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getMainPage() {
		logger.debug("Received request to show main page");
		
		return "index";
	}
}
