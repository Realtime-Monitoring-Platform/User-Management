package com.realtime_monitoring.user_manag.config;

import java.util.Arrays;
import java.util.List;


public final class PermissionDefinition {

        private PermissionDefinition() {
        }

        public record PermissionDef(String name, String description, String resource, String action) {
        }

        public static final PermissionDef USER_CREATE = new PermissionDef(
                        "USER_CREATE", "Create new users", "USER", "CREATE");
        public static final PermissionDef USER_READ = new PermissionDef(
                        "USER_READ", "View user details", "USER", "READ");
        public static final PermissionDef USER_UPDATE = new PermissionDef(
                        "USER_UPDATE", "Update existing users", "USER", "UPDATE");
        public static final PermissionDef USER_DELETE = new PermissionDef(
                        "USER_DELETE", "Delete users", "USER", "DELETE");

        public static final PermissionDef ROLE_CREATE = new PermissionDef(
                        "ROLE_CREATE", "Create new roles", "ROLE", "CREATE");
        public static final PermissionDef ROLE_READ = new PermissionDef(
                        "ROLE_READ", "View role details", "ROLE", "READ");
        public static final PermissionDef ROLE_UPDATE = new PermissionDef(
                        "ROLE_UPDATE", "Update existing roles", "ROLE", "UPDATE");
        public static final PermissionDef ROLE_DELETE = new PermissionDef(
                        "ROLE_DELETE", "Delete roles", "ROLE", "DELETE");

        public static final PermissionDef PERMISSION_READ = new PermissionDef(
                        "PERMISSION_READ", "View permissions", "PERMISSION", "READ");
        public static final PermissionDef PERMISSION_ASSIGN = new PermissionDef(
                        "PERMISSION_ASSIGN", "Assign permissions to roles", "PERMISSION", "ASSIGN");

        public static final PermissionDef TENANT_CREATE = new PermissionDef(
                        "TENANT_CREATE", "Create new tenants", "TENANT", "CREATE");
        public static final PermissionDef TENANT_READ = new PermissionDef(
                        "TENANT_READ", "View tenant details", "TENANT", "READ");
        public static final PermissionDef TENANT_UPDATE = new PermissionDef(
                        "TENANT_UPDATE", "Update existing tenants", "TENANT", "UPDATE");
        public static final PermissionDef TENANT_DELETE = new PermissionDef(
                        "TENANT_DELETE", "Delete tenants", "TENANT", "DELETE");

        public static final PermissionDef TEAM_CREATE = new PermissionDef(
                        "TEAM_CREATE", "Create new teams", "TEAM", "CREATE");
        public static final PermissionDef TEAM_READ = new PermissionDef(
                        "TEAM_READ", "View team details", "TEAM", "READ");
        public static final PermissionDef TEAM_UPDATE = new PermissionDef(
                        "TEAM_UPDATE", "Update existing teams", "TEAM", "UPDATE");
        public static final PermissionDef TEAM_DELETE = new PermissionDef(
                        "TEAM_DELETE", "Delete teams", "TEAM", "DELETE");

        public static final PermissionDef DEVICE_CREATE = new PermissionDef(
                        "DEVICE_CREATE", "Register new devices", "DEVICE", "CREATE");
        public static final PermissionDef DEVICE_READ = new PermissionDef(
                        "DEVICE_READ", "View device details", "DEVICE", "READ");
        public static final PermissionDef DEVICE_UPDATE = new PermissionDef(
                        "DEVICE_UPDATE", "Update device configuration", "DEVICE", "UPDATE");
        public static final PermissionDef DEVICE_DELETE = new PermissionDef(
                        "DEVICE_DELETE", "Delete devices", "DEVICE", "DELETE");
        public static final PermissionDef DEVICE_COMMAND = new PermissionDef(
                        "DEVICE_COMMAND", "Send commands to devices", "DEVICE", "COMMAND");

        public static final PermissionDef AI_ANALYZE = new PermissionDef(
                        "AI_ANALYZE", "Run AI-powered analysis", "AI", "ANALYZE");
        public static final PermissionDef AI_READ = new PermissionDef(
                        "AI_READ", "View AI analysis results", "AI", "READ");

        public static final PermissionDef SYSTEM_CONFIG_READ = new PermissionDef(
                        "SYSTEM_CONFIG_READ", "View system configuration", "SYSTEM", "CONFIG_READ");
        public static final PermissionDef SYSTEM_CONFIG_UPDATE = new PermissionDef(
                        "SYSTEM_CONFIG_UPDATE", "Update system configuration", "SYSTEM", "CONFIG_UPDATE");
        public static final PermissionDef SYSTEM_LOGS_READ = new PermissionDef(
                        "SYSTEM_LOGS_READ", "View system logs", "SYSTEM", "LOGS_READ");

        /**
         * Returns the complete list of all permission definitions that should
         * exist in the database.
         */
        public static List<PermissionDef> all() {
                return Arrays.asList(
                                USER_CREATE, USER_READ, USER_UPDATE, USER_DELETE,
                                ROLE_CREATE, ROLE_READ, ROLE_UPDATE, ROLE_DELETE,
                                PERMISSION_READ, PERMISSION_ASSIGN,
                                TENANT_CREATE, TENANT_READ, TENANT_UPDATE, TENANT_DELETE,
                                TEAM_CREATE, TEAM_READ, TEAM_UPDATE, TEAM_DELETE,
                                DEVICE_CREATE, DEVICE_READ, DEVICE_UPDATE, DEVICE_DELETE, DEVICE_COMMAND,
                                AI_ANALYZE, AI_READ,

                                SYSTEM_CONFIG_READ, SYSTEM_CONFIG_UPDATE, SYSTEM_LOGS_READ);
        }
}