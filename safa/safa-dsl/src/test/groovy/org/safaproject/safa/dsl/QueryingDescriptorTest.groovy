package org.safaproject.safa.dsl
import org.safaproject.safa.dsl.filter.Filter


class QueryingDescriptorTest extends GroovyTestCase {
	
	void testExecutes() {
		assertTrue new QueryingDescriptor().parse("true")
	}
	
	void testIsBindingDefined() {
		assertTrue new QueryingDescriptor().parse("binding != null")
	}
	
	void testValidatesAnIs() {
		assertTrue new QueryingDescriptor().parse("tipo.is 'test'").evaluate(new TestTag())
	}

	/** FILTER TESTS **/
	
	void testFilterIs() {
		def filter = new Filter("tipo")
		filter.is("test")
		assertTrue filter.evaluate(new TestTag())
	}
	
	void testFilterIsFails() {
		def filter = new Filter("tipo")
		filter.is("not-test")
		assertFalse filter.evaluate(new TestTag())
	}
}

class TestTag {
	def getValue() {
		return "test"
	}
} 