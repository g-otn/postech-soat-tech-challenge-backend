package br.com.grupo63.techchallenge.api.controller.client;

import br.com.grupo63.techchallenge.api.controller.AbstractAPIController;
import br.com.grupo63.techchallenge.api.controller.dto.DefaultResponseDTO;
import br.com.grupo63.techchallenge.usecase.client.IClientUseCase;
import br.com.grupo63.techchallenge.controller.dto.ClientControllerDTO;
import br.com.grupo63.techchallenge.usecase.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "CRUD de clientes permitindo identificação e gerenciamento")
@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClientAPIController extends AbstractAPIController {

    private final IClientUseCase useCase;

    @Operation(
            summary = "Recuperar cliente por CPF",
            description = "Exibe os dados de um cliente a partir de seu CPF")
    @GetMapping("/cpf/{nationalId}")
    public ClientControllerDTO findByNationalId(@PathVariable String nationalId) throws NotFoundException {
        return useCase.getByNationalId(nationalId);
    }

    @Operation(
            tags = "1ª chamada - Fluxo principal - Pedido",
            summary = "Identificação: Identifica um cliente",
            description = "Registra um cliente através de seu CPF")
    @PostMapping("/criar")
    public ResponseEntity<ClientControllerDTO> create(@RequestParam String nationalId) throws NotFoundException {
        ClientControllerDTO clientDTO = new ClientControllerDTO(nationalId);
        return ResponseEntity.ok(useCase.create(clientDTO));
    }

    @Operation(
            summary = "Recuperar cliente",
            description = "Exibe os dados de um produto a partir de seu id")
    @GetMapping("/{id}")
    public ResponseEntity<ClientControllerDTO> read(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(useCase.read(id));
    }

    @Operation(
            summary = "Listar clientes",
            description = "Lista todos os clientes")
    @GetMapping("/listar")
    public ResponseEntity<List<ClientControllerDTO>> list() {
        return ResponseEntity.ok(useCase.list());
    }

    @Operation(
            summary = "Atualizar cliente",
            description = "Atualiza um cliente por id com os dados enviados")
    @PutMapping("/{id}")
    public ResponseEntity<ClientControllerDTO> update(@Valid @RequestBody ClientControllerDTO dto, @PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(useCase.update(dto, id));
    }

    @Operation(
            summary = "Excluir cliente",
            description = "Exclui um cliente por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponseDTO> delete(@PathVariable("id") Long id) throws NotFoundException {
        useCase.delete(id);
        return ResponseEntity.ok().build();
    }


}
