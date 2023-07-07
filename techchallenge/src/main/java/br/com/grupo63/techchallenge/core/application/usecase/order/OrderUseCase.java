package br.com.grupo63.techchallenge.core.application.usecase.order;

import br.com.grupo63.techchallenge.core.application.repository.IOrderRepository;
import br.com.grupo63.techchallenge.core.application.usecase.dto.OrderDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.core.domain.model.Order;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@RequiredArgsConstructor
@Service
public class OrderUseCase implements IOrderUseCase {

    private final IOrderRepository orderRepository;
    private final Map<Order.Status, Order.Status> nextOrderMap = Map.ofEntries(
            entry(Order.Status.RECEIVED, Order.Status.PREPARING),
            entry(Order.Status.PREPARING, Order.Status.READY),
            entry(Order.Status.READY, Order.Status.DONE));

    @Override
    public void advanceOrderStatus(@NotNull Long orderId) throws NotFoundException, ValidationException {
        Order order = orderRepository.findByIdAndDeletedFalse(orderId).orElseThrow(NotFoundException::new);

        if (order.getStatus() == null) {
            order.setStatus(Order.Status.RECEIVED);
        } else if (order.getStatus() == Order.Status.DONE) {
            throw new ValidationException("order.cantAdvance.done.title", "order.cantAdvance.done.description");
        } else {
            order.setStatus(nextOrderMap.get(order.getStatus()));
        }

        orderRepository.saveAndFlush(order);
    }

    public List<OrderDTO> listUnfinishedOrders() {
        return orderRepository.findByStatusDoneAndDeletedFalseOrderByCreationDate().stream().map(OrderDTO::toDto).toList();
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
