package br.com.grupo63.techchallenge.core.application.repository;

import br.com.grupo63.techchallenge.core.domain.model.product.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends IRepository<Product> {

    Product saveAndFlush(Product product);

    Optional<Product> findByIdAndDeletedFalse(Long id);

    List<Product> findByDeletedFalseAndCategory_Name(String categoryName);
}
