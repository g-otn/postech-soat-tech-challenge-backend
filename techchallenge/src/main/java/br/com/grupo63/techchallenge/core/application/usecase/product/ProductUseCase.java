package br.com.grupo63.techchallenge.core.application.usecase.product;

import br.com.grupo63.techchallenge.core.application.repository.IProductRepository;
import br.com.grupo63.techchallenge.core.application.usecase.ICRUDUseCase;
import br.com.grupo63.techchallenge.core.application.usecase.dto.ProductDTO;
import br.com.grupo63.techchallenge.core.application.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.core.domain.model.product.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ProductUseCase implements IProductUseCase {

    private final IProductRepository repository;

    @Override
    public ProductDTO create(@Valid ProductDTO productDTO) {
        Product product = new Product();

        productDTO.fillDomain(product);

        return ProductDTO.toDto(repository.saveAndFlush(product));
    }

    public ProductDTO read(Long id) throws NotFoundException {
        Product product = repository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        return ProductDTO.toDto(product);
    }

    public List<ProductDTO> list() {
        return repository.findByDeletedFalse().stream().map(ProductDTO::toDto).toList();
    }

    public ProductDTO update(@Valid ProductDTO productDTO, Long id) throws NotFoundException {
        Product product = repository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        productDTO.fillDomain(product);

        return ProductDTO.toDto(repository.saveAndFlush(product));
    }

    public void delete(Long id) throws NotFoundException {
        Product product = repository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        product.delete();

        repository.saveAndFlush(product);
    }

    public List<ProductDTO> listByCategoryName(String categoryName) {
        return repository.findByDeletedFalseAndCategory_Name(categoryName).stream().map(ProductDTO::toDto).toList();
    }
}
