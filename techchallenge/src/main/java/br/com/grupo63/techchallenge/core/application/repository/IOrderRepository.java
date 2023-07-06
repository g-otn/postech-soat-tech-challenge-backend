package br.com.grupo63.techchallenge.core.application.repository;

import br.com.grupo63.techchallenge.core.domain.entity.Order;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository extends IRepository<Order> {

    @Query("SELECT " +
            "   order " +
            "FROM " +
            "   Order order " +
            "WHERE " +
            "   order.status IN ('RECEIVED', 'PREPARING', 'READY') " +
            "   AND order.deleted = false " +
            "ORDER BY " +
            "   order.creationDate")
    List<Order> findByStatusDoneAndDeletedFalseOrderByCreationDate();

    Optional<Order> findById(Long id);

    Order saveAndFlush(Order order);

    Optional<Order> findByIdAndDeletedFalse(Long id);

}
