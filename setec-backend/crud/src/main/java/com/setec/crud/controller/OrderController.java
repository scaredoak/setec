package com.setec.crud.controller;

import com.setec.crud.domain.Order;
import com.setec.crud.dto.OrderRequest;
import com.setec.crud.dto.OrderResponse;
import com.setec.crud.exceptions.ErrorResponse;
import com.setec.crud.mapper.OrderMapper;
import com.setec.crud.service.OrderService;
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
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Operation(summary = "Retorna pedidos", description = "Busca informações de todos os pedidos registrados")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Pedidos encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class)
                    )
            ),
    })
    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<Order> orders = orderService.findAll();
        var response = orderMapper.toListOrderResponse(orders);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Retorna pedido", description = "Busca informações de um pedido pelo identificador")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Pedido encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Pedido não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id) {
        var order = orderService.findById(id);
        var response = orderMapper.toOrderResponse(order);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Retorna pedido pelo identificador do cliente",
            description = "Busca informações de todos os pedidos de um cliente pelo seu identificador"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Pedido encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Pedido não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
    })
    @GetMapping("/by-client/{id}")
    public ResponseEntity<List<OrderResponse>> getByClientId(@PathVariable Long id) {
        var orders = orderService.findByClientId(id);
        var response = orderMapper.toListOrderResponse(orders);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cria pedido", description = "Cria um novo pedido")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Pedido criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400", description = "Dados de entrada inválidos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
    })
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
