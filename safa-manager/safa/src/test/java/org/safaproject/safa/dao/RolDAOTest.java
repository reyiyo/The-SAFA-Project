package org.safaproject.safa.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:safa-unit-test-context.xml")
@Transactional
public class RolDAOTest {

	@Autowired
	private RolDAO rolDAO;

	@Test
	public void shallCreateRol() {
		Rol rol = new Rol();
		rol.setName("ADMIN");
		rol = rolDAO.save(rol);

		Rol rolFromDB = rolDAO.findById(rol.getRolId());

		assertEquals(rol, rolFromDB);
	}
	
	@Test
	public void shallFindAll() {
		Rol rol = new Rol();
		rol.setName("ADMIN");
		rol = rolDAO.save(rol);

		Rol rol2 = new Rol();
		rol2.setName("USER");
		rol2 = rolDAO.save(rol2);

		assertEquals(rolDAO.findAll().size(), 2);
	}
	
	@Test
	public void shallFindByExample() {
		Rol rol = new Rol();
		rol.setName("ADMIN");
		rol = rolDAO.save(rol);
		
		Rol example = new Rol();
		example.setName("ADMIN");

		Rol rolFromDB = rolDAO.findByExample(example).get(0);

		assertEquals(rol, rolFromDB);
	}
	
	@Test
	public void shallGetRolCount() {
		Rol rol = new Rol();
		rol.setName("ADMIN");
		rol = rolDAO.save(rol);

		Rol rol2 = new Rol();
		rol2.setName("USER");
		rol2 = rolDAO.save(rol2);

		assertEquals(rolDAO.countAll(), new Long(2));
	}
	
	@Test
	public void shallCountByExample() {
		Rol rol = new Rol();
		rol.setName("ADMIN");
		rol = rolDAO.save(rol);

		Rol rol2 = new Rol();
		rol2.setName("USER");
		rol2 = rolDAO.save(rol2);
		
		Rol example = new Rol();
		example.setName("ADMIN");

		assertEquals(rolDAO.countByExample(example), new Long(1));
	}
	
	@Test
	public void shallDeleteRol() {
		Rol rol = new Rol();
		rol.setName("ADMIN");
		rol = rolDAO.save(rol);

		Rol rol2 = new Rol();
		rol2.setName("USER");
		rol2 = rolDAO.save(rol2);
		
		rolDAO.delete(rol);

		assertEquals(rolDAO.countAll(), new Long(1));
	}
	
	@Test(expected=PersistenceException.class)
	public void shallGetNullNameConstraintViolationException() {
		Rol rol = new Rol();
		rol = rolDAO.save(rol);
	}
	
	@Test(expected=PersistenceException.class)
	public void shallGetNotUniqueNameConstraintViolationException() {
		Rol rol = new Rol();
		rol.setName("ADMIN");
		rol = rolDAO.save(rol);

		Rol rol2 = new Rol();
		rol2.setName("ADMIN");
		rol2 = rolDAO.save(rol2);
	}
	
}
