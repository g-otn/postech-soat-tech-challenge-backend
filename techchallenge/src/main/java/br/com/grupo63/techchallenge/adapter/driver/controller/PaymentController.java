package br.com.grupo63.techchallenge.adapter.driver.controller;

import br.com.grupo63.techchallenge.core.domain.entity.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payments", description = "Controller that handle rest requests related to payment using JSON.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Operation(
            summary = "Simulate fake checkout: Start payment process",
            description = "Creates a payment entity associated with an order and returns it's id and Mercado Pago QR Code to be paid." )
    @PostMapping("/create")
    public String findById(@Parameter(description = "Id of the payment") @RequestParam Long orderId) {
        // TODO
        return "new payment id and qrcode";
    }


    @Operation(
            summary = "Simulate fake checkout: Confirm payment",
            description = "Would be called by the external system Mercado Pago via an IPN Webhook integration to notify backend that the payment was completed." )
    @PostMapping("/complete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void confirmPayment(@Parameter(description = "Id of the payment") @PathVariable Long id) {
        // https://www.mercadopago.com.br/developers/pt/docs/qr-code/integration-configuration/qr-dynamic/integration
        // https://www.mercadopago.com.br/developers/pt/docs/your-integrations/notifications/webhooks
        // point_integration_wh state_FINISHED
        // TODO
    }

    @Operation(
            summary = "Get a payment by its id",
            description = "Would be polled by the customer totem to check if the payment was completed successfully." )
    @GetMapping("/status/{id}")
    public String getStatusById(@Parameter(description = "Id of the payment") @PathVariable Long id) {
        // TODO
        return id.toString();
    }

}
