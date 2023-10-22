package br.com.grupo63.techchallenge.presenter;

import br.com.grupo63.techchallenge.controller.dto.ProductControllerDTO;
import br.com.grupo63.techchallenge.entity.product.Product;

public class ProductPresenter {

    public static ProductControllerDTO toDto(Product entity) {
    ProductControllerDTO dto = new ProductControllerDTO();

    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setPrice(entity.getPrice());
    dto.setCategory(entity.getCategory().getId());

    return dto;
}

}
