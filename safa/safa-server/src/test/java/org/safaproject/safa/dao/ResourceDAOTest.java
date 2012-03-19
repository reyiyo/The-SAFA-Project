package org.safaproject.safa.dao;

import javax.validation.ConstraintViolationException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.model.content.Resource;
import org.safaproject.safa.model.content.builder.ResourceBuilder;
import org.safaproject.safa.model.tag.Tag;
import org.safaproject.safa.model.tag.TagDataTypes;
import org.safaproject.safa.model.tag.TagType;
import org.safaproject.safa.model.tag.builder.TagBuilder;
import org.safaproject.safa.model.tag.builder.TagTypeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:safa-unit-test-context.xml")
@Transactional
public class ResourceDAOTest {

	private TagType testTagType = new TagTypeBuilder()
			.withTagName("Resource Type").withTagDataType(TagDataTypes.STRING)
			.build();

	private Tag testResourceType = new TagBuilder().withTagType(testTagType)
			.withValue("PDF").build();
	
	@Autowired
	private TagTypeDAO tagTypeDAO;

	@Autowired
	private TagDAO tagDAO;
	
	@Autowired
	private ResourceDAO resourceDAO;
	
	@Before
	public void init() {
		tagTypeDAO.save(testTagType);
		tagDAO.save(testResourceType);
	}
	
	@Test
	public void shallCreateResource() {
		Resource testResource = new ResourceBuilder().withDescription("")
				.withSize(10L).withUrl("http://tuvieja.com")
				.withResourceType(testResourceType).build();
		
		resourceDAO.save(testResource);
		
		Resource resourceFromDB = resourceDAO.findById(testResource.getResourceId());
		
		Assert.assertEquals(testResource, resourceFromDB);
	}
	
	@Test
	public void shallFindAll() {
		Resource testResource = new ResourceBuilder().withDescription("no la toco ni con un palo")
				.withSize(10L).withUrl("http://tuvieja.com")
				.withResourceType(testResourceType).build();
		Resource testResource2 = new ResourceBuilder().withDescription("le re doy")
				.withSize(20L).withUrl("http://tuhermana.com")
				.withResourceType(testResourceType).build();
		
		resourceDAO.save(testResource);
		resourceDAO.save(testResource2);
		
		Assert.assertEquals(2, resourceDAO.findAll().size());
	}
	
	@Test
	public void shallFindByExample() {
		Long size = 10L;
		String URL = "http://tuvieja.com";
		Resource testResource = new ResourceBuilder().withDescription("no la toco ni con un palo")
				.withSize(size).withUrl(URL)
				.withResourceType(testResourceType).build();
		resourceDAO.save(testResource);
		
		Resource resourceByExample = resourceDAO.findByExample(new ResourceBuilder().withDescription("no la toco ni con un palo")
				.withSize(size).withUrl(URL)
				.withResourceType(testResourceType).build()).get(0);
		
		Assert.assertEquals(testResource, resourceByExample);
	}
	
	@Test
	public void shallCountByExample() {
		Long size = 10L;
		String URL = "http://tuvieja.com";
		Resource testResource = new ResourceBuilder().withDescription("no la toco ni con un palo")
				.withSize(size).withUrl(URL)
				.withResourceType(testResourceType).build();
		resourceDAO.save(testResource);
		
		Long countByExample = resourceDAO.countByExample(new ResourceBuilder().withDescription("no la toco ni con un palo")
				.withSize(size).withUrl(URL)
				.withResourceType(testResourceType).build());
		
		Assert.assertEquals(new Long(1), countByExample);
	}
	
	@Test
	public void shallDeleteResource() {
		Resource testResource = new ResourceBuilder().withDescription("no la toco ni con un palo")
				.withSize(10L).withUrl("http://tuvieja.com")
				.withResourceType(testResourceType).build();
		Resource testResource2 = new ResourceBuilder().withDescription("le re doy")
				.withSize(20L).withUrl("http://tuhermana.com")
				.withResourceType(testResourceType).build();
		resourceDAO.save(testResource);
		resourceDAO.save(testResource2);
		
		Assert.assertEquals(new Long(2), resourceDAO.countAll());
		resourceDAO.delete(testResource);
		Assert.assertEquals(new Long(1), resourceDAO.countAll());
		resourceDAO.delete(testResource2);
		Assert.assertEquals(new Long(0), resourceDAO.countAll());
	}
	
	@Test
	public void shallUpdateResource() {
		String newURL = "http://tuhermana.com";
		Resource testResource = new ResourceBuilder().withDescription("no la toco ni con un palo")
				.withSize(10L).withUrl("http://tuvieja.com")
				.withResourceType(testResourceType).build();
		resourceDAO.save(testResource);
		
		testResource.setUrl(newURL);
		resourceDAO.save(testResource);
		
		Assert.assertEquals(newURL, resourceDAO.findAll().get(0).getUrl());
	}
	
	@Test(expected=NullPointerException.class)
	public void shallFailBecauseOfNotURL() {
		Resource testResource = new ResourceBuilder().withDescription("")
				.withSize(10L).withUrl(null)
				.withResourceType(testResourceType).build();
		
		resourceDAO.save(testResource);
	}
	
	@Test(expected=ConstraintViolationException.class)
	public void shallFailBecauseNegativeSize() {
		Resource testResource = new ResourceBuilder().withDescription("")
				.withSize(-10L).withUrl("")
				.withResourceType(testResourceType).build();
		
		resourceDAO.save(testResource);
	}
	
	@Test
	public void shallRetrieveResourceType() {
		Resource testResource = new ResourceBuilder().withDescription("")
				.withSize(10L).withUrl("")
				.withResourceType(testResourceType).build();
		resourceDAO.save(testResource);
		
		Assert.assertEquals(testResourceType, resourceDAO.findAll().get(0).getResourceType());
	}
}
