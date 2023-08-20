package br.com.grupo63.techchallenge.controller.dto;

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

public class ProductControllerDTO extends AbstractControllerDTO {

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

}
