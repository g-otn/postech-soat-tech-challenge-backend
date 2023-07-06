package br.com.grupo63.techchallenge.adapter.out.repository.order;

import br.com.grupo63.techchallenge.adapter.out.repository.order.entity.OrderEntity;
import br.com.grupo63.techchallenge.core.application.repository.IOrderRepository;
import br.com.grupo63.techchallenge.core.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, IOrderRepository {

    Optional<OrderEntity> findByIdAndDeleted(Long id, boolean deleted);
    List<OrderEntity> findByDeleted(boolean deleted);

    @Query("SELECT " +
            "   order " +
            "FROM " +
            "   OrderEntity order " +
            "WHERE " +
            "   order.status IN ('RECEIVED', 'PREPARING', 'READY') " +
            "   AND order.deleted = false " +
            "ORDER BY " +
            "   order.creationDate")
    List<Order> findByStatusDoneAndDeletedOrderByCreationDate();

    @Override
    default List<Order> findByStatusDoneAndDeletedFalseOrderByCreationDate() {
        return this.findByStatusDoneAndDeletedOrderByCreationDate();
    }

    @Override
    default Optional<Order> findByIdAndDeletedFalse(Long id) {
        return this.findByIdAndDeleted(id, false).map(OrderEntity::toModel);
    }

    @Override
    default Order saveAndFlush(Order order) {
        OrderEntity entity = new OrderEntity(order);

        entity = this.saveAndFlush(entity);

        return entity.toModel();
    }

    @Override
    default List<Order> findByDeletedFalse() {
        return this.findByDeleted(false).stream().map(OrderEntity::toModel).toList();
    }

}
