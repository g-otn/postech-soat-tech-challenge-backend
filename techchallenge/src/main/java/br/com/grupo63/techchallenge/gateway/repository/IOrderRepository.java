package br.com.grupo63.techchallenge.gateway.repository;

import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.gateway.IPersistenceEntityRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends IPersistenceEntityRepository<Order> {

    List<Order> findByStatusNotFinishedAndDeletedOrderByCreationDate();

    Optional<Order> findByIdAndDeletedFalse(Long id);

    Order saveAndFlush(Order order);

}
