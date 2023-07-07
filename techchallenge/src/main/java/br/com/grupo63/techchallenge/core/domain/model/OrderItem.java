package br.com.grupo63.techchallenge.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OrderItem extends Domain {

    private Long quantity;
    private Double price;
    private Order order;
    private Product product;

    public OrderItem(Long id, boolean deleted, Long quantity, Double price, Order order, Product product) {
        super(id, deleted);
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.product = product;
    }
}
