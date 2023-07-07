package br.com.grupo63.techchallenge.core.application.usecase.payment;

import br.com.grupo63.techchallenge.core.application.external.payment.IMercadoPagoService;
import br.com.grupo63.techchallenge.core.application.usecase.dto.OrderDTO;
import br.com.grupo63.techchallenge.core.application.usecase.dto.PaymentDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.core.application.usecase.order.OrderUseCase;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentMethod;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentUseCase implements IPaymentUseCase {

    private final IMercadoPagoService mercadoPagoService;
    private final OrderUseCase orderUseCase;

    @Override
    public String startPayment(@NotNull(message = "payment.order.id.notNull") Long orderId) throws NotFoundException {
        OrderDTO orderDTO = orderUseCase.read(orderId);

        String qrData = mercadoPagoService.generateQRCode(orderDTO.getId(), orderDTO.getTotalPrice());

        orderDTO.setPayment(new PaymentDTO(PaymentStatus.PENDING, PaymentMethod.MERCADO_PAGO_QR_CODE, qrData));
        orderUseCase.update(orderDTO, orderId);
        return qrData;
    }

    @Override
    public void finishPayment(@NotNull(message = "payment.order.id.notNull") Long orderId) throws ValidationException, NotFoundException {
        OrderDTO orderDTO = orderUseCase.read(orderId);

        if (orderDTO.getPayment() == null || PaymentStatus.PAID.equals(orderDTO.getPayment().getStatus())) {
            throw new ValidationException("payment.confirm.title", "payment.confirm.alreadyPaid");
        }

        orderDTO.getPayment().setStatus(PaymentStatus.PAID);
        orderUseCase.update(orderDTO, orderId);
        orderUseCase.advanceOrderStatus(orderId);
    }

    @Override
    public PaymentStatus getPaymentStatus(@NotNull Long orderId) throws NotFoundException, ValidationException {
        OrderDTO orderDTO = orderUseCase.read(orderId);

        if (orderDTO.getPayment() == null) {
            throw new ValidationException("payment.confirm.title", "payment.notStarted");
        }

        return orderDTO.getPayment().getStatus();
    }
}
