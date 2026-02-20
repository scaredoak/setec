package com.setec.crud.controller;

import com.setec.crud.domain.Product;
import com.setec.crud.dto.ProductRequest;
import com.setec.crud.dto.ProductResponse;
import com.setec.crud.mapper.ProductMapper;
import com.setec.crud.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<Product> products = productService.findAll();
        var response = productMapper.toListProductResponse(products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        var product = productService.findById(id);
        var response = productMapper.toProductResponse(product);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<ProductResponse> getByDescription(@PathVariable String description) {
        var product = productService.findByDescription(description);
        var response = productMapper.toProductResponse(product);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody @Valid ProductRequest request) {
        var product = productMapper.toProduct(request);
        var savedProduct = productService.save(product);
        var response = productMapper.toProductResponse(savedProduct);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
