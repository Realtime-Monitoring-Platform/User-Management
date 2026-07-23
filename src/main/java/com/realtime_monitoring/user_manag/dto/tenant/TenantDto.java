package com.realtime_monitoring.user_manag.dto.tenant;

import java.util.UUID;

public record TenantDto(

UUID id,

String name,

String status

){}