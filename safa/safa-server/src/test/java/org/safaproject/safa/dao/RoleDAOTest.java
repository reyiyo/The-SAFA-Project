package org.safaproject.safa.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:safa-unit-test-context.xml")
@Transactional
public class RoleDAOTest {

	@Autowired
	private RoleDAO roleDAO;

	@Test
	public void shallCreateRole() {
		Role role = new Role();
		role.setName("ADMIN");
		roleDAO.save(role);

		Role roleFromDB = roleDAO.findById(role.getRoleId());

		assertEquals(role, roleFromDB);
	}
	
	@Test
	public void shallFindAll() {
		Role role = new Role();
		role.setName("ADMIN");
		roleDAO.save(role);

		Role role2 = new Role();
		role2.setName("USER");
		roleDAO.save(role2);

		assertEquals(roleDAO.findAll().size(), 2);
	}
	
	@Test
	public void shallGetRoleCount() {
		Role role = new Role();
		role.setName("ADMIN");
		roleDAO.save(role);

		Role role2 = new Role();
		role2.setName("USER");
		roleDAO.save(role2);

		assertEquals(roleDAO.countAll(), new Long(2));
	}
	
	@Test
	public void shallDeleteRole() {
		Role role = new Role();
		role.setName("ADMIN");
		roleDAO.save(role);

		Role role2 = new Role();
		role2.setName("USER");
		roleDAO.save(role2);
		
		roleDAO.delete(role);

		assertEquals(roleDAO.countAll(), new Long(1));
	}
	
	@Test(expected=PersistenceException.class)
	public void shallGetNullNameConstraintViolationException() {
		Role role = new Role();
		roleDAO.save(role);
	}
	
	@Test(expected=PersistenceException.class)
	public void shallGetNotUniqueNameConstraintViolationException() {
		Role role = new Role();
		role.setName("ADMIN");
		roleDAO.save(role);

		Role role2 = new Role();
		role2.setName("ADMIN");
		roleDAO.save(role2);
	}
	
}
