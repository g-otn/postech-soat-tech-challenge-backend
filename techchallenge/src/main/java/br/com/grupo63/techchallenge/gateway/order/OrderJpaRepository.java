package br.com.grupo63.techchallenge.gateway.order;

import br.com.grupo63.techchallenge.gateway.repository.IJpaRepository;
import br.com.grupo63.techchallenge.gateway.order.entity.OrderPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderPersistenceEntity, Long>, IJpaRepository<OrderPersistenceEntity> {

    @Query("SELECT " +
            "   order " +
            "FROM " +
            "   OrderPersistenceEntity order " +
            "WHERE " +
            "   order.status IN ('RECEIVED', 'PREPARING', 'READY') " +
            "   AND order.deleted = false " +
            "ORDER BY " +
            "   CASE " +
            "       WHEN order.status = 'READY' THEN 1 " +
            "       WHEN order.status = 'PREPARING' THEN 2 " +
            "       WHEN order.status = 'RECEIVED' THEN 3 " +
            "       ELSE 4 " +
            "   END")
    List<OrderPersistenceEntity> findByStatusNotFinishedAndDeletedOrderByCreationDate();

}
