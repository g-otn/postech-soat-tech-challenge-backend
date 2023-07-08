package br.com.grupo63.techchallenge.core.application.usecase.dto;

import br.com.grupo63.techchallenge.core.domain.model.product.Category;
import br.com.grupo63.techchallenge.core.domain.model.product.Product;
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

public class ProductDTO extends AbstractUseCaseDomainDTO<Product> {

    @Schema(example = "X-Bacon")
    @NotEmpty(message = "product.name.notEmpty")
    @NotNull(message = "product.name.notNull")
    private String name;

    @Schema(example = "12.5")
    @NotNull(message = "product.price.notNull")
    @Min(message = "product.price.notLessThanZero", value = 0)
    private Double price;

    @Schema(example = "1")
    @NotNull(message = "product.category.notNull")
    @Min(message = "product.category.notLessThanOne", value = 1)
    private Long category;

    public static ProductDTO toDto(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategory(product.getCategory().getId());

        return productDTO;
    }

    public void fillDomain(Product product) {
        product.setName(name);
        product.setPrice(price);
        product.setCategory(new Category(category));
    }
}
