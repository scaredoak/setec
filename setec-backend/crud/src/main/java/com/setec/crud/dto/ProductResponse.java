package com.setec.crud.dto;

import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String description,
        float price,
        Long stockAmount,
        LocalDateTime createdAt
) {
}
