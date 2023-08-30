package br.com.grupo63.techchallenge.usecase.order;

import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.entity.order.OrderItem;
import br.com.grupo63.techchallenge.entity.order.OrderStatus;
import br.com.grupo63.techchallenge.entity.product.Product;
import br.com.grupo63.techchallenge.entity.validation.group.Create;
import br.com.grupo63.techchallenge.entity.validation.group.Update;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.exception.ValidationException;
import br.com.grupo63.techchallenge.gateway.order.IOrderGateway;
import br.com.grupo63.techchallenge.gateway.product.IProductGateway;
import br.com.grupo63.techchallenge.usecase.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderUseCase implements IOrderUseCase {

    private final Validator validator;
    private final IProductGateway productGateway;
    private final IOrderGateway gateway;

    private void fillCurrentPrices(Order order) throws NotFoundException {
        double totalPrice = 0.0D;
        for (OrderItem orderItem : order.getItems()) {
            Product product = productGateway.findByIdAndDeletedFalse(orderItem.getProduct().getId()).orElseThrow(NotFoundException::new);
            orderItem.setPrice(product.getPrice());
            totalPrice += product.getPrice() * orderItem.getQuantity();
        }
        order.setTotalPrice(totalPrice);
    }

    @Override
    public OrderStatus advanceStatus(Order entity) throws ValidationException {
        entity.advanceStatus();
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
