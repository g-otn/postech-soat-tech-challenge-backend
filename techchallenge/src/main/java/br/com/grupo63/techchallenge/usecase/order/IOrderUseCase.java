package br.com.grupo63.techchallenge.usecase.order;

import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.entity.order.OrderStatus;
import br.com.grupo63.techchallenge.gateway.repository.IOrderRepository;
import br.com.grupo63.techchallenge.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.usecase.exception.ValidationException;

import java.util.List;

public interface IOrderUseCase {

    OrderStatus advanceStatus(Long orderId, IOrderRepository gateway) throws NotFoundException, ValidationException;

    List<Order> listUnfinishedOrders(IOrderRepository gateway);

    Order create(Order entity, IOrderRepository gateway) throws NotFoundException;

    Order read(Long id, IOrderRepository gateway) throws NotFoundException;

    List<Order> list(IOrderRepository gateway);

    Order update(Order entity, IOrderRepository gateway) throws NotFoundException;

    void delete(Long id, IOrderRepository gateway) throws NotFoundException;

}
