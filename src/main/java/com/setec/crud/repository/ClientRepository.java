package com.setec.crud.repository;

import com.setec.crud.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "SELECT * FROM clients WHERE email = :email", nativeQuery = true)
    Optional<Client> findByEmail(String email);

    @Query(value = "SELECT * FROM clients WHERE name = :name", nativeQuery = true)
    Optional<Client> findByName(String name);

    @Query(value = "SELECT * FROM clients WHERE id = :id", nativeQuery = true)
    Optional<Client> findById(Long id);

    @Query(value = "SELECT * FROM clients", nativeQuery = true)
    List<Client> findAll();
}
