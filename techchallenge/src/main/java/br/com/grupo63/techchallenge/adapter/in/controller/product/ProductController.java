package br.com.grupo63.techchallenge.adapter.in.controller.product;

import br.com.grupo63.techchallenge.adapter.in.controller.IController;
import br.com.grupo63.techchallenge.adapter.in.controller.dto.DefaultResponseDTO;
import br.com.grupo63.techchallenge.adapter.in.controller.product.dto.ProductDTO;
import br.com.grupo63.techchallenge.core.application.usecase.ProductUseCase;
import br.com.grupo63.techchallenge.core.domain.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProductController implements IController {

    private final ProductUseCase useCase;

    @Operation(
            summary = "Registers a product",
            description = "Register a new product in the database with the DTO data." )
    @PostMapping("/criar")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) {
        Product product = useCase.create(dto.toDomain());
        return ResponseEntity.ok(ProductDTO.toDto(product));
    }

    @Operation(
            summary = "Get a product by it's id",
            description = "Find a product by their id." )
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> read(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ProductDTO.toDto(useCase.read(id).orElseThrow()));
    }

    @Operation(
            summary = "List all products",
            description = "List all products." )
    @GetMapping("/listar")
    public ResponseEntity<List<ProductDTO>> list() {
        return ResponseEntity.ok(useCase.list().stream().map(ProductDTO::toDto).collect(Collectors.toList()));
    }

    @Operation(
            summary = "Update a product",
            description = "Update a product in the database with the DTO data." )
    @PostMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO dto, @PathVariable("id") Long id) {
        Product product = useCase.update(dto.toDomain(), id);
        return ResponseEntity.ok(ProductDTO.toDto(product));
    }

    @Operation(
            summary = "Delete a product",
            description = "Delete a product in the database by their id." )
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponseDTO> delete(@PathVariable("id") Long id) {
        useCase.delete(id);
        return ResponseEntity.ok(new DefaultResponseDTO());
    }
}
