package org.safaproject.safa.dsl.filter

class Filter {

	def tag;
	
	def filters = []
	
	def Filter(String tag) {
		this.tag = tag;
	}
	
	def is(String tagValue) {
		filters.add { testSubject -> testSubject.value.equals tagValue }
		return this
	}
	
	def evaluate(testSubject) {
		filters.every { it testSubject }
	}
}
