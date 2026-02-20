package com.setec.crud.service;

import com.setec.crud.domain.Product;
import com.setec.crud.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));
    }

    public Product findByDescription(String description) {
        return productRepository.findByDescription(description)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with description: " + description));
    }

    public void update(Product product) {
        findById(product.getId());
        save(product);
    }

    public void delete(Long id) {
        findById(id);
        productRepository.deleteById(id);
    }
}
