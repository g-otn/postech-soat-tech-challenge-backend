package br.com.grupo63.techchallenge.core.application.usecase.dto;

import br.com.grupo63.techchallenge.core.domain.model.Category;
import br.com.grupo63.techchallenge.core.domain.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProductDTO {

    @Schema(defaultValue = "0")
    private Long id;

    @Schema(example = "X-Bacon")
    @NotEmpty(message = "product.name.notEmpty")
    @NotNull(message = "product.name.notNull")
    private String name;

    @Schema(example = "12.5")
    @NotNull(message = "product.price.notNull")
    @Min(message = "product.price.notLessThanZero", value = 0)
    private Double price;

    @Schema(example = "10.0")
    @NotNull(message = "product.quantity.notNull")
    @Min(message = "product.quantity.notLessThanZero", value = 0)
    private Double quantity;

    @Schema(example = "1")
    @NotNull(message = "product.category.notNull")
    @Min(message = "product.category.notLessThanOne", value = 1)
    private Long category;

    public static ProductDTO toDto(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setCategory(product.getCategory().getId());

        return productDTO;
    }

    public void toDomain(Product product) {
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setCategory(new Category(category));
    }
}
