package br.com.grupo63.techchallenge.api.controller.product;

import br.com.grupo63.techchallenge.api.controller.AbstractAPIController;
import br.com.grupo63.techchallenge.api.controller.dto.DefaultResponseDTO;
import br.com.grupo63.techchallenge.controller.dto.ProductControllerDTO;
import br.com.grupo63.techchallenge.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.usecase.product.IProductUseCase;
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
public class ProductAPIController extends AbstractAPIController {

    private final IProductUseCase useCase;

    @Operation(
            tags = "2ª chamada - Fluxo principal - Pedido",
            summary = "Listar produtos por categoria",
            description = "Lista todos os produtos por nome da categoria")
    @GetMapping("/listar-por-categoria")
    public ResponseEntity<List<ProductControllerDTO>> listByCategoryName(
            @Schema(allowableValues = {"Lanche", "Acompanhamento", "Bebida", "Sobremesa"})
            @RequestParam(value = "categoria") String categoryName) {
        return ResponseEntity.ok(useCase.listByCategoryName(categoryName));
    }

    @Operation(
            summary = "Criar um produto",
            description = "Cria um produto com nome, preço, estoque inicial e categoria. Possíveis categorias (IDs): " +
                    "1 - Lanche, 2 - Acompanhamento, 3 - Bebida, 4 - Sobremesa")
    @PostMapping("/criar")
    public ResponseEntity<ProductControllerDTO> create(@Valid @RequestBody ProductControllerDTO dto) throws NotFoundException {
        return ResponseEntity.ok(useCase.create(dto));
    }

    @Operation(
            summary = "Recuperar produto",
            description = "Exibe os dados de um produto a partir de seu id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductControllerDTO> read(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(useCase.read(id));
    }

    @Operation(
            summary = "Listar produtos",
            description = "Lista todos os produtos")
    @GetMapping("/listar")
    public ResponseEntity<List<ProductControllerDTO>> list() {
        return ResponseEntity.ok(useCase.list());
    }

    @Operation(
            summary = "Atualizar produto",
            description = "Atualiza um produto por id com os dados enviados")
    @PutMapping("/{id}")
    public ResponseEntity<ProductControllerDTO> update(@Valid @RequestBody ProductControllerDTO dto, @PathVariable("id") Long id) throws NotFoundException {
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
