package br.com.grupo63.techchallenge.core.application.usecase.order;

import br.com.grupo63.techchallenge.core.application.usecase.ICRUDUseCase;
import br.com.grupo63.techchallenge.core.application.usecase.dto.OrderDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import jakarta.validation.constraints.NotNull;

public interface IOrderUseCase extends ICRUDUseCase<OrderDTO> {

    void advanceOrderStatus(@NotNull Long orderId) throws NotFoundException;

}
