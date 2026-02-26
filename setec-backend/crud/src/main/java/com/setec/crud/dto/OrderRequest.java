package com.setec.crud.dto;

import java.util.List;

public class OrderRequest {
    public record Costumer(Long id) {}
    public record Product(Long id) {}

    public Costumer costumer;
    public List<Product> products;
}
