package br.com.grupo63.techchallenge.adapter;

import br.com.grupo63.techchallenge.controller.dto.ProductControllerDTO;
import br.com.grupo63.techchallenge.entity.product.Category;
import br.com.grupo63.techchallenge.entity.product.Product;

public class ProductAdapter {

    public static ProductControllerDTO toDto(Product entity) {
        ProductControllerDTO dto = new ProductControllerDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setCategory(entity.getCategory().getId());

        return dto;
    }

    public static void fillEntity(ProductControllerDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());

        if (dto.getCategory() != null) {
            entity.setCategory(new Category(dto.getCategory()));
        }
    }

}
