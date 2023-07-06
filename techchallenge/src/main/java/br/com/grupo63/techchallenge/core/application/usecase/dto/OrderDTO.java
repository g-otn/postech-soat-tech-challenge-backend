package br.com.grupo63.techchallenge.core.application.usecase.dto;

import br.com.grupo63.techchallenge.core.domain.model.Order;
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
public class OrderDTO {

    private Long id;

    private Double totalPrice;

    private String status;

    private ClientDTO clientDTO;

    private List<OrderItemDTO> itemsDTO = new ArrayList<>();

    private PaymentDTO paymentDTO;

    public Order toDomain() {
        return new Order(
                Order.Status.valueOf(status),
                totalPrice,
                clientDTO.toDomain(),
                itemsDTO.stream().map(OrderItemDTO::toDomain).toList(),
                paymentDTO.toDomain()
        );
    }

    public static OrderDTO toDto(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setStatus(orderDTO.getStatus().toString());
        orderDTO.setTotalPrice(order.getTotalPrice());

        orderDTO.setPaymentDTO(PaymentDTO.toDto(order.getPayment()));
        orderDTO.setClientDTO(ClientDTO.toDto(order.getClient()));
        orderDTO.setItemsDTO(order.getItems().stream().map(OrderItemDTO::toDto).toList());

        return orderDTO;
    }
}
