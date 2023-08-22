package br.com.grupo63.techchallenge.entity.order;

import br.com.grupo63.techchallenge.entity.Entity;
import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.entity.payment.Payment;
import br.com.grupo63.techchallenge.entity.validation.group.Create;
import br.com.grupo63.techchallenge.entity.validation.group.Update;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
}
