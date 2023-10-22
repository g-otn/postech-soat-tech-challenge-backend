package br.com.grupo63.techchallenge.adapter;

import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;
import br.com.grupo63.techchallenge.entity.client.Client;

public class ClientAdapter {

    public static void fillEntity(ClientControllerDTO dto, Client entity) {
        entity.setId(dto.getId());
        entity.setNationalId(dto.getNationalId());
    }

}
