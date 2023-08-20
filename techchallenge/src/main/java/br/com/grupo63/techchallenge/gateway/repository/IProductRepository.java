package br.com.grupo63.techchallenge.gateway.repository;

import br.com.grupo63.techchallenge.entity.product.Product;
import br.com.grupo63.techchallenge.gateway.IPersistenceEntityRepository;

import java.util.List;
import java.util.Optional;

public interface IProductRepository extends IPersistenceEntityRepository<Product> {

    Product saveAndFlush(Product product);

    Optional<Product> findByIdAndDeletedFalse(Long id);

    List<Product> findByDeletedFalseAndCategory_Name(String categoryName);
}
