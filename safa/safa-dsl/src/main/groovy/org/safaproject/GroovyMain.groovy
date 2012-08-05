package org.safaproject
import org.safaproject.JavaHello

class GroovyMain {
	static void main(String... args) {
		new GroovyHello().sayHello()
		new JavaHello().sayHello()
	}
}