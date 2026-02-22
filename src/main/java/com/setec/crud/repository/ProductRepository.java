package com.setec.crud.repository;

import com.setec.crud.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM tb_product", nativeQuery = true)
    List<Product> findAll();

    @Query(value = "SELECT * FROM tb_product WHERE id = :id", nativeQuery = true)
    Optional<Product> findById(Long id);

    @Query(value = "SELECT * FROM tb_product WHERE description = :description", nativeQuery = true)
    Optional<Product> findByDescription(String description);
}
