package br.com.grupo63.techchallenge.core.application.usecase.product;

import br.com.grupo63.techchallenge.core.application.usecase.ICRUDUseCase;
import br.com.grupo63.techchallenge.core.application.usecase.dto.ProductDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface IProductUseCase extends ICRUDUseCase<ProductDTO> {

    List<ProductDTO> listByCategoryName(String categoryName);

}
