package br.com.grupo63.techchallenge.core.application.usecase.payment;

import br.com.grupo63.techchallenge.core.application.external.payment.IMercadoPagoService;
import br.com.grupo63.techchallenge.core.application.repository.payment.IPaymentRepository;
import br.com.grupo63.techchallenge.core.application.usecase.ICRUDUseCase;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.core.domain.entity.Order;
import br.com.grupo63.techchallenge.core.domain.entity.Payment;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaymentUseCase implements IPaymentUseCase {

    private final MessageSource messageSource;
    private final IMercadoPagoService mercadoPagoService;
    private final IPaymentRepository paymentRepository;
    // TODO: Uncomment related code once usecase is implemented
//    private OrderUseCase orderUseCase;

    public String generateQRCode(@NotNull(message = "payment.order.id.notNull") Long orderId) {
//        Order order = orderUseCase.read(orderId);
        return mercadoPagoService.generateQRCode(123L, 25.04);
    }

    public void confirmPayment(@NotNull(message = "payment.order.id.notNull") Long orderId) throws ValidationException {
//        Order order = orderUseCase.read(orderId);
//        Payment payment = paymentRepository.findById(order.getId()).orElseThrow();
//
//        if (payment.getStatus().equals(Payment.Status.PAID)) {
//            throw new ValidationException(
//                    messageSource.getMessage("payment.confirm.title", null, LocaleContextHolder.getLocale()),
//                    messageSource.getMessage("payment.confirm.alreadyPaid", null, LocaleContextHolder.getLocale())
//            );
//        }
//
//        payment.setStatus(Payment.Status.PAID);
//        paymentRepository.saveAndFlush(payment);
    }

    public Payment.Status getPaymentStatus(@NotNull Long orderId) {
//        Order order = orderUseCase.read(orderId);
//        Payment payment = paymentRepository.findById(order.getId()).orElseThrow();
//
//        return payment.getStatus();
        return null;
    }
}
