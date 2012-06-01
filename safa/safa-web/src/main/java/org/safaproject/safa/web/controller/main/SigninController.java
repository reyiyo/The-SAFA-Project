package org.safaproject.safa.web.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Transactional
public class SigninController {

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public void signin() {
	}
}