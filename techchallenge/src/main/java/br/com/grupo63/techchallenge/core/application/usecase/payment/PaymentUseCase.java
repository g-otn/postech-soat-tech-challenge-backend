package br.com.grupo63.techchallenge.core.application.usecase.payment;

import br.com.grupo63.techchallenge.core.application.external.payment.IMercadoPagoService;
import br.com.grupo63.techchallenge.core.application.repository.IPaymentRepository;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.core.application.usecase.order.OrderUseCase;
import br.com.grupo63.techchallenge.core.domain.model.Order;
import br.com.grupo63.techchallenge.core.domain.model.payment.Payment;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentUseCase implements IPaymentUseCase {

    private final MessageSource messageSource;

    private final IMercadoPagoService mercadoPagoService;
    private final IPaymentRepository paymentRepository;
    private final OrderUseCase orderUseCase;

    @Override
    public String startPayment(@NotNull(message = "payment.order.id.notNull") Long orderId) throws NotFoundException {
        Order order = new Order();
        orderUseCase.read(orderId).toDomain(order);

        String qrData = mercadoPagoService.generateQRCode(order.getId(), order.getTotalPrice());
        // TODO: Update payment with qrData
        return qrData;
    }

    @Override
    public void finishPayment(@NotNull(message = "payment.order.id.notNull") Long orderId) throws ValidationException, NotFoundException {
        Order order = new Order();
        orderUseCase.read(orderId).toDomain(order);
        Payment payment = paymentRepository.findByIdAndDeletedFalse(order.getId()).orElseThrow();

        if (PaymentStatus.PAID.equals(payment.getStatus())) {
            throw new ValidationException(
                    messageSource.getMessage("payment.confirm.title", null, LocaleContextHolder.getLocale()),
                    messageSource.getMessage("payment.confirm.alreadyPaid", null, LocaleContextHolder.getLocale())
            );
        }

        payment.setStatus(PaymentStatus.PAID);
        paymentRepository.saveAndFlush(payment);
        orderUseCase.advanceOrderStatus(orderId);
    }

    @Override
    public PaymentStatus getPaymentStatus(@NotNull Long orderId) throws NotFoundException {
        Order order = new Order();
        orderUseCase.read(orderId).toDomain(order);
        Payment payment = paymentRepository.findByIdAndDeletedFalse(order.getId()).orElseThrow();

        return payment.getStatus();
    }
}
