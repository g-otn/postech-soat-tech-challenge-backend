package br.com.grupo63.techchallenge.core.application.usecase.client;

import br.com.grupo63.techchallenge.core.application.usecase.dto.ClientDTO;
import br.com.grupo63.techchallenge.core.application.repository.IClientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class ClientUseCase implements IClientUseCase {

    private final IClientRepository repository;

    @Override
    public ClientDTO getByNationalId(String nationalId) {
        return ClientDTO.toDto(repository.findByNationalId(nationalId).orElseThrow());
    }

    @Override
    public ClientDTO create(@Valid ClientDTO clientDTO) {
        return ClientDTO.toDto(repository.saveAndFlush(clientDTO.toDomain()));
    }

    @Override
    public ClientDTO read(Long id) {
        return ClientDTO.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<ClientDTO> list() {
        return repository.findByDeletedFalse().stream().map(ClientDTO::toDto).collect(Collectors.toList());
    }

    @Override
    public ClientDTO update(@Valid ClientDTO clientDTO, Long id) {
        return ClientDTO.toDto(repository.saveAndFlush(clientDTO.toDomain()));
    }

    @Override
    public void delete(Long id) {
        repository.removeLogicallyById(id);
    }
}
