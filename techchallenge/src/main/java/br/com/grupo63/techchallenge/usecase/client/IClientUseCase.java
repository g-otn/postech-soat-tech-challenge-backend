package br.com.grupo63.techchallenge.usecase.client;

import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.gateway.client.gateways.IClientGateway;

import java.util.List;

public interface IClientUseCase {

    Client getByNationalId(String nationalId, IClientGateway gateway) throws NotFoundException;

    Client create(Client entity, IClientGateway gateway);

    Client read(Long id, IClientGateway gateway) throws NotFoundException;

    List<Client> list(IClientGateway gateway);

    Client update(Client entity, IClientGateway gateway);

    void delete(Client entity, IClientGateway gateway);

}
