package com.realtime_monitoring.usermanag.dto.user;

import java.util.UUID;

import com.realtime_monitoring.usermanag.dto.tenant.TenantDto;

public record UserWithTenantResponse(

UUID id,

String username,

String email,

TenantDto tenant

){}