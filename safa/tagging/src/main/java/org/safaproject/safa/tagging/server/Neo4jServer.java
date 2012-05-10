package org.safaproject.safa.tagging.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.neo4j.server.WrappingNeoServerBootstrapper;
import org.safaproject.safa.node.Tag;
import org.safaproject.safa.node.TagType;
import org.safaproject.safa.tagging.repository.TagRepository;
import org.safaproject.safa.tagging.repository.TagTypeRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Neo4jServer {

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		Properties properties = new Properties();
		properties.load(new FileInputStream("src/main/resources/embedded-neo4j.properties"));
		deleteFileOrDirectory(new File(properties.getProperty("embeddedDbPath")));

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"tagging-applicationContext.xml");
		WrappingNeoServerBootstrapper server = (WrappingNeoServerBootstrapper) context
				.getBean("serverWrapper");
		TagTypeRepository tagTypeRepository = context
				.getBean(TagTypeRepository.class);
		TagRepository tagRepository = context.getBean(TagRepository.class);

		// Create TagTypes
		TagType universidad = new TagType("Universidad");
		TagType facultad = new TagType("Facultad");
		TagType carrera = new TagType("Carrera");
		TagType materia = new TagType("Materia");

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

		tagRepository.save(Arrays.asList(utn, uba, frba, fce, sistemas,
				administracion, am1, algoritmos, tContable));

		tagRepository.findAll();

		server.start();

	}

	public static void deleteFileOrDirectory(final File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				for (File child : file.listFiles()) {
					deleteFileOrDirectory(child);
				}
			}
			file.delete();
		}
	}

}
