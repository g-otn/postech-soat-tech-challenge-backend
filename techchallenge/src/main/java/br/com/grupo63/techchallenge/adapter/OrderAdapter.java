package br.com.grupo63.techchallenge.adapter;

import br.com.grupo63.techchallenge.api.controller.order.dto.AdvanceOrderStatusResponseDTO;
import br.com.grupo63.techchallenge.api.controller.order.dto.CreateOrderRequestDTO;
import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.entity.order.OrderItem;
import br.com.grupo63.techchallenge.entity.order.OrderStatus;
import br.com.grupo63.techchallenge.entity.payment.Payment;
import br.com.grupo63.techchallenge.entity.product.Product;
import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;
import br.com.grupo63.techchallenge.controller.dto.OrderItemControllerDTO;
import br.com.grupo63.techchallenge.controller.dto.OrderControllerDTO;

import java.util.stream.Collectors;

public class OrderAdapter {

    public static OrderControllerDTO toDto(Order entity) {
        OrderControllerDTO dto = new OrderControllerDTO();

        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setPayment(entity.getPayment() != null ? PaymentAdapter.toDto(entity.getPayment()) : null);
        dto.setClient(ClientAdapter.toDto(entity.getClient()));
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

    //TODO: Perguntar se isso faz sentido ou devemos renomear o metodo
    // por que esse dto é "de saída" em vez de outros que vão para o usecase
    public static AdvanceOrderStatusResponseDTO toDto(OrderStatus status) {
        return new AdvanceOrderStatusResponseDTO(status);
    }

    public static void fillEntity(OrderControllerDTO dto, Order entity) {
        entity.setTotalPrice(dto.getTotalPrice());
        entity.setStatus(dto.getStatus());
        entity.setItems(dto.getItems().stream().map(item -> {
            OrderItem orderItem = entity.getByProductId(item.getProductId());

            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getPrice());
            if (orderItem.getProduct() == null)
                orderItem.setProduct(new Product(item.getProductId()));

            return orderItem;
        }).toList());
        Client clientEntity = entity.getClient() != null ? entity.getClient() : new Client();
        ClientAdapter.fillEntity(dto.getClient(), clientEntity);
        entity.setClient(clientEntity);
        if (dto.getPayment() != null) {
            Payment paymentEntity = entity.getPayment() != null ? entity.getPayment() : new Payment();
            PaymentAdapter.fillEntity(dto.getPayment(), paymentEntity);
            entity.setPayment(paymentEntity);
        }
    }

}
