package org.safaproject.safa.tagging.service;

import java.util.Arrays;

import org.neo4j.helpers.collection.ClosableIterable;
import org.safaproject.safa.node.Tag;
import org.safaproject.safa.node.TagType;
import org.safaproject.safa.tagging.repository.TagRepository;
import org.safaproject.safa.tagging.repository.TagTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabasePopulator {

	@Autowired
	private TagTypeRepository tagTypeRepository;

	@Autowired
	private TagRepository tagRepository;

	public ClosableIterable<Tag> populateDb() {
		// Create TagTypes
		TagType universidad = new TagType("Universidad");
		TagType facultad = new TagType("Facultad");
		TagType carrera = new TagType("Carrera");
		TagType materia = new TagType("Materia");
		TagType profesor = new TagType("Profesor");

		facultad.addDependency(universidad);
		carrera.addDependency(facultad);
		materia.addDependency(carrera);
		profesor.addDependency(materia);

		universidad = tagTypeRepository.save(universidad);
		facultad = tagTypeRepository.save(facultad);
		carrera = tagTypeRepository.save(carrera);
		materia = tagTypeRepository.save(materia);
		profesor = tagTypeRepository.save(profesor);

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
		Tag capurro = new Tag(profesor, "Lidia Capurro");

		frba.addParent(utn);
		fce.addParent(uba);
		sistemas.addParent(frba);
		administracion.addParent(fce);
		am1.addParent(administracion);
		am1.addParent(sistemas);
		algoritmos.addParent(sistemas);
		tContable.addParent(administracion);

		tagRepository.save(Arrays.asList(utn, uba, frba, fce, sistemas,
				administracion, am1, algoritmos, tContable, capurro));

		return tagRepository.findAll();
	}

	public TagTypeRepository getTagTypeRepository() {
		return tagTypeRepository;
	}

	public void setTagTypeRepository(TagTypeRepository tagTypeRepository) {
		this.tagTypeRepository = tagTypeRepository;
	}

	public TagRepository getTagRepository() {
		return tagRepository;
	}

	public void setTagRepository(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

}