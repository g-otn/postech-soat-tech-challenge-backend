package br.com.grupo63.techchallenge.api.controller.payment;

import br.com.grupo63.techchallenge.api.controller.AbstractAPIController;
import br.com.grupo63.techchallenge.api.controller.payment.dto.PaymentStatusResponseDTO;
import br.com.grupo63.techchallenge.api.controller.payment.dto.QRCodeResponseDTO;
import br.com.grupo63.techchallenge.controller.PaymentController;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.exception.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pagamentos", description = "Gerencia o processo de pagamento de um pedido")
@RequiredArgsConstructor
@RestController
@RequestMapping("/pagamentos")
public class PaymentAPIController extends AbstractAPIController {

    private final PaymentController controller;

    @Operation(
            tags = "3ª chamada - Fluxo principal - Pagamento",
            summary = "Fake checkout: Confirma pedido",
            description = "Registra um pedido e o associa a um pedido. Retorna o QRCode gerado via Mercado Pago para exibição ao cliente.")
    @PostMapping("/iniciar")
    public QRCodeResponseDTO startPayment(@Parameter(description = "Id do pedido") @RequestParam Long orderId,
                                          HttpServletRequest request) throws NotFoundException, ValidationException {
        return controller.startPayment(Long.parseLong((String) request.getAttribute("clientId")), orderId);
    }

    @Operation(
            tags = "4ª chamada - Fluxo principal - Pagamento",
            summary = "Fake checkout: Finalizar pagamento",
            description = "Atualiza o status do pagamento e do pedido. Seria utilizado pelo sistema externo Mercado Pago para simular uma integração de Webhook IPN para notificar o sistema que o pagamento foi concluido.")
    @PostMapping("/finalizar")
    @ResponseStatus(HttpStatus.OK)
    public void confirmPaymentFromOrderId(@Parameter(description = "Id do pedido associado ao pagamento")
                                          @RequestParam Long orderId) throws ValidationException, NotFoundException {
        controller.finishPayment(orderId);
    }

    @Operation(
            tags = "4ª chamada - Fluxo principal - Pagamento",
            summary = "Recuperar status do pagamento",
            description = "Recupera o status atual do pagamento. Seria utilizado na tela de pagamento do cliente para verificar se o pagamento foi realizado.")
    @GetMapping("/status")
    public PaymentStatusResponseDTO getStatusByOrderId(@Parameter(description = "Id do pedido associado ao pagamento")
                                                       @RequestParam Long orderId) throws NotFoundException, ValidationException {
        return controller.getPaymentStatus(orderId);
    }

}
