package br.com.grupo63.techchallenge.gateway.client;

import br.com.grupo63.techchallenge.gateway.client.entity.ClientPersistenceEntity;
import br.com.grupo63.techchallenge.gateway.repository.IClientRepository;
import br.com.grupo63.techchallenge.entity.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientJpaAdapter implements IClientRepository {

    private final ClientJpaRepository jpaRepository;

    @Override
    public List<Client> findByDeletedFalse() {
        return jpaRepository.findByDeletedFalse().stream()
                .map(ClientPersistenceEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Client saveAndFlush(Client client) {
        ClientPersistenceEntity entity = new ClientPersistenceEntity(client);

        entity = jpaRepository.saveAndFlush(entity);

        return entity.toModel();
    }

    @Override
    public Optional<Client> findByNationalId(String nationalId) {
        return jpaRepository.findByNationalIdAndDeletedFalse(nationalId)
                .map(ClientPersistenceEntity::toModel);
    }

    @Override
    public Optional<Client> findByIdAndDeletedFalse(Long id) {
        return jpaRepository.findByIdAndDeletedFalse(id)
                .map(ClientPersistenceEntity::toModel);
    }
}
