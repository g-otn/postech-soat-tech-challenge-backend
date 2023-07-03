package br.com.grupo63.techchallenge.adapter.in.controller.product.dto;

import br.com.grupo63.techchallenge.core.domain.entity.Category;
import br.com.grupo63.techchallenge.core.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProductDTO {

    private String name;
    private Double price;
    private Double quantity;
    private Long category;

    public Product toDomain() {
        return new Product(name, price, quantity, new Category(category));
    }

    public static ProductDTO toDto(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setName(productDTO.getName());

        return new ProductDTO();
    }
}
