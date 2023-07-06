package br.com.grupo63.techchallenge.core.application.usecase.order;

import br.com.grupo63.techchallenge.core.application.repository.IOrderRepository;
import br.com.grupo63.techchallenge.core.application.usecase.dto.OrderDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.domain.entity.Order;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Order order = new Order();

        orderDTO.toDomain(order);

        return OrderDTO.toDto(orderRepository.saveAndFlush(order));
    }

    @Override
    public OrderDTO read(Long id) throws NotFoundException {
        return OrderDTO.toDto(orderRepository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public List<OrderDTO> list() {
        return orderRepository.findByStatusDoneAndDeletedFalseOrderByCreationDate().stream().map(OrderDTO::toDto).toList();
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO, Long id) throws NotFoundException {
        Order order = orderRepository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        orderDTO.toDomain(order);

        return OrderDTO.toDto(orderRepository.saveAndFlush(order));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Order order = orderRepository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        order.delete();

        orderRepository.saveAndFlush(order);
    }
}
