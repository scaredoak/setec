package com.setec.crud.repository;

import com.setec.crud.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT * FROM tb_order", nativeQuery = true)
    List<Order> findAll();

    @Query(value = "SELECT * FROM tb_order WHERE id = :id", nativeQuery = true)
    Optional<Order> findById(Long id);
}
