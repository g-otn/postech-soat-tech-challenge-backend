package br.com.grupo63.techchallenge.core.application.usecase.order;

import br.com.grupo63.techchallenge.core.application.usecase.ICRUDUseCase;
import br.com.grupo63.techchallenge.core.application.usecase.dto.OrderDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface IOrderUseCase extends ICRUDUseCase<OrderDTO> {

    void advanceStatus(@NotNull Long orderId) throws NotFoundException, ValidationException;

    List<OrderDTO> listUnfinishedOrders();

}
