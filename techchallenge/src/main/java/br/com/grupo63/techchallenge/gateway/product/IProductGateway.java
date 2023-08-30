package br.com.grupo63.techchallenge.gateway.product;

import br.com.grupo63.techchallenge.entity.product.Product;
import br.com.grupo63.techchallenge.gateway.IPersistenceEntityGateway;

import java.util.List;
import java.util.Optional;

public interface IProductGateway extends IPersistenceEntityGateway<Product> {

    Product saveAndFlush(Product product);

    Optional<Product> findByIdAndDeletedFalse(Long id);

    List<Product> findByDeletedFalseAndCategory_Name(String categoryName);
}
