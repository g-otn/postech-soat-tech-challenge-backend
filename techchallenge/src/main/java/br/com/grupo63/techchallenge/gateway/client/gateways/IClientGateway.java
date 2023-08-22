package br.com.grupo63.techchallenge.gateway.client.gateways;

import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.gateway.IGateway;

import java.util.Optional;

public interface IClientGateway extends IGateway<Client> {

    Optional<Client> getbyNationalId(String nationalId);

}
