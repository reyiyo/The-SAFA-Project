package org.safaproject.safa.social.service;

import org.junit.*;

public class URLFactoryTest {

	private URLFactory urlFactory;
	
	@Before
	public void init() {
		urlFactory = new URLFactory();
		urlFactory.setDomain("exampleDomain");
		urlFactory.setContentTemplate("exampleContentTemplateId=");
	}
	
	@Test
	public void shallGenerateTheURLWithTheIdOfTheContent() {
		String generatedURL = urlFactory.generateAbsoluteContentURL(new Long(1));
		
		Assert.assertEquals("exampleDomain/exampleContentTemplateId=1", generatedURL);
	}
}
