package com.trace.user_service.dto;

import com.trace.user_service.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String email;
    private String password;
    private String fullName;
    private Role role;

}
