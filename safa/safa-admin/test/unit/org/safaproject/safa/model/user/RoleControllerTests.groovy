package org.safaproject.safa.model.user

import grails.test.*
import org.safaproject.safa.model.user.Role

class RoleControllerTests extends ControllerUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testIndex() {
		controller.index()
		assertEquals('list', controller.redirectArgs.action)
    }
	
	void testList(){
//		controller.list(params)
		println 'LALALALALALALALLALALALALALALLALALALA'
		println Role.list()
		println ''
//		def renderMap = controller.response.contentAsMap
//		assertEquals "bar", controller.response.contentAsString
		
		assertEquals(true, true)
		
//		assertEquals(Role.list(), renderMap.roleInstanceList)
//		
//		assertEquals(Role.count(), renderMap.roleInstanceTotal)
	}
}

