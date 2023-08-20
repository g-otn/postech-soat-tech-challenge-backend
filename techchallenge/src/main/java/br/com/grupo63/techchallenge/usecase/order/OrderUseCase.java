package br.com.grupo63.techchallenge.usecase.order;

import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.entity.order.OrderItem;
import br.com.grupo63.techchallenge.entity.order.OrderStatus;
import br.com.grupo63.techchallenge.entity.payment.PaymentStatus;
import br.com.grupo63.techchallenge.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.gateway.repository.IOrderRepository;
import br.com.grupo63.techchallenge.controller.dto.ProductControllerDTO;
import br.com.grupo63.techchallenge.usecase.product.ProductUseCase;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@RequiredArgsConstructor
@Service
public class OrderUseCase implements IOrderUseCase {

    Validator validator;
    private final ProductUseCase productUseCase;
    private final Map<OrderStatus, OrderStatus> nextOrderMap = Map.ofEntries(
            entry(OrderStatus.RECEIVED, OrderStatus.PREPARING),
            entry(OrderStatus.PREPARING, OrderStatus.READY),
            entry(OrderStatus.READY, OrderStatus.FINISHED));

    private void fillCurrentPrices(Order order) throws NotFoundException {
        double totalPrice = 0.0D;
        for (OrderItem orderItem : order.getItems()) {
            ProductControllerDTO productUseCaseDTO = productUseCase.read(orderItem.getProduct().getId());

            orderItem.setPrice(productUseCaseDTO.getPrice());
            totalPrice += productUseCaseDTO.getPrice() * orderItem.getQuantity();
        }
        order.setTotalPrice(totalPrice);
    }

    @Override
    public OrderStatus advanceStatus(Long orderId, IOrderRepository gateway) throws NotFoundException, ValidationException {
        Order order = gateway.findByIdAndDeletedFalse(orderId).orElseThrow(NotFoundException::new);

        if (order.getPayment() == null || order.getPayment().getStatus() != PaymentStatus.PAID) {
            throw new ValidationException("order.advanceStatus.title", "order.advanceStatus.notPaid");
        } else if (order.getStatus() == OrderStatus.FINISHED) {
            throw new ValidationException("order.advanceStatus.title", "order.advanceStatus.finished");
        } else if (order.getStatus() == null) {
            order.setStatus(OrderStatus.RECEIVED);
        }  else {
            order.setStatus(nextOrderMap.get(order.getStatus()));
        }

        return gateway.saveAndFlush(order).getStatus();
    }

    @Override
    public List<Order> listUnfinishedOrders(IOrderRepository gateway) {
        return gateway.findByStatusNotFinishedAndDeletedOrderByCreationDate();
    }

    @Override
    public Order create(Order entity, IOrderRepository gateway) throws NotFoundException {
        fillCurrentPrices(entity);

        validator.validate(entity);

        return gateway.saveAndFlush(entity);
    }

    @Override
    public Order read(Long id, IOrderRepository gateway) throws NotFoundException {
        return gateway.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Order> list(IOrderRepository gateway) {
        return gateway.findByDeletedFalse().stream().toList();
    }

    @Override
    public Order update(Order entity, IOrderRepository gateway) throws NotFoundException {
        validator.validate(entity);

        return gateway.saveAndFlush(entity);
    }

    @Override
    public void delete(Long id, IOrderRepository gateway) throws NotFoundException {
        Order order = gateway.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        order.delete();

        gateway.saveAndFlush(order);
    }

}
