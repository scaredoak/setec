package com.setec.crud.controller;

import com.setec.crud.domain.Product;
import com.setec.crud.dto.ProductRequest;
import com.setec.crud.dto.ProductResponse;
import com.setec.crud.exceptions.ErrorResponse;
import com.setec.crud.mapper.ProductMapper;
import com.setec.crud.service.ProductService;
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
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "Retorna produtos", description = "Busca informações de todos os produtos registrados")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Produtos encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)
                    )
            ),
    })
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<Product> products = productService.findAll();
        var response = productMapper.toListProductResponse(products);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Retorna produto", description = "Busca informações de um produto pelo identificador")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Produto encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)
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
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        var product = productService.findById(id);
        var response = productMapper.toProductResponse(product);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<ProductResponse> getByDescription(@PathVariable String description) {
        var product = productService.findByDescription(description);
        var response = productMapper.toProductResponse(product);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cria produto", description = "Cria um novo produto")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Produto criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)
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
                    responseCode = "409", description = "Produto já existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
    })
    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody @Valid ProductRequest request) {
        var product = productMapper.toProduct(request);
        var savedProduct = productService.save(product);
        var response = productMapper.toProductResponse(savedProduct);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
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
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
