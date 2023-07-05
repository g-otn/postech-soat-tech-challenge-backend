package br.com.grupo63.techchallenge.core.application.usecase.product;

import br.com.grupo63.techchallenge.core.application.repository.IProductRepository;
import br.com.grupo63.techchallenge.core.application.usecase.ICRUDUseCase;
import br.com.grupo63.techchallenge.core.application.usecase.dto.ProductDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.domain.entity.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class ProductUseCase implements ICRUDUseCase<ProductDTO> {

    private final IProductRepository repository;

    @Override
    public ProductDTO create(@Valid ProductDTO productDTO) {
        Product product = new Product();

        productDTO.toDomain(product);

        return ProductDTO.toDto(repository.saveAndFlush(product));
    }

    public ProductDTO read(Long id) throws NotFoundException {
        Product product = repository.findById(id).orElseThrow(NotFoundException::new);

        return ProductDTO.toDto(product);
    }

    public List<ProductDTO> list() {
        return repository.findByDeletedFalse().stream().map(ProductDTO::toDto).toList();
    }

    public ProductDTO update(@Valid ProductDTO productDTO, Long id) throws NotFoundException {
        Product product = repository.findById(id).orElseThrow(NotFoundException::new);

        productDTO.toDomain(product);

        return ProductDTO.toDto(repository.saveAndFlush(product));
    }

    public void delete(Long id) throws NotFoundException {
        Product product = repository.findById(id).orElseThrow(NotFoundException::new);

        product.delete();

        repository.saveAndFlush(product);
    }
}
