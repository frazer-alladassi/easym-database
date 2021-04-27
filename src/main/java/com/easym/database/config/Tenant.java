package com.easym.database.config;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tenant {

    private String databaseName;
    private String databaseUsername;
    private String databasePassword;
}
