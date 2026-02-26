package com.setec.crud.dto;

import java.time.LocalDateTime;

public record CostumerResponse(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt
) {}
