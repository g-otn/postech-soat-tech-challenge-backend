package br.com.grupo63.techchallenge.core.application.usecase.dto;

import br.com.grupo63.techchallenge.core.domain.model.Domain;
import br.com.grupo63.techchallenge.core.domain.model.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractUseCaseDomainDTO<D extends Domain> implements IUseCaseDomainDTO<D> {

    @Schema(defaultValue = "1")
    protected Long id;

}
