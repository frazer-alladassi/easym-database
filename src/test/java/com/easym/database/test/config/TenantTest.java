package com.easym.database.test.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TenantTest {
    private String databaseName;
    private String databaseUsername;
    private char[] databasePassword;
}
