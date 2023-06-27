package br.com.grupo63.techchallenge.core.application.repository;

import br.com.grupo63.techchallenge.core.domain.entity.Order;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderRepository extends IRepository<Order> {

    @Query("SELECT " +
            "   order " +
            "FROM " +
            "   Order order " +
            "WHERE " +
            "   order.status != Order.Status.DONE " +
            "   AND order.deleted = false " +
            "ORDER BY " +
            "   order.creationDate")
    List<Order> findByStatusDoneAndDeletedFalseOrderByCreationDate();

}
