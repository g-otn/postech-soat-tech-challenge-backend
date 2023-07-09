package br.com.grupo63.techchallenge.core.application.usecase.payment;

import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public interface IPaymentUseCase {

    String startPayment(Long orderId) throws NotFoundException, ValidationException;

    void finishPayment(Long orderId) throws ValidationException, NotFoundException;

    PaymentStatus getPaymentStatus(Long orderId) throws NotFoundException, ValidationException;

}
