package com.realtime_monitoring.usermanag.dto.user;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TenantSummaryDto {
    UUID id;
    String name;
    String companyName;
    String status;
    String email;
    String phone;
}
