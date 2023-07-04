package br.com.grupo63.techchallenge.adapter.in.controller.payment;

import br.com.grupo63.techchallenge.adapter.in.controller.AbstractController;
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

    @Operation(
            summary = "Fake checkout: Gerar QR Code",
            description = "Creates a payment entity associated with an order and returns it's id and Mercado Pago QR Code to be paid." )
    @PostMapping("/criar-qrcode")
    public String createQRCodeFromOrderId(
            @Parameter(description = "Id do pedido associado ao pagamento") @RequestParam Long orderId
    ) {
        // TODO
        return "new payment id and qrcode";
    }

    @Operation(
            summary = "Fake checkout: Finalizar pagamento",
            description = "Would be called by the external system Mercado Pago via an IPN Webhook integration to notify backend that the payment was completed." )
    @PostMapping("/finalizar")
    @ResponseStatus(HttpStatus.OK)
    public void confirmPaymentFromOrderId(@Parameter(description = "Id do pedido associado ao pagamento")
                                              @RequestParam Long orderId) {
        // https://www.mercadopago.com.br/developers/pt/docs/qr-code/integration-configuration/qr-dynamic/integration
        // https://www.mercadopago.com.br/developers/pt/docs/your-integrations/notifications/webhooks
        // point_integration_wh state_FINISHED
        // TODO
    }

    @Operation(
            summary = "Get a payment by its id",
            description = "Would be polled by the customer totem to check if the payment was completed successfully." )
    @GetMapping("/status")
    public String getStatusByOrderId(@Parameter(description = "Id do pedido associado ao pagamento")
                                         @RequestParam Long orderId) {
        // TODO
        return orderId.toString();
    }

}
