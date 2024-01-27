package br.com.grupo63.techchallenge.usecase.payment;

import br.com.grupo63.techchallenge.entity.order.Order;
import br.com.grupo63.techchallenge.entity.payment.Payment;
import br.com.grupo63.techchallenge.entity.payment.PaymentMethod;
import br.com.grupo63.techchallenge.entity.payment.PaymentStatus;
import br.com.grupo63.techchallenge.exception.ValidationException;
import br.com.grupo63.techchallenge.gateway.payment.IMercadoPagoGateway;
import br.com.grupo63.techchallenge.usecase.order.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentUseCase implements IPaymentUseCase {

    private final IMercadoPagoGateway mercadoPagoService;
    private final OrderUseCase orderUseCase;

    @Override
    public String startPayment(Order entity) throws ValidationException {
        entity.canStartPayment();

        String qrData = mercadoPagoService.generateQRCode(entity.getId(), entity.getTotalPrice());

        entity.setPayment(new Payment(null, false, PaymentStatus.PENDING, PaymentMethod.MERCADO_PAGO_QR_CODE, qrData, entity));
        orderUseCase.update(entity);
        return qrData;
    }

    @Override
    public void finishPayment(Order entity) throws ValidationException {
        entity.canFinishPayment();
        entity.getPayment().setStatus(PaymentStatus.PAID);
        entity = orderUseCase.update(entity);
        orderUseCase.advanceStatus(entity);
    }

    @Override
    public PaymentStatus getPaymentStatus(Order entity) throws ValidationException {
        if (entity.getPayment() == null) {
            throw new ValidationException("payment.confirm.title", "payment.notStarted");
        }

        return entity.getPayment().getStatus();
    }
}
