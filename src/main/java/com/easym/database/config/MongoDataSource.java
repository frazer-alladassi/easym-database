package com.easym.database.config;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:easym-database.properties")
public class MongoDataSource {

    @Value("${mongo.connection.url}")
    private String connectionUrl;

    @Value("${mongo.connection.username}")
    private String username;

    @Value("${mongo.connection.password}")
    private String password;

    @Value("${mongo.connection.admin-db-name}")
    private String adminDbName;


    @Bean
    public String databaseName() {
        return adminDbName;
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoUtils.createClient(connectionUrl, username, password, databaseName());
    }
}
