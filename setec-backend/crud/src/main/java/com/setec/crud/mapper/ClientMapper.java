package com.setec.crud.mapper;

import com.setec.crud.domain.Client;
import com.setec.crud.dto.ClientRequest;
import com.setec.crud.dto.ClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Client toClient(ClientRequest clientRequest);
    ClientResponse toClientResponse(Client client);
    List<ClientResponse> toListClientResponse(List<Client> clients);
}
