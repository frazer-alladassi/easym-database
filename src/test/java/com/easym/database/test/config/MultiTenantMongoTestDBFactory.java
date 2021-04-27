package com.easym.database.test.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.TestPropertySource;

@EnableMongoRepositories(basePackages = "com.easym.database.repositories")
@Configuration
@TestPropertySource("classpath:easym-database-test.properties")
@Import(MongoTestDataSource.class)
public class MultiTenantMongoTestDBFactory extends SimpleMongoClientDatabaseFactory {

    @Value("${mongo.connection.url}")
    private String connectionUrl;

    public MultiTenantMongoTestDBFactory(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }

    @Override
    protected MongoDatabase doGetMongoDatabase(String dbName) {
        return MongoTestUtils.mongoDatabaseCurrentTenantResolver(connectionUrl);
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(this);
    }
}
