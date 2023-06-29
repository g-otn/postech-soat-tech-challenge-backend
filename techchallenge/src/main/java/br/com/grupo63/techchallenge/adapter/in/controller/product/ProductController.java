package br.com.grupo63.techchallenge.adapter.in.controller.product;

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

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductUseCase useCase;

    @Operation(
            summary = "Registers a product",
            description = "Register a new product in the database with the DTO data." )
    @PostMapping("/create")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) {
        Product product = useCase.create(dto.toDomain());
        return ResponseEntity.ok(ProductDTO.toDto(product));
    }

    @ExceptionHandler
    public ResponseEntity<DefaultResponseDTO> handleException(Exception exception) {

        DefaultResponseDTO responseDTO = new DefaultResponseDTO();

        if (exception instanceof HttpClientErrorException.NotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }

        return ResponseEntity.ok().build();
    }

}
