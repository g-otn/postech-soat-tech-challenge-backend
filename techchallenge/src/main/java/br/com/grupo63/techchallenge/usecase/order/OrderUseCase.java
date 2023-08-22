package br.com.grupo63.techchallenge.usecase.order;

import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.entity.order.OrderItem;
import br.com.grupo63.techchallenge.entity.order.OrderStatus;
import br.com.grupo63.techchallenge.entity.payment.PaymentStatus;
import br.com.grupo63.techchallenge.entity.product.Product;
import br.com.grupo63.techchallenge.entity.validation.group.Create;
import br.com.grupo63.techchallenge.entity.validation.group.Update;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.exception.ValidationException;
import br.com.grupo63.techchallenge.gateway.repository.IOrderRepository;
import br.com.grupo63.techchallenge.gateway.repository.IProductRepository;
import br.com.grupo63.techchallenge.usecase.Validator;
import br.com.grupo63.techchallenge.usecase.product.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@RequiredArgsConstructor
@Service
public class OrderUseCase implements IOrderUseCase {

    private final Validator validator;
    private final ProductUseCase productUseCase;
    private final IProductRepository productGateway;
    private final IOrderRepository gateway;
    private final Map<OrderStatus, OrderStatus> nextOrderMap = Map.ofEntries(
            entry(OrderStatus.RECEIVED, OrderStatus.PREPARING),
            entry(OrderStatus.PREPARING, OrderStatus.READY),
            entry(OrderStatus.READY, OrderStatus.FINISHED));

    private void fillCurrentPrices(Order order) throws NotFoundException {
        double totalPrice = 0.0D;
        for (OrderItem orderItem : order.getItems()) {
            Product product = productUseCase.read(orderItem.getProduct().getId(), productGateway);
            orderItem.setPrice(product.getPrice());
            totalPrice += product.getPrice() * orderItem.getQuantity();
        }
        order.setTotalPrice(totalPrice);
    }

    @Override
    public OrderStatus advanceStatus(Order entity) throws ValidationException {
        // TODO: Perguntar se essa logica deveria ir para dentro da entidade em um novo metodo
        // ex: produto.advanceStatus()
        if (entity.getPayment() == null || entity.getPayment().getStatus() != PaymentStatus.PAID) {
            throw new ValidationException("order.advanceStatus.title", "order.advanceStatus.notPaid");
        } else if (entity.getStatus() == OrderStatus.FINISHED) {
            throw new ValidationException("order.advanceStatus.title", "order.advanceStatus.finished");
        } else if (entity.getStatus() == null) {
            entity.setStatus(OrderStatus.RECEIVED);
        } else {
            entity.setStatus(nextOrderMap.get(entity.getStatus()));
        }

        return gateway.saveAndFlush(entity).getStatus();
    }

    @Override
    public List<Order> listUnfinishedOrders() {
        return gateway.findByStatusNotFinishedAndDeletedOrderByCreationDate();
    }

    @Override
    public Order create(Order entity) throws ValidationException, NotFoundException {
        validator.validate(entity, Create.class);
        fillCurrentPrices(entity);
        return gateway.saveAndFlush(entity);
    }

    @Override
    public Order read(Long id) throws NotFoundException {
        return gateway.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Order> list() {
        return gateway.findByDeletedFalse();
    }

    @Override
    public Order update(Order entity) throws ValidationException{
        validator.validate(entity, Update.class);
        return gateway.saveAndFlush(entity);
    }

    @Override
    public void delete(Order entity) {
        entity.delete();
        gateway.saveAndFlush(entity);
    }

}
