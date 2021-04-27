package com.easym.database.test.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoTestUtils {

    public static MongoClient createClient(String connectionUrl, String username, char[] password, String database) {
        MongoCredential credential = MongoCredential.createCredential(username, database, password);

        return MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionUrl))
                .credential(credential)
                .build()
        );
    }

    public static MongoDatabase mongoDatabaseCurrentTenantResolver(String connectionUrl) {
        try {
            TenantTest tenant = TenantTestContext.getTenant();
            return createClient(connectionUrl, tenant.getDatabaseUsername(), tenant.getDatabasePassword(), tenant.getDatabaseName()).getDatabase(tenant.getDatabaseName());
        } catch (NullPointerException exception) {
            throw exception;
        }
    }
}
