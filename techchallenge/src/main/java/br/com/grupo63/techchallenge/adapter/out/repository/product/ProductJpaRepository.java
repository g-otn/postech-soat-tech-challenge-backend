package br.com.grupo63.techchallenge.adapter.out.repository.product;

import br.com.grupo63.techchallenge.adapter.out.repository.IJpaRepository;
import br.com.grupo63.techchallenge.adapter.out.repository.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long>, IJpaRepository<ProductEntity> {

    List<ProductEntity> findByDeletedFalseAndCategory_Name(String categoryName);

}
