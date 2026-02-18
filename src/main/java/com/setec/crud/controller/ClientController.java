package com.setec.crud.controller;

import com.setec.crud.domain.Client;
import com.setec.crud.dto.ClientRequest;
import com.setec.crud.dto.ClientResponse;
import com.setec.crud.exceptions.ErrorResponse;
import com.setec.crud.mapper.ClientMapper;
import com.setec.crud.service.ClientService;

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
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @Operation(summary = "Retorna clientes", description = "Busca informações de todos os clientes registrados")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Clientes encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClientResponse.class)
                    )
            ),
    })
    @GetMapping("/all")
    public ResponseEntity<List<ClientResponse>> getAll() {
        List<Client> clients = clientService.findAll();
        var response = clientMapper.toListClientResponse(clients);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cria cliente", description = "Cria um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Cliente criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClientResponse.class)
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
                    responseCode = "403", description = "O usuário autenticado não pode criar clientes",
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
    public ResponseEntity<ClientResponse> save(@RequestBody @Valid ClientRequest request) {
        var client = clientMapper.toClient(request);
        var savedClient = clientService.save(client);
        var response = clientMapper.toClientResponse(savedClient);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedClient.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Retorna cliente", description = "Busca informações de um cliente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Cliente encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClientResponse.class)
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
    public ResponseEntity<ClientResponse> getById(@PathVariable Long id) {
        var client = clientService.findById(id);
        var response = clientMapper.toClientResponse(client);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Retorna cliente", description = "Busca informações de um cliente pelo nome")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Cliente encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClientResponse.class)
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
    public ResponseEntity<ClientResponse> getByName(@PathVariable String name) {
        var client = clientService.findByName(name);
        var response = clientMapper.toClientResponse(client);
        return ResponseEntity.ok(response);
    }
}
