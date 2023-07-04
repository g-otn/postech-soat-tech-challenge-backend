package br.com.grupo63.techchallenge.core.application.repository;

import br.com.grupo63.techchallenge.core.domain.entity.Client;

import java.util.Optional;

public interface IClientRepository extends IRepository<Client> {

    Client saveAndFlush(Client client);

    Optional<Client> findByNationalId(String nationalId);

    Optional<Client> findById(Long id);
}
