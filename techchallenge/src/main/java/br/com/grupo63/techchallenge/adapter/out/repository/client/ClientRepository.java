package br.com.grupo63.techchallenge.adapter.out.repository.client;

import br.com.grupo63.techchallenge.adapter.out.repository.client.entity.ClientEntity;
import br.com.grupo63.techchallenge.core.application.repository.IClientRepository;
import br.com.grupo63.techchallenge.core.domain.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long>, IClientRepository {

    Optional<ClientEntity> findByNationalIdAndDeleted(String nationalId, boolean deleted);

    Optional<ClientEntity> findByIdAndDeleted(Long id, boolean deleted);

    @Override
    default Client saveAndFlush(Client client) {
        ClientEntity entity = new ClientEntity(client);

        entity = this.saveAndFlush(entity);

        return entity.toModel();
    }

    @Override
    default Optional<Client> findByNationalId(String nationalId) {
        return this.findByNationalIdAndDeleted(nationalId, false).map(ClientEntity::toModel);
    }

    @Override
    default Optional<Client> findByIdAndDeletedFalse(Long id) {
        return this.findByIdAndDeleted(id, false).map(ClientEntity::toModel);
    }
}
