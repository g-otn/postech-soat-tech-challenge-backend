package br.com.grupo63.techchallenge.controller;

import br.com.grupo63.techchallenge.adapter.ClientAdapter;
import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;
import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.gateway.client.gateways.ClientGateway;
import br.com.grupo63.techchallenge.usecase.client.IClientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientController {

    private final ClientGateway clientGateway;
    private final IClientUseCase clientUseCase;

    public ClientControllerDTO create(ClientControllerDTO dto) throws NotFoundException {
        Client entity = new Client();

        ClientAdapter.fillEntity(dto, entity);
        entity = clientUseCase.create(entity, clientGateway);

        return ClientAdapter.toDto(entity);
    }

    public ClientControllerDTO read(Long orderId) throws NotFoundException {
        return ClientAdapter.toDto(clientUseCase.read(orderId, clientGateway));
    }

    public List<ClientControllerDTO> list() {
        return this.clientUseCase.list(clientGateway).stream().map(ClientAdapter::toDto).toList();
    }

    public ClientControllerDTO update(ClientControllerDTO dto, Long orderId) throws NotFoundException {
        Client entity = clientUseCase.read(orderId, clientGateway);
        ClientAdapter.fillEntity(dto, entity);
        entity = clientUseCase.update(entity, clientGateway);
        return ClientAdapter.toDto(entity);
    }

    public void delete(Long orderId) throws NotFoundException {
        Client entity = clientUseCase.read(orderId, clientGateway);
        clientUseCase.delete(entity, clientGateway);
    }
}
