package com.setec.crud.dto;

import java.time.LocalDateTime;

public record ClientResponse(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt
) {}
