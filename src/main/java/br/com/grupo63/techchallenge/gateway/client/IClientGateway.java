package br.com.grupo63.techchallenge.gateway.client;

import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.gateway.IPersistenceEntityGateway;

import java.util.Optional;

public interface IClientGateway extends IPersistenceEntityGateway<Client> {

    Optional<Client> findByNationalId(String nationalId);

}
