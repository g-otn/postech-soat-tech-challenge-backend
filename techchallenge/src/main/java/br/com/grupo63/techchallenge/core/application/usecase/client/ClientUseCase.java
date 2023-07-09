package br.com.grupo63.techchallenge.core.application.usecase.client;

import br.com.grupo63.techchallenge.core.application.repository.IClientRepository;
import br.com.grupo63.techchallenge.core.application.usecase.dto.ClientDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.domain.model.client.Client;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ClientUseCase implements IClientUseCase {

    private final IClientRepository repository;

    @Override
    public ClientDTO getByNationalId(String nationalId) throws NotFoundException {
        Client client = repository.findByNationalId(nationalId).orElseThrow(NotFoundException::new);

        return ClientDTO.toDto(client);
    }

    @Override
    public ClientDTO create(ClientDTO clientDTO) {
        Client client = repository.findByNationalId(clientDTO.getNationalId()).orElse(new Client());

        if (client.getId() != null) {
            return ClientDTO.toDto(client);
        }

        clientDTO.fillDomain(client);

        return ClientDTO.toDto(repository.saveAndFlush(client));
    }

    @Override
    public ClientDTO read(Long id) throws NotFoundException {
        Client client = repository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        return ClientDTO.toDto(client);
    }

    @Override
    public List<ClientDTO> list() {
        return repository.findByDeletedFalse().stream().map(ClientDTO::toDto).toList();
    }

    @Override
    public ClientDTO update(ClientDTO clientDTO, Long id) throws NotFoundException {
        Client client = repository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        clientDTO.fillDomain(client);

        return ClientDTO.toDto(repository.saveAndFlush(client));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Client client = repository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        client.delete();

        repository.saveAndFlush(client);
    }

}
