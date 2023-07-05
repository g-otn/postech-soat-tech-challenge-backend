package br.com.grupo63.techchallenge.core.application.usecase.order;

import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import jakarta.validation.constraints.NotNull;

public interface IOrderUseCase {

    void advanceOrderStatus(@NotNull Long orderId) throws NotFoundException;

}
