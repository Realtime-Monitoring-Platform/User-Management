package com.realtime_monitoring.usermanag.dto.user;

import java.util.UUID;

import com.realtime_monitoring.usermanag.model.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
	private String avatarUrl;
	private UUID tenantId;
	private UserStatus status;
	private UUID roleId;
    private UUID teamId;
}
