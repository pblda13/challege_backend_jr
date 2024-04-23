package com.challege.backend_jr.dto;

import com.challege.backend_jr.enums.RoleEnum;
import jakarta.validation.constraints.NotNull;

public record RegisterDto(@NotNull String username, @NotNull String password, @NotNull RoleEnum role ) {

}