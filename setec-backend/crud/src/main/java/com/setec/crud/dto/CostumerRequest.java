package com.setec.crud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CostumerRequest(
        @NotBlank String name,
        @Email String email
) {}
