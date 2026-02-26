package com.setec.crud.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_product")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    @NotNull
    private String description;

    @NotNull
    private float price;

    @NotNull
    private Long stockAmount; // quantidade no estoque

    private LocalDateTime createdAt;
}
