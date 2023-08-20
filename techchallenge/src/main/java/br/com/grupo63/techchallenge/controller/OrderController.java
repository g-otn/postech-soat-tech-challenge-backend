package br.com.grupo63.techchallenge.controller;

import br.com.grupo63.techchallenge.adapter.OrderAdapter;
import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.gateway.repository.IOrderRepository;
import br.com.grupo63.techchallenge.controller.dto.OrderControllerDTO;
import br.com.grupo63.techchallenge.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.usecase.order.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderController {

    private final OrderUseCase orderUseCase;
    private final IOrderRepository gateway;

    public OrderControllerDTO create(OrderControllerDTO dto) throws NotFoundException {
        Order entity = new Order();
        OrderAdapter.fillEntity(dto, entity);
        entity = orderUseCase.create(entity, gateway);
        return OrderAdapter.toDto(entity);
    }

    public OrderControllerDTO update(OrderControllerDTO dto, Long orderId) throws NotFoundException {
        Order entity = orderUseCase.read(orderId, gateway);
        OrderAdapter.fillEntity(dto, entity);
        entity = orderUseCase.create(entity, gateway);
        return OrderAdapter.toDto(entity);
    }

}
