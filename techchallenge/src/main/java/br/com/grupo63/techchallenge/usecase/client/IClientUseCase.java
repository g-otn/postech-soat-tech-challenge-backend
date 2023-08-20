package br.com.grupo63.techchallenge.usecase.client;

import br.com.grupo63.techchallenge.gateway.IPersistenceEntityRepository;
import br.com.grupo63.techchallenge.usecase.ICRUDUseCase;
import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;
import br.com.grupo63.techchallenge.usecase.exception.NotFoundException;

public interface IClientUseCase extends ICRUDUseCase<ClientControllerDTO> {

    ClientControllerDTO getByNationalId(String nationalId, IPersistenceEntityRepository gateway) throws NotFoundException;

}
