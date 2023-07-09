package br.com.grupo63.techchallenge.adapter.in.controller.order;

import br.com.grupo63.techchallenge.adapter.in.controller.AbstractController;
import br.com.grupo63.techchallenge.adapter.in.controller.dto.DefaultResponseDTO;
import br.com.grupo63.techchallenge.adapter.in.controller.order.dto.AdvanceOrderStatusResponseDTO;
import br.com.grupo63.techchallenge.adapter.in.controller.order.dto.CreateOrderRequestDTO;
import br.com.grupo63.techchallenge.core.application.usecase.dto.OrderDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.exception.ValidationException;
import br.com.grupo63.techchallenge.core.application.usecase.order.IOrderUseCase;
import br.com.grupo63.techchallenge.core.domain.model.order.OrderStatus;
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

    private final IOrderUseCase useCase;

    @Operation(
            tags = "5ª chamada - Fluxo principal - Acompanhamento e entrega",
            summary = "Listar pedidos pagos porém não finalizados",
            description = "Seria utilizado para acompanha os pedido Recebidos, Em preparação e Prontos")
    @GetMapping("/listar-fila")
    public ResponseEntity<List<OrderDTO>> listUnfinishedOrders() {
        return ResponseEntity.ok(useCase.listUnfinishedOrders());
    }

    @Operation(
            tags = "5ª chamada - Fluxo principal - Acompanhamento e entrega",
            summary = "Avança com o status do pedido",
            description = "Após o pedido ser recebido, esse endpoint seria utilizado pelo funcionário para avançar o status do pedido")
    @PostMapping("/avancar-status")
    public AdvanceOrderStatusResponseDTO advanceOrderStatusFromOrderId(@Parameter(description = "Id do pedido.") @RequestParam Long orderId) throws NotFoundException, ValidationException {
        return new AdvanceOrderStatusResponseDTO(useCase.advanceStatus(orderId));
    }

    @Operation(
            tags = "2ª chamada - Fluxo principal - Pedido",
            summary = "Fake checkout: Tela de resumo do pedido",
            description = "Registra um pedido a ser realizado, retorna o valor total")
    @PostMapping("/criar")
    public ResponseEntity<OrderDTO> create(@RequestParam Long clientId,
                                           @RequestBody CreateOrderRequestDTO createOrderRequestDTO) throws NotFoundException {

        return ResponseEntity.ok(useCase.create(createOrderRequestDTO.toDomainDto(clientId)));
    }

    @Operation(
            tags = { "3ª chamada - Fluxo principal - Pagamento", "5ª chamada - Fluxo principal - Acompanhamento e entrega" },
            summary = "Recupera pedido",
            description = "Exibe os dados de um pedido a partir de seu id")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> read(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(useCase.read(id));
    }

    @Operation(
            summary = "Listar pedidos",
            description = "Lista todos os pedidos")
    @GetMapping("/listar")
    public ResponseEntity<List<OrderDTO>> list() {
        return ResponseEntity.ok(useCase.list());
    }

//    @Operation(
//            summary = "Atualizar pedido",
//            description = "Atualiza um pedido por id com os dados enviados")
//    @PutMapping("/{id}")
//    public ResponseEntity<OrderDTO> update(@RequestBody OrderDTO dto, @PathVariable("id") Long id) throws NotFoundException {
//        return ResponseEntity.ok(useCase.update(dto, id));
//    }

    @Operation(
            summary = "Excluir cliente",
            description = "Exclui um cliente por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponseDTO> delete(@PathVariable("id") Long id) throws NotFoundException {
        useCase.delete(id);
        return ResponseEntity.ok().build();
    }

}
