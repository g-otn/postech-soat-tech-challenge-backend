package br.com.grupo63.techchallenge.adapter.in.controller.payment;

import br.com.grupo63.techchallenge.adapter.in.controller.AbstractController;
import br.com.grupo63.techchallenge.adapter.in.controller.payment.dto.PaymentStatusResponseDTO;
import br.com.grupo63.techchallenge.adapter.in.controller.payment.dto.QRCodeResponseDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.core.application.usecase.payment.IPaymentUseCase;
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
            tags = { "1º Chamada" ," asfasfa"},
            summary = "Fake checkout: Gerar QR Code",
            description = "Cria e associa um QRCode a um pagamento de um pedido. Retorna o QRCode gerado para exibição ao cliente." )
    @PostMapping("/iniciar")
    public QRCodeResponseDTO startPayment(
            @Parameter(description = "Id do pedido") @RequestParam Long orderId
    ) throws NotFoundException {
        String qrData = paymentUseCase.startPayment(orderId);
        return new QRCodeResponseDTO(qrData);
    }

    @Operation(
            summary = "Fake checkout: Finalizar pagamento",
            description = "Atualiza o status do pagamento e do pedido. Seria utilizado pelo sistema externo Mercado Pago para simular uma integração de Webhook IPN para notificar o sistema que o pagamento foi concluido." )
    @PostMapping("/finalizar")
    @ResponseStatus(HttpStatus.OK)
    public void confirmPaymentFromOrderId(@Parameter(description = "Id do pedido associado ao pagamento")
                                              @RequestParam Long orderId) throws ValidationException, NotFoundException {
        paymentUseCase.finishPayment(orderId);
    }

    @Operation(
            summary = "Recuperar status do pagamento",
            description = "Recupera o status atual do pagamento. Seria utilizado na tela de pagamento do cliente para verificar se o pagamento foi realizado." )
    @GetMapping("/status")
    public PaymentStatusResponseDTO getStatusByOrderId(@Parameter(description = "Id do pedido associado ao pagamento")
                                         @RequestParam Long orderId) throws NotFoundException, ValidationException {
        PaymentStatus status = paymentUseCase.getPaymentStatus(orderId);
        return new PaymentStatusResponseDTO(status);
    }

}
