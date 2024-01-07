package com.alibou.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum representing different permissions available in the application.
 */
@RequiredArgsConstructor
public enum Permission {

    // Permissions for administrators
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    // Permissions for managers
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),

    // Add more permissions as needed

    ;

    // The actual permission string
    @Getter
    private final String permission;
}
