package com.easym.database.test.config;

public class TenantTestContext {
    private static final ThreadLocal<TenantTest> CONTEXT = new ThreadLocal<>();

    public static void setTenant(TenantTest tenant) {
        CONTEXT.set(tenant);
    }

    public static TenantTest getTenant() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
