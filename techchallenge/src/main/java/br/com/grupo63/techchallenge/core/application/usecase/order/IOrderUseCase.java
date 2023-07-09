package br.com.grupo63.techchallenge.core.application.usecase.order;

import br.com.grupo63.techchallenge.core.application.usecase.ICRUDUseCase;
import br.com.grupo63.techchallenge.core.application.usecase.dto.OrderDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.core.domain.model.order.OrderStatus;

import java.util.List;

public interface IOrderUseCase extends ICRUDUseCase<OrderDTO> {

    OrderStatus advanceStatus(Long orderId) throws NotFoundException, ValidationException;

    List<OrderDTO> listUnfinishedOrders();

}
