package br.com.grupo63.techchallenge.core.application.repository;

import br.com.grupo63.techchallenge.core.domain.entity.Product;

import java.util.Optional;

public interface IProductRepository extends IRepository<Product> {

    Product saveAndFlush(Product product);

    Optional<Product> findById(Long id);

}
