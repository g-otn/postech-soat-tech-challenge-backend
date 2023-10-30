package br.com.grupo63.techchallenge.controller;

import br.com.grupo63.techchallenge.api.controller.payment.dto.PaymentStatusResponseDTO;
import br.com.grupo63.techchallenge.api.controller.payment.dto.QRCodeResponseDTO;
import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.exception.ValidationException;
import br.com.grupo63.techchallenge.presenter.PaymentPresenter;
import br.com.grupo63.techchallenge.usecase.order.OrderUseCase;
import br.com.grupo63.techchallenge.usecase.payment.PaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentController {

    private final OrderUseCase orderUseCase;
    private final PaymentUseCase useCase;

    public QRCodeResponseDTO startPayment(Long clientId, Long orderId) throws NotFoundException, ValidationException {
        Order entity = orderUseCase.read(orderId);
        return PaymentPresenter.toDto(useCase.startPayment(clientId, entity));
    }

    public void finishPayment(Long orderId) throws NotFoundException, ValidationException {
        Order entity = orderUseCase.read(orderId);
        useCase.finishPayment(entity);
    }

    public PaymentStatusResponseDTO getPaymentStatus(Long orderId) throws NotFoundException, ValidationException {
        Order entity = orderUseCase.read(orderId);
        return PaymentPresenter.toDto(useCase.getPaymentStatus(entity));
    }

}
