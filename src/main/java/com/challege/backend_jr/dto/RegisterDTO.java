package com.challege.backend_jr.dto;

import com.challege.backend_jr.enums.RoleEnum;

public record RegisterDTO(String username, String password, RoleEnum role) {
}
