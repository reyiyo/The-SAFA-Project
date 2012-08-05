package org.safaproject.safa.tagging.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.safaproject.safa.node.Tag;
import org.safaproject.safa.node.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@ContextConfiguration(locations = "classpath:tagging-unit-test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TagRepositoryTest {

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private Neo4jTemplate template;

	@Rollback(false)
	@BeforeTransaction
	public void clearDatabase() {
		Neo4jHelper.cleanDb(template);
	}

	@Test
	public void shouldAllowDirectTagTypeCreation() {
		Assert.assertEquals(0, template.count(Tag.class));
		TagType universidad = template.save(new TagType("Universidad"));
		Tag utn = tagRepository.save(new Tag(universidad, "UTN"));
		Assert.assertEquals(1, template.count(Tag.class));
		Tag foundTag = tagRepository.findOne(utn.getNodeId());
		Assert.assertEquals(utn.getValue(), foundTag.getValue());
	}

	@Test
	public void shouldPopulateDBWithTags() {
		Iterable<Tag> tags = this.makeSomeTags();
		Assert.assertNotNull(tags);
	}

	@Test
	public void shouldHaveCorrectNumberOfTags() {
		this.makeSomeTags();
		Assert.assertEquals(9, tagRepository.count());
	}

	@Test
	public void shouldFindAllTags() {
		List<Tag> madeTags = Lists.newArrayList(this.makeSomeTags());
		Iterable<Tag> foundTags = tagRepository.findAll();

		int countOfFoundTags = 0;
		for (Tag foundTag : foundTags) {
			Assert.assertTrue(madeTags.contains(foundTag));
			countOfFoundTags++;
		}

		Assert.assertEquals(madeTags.size(), countOfFoundTags);
	}

	@Test
	public void shouldFindTagsByValue() {
		for (Tag t : this.makeSomeTags()) {
			Assert.assertNotNull(tagRepository.findTagByValue(t.getValue()));
		}
	}

	@Test
	public void shouldFilterTagsWithoutSelectedTags() {
		this.makeSomeTags();
		TagType universidad = null;
		for (TagType type : template.findAll(TagType.class)) {
			if (type.getName().equals("Universidad")) {
				universidad = type;
			}
		}

		Iterable<Tag> tags = tagRepository.filterTags(universidad.getNodeId());
		for (Tag tag : tags) {
			Assert.assertTrue(tag.getValue().equalsIgnoreCase("UTN")
					|| tag.getValue().equalsIgnoreCase("UBA"));
		}
	}

	// TODO: More test cases for fiterTags are needed!
	@Test
	public void shouldFilterTagsWithOneSelectedTag() {
		this.makeSomeTags();
		Tag utn = tagRepository.findByPropertyValue("value", "UTN");

		Iterable<Tag> tags = tagRepository.filterTags(
				Sets.newHashSet(utn.getNodeId()), "Facultad");
		for (Tag tag : tags) {
			System.out.println(tag.getValue());
			Assert.assertTrue("Shall return FRBA", tag.getValue()
					.equalsIgnoreCase("FRBA"));
		}
	}

	@Test
	public void shouldFilterTagsWithManySelectedTags() {
		this.makeSomeTags();
		Tag utn = tagRepository.findByPropertyValue("value", "UTN");
		Tag frba = tagRepository.findByPropertyValue("value", "FRBA");

		Iterable<Tag> tags = tagRepository.filterTags(
				Sets.newHashSet(utn.getNodeId(), frba.getNodeId()), "Materia");

		for (Tag tag : tags) {
			Assert.assertTrue(
					"Shall return UTN-FRBA materias",
					tag.getValue().equalsIgnoreCase("Análisis Matemático I")
							|| tag.getValue().equalsIgnoreCase(
									"Algoritmos y Estructuras de Datos"));
		}
	}

	private Iterable<Tag> makeSomeTags() {

		// Create TagTypes
		TagType universidad = new TagType("Universidad");
		TagType facultad = new TagType("Facultad");
		TagType carrera = new TagType("Carrera");
		TagType materia = new TagType("materia");

		facultad.addDependency(universidad);
		carrera.addDependency(facultad);
		materia.addDependency(carrera);

		template.save(universidad);
		template.save(facultad);
		template.save(carrera);
		template.save(materia);

		// Create some Tags
		Tag utn = new Tag(universidad, "UTN");
		Tag uba = new Tag(universidad, "UBA");
		Tag frba = new Tag(facultad, "FRBA");
		Tag fce = new Tag(facultad, "FCE");
		Tag sistemas = new Tag(carrera, "Ingeniería en Sistemas de Información");
		Tag administracion = new Tag(carrera, "Licenciatura en Administración");
		Tag am1 = new Tag(materia, "Análisis Matemático I");
		Tag algoritmos = new Tag(materia, "Algoritmos y Estructuras de Datos");
		Tag tContable = new Tag(materia, "Teoría Contable");

		frba.addParent(utn);
		fce.addParent(uba);
		sistemas.addParent(frba);
		administracion.addParent(fce);
		am1.addParent(administracion);
		am1.addParent(sistemas);
		algoritmos.addParent(sistemas);
		tContable.addParent(administracion);

		return tagRepository.save(Arrays.asList(utn, uba, frba, fce, sistemas,
				administracion, am1, algoritmos, tContable));
	}

}
