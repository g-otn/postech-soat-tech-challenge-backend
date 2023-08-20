package br.com.grupo63.techchallenge.adapter;

import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;

public class ClientAdapter {

    public static ClientControllerDTO toDto(Client entity) {
        ClientControllerDTO dto = new ClientControllerDTO();

        dto.setId(entity.getId());
        dto.setNationalId(entity.getNationalId());

        return dto;
    }

    public static void fillEntity(ClientControllerDTO dto, Client entity) {
        entity.setId(dto.getId());
        entity.setNationalId(dto.getNationalId());
    }

}
