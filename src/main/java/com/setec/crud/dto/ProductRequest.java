package com.setec.crud.dto;

public record ProductRequest(
        String description,
        float price,
        int stockAmount
) {}
