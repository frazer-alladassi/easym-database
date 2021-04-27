package com.easym.database.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoUtils {

    public static MongoClient createClient(String connectionUrl, String username, String password, String database) {
        return MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionUrl))
                .credential(MongoCredential.createCredential(username, database, password.toCharArray()))
                .build()
        );
    }

    public static MongoDatabase mongoDatabaseCurrentTenantResolver(String connectionUrl) {
        try {
            Tenant tenant = TenantContext.getTenant();
            return createClient(connectionUrl, tenant.getDatabaseUsername(), tenant.getDatabaseName(), tenant.getDatabasePassword()).getDatabase(tenant.getDatabaseName());
        } catch (NullPointerException exception) {
            throw exception;
        }
    }
}
