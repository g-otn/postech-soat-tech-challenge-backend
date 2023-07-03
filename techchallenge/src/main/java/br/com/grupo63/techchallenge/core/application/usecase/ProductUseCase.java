package br.com.grupo63.techchallenge.core.application.usecase;

import br.com.grupo63.techchallenge.adapter.out.infrastructure.ProductRepository;
import br.com.grupo63.techchallenge.core.application.repository.IProductRepository;
import br.com.grupo63.techchallenge.core.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ProductUseCase implements ICRUDUseCase<Product> {

    private final IProductRepository repository;

    @Override
    public Product create(Product product) {
        return repository.saveAndFlush(product);
    }

    public Optional<Product> read(Long id) {
        return repository.findById(id);
    }

    public List<Product> list() {
        return repository.findByDeletedFalse();
    }

    public Product update(Product product, Long id) {
        return repository.saveAndFlush(product);
    }

    public void delete(Long id) {
        repository.removeLogicallyById(id);
    }
}
