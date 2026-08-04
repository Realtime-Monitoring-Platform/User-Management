package com.realtime_monitoring.usermanag.config;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.realtime_monitoring.usermanag.config.PermissionDefinition.PermissionDef;

/**
 * Defines the canonical set of roles that should exist in the database,
 * along with the permissions each role is granted.
 * <p>
 * Each role is defined by a name, a description and the set of
 * {@link PermissionDef permissions} it should contain.
 */
public final class RoleDefinition {

    private RoleDefinition() {
    }

    public record RoleDef(String name, String description, Set<String> permissionNames) {
    }

    // ------------------------------------------------------------------
    // Roles
    // ------------------------------------------------------------------

    public static final RoleDef PLATFORM_ADMIN = new RoleDef(
            "PLATFORM_ADMIN",
            "Full access to every resource in the system",
            Set.of(
                    PermissionDefinition.USER_CREATE.name(),
                    PermissionDefinition.USER_READ.name(),
                    PermissionDefinition.USER_UPDATE.name(),
                    PermissionDefinition.USER_DELETE.name(),
                    PermissionDefinition.ROLE_CREATE.name(),
                    PermissionDefinition.ROLE_READ.name(),
                    PermissionDefinition.ROLE_UPDATE.name(),
                    PermissionDefinition.ROLE_DELETE.name(),
                    PermissionDefinition.PERMISSION_READ.name(),
                    PermissionDefinition.PERMISSION_ASSIGN.name(),
                    PermissionDefinition.TENANT_CREATE.name(),
                    PermissionDefinition.TENANT_READ.name(),
                    PermissionDefinition.TENANT_UPDATE.name(),
                    PermissionDefinition.TENANT_DELETE.name(),
                    PermissionDefinition.TEAM_CREATE.name(),
                    PermissionDefinition.TEAM_READ.name(),
                    PermissionDefinition.TEAM_UPDATE.name(),
                    PermissionDefinition.TEAM_DELETE.name(),

                    PermissionDefinition.DEVICE_CREATE.name(),
                    PermissionDefinition.DEVICE_READ.name(),
                    PermissionDefinition.DEVICE_UPDATE.name(),
                    PermissionDefinition.DEVICE_DELETE.name(),
                    PermissionDefinition.DEVICE_COMMAND.name(),

                    PermissionDefinition.AI_ANALYZE.name(),
                    PermissionDefinition.AI_READ.name(),

                    PermissionDefinition.SYSTEM_CONFIG_READ.name(),
                    PermissionDefinition.SYSTEM_CONFIG_UPDATE.name(),
                    PermissionDefinition.SYSTEM_LOGS_READ.name()));

    public static final RoleDef TENANT_ADMIN = new RoleDef(
            "ADMIN",
            "Manages users, roles, teams and devices within their tenant",
            Set.of(
                    PermissionDefinition.USER_CREATE.name(),
                    PermissionDefinition.USER_READ.name(),
                    PermissionDefinition.USER_UPDATE.name(),
                    PermissionDefinition.USER_DELETE.name(),
                    PermissionDefinition.ROLE_CREATE.name(),
                    PermissionDefinition.ROLE_READ.name(),
                    PermissionDefinition.ROLE_UPDATE.name(),
                    PermissionDefinition.ROLE_DELETE.name(),
                    PermissionDefinition.PERMISSION_READ.name(),
                    PermissionDefinition.PERMISSION_ASSIGN.name(),
                    PermissionDefinition.TEAM_CREATE.name(),
                    PermissionDefinition.TEAM_READ.name(),
                    PermissionDefinition.TEAM_UPDATE.name(),
                    PermissionDefinition.TEAM_DELETE.name(),
                    PermissionDefinition.DEVICE_CREATE.name(),
                    PermissionDefinition.DEVICE_READ.name(),
                    PermissionDefinition.DEVICE_UPDATE.name(),
                    PermissionDefinition.DEVICE_DELETE.name(),
                    PermissionDefinition.DEVICE_COMMAND.name(),
                    PermissionDefinition.AI_ANALYZE.name(),
                    PermissionDefinition.AI_READ.name()));

    public static final RoleDef MANAGER = new RoleDef(
            "MANAGER",
            "Manages teams and devices, can view users and reports",
            Set.of(

                    PermissionDefinition.USER_READ.name(),
                    PermissionDefinition.ROLE_READ.name(),
                    PermissionDefinition.PERMISSION_READ.name(),
                    PermissionDefinition.TEAM_CREATE.name(),
                    PermissionDefinition.TEAM_READ.name(),
                    PermissionDefinition.TEAM_UPDATE.name(),
                    PermissionDefinition.TEAM_DELETE.name(),
                    PermissionDefinition.DEVICE_CREATE.name(),
                    PermissionDefinition.DEVICE_READ.name(),
                    PermissionDefinition.DEVICE_UPDATE.name(),
                    PermissionDefinition.DEVICE_DELETE.name(),
                    PermissionDefinition.DEVICE_COMMAND.name(),
                    PermissionDefinition.AI_ANALYZE.name(),
                    PermissionDefinition.AI_READ.name()));

    public static final RoleDef OPERATOR = new RoleDef(
            "OPERATOR",
            "Operates devices and sends commands, can view dashboards",
            Set.of(
                    PermissionDefinition.USER_READ.name(),

                    PermissionDefinition.ROLE_READ.name(),

                    PermissionDefinition.PERMISSION_READ.name(),

                    PermissionDefinition.TEAM_READ.name(),

                    PermissionDefinition.DEVICE_READ.name(),
                    PermissionDefinition.DEVICE_UPDATE.name(),
                    PermissionDefinition.DEVICE_COMMAND.name(),

                    PermissionDefinition.AI_READ.name()));

    public static final RoleDef VIEWER = new RoleDef(
            "VIEWER",
            "Read-only access to devices, teams and reports",
            Set.of(
                    PermissionDefinition.USER_READ.name(),

                    PermissionDefinition.ROLE_READ.name(),

                    PermissionDefinition.PERMISSION_READ.name(),

                    PermissionDefinition.TEAM_READ.name(),

                    PermissionDefinition.DEVICE_READ.name(),

                    PermissionDefinition.AI_READ.name()));

    /**
     * Returns the complete list of all role definitions that should exist
     * in the database.
     */
    public static List<RoleDef> all() {
        return Arrays.asList(
                PLATFORM_ADMIN,
                TENANT_ADMIN,
                MANAGER,
                OPERATOR,
                VIEWER);
    }
}