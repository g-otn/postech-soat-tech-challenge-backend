package br.com.grupo63.techchallenge.gateway.product;

import br.com.grupo63.techchallenge.gateway.product.entity.ProductPersistenceEntity;
import br.com.grupo63.techchallenge.gateway.repository.IJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductPersistenceEntity, Long>, IJpaRepository<ProductPersistenceEntity> {

    List<ProductPersistenceEntity> findByDeletedFalseAndCategory_Name(String categoryName);

}
