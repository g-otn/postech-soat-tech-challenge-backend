package br.com.grupo63.techchallenge.controller;

import br.com.grupo63.techchallenge.adapter.OrderAdapter;
import br.com.grupo63.techchallenge.api.controller.order.dto.CreateOrderRequestDTO;
import br.com.grupo63.techchallenge.controller.dto.OrderControllerDTO;
import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.entity.order.OrderStatus;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.exception.ValidationException;
import br.com.grupo63.techchallenge.presenter.OrderPresenter;
import br.com.grupo63.techchallenge.usecase.order.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderController {

    private final OrderUseCase orderUseCase;

    // Remover este metodo, status nao fica mais na order
    public OrderStatus advanceStatus(Long orderId) throws NotFoundException, ValidationException {
        Order entity = orderUseCase.read(orderId);
        return orderUseCase.advanceStatus(entity);
    }

    // Remover este metodo, status nao fica mais na order
    public List<OrderControllerDTO> listUnfinishedOrders() {
        return orderUseCase.listUnfinishedOrders().stream().map(OrderPresenter::toDto).toList();
    }

    public OrderControllerDTO create(Long clientId, CreateOrderRequestDTO dto) throws ValidationException, NotFoundException {
        Order entity = new Order();
        OrderAdapter.fillEntity(dto, clientId, entity);
        entity = orderUseCase.create(entity);
        return OrderPresenter.toDto(entity);
    }

    public OrderControllerDTO read(Long orderId) throws NotFoundException {
        return OrderPresenter.toDto(orderUseCase.read(orderId));
    }

    public List<OrderControllerDTO> list() {
        return orderUseCase.list().stream().map(OrderPresenter::toDto).toList();
    }

    public OrderControllerDTO update(OrderControllerDTO dto, Long orderId) throws ValidationException, NotFoundException {
        Order entity = orderUseCase.read(orderId);
        OrderAdapter.fillEntity(dto, entity);
        entity = orderUseCase.update(entity);
        return OrderPresenter.toDto(entity);
    }

    public void delete(Long orderId) throws NotFoundException {
        Order entity = orderUseCase.read(orderId);
        orderUseCase.delete(entity);
    }

}
