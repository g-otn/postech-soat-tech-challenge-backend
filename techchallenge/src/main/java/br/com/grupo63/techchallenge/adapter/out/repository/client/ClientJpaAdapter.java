package br.com.grupo63.techchallenge.adapter.out.repository.client;

import br.com.grupo63.techchallenge.adapter.out.repository.client.entity.ClientEntity;
import br.com.grupo63.techchallenge.core.application.repository.IClientRepository;
import br.com.grupo63.techchallenge.core.domain.model.client.Client;
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
                .map(ClientEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Client saveAndFlush(Client client) {
        ClientEntity entity = new ClientEntity(client);

        entity = jpaRepository.saveAndFlush(entity);

        return entity.toModel();
    }

    @Override
    public Optional<Client> findByNationalId(String nationalId) {
        return jpaRepository.findByNationalIdAndDeletedFalse(nationalId)
                .map(ClientEntity::toModel);
    }

    @Override
    public Optional<Client> findByIdAndDeletedFalse(Long id) {
        return jpaRepository.findByIdAndDeletedFalse(id)
                .map(ClientEntity::toModel);
    }
}
