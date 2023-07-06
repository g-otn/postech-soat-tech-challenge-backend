package br.com.grupo63.techchallenge.adapter.out.repository.client;

import br.com.grupo63.techchallenge.adapter.out.repository.client.entity.ClientEntity;
import br.com.grupo63.techchallenge.core.application.repository.IClientRepository;
import br.com.grupo63.techchallenge.core.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long>, IClientRepository {

    Optional<Client> findByNationalId(String nationalId);

    Optional<Client> findByIdAndDeletedFalse(Long id);

    @Override
    default Client saveAndFlush(Client client) {
        ClientEntity entity = new ClientEntity(client);

        entity = this.saveAndFlush(entity);

        return entity.toModel();
    }

}
