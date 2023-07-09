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
    public String startPayment(Long orderId) throws NotFoundException, ValidationException {
        OrderDTO orderDTO = orderUseCase.read(orderId);

        if (orderDTO.getStatus() != null) {
            throw new ValidationException("payment.startPayment.title", "payment.startPayment.alreadyFinished");
        } else if (orderDTO.getPayment() != null) {
            throw new ValidationException("payment.startPayment.title", "payment.startPayment.alreadyStarted");
        }

        String qrData = mercadoPagoService.generateQRCode(orderDTO.getId(), orderDTO.getTotalPrice());

        orderDTO.setPayment(new PaymentDTO(PaymentStatus.PENDING, PaymentMethod.MERCADO_PAGO_QR_CODE, qrData));
        orderUseCase.update(orderDTO, orderId);
        return qrData;
    }

    @Override
    public void finishPayment(Long orderId) throws ValidationException, NotFoundException {
        OrderDTO orderDTO = orderUseCase.read(orderId);

        if (orderDTO.getPayment() == null) {
            throw new ValidationException("payment.confirm.title", "payment.notStarted");
        }
        if (PaymentStatus.PAID.equals(orderDTO.getPayment().getStatus())) {
            throw new ValidationException("payment.confirm.title", "payment.confirm.alreadyPaid");
        }

        orderDTO.getPayment().setStatus(PaymentStatus.PAID);
        orderUseCase.update(orderDTO, orderId);
        orderUseCase.advanceStatus(orderId);
    }

    @Override
    public PaymentStatus getPaymentStatus(Long orderId) throws NotFoundException, ValidationException {
        OrderDTO orderDTO = orderUseCase.read(orderId);

        if (orderDTO.getPayment() == null) {
            throw new ValidationException("payment.confirm.title", "payment.notStarted");
        }

        return orderDTO.getPayment().getStatus();
    }
}
