package com.setec.crud.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_costumer")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Costumer {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @Column(unique = true)
    @Email
    @NotBlank
    @NotNull
    private String email;

    private LocalDateTime createdAt;
}
