package com.setec.crud.dto;

import jakarta.validation.constraints.NotBlank;

public record ProductRequest(
        @NotBlank String description,
        float price,
        Long stockAmount
) {
}
