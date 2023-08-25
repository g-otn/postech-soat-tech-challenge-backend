package br.com.grupo63.techchallenge.presenter;

import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;
import br.com.grupo63.techchallenge.entity.client.Client;

public class ClientPresenter {

    public static ClientControllerDTO toDto(Client entity) {
        ClientControllerDTO dto = new ClientControllerDTO();

        dto.setId(entity.getId());
        dto.setNationalId(entity.getNationalId());

        return dto;
    }

}
