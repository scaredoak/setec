package com.setec.crud.repository;

import com.setec.crud.domain.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CostumerRepository extends JpaRepository<Costumer, Long> {
    @Query(value = "SELECT * FROM tb_costumer WHERE email = :email", nativeQuery = true)
    Optional<Costumer> findByEmail(String email);

    @Query(value = "SELECT * FROM tb_costumer WHERE name = :name", nativeQuery = true)
    Optional<Costumer> findByName(String name);

    @Query(value = "SELECT * FROM tb_costumer WHERE id = :id", nativeQuery = true)
    Optional<Costumer> findById(Long id);

    @Query(value = "SELECT * FROM tb_costumer", nativeQuery = true)
    List<Costumer> findAll();
}
