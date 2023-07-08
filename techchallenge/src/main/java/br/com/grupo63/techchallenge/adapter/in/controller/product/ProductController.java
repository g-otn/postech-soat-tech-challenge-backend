package br.com.grupo63.techchallenge.adapter.in.controller.product;

import br.com.grupo63.techchallenge.adapter.in.controller.AbstractController;
import br.com.grupo63.techchallenge.adapter.in.controller.dto.DefaultResponseDTO;
import br.com.grupo63.techchallenge.core.application.usecase.dto.ProductDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.application.usecase.product.IProductUseCase;
import br.com.grupo63.techchallenge.core.application.usecase.product.ProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Produtos", description = "CRUD de produtos para gerenciamento e exibição ao cliente")
@RequiredArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProductController extends AbstractController {

    private final IProductUseCase useCase;

    @Operation(
            summary = "Criar um produto",
            description = "Cria um produto com nome, preço, estoque inicial e categoria. Possíveis categorias (IDs): " +
                    "1 - Lanche, 2 - Acompanhamento, 3 - Bebida, 4 - Sobremesa")
    @PostMapping("/criar")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) throws NotFoundException {
        return ResponseEntity.ok(useCase.create(dto));
    }

    @Operation(
            summary = "Get a product by it's id",
            description = "Find a product by their id.")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> read(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(useCase.read(id));
    }

    @Operation(
            summary = "List all products",
            description = "List all products.")
    @GetMapping("/listar")
    public ResponseEntity<List<ProductDTO>> list() {
        return ResponseEntity.ok(useCase.list());
    }

    @Operation(
            summary = "Update a product",
            description = "Update a product in the database with the DTO data.")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO dto, @PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(useCase.update(dto, id));
    }

    @Operation(
            summary = "Delete a product",
            description = "Delete a product in the database by their id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponseDTO> delete(@PathVariable("id") Long id) throws NotFoundException {
        useCase.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Listar produtos por categoria",
            description = "Lista todos os produtos por nome da categoria")
    @GetMapping("/listar-por-categoria")
    public ResponseEntity<List<ProductDTO>> listByCategoryName(
            @Schema(allowableValues = {"Lanche", "Acompanhamento", "Bebida", "Sobremesa"})
            @RequestParam(value = "categoria") String categoryName) {
        return ResponseEntity.ok(useCase.listByCategoryName(categoryName));
    }
}
