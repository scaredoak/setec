package com.setec.crud.controller;

import com.setec.crud.domain.Costumer;
import com.setec.crud.dto.CostumerRequest;
import com.setec.crud.dto.CostumerResponse;
import com.setec.crud.exceptions.ErrorResponse;
import com.setec.crud.mapper.CostumerMapper;
import com.setec.crud.service.CostumerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/costumer")
public class CostumerController {

    private final CostumerService costumerService;
    private final CostumerMapper costumerMapper;

    @Operation(summary = "Retorna clientes", description = "Busca informações de todos os clientes registrados")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Clientes encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CostumerResponse.class)
                    )
            ),
    })
    @GetMapping("/all")
    public ResponseEntity<List<CostumerResponse>> getAll() {
        List<Costumer> costumers = costumerService.findAll();
        var response = costumerMapper.toListCostumerResponse(costumers);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cria cliente", description = "Cria um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Cliente criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CostumerResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400", description = "Dados de entrada inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409", description = "Cliente já existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
    })
    @PostMapping
    public ResponseEntity<CostumerResponse> save(@RequestBody @Valid CostumerRequest request) {
        var costumer = costumerMapper.toCostumer(request);
        var savedCostumer = costumerService.save(costumer);
        var response = costumerMapper.toCostumerResponse(savedCostumer);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCostumer.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Retorna cliente", description = "Busca informações de um cliente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Cliente encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CostumerResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Cliente não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
    })
    @GetMapping("/{id}")
    public ResponseEntity<CostumerResponse> getById(@PathVariable Long id) {
        var costumer = costumerService.findById(id);
        var response = costumerMapper.toCostumerResponse(costumer);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Retorna cliente", description = "Busca informações de um cliente pelo nome")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Cliente encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CostumerResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Cliente não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<CostumerResponse> getByName(@PathVariable String name) {
        var costumer = costumerService.findByName(name);
        var response = costumerMapper.toCostumerResponse(costumer);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deleta produto", description = "Deleta um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(
                    responseCode = "400", description = "Dados de entrada inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Produto não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        costumerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
