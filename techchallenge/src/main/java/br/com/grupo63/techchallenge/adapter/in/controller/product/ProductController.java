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
            tags = "2ª chamada - Fluxo principal - Pedido",
            summary = "Listar produtos por categoria",
            description = "Lista todos os produtos por nome da categoria")
    @GetMapping("/listar-por-categoria")
    public ResponseEntity<List<ProductDTO>> listByCategoryName(
            @Schema(allowableValues = {"Lanche", "Acompanhamento", "Bebida", "Sobremesa"})
            @RequestParam(value = "categoria") String categoryName) {
        return ResponseEntity.ok(useCase.listByCategoryName(categoryName));
    }

    @Operation(
            summary = "Criar um produto",
            description = "Cria um produto com nome, preço, estoque inicial e categoria. Possíveis categorias (IDs): " +
                    "1 - Lanche, 2 - Acompanhamento, 3 - Bebida, 4 - Sobremesa")
    @PostMapping("/criar")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) throws NotFoundException {
        return ResponseEntity.ok(useCase.create(dto));
    }

    @Operation(
            summary = "Recuperar produto",
            description = "Exibe os dados de um produto a partir de seu id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> read(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(useCase.read(id));
    }

    @Operation(
            summary = "Listar produtos",
            description = "Lista todos os produtos")
    @GetMapping("/listar")
    public ResponseEntity<List<ProductDTO>> list() {
        return ResponseEntity.ok(useCase.list());
    }

    @Operation(
            summary = "Atualizar produto",
            description = "Atualiza um produto por id com os dados enviados")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto, @PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(useCase.update(dto, id));
    }

    @Operation(
            summary = "Excluir produto",
            description = "Exclui um produto por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponseDTO> delete(@PathVariable("id") Long id) throws NotFoundException {
        useCase.delete(id);
        return ResponseEntity.ok().build();
    }
}
