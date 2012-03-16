package org.safaproject.safa.service;

import org.safaproject.safa.dao.RoleDAO;
import org.safaproject.safa.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;

	@Override
	public Role getRole(Long roleId) {
		return roleDAO.findById(roleId);
	}

}
