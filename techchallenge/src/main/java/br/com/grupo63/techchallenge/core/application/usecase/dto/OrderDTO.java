package br.com.grupo63.techchallenge.core.application.usecase.dto;

import br.com.grupo63.techchallenge.core.domain.model.client.Client;
import br.com.grupo63.techchallenge.core.domain.model.order.Order;
import br.com.grupo63.techchallenge.core.domain.model.order.OrderItem;
import br.com.grupo63.techchallenge.core.domain.model.order.OrderStatus;
import br.com.grupo63.techchallenge.core.domain.model.payment.Payment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
public class OrderDTO extends AbstractUseCaseDomainDTO<Order> {

    @Schema(defaultValue = "15.99")
    private Double totalPrice;

    @Schema(defaultValue = "Recebido")
    private OrderStatus status;

    private ClientDTO client;

    @NotNull
    private List<OrderItemDTO> items = new ArrayList<>();

    private PaymentDTO payment;

    public static OrderDTO toDto(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setPayment(order.getPayment() != null ? PaymentDTO.toDto(order.getPayment()) : null);
        orderDTO.setClient(ClientDTO.toDto(order.getClient()));
        orderDTO.setItems(order.getItems().stream().map(OrderItemDTO::toDto).toList());

        return orderDTO;
    }

    public void fillDomain(Order order) {
        order.setTotalPrice(totalPrice);
        order.setStatus(status);
        order.setItems(items.stream().map(item -> {
            OrderItem orderItem = order.getByProductId(item.getProductId());
            item.fillDomain(orderItem);
            return orderItem;
        }).toList());
        Client clientModel = order.getClient() != null ? order.getClient() : new Client();
        client.fillDomain(clientModel);
        order.setClient(clientModel);
        if (payment != null) {
            Payment paymentModel = order.getPayment() != null ? order.getPayment() : new Payment();
            payment.fillDomain(paymentModel);
            order.setPayment(paymentModel);
        }
    }
}
