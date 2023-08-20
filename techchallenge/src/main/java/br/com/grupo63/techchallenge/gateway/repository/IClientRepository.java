package br.com.grupo63.techchallenge.gateway.repository;

import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.gateway.IPersistenceEntityRepository;

import java.util.Optional;

public interface IClientRepository extends IPersistenceEntityRepository<Client> {

    Client saveAndFlush(Client client);

    Optional<Client> findByNationalId(String nationalId);

    Optional<Client> findByIdAndDeletedFalse(Long id);
}
