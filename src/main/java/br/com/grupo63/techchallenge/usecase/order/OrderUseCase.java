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
    // IdentificationGateway identificationGateway;

    private void fillCurrentPrices(Order order) throws NotFoundException {
        double totalPrice = 0.0D;
        for (OrderItem orderItem : order.getItems()) {
            Product product = productGateway.findByIdAndDeletedFalse(orderItem.getProduct().getId()).orElseThrow(NotFoundException::new);
            orderItem.setPrice(product.getPrice());
            totalPrice += product.getPrice() * orderItem.getQuantity();
        }
        order.setTotalPrice(totalPrice);
    }

    // ISSO E DE PRODUCTION NAO FAZER!!!!
    // REMOVER este metodo, status nao fica mais na order
    @Override
    public OrderStatus advanceStatus(Order entity) throws ValidationException {
        // GET /public/order/{orderId}
        // Order order = orderGateway.getById(orderId);
        entity.advanceStatus();
        return gateway.saveAndFlush(entity).getStatus();
    }

    // REMOVER este metodo, status nao fica mais na order
    @Override
    public List<Order> listUnfinishedOrders() {
        // return gateway.findByStatusNotFinishedOrderByCreationDate();
        return gateway.findByStatusNotFinishedAndDeletedOrderByCreationDate();
    }

    @Override
    public Order create(Order entity) throws ValidationException, NotFoundException {
        // GET /public/clients/{clientId}
        // Client client = identificationGateway.getById(clientId);
        // order.setClient(client);
        fillCurrentPrices(entity);
        validator.validate(entity, Create.class);
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
