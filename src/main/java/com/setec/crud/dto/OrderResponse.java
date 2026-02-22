package com.setec.crud.dto;

import java.util.List;

public class OrderResponse {
    public record Client(Long id, String name, String email) {}
    public record Product(Long id, String description, float price, int stackAmount) {}

    public Client client;
    public List<Product> products;
}
