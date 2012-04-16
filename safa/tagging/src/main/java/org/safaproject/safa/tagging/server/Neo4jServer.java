package org.safaproject.safa.tagging.server;

import java.util.Arrays;

import org.neo4j.server.WrappingNeoServerBootstrapper;
import org.safaproject.safa.tagging.node.Tag;
import org.safaproject.safa.tagging.node.TagType;
import org.safaproject.safa.tagging.repository.TagRepository;
import org.safaproject.safa.tagging.repository.TagTypeRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Neo4jServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"tagging-applicationContext.xml");
		WrappingNeoServerBootstrapper server = (WrappingNeoServerBootstrapper) context
				.getBean("serverWrapper");
		TagTypeRepository tagTypeRepository = context.getBean(TagTypeRepository.class);
		TagRepository tagRepository = context.getBean(TagRepository.class);

		// Create TagTypes
		TagType universidad = new TagType("Universidad");
		TagType facultad = new TagType("Facultad");
		TagType carrera = new TagType("Carrera");
		TagType materia = new TagType("materia");

		facultad.addDependency(universidad);
		carrera.addDependency(facultad);
		materia.addDependency(carrera);

		tagTypeRepository.save(universidad);
		tagTypeRepository.save(facultad);
		tagTypeRepository.save(carrera);
		tagTypeRepository.save(materia);

		// Create some Tags
		Tag utn = new Tag(universidad, "UTN");
		Tag uba = new Tag(universidad, "UBA");
		Tag frba = new Tag(facultad, "FRBA");
		Tag fce = new Tag(universidad, "FCE");
		Tag sistemas = new Tag(universidad,
				"Ingeniería en Sistemas de Información");
		Tag administracion = new Tag(universidad,
				"Licenciatura en Administración");
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

		tagRepository.save(Arrays.asList(utn, uba, frba, fce, sistemas,
				administracion, am1, algoritmos, tContable));
		
		tagRepository.findAll();

		server.start();

	}

}
