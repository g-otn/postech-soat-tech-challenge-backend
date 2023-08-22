package br.com.grupo63.techchallenge.entity.order;

import br.com.grupo63.techchallenge.entity.Entity;
import br.com.grupo63.techchallenge.entity.product.Product;
import br.com.grupo63.techchallenge.entity.validation.group.Create;
import br.com.grupo63.techchallenge.entity.validation.group.Update;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OrderItem extends Entity {

    @NotNull(message = "order.create.item.quantityNotNull", groups = {Create.class, Update.class})
    @Min(value = 1, message = "order.create.item.moreThan1Quantity", groups = {Create.class, Update.class})
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
