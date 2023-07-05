package br.com.grupo63.techchallenge.adapter.out.repository;

import br.com.grupo63.techchallenge.core.domain.entity.Product;
import br.com.grupo63.techchallenge.core.application.repository.IProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, IProductRepository {
}
