package com.easym.database.test.config;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

@Configuration
@TestPropertySource("classpath:easym-database-test.properties")
public class MongoTestDataSource {
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
        return MongoTestUtils.createClient(connectionUrl, username, password.toCharArray(), databaseName());
    }
}
