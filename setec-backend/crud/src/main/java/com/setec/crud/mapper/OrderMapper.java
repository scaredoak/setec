package com.setec.crud.mapper;

import com.setec.crud.domain.Order;
import com.setec.crud.domain.Product;
import com.setec.crud.dto.OrderRequest;
import com.setec.crud.dto.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")

    @Mapping(source = "client", target = "client")
    @Mapping(source = "products", target = "products")
    Order toOrder(OrderRequest orderRequest);

    @Mapping(target = "client", expression = "java(mapClient(order))")
    OrderResponse toOrderResponse(Order order);
    List<OrderResponse> toListOrderResponse(List<Order> orders);

    default OrderResponse.Client mapClient(Order order) {
        var orderClient = order.getClient();
        return new OrderResponse.Client(orderClient.getId(), orderClient.getName(), orderClient.getEmail());
    }
}
