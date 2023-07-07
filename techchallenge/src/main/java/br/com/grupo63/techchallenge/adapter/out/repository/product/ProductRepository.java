package br.com.grupo63.techchallenge.adapter.out.repository.product;

import br.com.grupo63.techchallenge.adapter.out.repository.product.entity.ProductEntity;
import br.com.grupo63.techchallenge.core.application.repository.IProductRepository;
import br.com.grupo63.techchallenge.core.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, IProductRepository {

    Optional<ProductEntity> findByIdAndDeleted(Long id, boolean deleted);
    List<ProductEntity> findByDeletedAndCategory_Id(boolean deleted, Long categoryId);
    List<ProductEntity> findByDeleted(boolean deleted);

    @Override
    default Product saveAndFlush(Product product) {
        ProductEntity entity = new ProductEntity(product);

        entity = this.saveAndFlush(entity);

        return entity.toModel();
    }

    @Override
    default Optional<Product> findByIdAndDeletedFalse(Long id) {
        return this.findByIdAndDeleted(id, false).map(ProductEntity::toModel);

    }

    @Override
    default List<Product> findByDeletedFalseAndCategory_Id(Long categoryId) {
        return this.findByDeletedAndCategory_Id(false, categoryId)
                .stream()
                .map(ProductEntity::toModel)
                .toList();
    }

    @Override
    default List<Product> findByDeletedFalse() {
        return this.findByDeleted(false)
                .stream()
                .map(ProductEntity::toModel)
                .toList();
    }

}
