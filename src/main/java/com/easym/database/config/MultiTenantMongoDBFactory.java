package com.easym.database.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.easym.database.repositories")
@Configuration
@PropertySource("classpath:easym-database.properties")
@Import(MongoDataSource.class)
public class MultiTenantMongoDBFactory extends SimpleMongoClientDatabaseFactory {

    @Value("${mongo.connection.url}")
    private String connectionUrl;

    public MultiTenantMongoDBFactory(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }

    @Override
    protected MongoDatabase doGetMongoDatabase(String dbName) {
        return MongoUtils.mongoDatabaseCurrentTenantResolver(connectionUrl);
    }


    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(this);
    }
}
