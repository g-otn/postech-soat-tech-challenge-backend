package br.com.grupo63.techchallenge.presenter;

import br.com.grupo63.techchallenge.api.controller.order.dto.AdvanceOrderStatusResponseDTO;
import br.com.grupo63.techchallenge.api.controller.order.dto.CreateOrderRequestDTO;
import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;
import br.com.grupo63.techchallenge.controller.dto.OrderControllerDTO;
import br.com.grupo63.techchallenge.controller.dto.OrderItemControllerDTO;
import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.entity.order.OrderStatus;

import java.util.stream.Collectors;

public class OrderPresenter {

    public static OrderControllerDTO toDto(Order entity) {
        OrderControllerDTO dto = new OrderControllerDTO();

        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setPayment(entity.getPayment() != null ? PaymentPresenter.toDto(entity.getPayment()) : null);
        dto.setClient(ClientPresenter.toDto(entity.getClient()));
        dto.setItems(entity.getItems().stream().map(orderItemEntity -> {
            OrderItemControllerDTO orderItemUseCaseDTO = new OrderItemControllerDTO();

            orderItemUseCaseDTO.setId(orderItemEntity.getId());
            orderItemUseCaseDTO.setQuantity(orderItemEntity.getQuantity());
            orderItemUseCaseDTO.setPrice(orderItemEntity.getPrice());
            orderItemUseCaseDTO.setProductId(orderItemEntity.getProduct().getId());

            return orderItemUseCaseDTO;
        }).toList());

        return dto;
    }

    public static OrderControllerDTO toDto(CreateOrderRequestDTO dto) {
        OrderControllerDTO orderUseCaseDTO = new OrderControllerDTO();

        ClientControllerDTO clientDTO = new ClientControllerDTO();
        clientDTO.setId(dto.getClientId());
        orderUseCaseDTO.setClient(clientDTO);

        if (dto.getItems() != null) {
            orderUseCaseDTO.setItems(dto.getItems().stream()
                    .map(i -> new OrderItemControllerDTO(i.getQuantity(), null, i.getId()))
                    .collect(Collectors.toList()));
        }
        return orderUseCaseDTO;
    }

    public static AdvanceOrderStatusResponseDTO toDto(OrderStatus status) {
        return new AdvanceOrderStatusResponseDTO(status);
    }

}
