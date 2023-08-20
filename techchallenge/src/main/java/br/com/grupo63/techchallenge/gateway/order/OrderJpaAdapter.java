package br.com.grupo63.techchallenge.gateway.order;

import br.com.grupo63.techchallenge.gateway.order.entity.OrderPersistenceEntity;
import br.com.grupo63.techchallenge.gateway.repository.IOrderRepository;
import br.com.grupo63.techchallenge.entity.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderRepository {

    private final OrderJpaRepository jpaRepository;

    @Override
    public List<Order> findByStatusNotFinishedAndDeletedOrderByCreationDate() {
        return jpaRepository.findByStatusNotFinishedAndDeletedOrderByCreationDate().stream()
                .map(OrderPersistenceEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findByIdAndDeletedFalse(Long id) {
        return jpaRepository.findByIdAndDeletedFalse(id)
                .map(OrderPersistenceEntity::toModel);
    }

    @Override
    @Transactional
    public Order saveAndFlush(Order order) {
        OrderPersistenceEntity entity = new OrderPersistenceEntity(order);

        entity = jpaRepository.saveAndFlush(entity);

        return entity.toModel();
    }

    @Override
    public List<Order> findByDeletedFalse() {
        return jpaRepository.findByDeletedFalse().stream()
                .map(OrderPersistenceEntity::toModel)
                .toList();
    }
}
