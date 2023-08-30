package br.com.grupo63.techchallenge.usecase.client;

import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.gateway.client.IClientGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientUseCase implements IClientUseCase {

    private final IClientGateway gateway;

    @Override
    public Client findByNationalId(String nationalId) throws NotFoundException {
        return gateway.findByNationalId(nationalId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Client create(Client client) {
        return gateway.saveAndFlush(client);
    }

    @Override
    public Client read(Long id) throws NotFoundException {
        return gateway.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Client> list() {
        return gateway.findByDeletedFalse();
    }

    @Override
    public Client update(Client client) {
        return gateway.saveAndFlush(client);
    }

    @Override
    public void delete(Client client) {
        client.delete();

        gateway.saveAndFlush(client);
    }

}
