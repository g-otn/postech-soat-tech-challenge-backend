package br.com.grupo63.techchallenge.core.application.usecase.dto;

import br.com.grupo63.techchallenge.core.domain.model.OrderItem;
import br.com.grupo63.techchallenge.core.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemDTO {

    private Long id;
    private Long quantity;
    private Double price;
    private Long productId;

    public static OrderItemDTO toDto(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();

        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setPrice(orderItem.getPrice());
        orderItemDTO.setProductId(orderItem.getProduct().getId());

        return orderItemDTO;
    }

    public void toDomain(OrderItem orderItem) {
        orderItem.setQuantity(quantity);
        orderItem.setPrice(price);
        if (orderItem.getProduct() == null)
            orderItem.setProduct(new Product(productId));
    }
}
