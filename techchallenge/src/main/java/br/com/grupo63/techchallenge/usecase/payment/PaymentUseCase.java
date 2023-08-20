package br.com.grupo63.techchallenge.usecase.payment;

import br.com.grupo63.techchallenge.entity.payment.PaymentMethod;
import br.com.grupo63.techchallenge.entity.payment.PaymentStatus;
import br.com.grupo63.techchallenge.external.payment.IMercadoPagoService;
import br.com.grupo63.techchallenge.controller.dto.PaymentControllerDTO;
import br.com.grupo63.techchallenge.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.controller.dto.OrderControllerDTO;
import br.com.grupo63.techchallenge.usecase.order.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentUseCase implements IPaymentUseCase {

    private final IMercadoPagoService mercadoPagoService;
    private final OrderUseCase orderUseCase;

    @Override
    public static String startPayment(Long orderId) throws NotFoundException, ValidationException {
        OrderControllerDTO orderDTO = orderUseCase.read(orderId);

        if (orderDTO.getStatus() != null) {
            throw new ValidationException("payment.startPayment.title", "payment.startPayment.alreadyFinished");
        } else if (orderDTO.getPayment() != null) {
            throw new ValidationException("payment.startPayment.title", "payment.startPayment.alreadyStarted");
        }

        String qrData = mercadoPagoService.generateQRCode(orderDTO.getId(), orderDTO.getTotalPrice());

        orderDTO.setPayment(new PaymentControllerDTO(PaymentStatus.PENDING, PaymentMethod.MERCADO_PAGO_QR_CODE, qrData));
        orderUseCase.update(orderDTO, orderId);
        return qrData;
    }

    @Override
    public void finishPayment(Long orderId) throws ValidationException, NotFoundException {
        OrderControllerDTO orderDTO = orderUseCase.read(orderId);

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
        OrderControllerDTO orderDTO = orderUseCase.read(orderId);

        if (orderDTO.getPayment() == null) {
            throw new ValidationException("payment.confirm.title", "payment.notStarted");
        }

        return orderDTO.getPayment().getStatus();
    }
}
