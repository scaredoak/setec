package com.setec.crud.mapper;

import com.setec.crud.domain.Product;
import com.setec.crud.dto.ProductRequest;
import com.setec.crud.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Product toProduct(ProductRequest productRequest);
    ProductResponse toProductResponse(Product product);
    List<ProductResponse> toListProductResponse(List<Product> products);
}
