package br.com.grupo63.techchallenge.usecase.client;

import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.gateway.client.gateways.IClientGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientUseCase implements IClientUseCase {

    @Override
    public Client getByNationalId(String nationalId, IClientGateway gateway) throws NotFoundException {
        return gateway.getbyNationalId(nationalId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Client create(Client client, IClientGateway gateway) {
        return gateway.saveAndFlush(client);
    }

    @Override
    public Client read(Long id, IClientGateway gateway) throws NotFoundException {
        return gateway.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Client> list(IClientGateway gateway) {
        return gateway.findByDeletedFalse();
    }

    @Override
    public Client update(Client client, IClientGateway gateway) {

        return gateway.saveAndFlush(client);
    }

    @Override
    public void delete(Client client, IClientGateway gateway) {
        client.delete();

        gateway.saveAndFlush(client);
    }

}
