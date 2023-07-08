package br.com.grupo63.techchallenge.adapter.out.repository.order;

import br.com.grupo63.techchallenge.adapter.out.repository.order.entity.OrderEntity;
import br.com.grupo63.techchallenge.core.application.repository.IOrderRepository;
import br.com.grupo63.techchallenge.core.domain.model.order.Order;
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
                .map(OrderEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findByIdAndDeletedFalse(Long id) {
        return jpaRepository.findByIdAndDeletedFalse(id)
                .map(OrderEntity::toModel);
    }

    @Override
    @Transactional
    public Order saveAndFlush(Order order) {
        OrderEntity entity = new OrderEntity(order);

        entity = jpaRepository.saveAndFlush(entity);

        return entity.toModel();
    }

    @Override
    public List<Order> findByDeletedFalse() {
        return jpaRepository.findByDeletedFalse().stream()
                .map(OrderEntity::toModel)
                .toList();
    }
}
