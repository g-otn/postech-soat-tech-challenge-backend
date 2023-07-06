package br.com.grupo63.techchallenge.core.application.usecase.payment;

import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.core.domain.model.payment.Payment;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public interface IPaymentUseCase {

    String generateQRCode(@NotNull Long orderId);

    void confirmPayment(@NotNull Long orderId) throws ValidationException;

    PaymentStatus getPaymentStatus(@NotNull Long orderId);

}
