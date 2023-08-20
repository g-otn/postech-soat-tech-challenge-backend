package br.com.grupo63.techchallenge.usecase.payment;

import br.com.grupo63.techchallenge.entity.payment.PaymentStatus;
import br.com.grupo63.techchallenge.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.usecase.exception.ValidationException;

public interface IPaymentUseCase {

    String startPayment(Long orderId) throws NotFoundException, ValidationException;

    void finishPayment(Long orderId) throws ValidationException, NotFoundException;

    PaymentStatus getPaymentStatus(Long orderId) throws NotFoundException, ValidationException;

}
