package br.com.grupo63.techchallenge.core.application.usecase.order;

import br.com.grupo63.techchallenge.core.application.repository.IOrderRepository;
import br.com.grupo63.techchallenge.core.application.usecase.dto.OrderDTO;
import br.com.grupo63.techchallenge.core.application.usecase.dto.ProductDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.core.application.usecase.product.ProductUseCase;
import br.com.grupo63.techchallenge.core.domain.model.order.Order;
import br.com.grupo63.techchallenge.core.domain.model.order.OrderItem;
import br.com.grupo63.techchallenge.core.domain.model.order.OrderStatus;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@RequiredArgsConstructor
@Service
public class OrderUseCase implements IOrderUseCase {

    private final ProductUseCase productUseCase;
    private final IOrderRepository orderRepository;
    private final Map<OrderStatus, OrderStatus> nextOrderMap = Map.ofEntries(
            entry(OrderStatus.RECEIVED, OrderStatus.PREPARING),
            entry(OrderStatus.PREPARING, OrderStatus.READY),
            entry(OrderStatus.READY, OrderStatus.FINISHED));

    private void fillCurrentPrices(Order order) throws NotFoundException {
        double totalPrice = 0.0D;
        for (OrderItem orderItem : order.getItems()) {
            ProductDTO productDTO = productUseCase.read(orderItem.getProduct().getId());

            orderItem.setPrice(productDTO.getPrice());
            totalPrice += productDTO.getPrice() * orderItem.getQuantity();
        }
        order.setTotalPrice(totalPrice);
    }

    @Override
    public OrderStatus advanceStatus(@NotNull Long orderId) throws NotFoundException, ValidationException {
        Order order = orderRepository.findByIdAndDeletedFalse(orderId).orElseThrow(NotFoundException::new);

        if (order.getPayment() == null || order.getPayment().getStatus() != PaymentStatus.PAID) {
            throw new ValidationException("order.advanceStatus.title", "order.advanceStatus.notPaid");
        } else if (order.getStatus() == OrderStatus.FINISHED) {
            throw new ValidationException("order.advanceStatus.title", "order.advanceStatus.finished");
        } else if (order.getStatus() == null) {
            order.setStatus(OrderStatus.RECEIVED);
        }  else {
            order.setStatus(nextOrderMap.get(order.getStatus()));
        }

        return orderRepository.saveAndFlush(order).getStatus();
    }

    public List<OrderDTO> listUnfinishedOrders() {
        return orderRepository.findByStatusNotFinishedAndDeletedOrderByCreationDate().stream().map(OrderDTO::toDto).toList();
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) throws NotFoundException {
        Order order = new Order();
        orderDTO.fillDomain(order);

        fillCurrentPrices(order);

        return OrderDTO.toDto(orderRepository.saveAndFlush(order));
    }

    @Override
    public OrderDTO read(Long id) throws NotFoundException {
        return OrderDTO.toDto(orderRepository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public List<OrderDTO> list() {
        return orderRepository.findByDeletedFalse().stream().map(OrderDTO::toDto).toList();
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO, Long id) throws NotFoundException {
        Order order = orderRepository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        orderDTO.fillDomain(order);

        return OrderDTO.toDto(orderRepository.saveAndFlush(order));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Order order = orderRepository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        order.delete();

        orderRepository.saveAndFlush(order);
    }

}
