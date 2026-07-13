package com.realtime_monitoring.user_manag.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserResponse {

    private String message;
    
    private UserResponse user;
}
