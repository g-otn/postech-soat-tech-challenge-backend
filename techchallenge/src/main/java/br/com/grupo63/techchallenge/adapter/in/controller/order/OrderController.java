package br.com.grupo63.techchallenge.adapter.in.controller.order;

import br.com.grupo63.techchallenge.adapter.in.controller.AbstractController;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.order.IOrderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pedidos", description = "Gerencia o processo de pedidos.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class OrderController extends AbstractController {

    private final IOrderUseCase orderUseCase;

    @Operation(
            summary = "Orders: Avançar com o status do pedido.",
            description = "Should be called to advance a specific order status.")
    @PostMapping("/avancar-estado")
    @ResponseStatus(HttpStatus.OK)
    public void advanceOrderStatusFromOrderId(@Parameter(description = "Id do pedido.") @RequestParam Long orderId) throws NotFoundException {
        orderUseCase.advanceOrderStatus(orderId);
    }

}
