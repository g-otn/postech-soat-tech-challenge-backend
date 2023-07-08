package br.com.grupo63.techchallenge.core.application.usecase.client;

import br.com.grupo63.techchallenge.core.application.usecase.ICRUDUseCase;
import br.com.grupo63.techchallenge.core.application.usecase.dto.ClientDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;

public interface IClientUseCase extends ICRUDUseCase<ClientDTO> {

    ClientDTO getByNationalId(String nationalId) throws NotFoundException;

}
