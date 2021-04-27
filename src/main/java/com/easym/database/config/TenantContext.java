package com.easym.database.config;

public class TenantContext {
    private static final ThreadLocal<Tenant> CONTEXT = new ThreadLocal<>();

    public static void setTenant(Tenant tenant) {
        CONTEXT.set(tenant);
    }

    public static Tenant getTenant() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
