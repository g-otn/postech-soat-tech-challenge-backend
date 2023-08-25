package br.com.grupo63.techchallenge.controller;

import br.com.grupo63.techchallenge.adapter.ClientAdapter;
import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;
import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.presenter.ClientPresenter;
import br.com.grupo63.techchallenge.usecase.client.ClientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientController {

    private final ClientUseCase clientUseCase;

    public ClientControllerDTO create(ClientControllerDTO dto) throws NotFoundException {
        Client entity = new Client();

        ClientAdapter.fillEntity(dto, entity);
        entity = clientUseCase.create(entity);

        return ClientPresenter.toDto(entity);
    }

    public ClientControllerDTO read(Long orderId) throws NotFoundException {
        return ClientPresenter.toDto(clientUseCase.read(orderId));
    }

    public List<ClientControllerDTO> list() {
        return this.clientUseCase.list().stream().map(ClientPresenter::toDto).toList();
    }

    public ClientControllerDTO update(ClientControllerDTO dto, Long orderId) throws NotFoundException {
        Client entity = clientUseCase.read(orderId);
        ClientAdapter.fillEntity(dto, entity);
        entity = clientUseCase.update(entity);
        return ClientPresenter.toDto(entity);
    }

    public void delete(Long orderId) throws NotFoundException {
        Client entity = clientUseCase.read(orderId);
        clientUseCase.delete(entity);
    }
}
