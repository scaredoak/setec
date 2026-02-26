package com.setec.crud.mapper;

import com.setec.crud.domain.Order;
import com.setec.crud.dto.OrderRequest;
import com.setec.crud.dto.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")

    @Mapping(source = "costumer", target = "costumer")
    @Mapping(source = "products", target = "products")
    Order toOrder(OrderRequest orderRequest);

    @Mapping(target = "costumer", expression = "java(mapCostumer(order))")
    OrderResponse toOrderResponse(Order order);
    List<OrderResponse> toListOrderResponse(List<Order> orders);

    default OrderResponse.Costumer mapCostumer(Order order) {
        var orderCostumer = order.getCostumer();
        return new OrderResponse.Costumer(orderCostumer.getId(), orderCostumer.getName(), orderCostumer.getEmail());
    }
}
