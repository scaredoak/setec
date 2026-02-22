package com.setec.crud.controller;

import com.setec.crud.domain.Client;
import com.setec.crud.domain.Order;
import com.setec.crud.dto.ClientResponse;
import com.setec.crud.dto.OrderRequest;
import com.setec.crud.dto.OrderResponse;
import com.setec.crud.mapper.OrderMapper;
import com.setec.crud.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<Order> orders = orderService.findAll();
        var response = orderMapper.toListOrderResponse(orders);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id) {
        var order = orderService.findById(id);
        var response = orderMapper.toOrderResponse(order);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(@RequestBody @Valid OrderRequest request) {
        var order = orderMapper.toOrder(request);
        var savedOrder = orderService.save(order);
        var response = orderMapper.toOrderResponse(savedOrder);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOrder.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
