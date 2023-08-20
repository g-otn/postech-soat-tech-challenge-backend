package br.com.grupo63.techchallenge.usecase.client;

import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;
import br.com.grupo63.techchallenge.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.gateway.repository.IClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ClientUseCase implements IClientUseCase {

    private final IClientRepository repository;

    @Override
    public ClientControllerDTO getByNationalId(String nationalId) throws NotFoundException {
        Client client = repository.findByNationalId(nationalId).orElseThrow(NotFoundException::new);

        return ClientControllerDTO.toDto(client);
    }

    @Override
    public ClientControllerDTO create(ClientControllerDTO clientDTO) {
        Client client = repository.findByNationalId(clientDTO.getNationalId()).orElse(new Client());

        if (client.getId() != null) {
            return ClientControllerDTO.toDto(client);
        }

        clientDTO.fillDomain(client);

        return ClientControllerDTO.toDto(repository.saveAndFlush(client));
    }

    @Override
    public ClientControllerDTO read(Long id) throws NotFoundException {
        Client client = repository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        return ClientControllerDTO.toDto(client);
    }

    @Override
    public List<ClientControllerDTO> list() {
        return repository.findByDeletedFalse().stream().map(ClientControllerDTO::toDto).toList();
    }

    @Override
    public ClientControllerDTO update(ClientControllerDTO clientDTO, Long id) throws NotFoundException {
        Client client = repository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        clientDTO.fillDomain(client);

        return ClientControllerDTO.toDto(repository.saveAndFlush(client));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Client client = repository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        client.delete();

        repository.saveAndFlush(client);
    }

}
