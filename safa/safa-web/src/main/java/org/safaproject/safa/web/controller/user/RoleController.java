package org.safaproject.safa.web.controller.user;

import org.safaproject.safa.model.user.Role;
import org.safaproject.safa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody
	Role getAvailability(@RequestParam Long id) {
		return roleService.getRole(id);
	}
}
