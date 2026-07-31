package com.realtime_monitoring.usermanag.dto.user;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserWithTenantDto {
    UUID id;
    String username;
    String email;
    String firstName;
    String lastName;
    String phone;
    String avatarUrl;
    String address;
    String status;
    UUID tenantId;
    TenantSummaryDto tenant;
}
