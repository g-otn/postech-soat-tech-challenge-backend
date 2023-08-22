package br.com.grupo63.techchallenge.usecase.product;

import br.com.grupo63.techchallenge.entity.product.Product;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.exception.ValidationException;
import br.com.grupo63.techchallenge.gateway.repository.IProductRepository;

import java.util.List;

public interface IProductUseCase {

    Product create(Product entity, IProductRepository gateway) throws ValidationException;

    Product read(Long id, IProductRepository gateway) throws NotFoundException;

    List<Product> list(IProductRepository gateway);

    Product update(Product entity, IProductRepository gateway) throws NotFoundException, ValidationException;

    void delete(Product entity, IProductRepository gateway);

    List<Product> listByCategoryName(String categoryName, IProductRepository gateway);

}
