package com.setec.crud.service;

import com.setec.crud.domain.Order;
import com.setec.crud.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CostumerService costumerService;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with id: " + id));
    }

    public List<Order> findByCostumerId(Long id) {
        costumerService.findById(id); // checa se cliente existe
        return orderRepository.findByCostumerId(id);
    }

    public Integer findTotalByCostumerId(Long id) {
        costumerService.findById(id); // checa se cliente existe
        return findByCostumerId(id).size();
    }

    public void delete(Long id) {
        findById(id);
        orderRepository.deleteById(id);
    }

    public Order save(Order order) {
        var savedOrder = orderRepository.save(order);
        return findById(savedOrder.getId());
    }
}
