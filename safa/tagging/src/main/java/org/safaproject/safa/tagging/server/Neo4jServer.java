package org.safaproject.safa.tagging.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.neo4j.server.WrappingNeoServerBootstrapper;
import org.safaproject.safa.tagging.service.DatabasePopulator;
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
		properties.load(new FileInputStream(
				"src/main/resources/embedded-neo4j.properties"));
		FileUtils.deleteDirectory(new File(properties
				.getProperty("embeddedDbPath")));

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"tagging-applicationContext.xml");
		WrappingNeoServerBootstrapper server = (WrappingNeoServerBootstrapper) context
				.getBean("serverWrapper");

		DatabasePopulator databasePopulator = context
				.getBean(DatabasePopulator.class);

		databasePopulator.populateDb();
		server.start();

	}

}
