package br.com.grupo63.techchallenge.usecase.product;

import br.com.grupo63.techchallenge.entity.product.Product;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.exception.ValidationException;
import br.com.grupo63.techchallenge.gateway.product.IProductGateway;

import java.util.List;

public interface IProductUseCase {

    Product create(Product entity, IProductGateway gateway) throws ValidationException;

    Product read(Long id, IProductGateway gateway) throws NotFoundException;

    List<Product> list(IProductGateway gateway);

    Product update(Product entity, IProductGateway gateway) throws NotFoundException, ValidationException;

    void delete(Product entity, IProductGateway gateway);

    List<Product> listByCategoryName(String categoryName, IProductGateway gateway);

}
