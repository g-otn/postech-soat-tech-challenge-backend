package br.com.grupo63.techchallenge.usecase.product;

import br.com.grupo63.techchallenge.gateway.IPersistenceEntityRepository;
import br.com.grupo63.techchallenge.usecase.ICRUDUseCase;
import br.com.grupo63.techchallenge.controller.dto.ProductControllerDTO;

import java.util.List;

public interface IProductUseCase extends ICRUDUseCase<ProductControllerDTO> {

    List<ProductControllerDTO> listByCategoryName(String categoryName, IPersistenceEntityRepository gateway);

}
