package br.com.grupo63.techchallenge.adapter.out.repository.product;

import br.com.grupo63.techchallenge.adapter.out.repository.product.entity.ProductEntity;
import br.com.grupo63.techchallenge.core.application.repository.IProductRepository;
import br.com.grupo63.techchallenge.core.domain.model.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductJpaAdapter implements IProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product saveAndFlush(Product product) {
        ProductEntity entity = new ProductEntity(product);

        entity = productJpaRepository.saveAndFlush(entity);

        return entity.toModel();
    }

    @Override
    public Optional<Product> findByIdAndDeletedFalse(Long id) {
        return productJpaRepository.findByIdAndDeletedFalse(id).map(ProductEntity::toModel);
    }

    @Override
    public List<Product> findByDeletedFalseAndCategory_Name(String categoryName) {
        return productJpaRepository.findByDeletedFalseAndCategory_Name(categoryName)
                .stream()
                .map(ProductEntity::toModel)
                .toList();
    }

    @Override
    public List<Product> findByDeletedFalse() {
        return productJpaRepository.findByDeletedFalse()
                .stream()
                .map(ProductEntity::toModel)
                .toList();
    }
}
