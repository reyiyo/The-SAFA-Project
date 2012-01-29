package org.safaproject.safa.web.controller.ajax;

import org.safaproject.safa.dao.RolDAO;
import org.safaproject.safa.model.Rol;
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
	private RolDAO rolDAO;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody Rol getAvailability(@RequestParam Long id) {
		return rolDAO.findById(id);
	}
}
