package com.gemicle.mailingsettings.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DBMongoConfig {

	@Autowired
	private Environment env;

	@Autowired
	MongoMappingContext mongoMappingContext;

	@Bean(name = "databaseName")
	public String getDatabaseName() {
		return env.getProperty("database.name");
	}

	
	@Bean
	public MongoCredential getCredential() {
		return MongoCredential.createScramSha1Credential(env.getProperty("database.user"), getDatabaseName(),
				env.getProperty("database.password").toCharArray());

	}

	@Bean(name = "mongoClient")
	public MongoClient mongo() {
		ServerAddress addr = new ServerAddress(env.getProperty("database.host"),
				env.getProperty("database.port", Integer.class));
		List<MongoCredential> credentialsList = new ArrayList<>();
		credentialsList.add(getCredential());
		return new MongoClient(addr, credentialsList);
	}

	@Bean(name = "mongoDbFactory()")
	public MongoDbFactory mongoDbFactory() {
		return new SimpleMongoDbFactory(mongo(), getDatabaseName());
	}

	@Primary
	@Bean(name = "mongoConverter")
	public MongoConverter mongoConverter() {
		return new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory()), mongoMappingContext);
	}

	@Bean(name = "mainDBTemplate")
	public MongoTemplate mongoTemplate() {
		MongoTypeMapper typeMapper = new DefaultMongoTypeMapper(null);
		MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory()),
				new MongoMappingContext());
		converter.setTypeMapper(typeMapper);
		return new MongoTemplate(mongoDbFactory(), converter);
	}

}