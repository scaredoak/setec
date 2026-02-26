package com.setec.crud.mapper;

import com.setec.crud.domain.Costumer;
import com.setec.crud.dto.CostumerRequest;
import com.setec.crud.dto.CostumerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CostumerMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Costumer toCostumer(CostumerRequest costumerRequest);
    CostumerResponse toCostumerResponse(Costumer costumer);
    List<CostumerResponse> toListCostumerResponse(List<Costumer> costumers);
}
