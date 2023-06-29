package br.com.grupo63.techchallenge.adapter.out.infrastructure;

import br.com.grupo63.techchallenge.core.application.repository.IOrderRepository;
import br.com.grupo63.techchallenge.core.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, IOrderRepository {
}
