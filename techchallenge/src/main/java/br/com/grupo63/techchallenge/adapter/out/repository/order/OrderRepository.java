package br.com.grupo63.techchallenge.adapter.out.repository.order;

import br.com.grupo63.techchallenge.adapter.out.repository.order.entity.OrderEntity;
import br.com.grupo63.techchallenge.core.application.repository.IOrderRepository;
import br.com.grupo63.techchallenge.core.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, IOrderRepository {
}
