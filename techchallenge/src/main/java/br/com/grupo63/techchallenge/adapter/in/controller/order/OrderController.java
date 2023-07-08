package br.com.grupo63.techchallenge.adapter.in.controller.order;

import br.com.grupo63.techchallenge.adapter.in.controller.AbstractController;
import br.com.grupo63.techchallenge.adapter.in.controller.dto.DefaultResponseDTO;
import br.com.grupo63.techchallenge.adapter.in.controller.order.dto.CreateOrderRequestDTO;
import br.com.grupo63.techchallenge.core.application.usecase.dto.OrderDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.core.application.usecase.order.IOrderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pedidos", description = "Gerencia o processo de pedidos.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class OrderController extends AbstractController {

    private final IOrderUseCase orderUseCase;

    @Operation(
            tags = "5ª chamada - fluxo principal",
            summary = "Listar pedidos pagos porém não finalizados.",
            description = "Seria utilizado pelo monitor no restaurante para exibir os pedidos em preparação e prontos")
    @GetMapping("/listar-fila")
    public ResponseEntity<List<OrderDTO>> listUnfinishedOrders() {
        return ResponseEntity.ok(orderUseCase.listUnfinishedOrders());
    }

    @Operation(
            tags = "5ª chamada - fluxo principal",
            summary = "Avançar com o status do pedido.",
            description = "Após o pedido ser recebido, esse endpoint seria utilizado pelo funcionário para avançar o status do pedido")
    @PostMapping("/avancar-estado")
    @ResponseStatus(HttpStatus.OK)
    public void advanceOrderStatusFromOrderId(@Parameter(description = "Id do pedido.") @RequestParam Long orderId) throws NotFoundException, ValidationException {
        orderUseCase.advanceStatus(orderId);
    }

    @Operation(
            tags = "2ª chamada - fluxo principal",
            summary = "Fake checkout: Tela de resumo do pedido",
            description = "Registra um pedido a ser realizado, retorna o valor total")
    @PostMapping("/criar")
    public ResponseEntity<OrderDTO> create(@RequestParam Long clientId,
                                           @RequestBody CreateOrderRequestDTO createOrderRequestDTO) throws NotFoundException {

        return ResponseEntity.ok(orderUseCase.create(createOrderRequestDTO.toDomainDto(clientId)));
    }

    @Operation(
            tags = { "3ª chamada - fluxo principal", "5ª chamada - fluxo principal" },
            summary = "Get a order by it's id",
            description = "Find a order by their id.")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> read(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(orderUseCase.read(id));
    }

    @Operation(
            summary = "List all order",
            description = "List all orders.")
    @GetMapping("/listar")
    public ResponseEntity<List<OrderDTO>> list() {
        return ResponseEntity.ok(orderUseCase.list());
    }

    @Operation(
            summary = "Update an order",
            description = "Update an order in the database with the DTO data.")
//    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@RequestBody OrderDTO dto, @PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(orderUseCase.update(dto, id));
    }

    @Operation(
            summary = "Delete a client",
            description = "Delete a client in the database by their id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponseDTO> delete(@PathVariable("id") Long id) throws NotFoundException {
        orderUseCase.delete(id);
        return ResponseEntity.ok().build();
    }

}
