package br.com.grupo63.techchallenge.core.application.usecase;

import br.com.grupo63.techchallenge.adapter.driven.infrastructure.ProductRepository;
import br.com.grupo63.techchallenge.core.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ProductUseCase implements ICRUDUseCase<Product> {

    private final ProductRepository repository;

    @Override
    public Product create(Product product) {
        return product;
    }

    public Optional<Product> read() {
        return Optional.empty();
    }

    public List<Product> list() {
        return new ArrayList<>();
    }

    public Product update() {
        return null;
    }

    public void delete() {
        //
    }
}
