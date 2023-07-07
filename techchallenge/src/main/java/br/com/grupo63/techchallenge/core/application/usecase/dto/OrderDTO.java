package br.com.grupo63.techchallenge.core.application.usecase.dto;

import br.com.grupo63.techchallenge.core.domain.model.Client;
import br.com.grupo63.techchallenge.core.domain.model.Order;
import br.com.grupo63.techchallenge.core.domain.model.OrderItem;
import br.com.grupo63.techchallenge.core.domain.model.payment.Payment;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(defaultValue = "0")
    private Long id;

    @Schema(defaultValue = "15.99")
    private Double totalPrice;

    @Schema(defaultValue = "Recebido")
    private String status;

    private ClientDTO client;

    private List<OrderItemDTO> items = new ArrayList<>();

    private PaymentDTO payment;

    public static OrderDTO toDto(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setStatus(orderDTO.getStatus());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setPayment(order.getPayment() != null ? PaymentDTO.toDto(order.getPayment()) : null);
        orderDTO.setClient(ClientDTO.toDto(order.getClient()));
        orderDTO.setItems(order.getItems().stream().map(OrderItemDTO::toDto).toList());

        return orderDTO;
    }

    public void toDomain(Order order) {
        order.setTotalPrice(totalPrice);
        order.setStatus(status != null ? Order.Status.valueOf(status) : null);
        order.setItems(items.stream().map(item -> {
            OrderItem orderItem = order.getByProductId(item.getProductId());
            item.toDomain(orderItem);
            return orderItem;
        }).toList());
        Client clientModel = order.getClient() != null ? order.getClient() : new Client();
        client.toDomain(clientModel);
        order.setClient(clientModel);
        if (payment != null) {
            Payment paymentModel = order.getPayment() != null ? order.getPayment() : new Payment();
            payment.toDomain(paymentModel);
            order.setPayment(paymentModel);
        }
    }
}
