package br.com.grupo63.techchallenge.entity.order;

import br.com.grupo63.techchallenge.entity.Entity;
import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.entity.payment.Payment;
import br.com.grupo63.techchallenge.entity.payment.PaymentStatus;
import br.com.grupo63.techchallenge.entity.validation.group.Create;
import br.com.grupo63.techchallenge.entity.validation.group.Update;
import br.com.grupo63.techchallenge.exception.ValidationException;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends Entity {

    private OrderStatus status;
    private Double totalPrice;
    private Client client;

    @NotNull(message = "order.create.items.notEmpty", groups = {Create.class, Update.class})
    @Size(min = 1, message = "order.create.items.notEmpty", groups = {Create.class, Update.class})
    private List<OrderItem> items = new ArrayList<>();
    private Payment payment;

    private final Map<OrderStatus, OrderStatus> nextOrderMap = Map.ofEntries(
            entry(OrderStatus.RECEIVED, OrderStatus.PREPARING),
            entry(OrderStatus.PREPARING, OrderStatus.READY),
            entry(OrderStatus.READY, OrderStatus.FINISHED));

    public Order(Long id, boolean deleted, OrderStatus status, Double totalPrice, Client client, List<OrderItem> items, Payment payment) {
        super(id, deleted);
        this.status = status;
        this.totalPrice = totalPrice;
        this.client = client;
        this.items = items;
        this.payment = payment;
    }

    public OrderItem getByProductId(Long id) {
        List<OrderItem> selectedItems = this.items.stream().filter(item -> item.getProduct().getId().equals(id)).toList();

        return selectedItems.isEmpty() ? new OrderItem() : selectedItems.get(0);
    }

    public void canStartPayment() throws ValidationException {
        if (this.getStatus() != null) {
            throw new ValidationException("payment.startPayment.title", "payment.startPayment.alreadyFinished");
        } else if (this.getPayment() != null) {
            throw new ValidationException("payment.startPayment.title", "payment.startPayment.alreadyStarted");
        }
    }

    public void canFinishPayment() throws ValidationException {
        if (this.getPayment() == null) {
            throw new ValidationException("payment.confirm.title", "payment.notStarted");
        } else if (PaymentStatus.PAID.equals(this.getPayment().getStatus())) {
            throw new ValidationException("payment.confirm.title", "payment.confirm.alreadyPaid");
        }
    }

    public void advanceStatus() throws ValidationException {
        if (this.getPayment() == null || this.getPayment().getStatus() != PaymentStatus.PAID) {
            throw new ValidationException("order.advanceStatus.title", "order.advanceStatus.notPaid");
        } else if (this.getStatus() == OrderStatus.FINISHED) {
            throw new ValidationException("order.advanceStatus.title", "order.advanceStatus.finished");
        } else if (this.getStatus() == null) {
            this.setStatus(OrderStatus.RECEIVED);
        } else {
            this.setStatus(nextOrderMap.get(this.getStatus()));
        }
    }
}
