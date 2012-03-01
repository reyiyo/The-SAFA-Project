package org.safaproject.safa.service;

import org.safaproject.safa.dao.RolDAO;
import org.safaproject.safa.model.user.Rol;
import org.springframework.beans.factory.annotation.Autowired;

public class RolServiceImpl implements RolService {

	@Autowired
	private RolDAO rolDAO;

	@Override
	public Rol getRol(Long rolId) {
		return rolDAO.findById(rolId);
	}

}
