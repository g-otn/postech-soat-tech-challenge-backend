package br.com.grupo63.techchallenge.adapter.in.controller.client;

import br.com.grupo63.techchallenge.adapter.in.controller.AbstractController;
import br.com.grupo63.techchallenge.adapter.in.controller.dto.DefaultResponseDTO;
import br.com.grupo63.techchallenge.core.application.usecase.client.ClientUseCase;
import br.com.grupo63.techchallenge.core.application.usecase.dto.ClientDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
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
public class ClientController extends AbstractController {

    private final ClientUseCase useCase;

    @Operation(
            summary = "Find a client by their national id",
            description = "Get client data by their national id.")
    @GetMapping("/{nationalId}")
    public ClientDTO findByNationalId(@PathVariable String nationalId) {
        return useCase.getByNationalId(nationalId);
    }

    @Operation(
            summary = "Registers a client",
            description = "Register a new client in the database with the DTO data.")
    @PostMapping("/criar")
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO dto) {
        return ResponseEntity.ok(useCase.create(dto));
    }

    @Operation(
            summary = "Get a client by it's id",
            description = "Find a client by their id.")
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> read(@PathVariable("id") Long id) {
        return ResponseEntity.ok(useCase.read(id));
    }

    @Operation(
            summary = "List all clients",
            description = "List all clients.")
    @GetMapping("/listar")
    public ResponseEntity<List<ClientDTO>> list() {
        return ResponseEntity.ok(useCase.list());
    }

    @Operation(
            summary = "Update a client",
            description = "Update a client in the database with the DTO data.")
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO dto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(useCase.update(dto, id));
    }

    @Operation(
            summary = "Delete a client",
            description = "Delete a client in the database by their id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponseDTO> delete(@PathVariable("id") Long id) throws NotFoundException {
        useCase.delete(id);
        return ResponseEntity.ok(new DefaultResponseDTO());
    }


}
