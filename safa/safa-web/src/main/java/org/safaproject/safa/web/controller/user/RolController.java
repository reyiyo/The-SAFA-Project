package org.safaproject.safa.web.controller.user;

import org.safaproject.safa.model.user.Rol;
import org.safaproject.safa.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/rol")
public class RolController {

	@Autowired
	private RolService rolService;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody
	Rol getAvailability(@RequestParam Long id) {
		return rolService.getRol(id);
	}
}
