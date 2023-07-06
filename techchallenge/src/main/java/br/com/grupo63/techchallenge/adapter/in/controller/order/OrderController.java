package br.com.grupo63.techchallenge.adapter.in.controller.order;

import br.com.grupo63.techchallenge.adapter.in.controller.AbstractController;
import br.com.grupo63.techchallenge.adapter.in.controller.dto.DefaultResponseDTO;
import br.com.grupo63.techchallenge.core.application.usecase.dto.OrderDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.order.IOrderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
            summary = "Orders: Listar pedidos não finalizados.",
            description = "Returns all unfinished orders." )
    @GetMapping("/listar-fila")
    public ResponseEntity<List<OrderDTO>> listUnfinishedOrders() {
        return ResponseEntity.ok(orderUseCase.listUnfinishedOrders());
    }

    @Operation(
            summary = "Orders: Avançar com o status do pedido.",
            description = "Should be called to advance a specific order status.")
    @PostMapping("/avancar-estado")
    @ResponseStatus(HttpStatus.OK)
    public void advanceOrderStatusFromOrderId(@Parameter(description = "Id do pedido.") @RequestParam Long orderId) throws NotFoundException {
        orderUseCase.advanceOrderStatus(orderId);
    }

    @Operation(
            summary = "Registers an order",
            description = "Register a new order in the database with the DTO data.")
    @PostMapping("/criar")
    public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO dto) {
        return ResponseEntity.ok(orderUseCase.create(dto));
    }

    @Operation(
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
    @PutMapping("/{id}")
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
