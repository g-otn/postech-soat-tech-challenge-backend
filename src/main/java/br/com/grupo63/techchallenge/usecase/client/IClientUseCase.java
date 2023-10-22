package br.com.grupo63.techchallenge.usecase.client;

import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.exception.NotFoundException;

import java.util.List;

public interface IClientUseCase {

    Client findByNationalId(String nationalId) throws NotFoundException;

    Client create(Client entity);

    Client read(Long id) throws NotFoundException;

    List<Client> list();

    Client update(Client entity);

    void delete(Client entity);

}
