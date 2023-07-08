package br.com.grupo63.techchallenge.core.application.usecase.dto;

import br.com.grupo63.techchallenge.core.domain.model.Domain;

public interface IUseCaseDomainDTO<D extends Domain> {

    void fillDomain(D domain);

}
