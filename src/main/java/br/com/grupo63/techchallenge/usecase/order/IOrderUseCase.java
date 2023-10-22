package br.com.grupo63.techchallenge.usecase.order;

import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.entity.order.OrderStatus;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.exception.ValidationException;

import java.util.List;

public interface IOrderUseCase {

    OrderStatus advanceStatus(Order entity) throws ValidationException;

    List<Order> listUnfinishedOrders();

    Order create(Order entity) throws ValidationException, NotFoundException;

    Order read(Long id) throws NotFoundException;

    List<Order> list();

    Order update(Order entity) throws ValidationException;

    void delete(Order entity);

}
