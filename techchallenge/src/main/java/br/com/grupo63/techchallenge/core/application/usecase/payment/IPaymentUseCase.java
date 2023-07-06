package br.com.grupo63.techchallenge.core.application.usecase.payment;

import br.com.grupo63.techchallenge.core.application.usecase.ICRUDUseCase;
import br.com.grupo63.techchallenge.core.application.usecase.dto.PaymentDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public interface IPaymentUseCase {

    String startPayment(@NotNull Long orderId) throws NotFoundException;

    void finishPayment(@NotNull Long orderId) throws ValidationException, NotFoundException;

    PaymentStatus getPaymentStatus(@NotNull Long orderId) throws NotFoundException;

}
