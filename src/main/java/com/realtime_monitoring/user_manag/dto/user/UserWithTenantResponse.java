package com.realtime_monitoring.user_manag.dto.user;

import java.util.UUID;

import com.realtime_monitoring.user_manag.dto.tenant.TenantDto;

public record UserWithTenantResponse(

UUID id,

String username,

String email,

TenantDto tenant

){}