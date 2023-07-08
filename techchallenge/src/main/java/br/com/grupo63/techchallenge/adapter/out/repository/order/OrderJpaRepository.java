package br.com.grupo63.techchallenge.adapter.out.repository.order;

import br.com.grupo63.techchallenge.adapter.out.repository.IJpaRepository;
import br.com.grupo63.techchallenge.adapter.out.repository.order.entity.OrderEntity;
import br.com.grupo63.techchallenge.core.domain.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long>, IJpaRepository<OrderEntity> {

    @Query("SELECT " +
            "   order " +
            "FROM " +
            "   OrderEntity order " +
            "WHERE " +
            "   order.status IN ('RECEIVED', 'PREPARING', 'READY') " +
            "   AND order.deleted = false " +
            "ORDER BY " +
            "   order.creationDate")
    List<OrderEntity> findByStatusNotFinishedAndDeletedOrderByCreationDate();

}
