package br.com.grupo63.techchallenge.gateway.client.gateways;

import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.gateway.repository.IClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientGateway implements IClientGateway {

    private final IClientRepository clientRepository;

    @Override
    public Optional<Client> getbyNationalId(String nationalId) {
        return this.clientRepository.findByNationalId(nationalId);
    }

    @Override
    public List<Client> findByDeletedFalse() {
        return this.clientRepository.findByDeletedFalse();
    }

    @Override
    public Optional<Client> findByIdAndDeletedFalse(Long id) {
        return this.clientRepository.findByIdAndDeletedFalse(id);
    }

    @Override
    public Client saveAndFlush(Client entity) {
        return this.clientRepository.saveAndFlush(entity);
    }
}
