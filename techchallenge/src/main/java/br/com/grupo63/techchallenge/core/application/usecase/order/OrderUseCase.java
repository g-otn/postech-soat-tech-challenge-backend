package br.com.grupo63.techchallenge.core.application.usecase.order;

import br.com.grupo63.techchallenge.core.application.repository.IOrderRepository;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.domain.model.Order;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.util.Map.entry;

@RequiredArgsConstructor
@Service
public class OrderUseCase implements IOrderUseCase {

    private final MessageSource messageSource;
    private final IOrderRepository orderRepository;
    private final Map<Order.Status, Order.Status> nextOrderMap = Map.ofEntries(
            entry(Order.Status.RECEIVED, Order.Status.PREPARING),
            entry(Order.Status.PREPARING, Order.Status.READY),
            entry(Order.Status.READY, Order.Status.DONE));

    @Override
    public void advanceOrderStatus(@NotNull Long orderId) throws NotFoundException {
        Order order = orderRepository.findByIdAndDeletedFalse(orderId).orElseThrow(NotFoundException::new);
        order.setStatus(null == order.getStatus() ? Order.Status.RECEIVED : nextOrderMap.get(order.getStatus()));

        orderRepository.saveAndFlush(order);
    }

}
