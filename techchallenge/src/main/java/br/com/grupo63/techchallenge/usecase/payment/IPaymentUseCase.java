package br.com.grupo63.techchallenge.usecase.payment;

import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.entity.payment.PaymentStatus;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.exception.ValidationException;

public interface IPaymentUseCase {

    String startPayment(Order entity) throws NotFoundException, ValidationException;

    void finishPayment(Order entity) throws NotFoundException, ValidationException;

    PaymentStatus getPaymentStatus(Order entity) throws NotFoundException, ValidationException;

}
