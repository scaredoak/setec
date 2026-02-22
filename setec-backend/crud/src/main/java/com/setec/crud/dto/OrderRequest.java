package com.setec.crud.dto;

import java.util.List;

public class OrderRequest {
    public record Client(Long id) {}
    public record Product(Long id) {}

    public Client client;
    public List<Product> products;
}
