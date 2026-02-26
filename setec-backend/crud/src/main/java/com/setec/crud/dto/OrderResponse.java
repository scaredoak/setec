package com.setec.crud.dto;

import java.util.List;

public class OrderResponse {
    public record Costumer(Long id, String name, String email) {}
    public record Product(Long id, String description, float price, int stackAmount) {}

    public Long id;
    public Costumer costumer;
    public List<Product> products;
}
