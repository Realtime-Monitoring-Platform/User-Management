package com.realtime_monitoring.user_manag.dto.user;

import com.realtime_monitoring.user_manag.model.UserStatus;

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
	private Long tenantId;
	private UserStatus status;
}
