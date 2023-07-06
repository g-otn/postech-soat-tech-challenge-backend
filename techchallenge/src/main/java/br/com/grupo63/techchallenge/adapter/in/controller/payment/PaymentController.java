package br.com.grupo63.techchallenge.adapter.in.controller.payment;

import br.com.grupo63.techchallenge.adapter.in.controller.AbstractController;
import br.com.grupo63.techchallenge.adapter.in.controller.payment.dto.PaymentStatusResponseDTO;
import br.com.grupo63.techchallenge.adapter.in.controller.payment.dto.QRCodeResponseDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.core.application.usecase.payment.IPaymentUseCase;
import br.com.grupo63.techchallenge.core.domain.model.payment.Payment;
import br.com.grupo63.techchallenge.core.domain.model.payment.PaymentStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pagamentos", description = "Gerencia o processo de pagamento de um pedido")
@RequiredArgsConstructor
@RestController
@RequestMapping("/pagamentos")
public class PaymentController extends AbstractController {

    private final IPaymentUseCase paymentUseCase;

    @Operation(
            summary = "Fake checkout: Gerar QR Code",
            description = "Creates a payment entity associated with an order and returns it's id and Mercado Pago QR Code to be paid." )
    @PostMapping("/criar-qrcode")
    public QRCodeResponseDTO createQRCodeFromOrderId(
            @Parameter(description = "Id do pedido associado ao pagamento") @RequestParam Long orderId
    ) {
        String qrData = paymentUseCase.generateQRCode(orderId);
        return new QRCodeResponseDTO(qrData);
    }

    @Operation(
            summary = "Fake checkout: Finalizar pagamento",
            description = "Would be called by the external system Mercado Pago via an IPN Webhook integration to notify backend that the payment was completed." )
    @PostMapping("/finalizar")
    @ResponseStatus(HttpStatus.OK)
    public void confirmPaymentFromOrderId(@Parameter(description = "Id do pedido associado ao pagamento")
                                              @RequestParam Long orderId) throws ValidationException {
        paymentUseCase.confirmPayment(orderId);
    }

    @Operation(
            summary = "Get a payment by its id",
            description = "Would be polled by the customer totem to check if the payment was completed successfully." )
    @GetMapping("/status")
    public PaymentStatusResponseDTO getStatusByOrderId(@Parameter(description = "Id do pedido associado ao pagamento")
                                         @RequestParam Long orderId) {
        PaymentStatus status = paymentUseCase.getPaymentStatus(orderId);
        return new PaymentStatusResponseDTO(status);
    }

}
