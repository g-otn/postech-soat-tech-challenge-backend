package br.com.grupo63.techchallenge.adapter;

import br.com.grupo63.techchallenge.api.controller.order.dto.AdvanceOrderStatusResponseDTO;
import br.com.grupo63.techchallenge.api.controller.order.dto.CreateOrderRequestDTO;
import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;
import br.com.grupo63.techchallenge.controller.dto.OrderControllerDTO;
import br.com.grupo63.techchallenge.controller.dto.OrderItemControllerDTO;
import br.com.grupo63.techchallenge.entity.client.Client;
import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.entity.order.OrderItem;
import br.com.grupo63.techchallenge.entity.order.OrderStatus;
import br.com.grupo63.techchallenge.entity.payment.Payment;
import br.com.grupo63.techchallenge.entity.product.Product;
import br.com.grupo63.techchallenge.presenter.ClientPresenter;
import br.com.grupo63.techchallenge.presenter.PaymentPresenter;

import java.util.stream.Collectors;

public class OrderAdapter {

    public static void fillEntity(CreateOrderRequestDTO dto, Long clientId, Order order) {
        OrderControllerDTO orderDTO = new OrderControllerDTO();

        ClientControllerDTO clientDTO = new ClientControllerDTO();
        clientDTO.setId(clientId);
        orderDTO.setClient(clientDTO);

        if (dto.getItems() != null) {
            orderDTO.setItems(dto.getItems().stream()
                    .map(i -> new OrderItemControllerDTO(i.getQuantity(), null, i.getId()))
                    .collect(Collectors.toList()));
        }

        fillEntity(orderDTO, order);
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
