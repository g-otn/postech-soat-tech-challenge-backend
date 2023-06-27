package br.com.grupo63.techchallenge.adapter.driver.controller.product.dto;

import br.com.grupo63.techchallenge.core.domain.entity.Product;

public class ProductDTO {

    public Product toDomain() {
        return new Product();
    }

    public static ProductDTO toDto(Product product) {
        return new ProductDTO();
    }

}
