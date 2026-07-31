package com.realtime_monitoring.usermanag.dto.tenant;

import java.util.UUID;

public record TenantDto(

UUID id,

String name,

String status

){}