package br.com.grupo63.techchallenge.core.domain.model.order;

import br.com.grupo63.techchallenge.core.domain.model.client.Client;
import br.com.grupo63.techchallenge.core.domain.model.Domain;
import br.com.grupo63.techchallenge.core.domain.model.payment.Payment;
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

public class Order extends Domain {

    private OrderStatus status;
    private Double totalPrice;
    private Client client;
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
