package br.com.grupo63.techchallenge.controller.dto;

import br.com.grupo63.techchallenge.entity.order.OrderStatus;
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
public class OrderControllerDTO extends AbstractControllerDTO {

    @Schema(defaultValue = "15.99")
    private Double totalPrice;

    @Schema(defaultValue = "Recebido")
    private OrderStatus status;
    private ClientControllerDTO client;
    private List<OrderItemControllerDTO> items = new ArrayList<>();
    private PaymentControllerDTO payment;

}
