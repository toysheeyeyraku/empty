package com.gemicle.mailingsettings.configuration;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages ={ "com.gemicle.messaging.repositories" }, mongoTemplateRef = "mainDBTemplate")
public class MainMongoConfig extends AbstractMongoConfiguration {

	@Autowired
	@Qualifier("databaseName")
	private String databaseName;

	@Autowired
	@Qualifier("mongoClient")
	private Mongo mongoClient;

	@Override
	protected String getDatabaseName() {
		return databaseName;
	}

	@Override
	public Mongo mongo() throws Exception {
		return mongoClient;
	}

	@Override
	protected String getMappingBasePackage() {
		return "com.gemicle";
	}
}