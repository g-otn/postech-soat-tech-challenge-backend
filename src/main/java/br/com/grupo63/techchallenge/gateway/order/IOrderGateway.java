package br.com.grupo63.techchallenge.gateway.order;

import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.gateway.IPersistenceEntityGateway;

import java.util.List;
import java.util.Optional;

public interface IOrderGateway extends IPersistenceEntityGateway<Order> {

    List<Order> findByStatusNotFinishedAndDeletedOrderByCreationDate();

    Optional<Order> findByIdAndDeletedFalse(Long id);

    Order saveAndFlush(Order order);

}
