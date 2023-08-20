package br.com.grupo63.techchallenge.usecase.product;

import br.com.grupo63.techchallenge.entity.product.Product;
import br.com.grupo63.techchallenge.usecase.exception.NotFoundException;
import br.com.grupo63.techchallenge.gateway.repository.IProductRepository;
import br.com.grupo63.techchallenge.controller.dto.ProductControllerDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ProductUseCase implements IProductUseCase {

    private final IProductRepository repository;

    @Override
    public ProductControllerDTO create(@Valid ProductControllerDTO productUseCaseDTO) {
        Product product = new Product();

        productUseCaseDTO.fillDomain(product);

        return ProductControllerDTO.toDto(repository.saveAndFlush(product));
    }

    public ProductControllerDTO read(Long id) throws NotFoundException {
        Product product = repository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        return ProductControllerDTO.toDto(product);
    }

    public List<ProductControllerDTO> list() {
        return repository.findByDeletedFalse().stream().map(ProductControllerDTO::toDto).toList();
    }

    public ProductControllerDTO update(@Valid ProductControllerDTO productUseCaseDTO, Long id) throws NotFoundException {
        Product product = repository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        productUseCaseDTO.fillDomain(product);

        return ProductControllerDTO.toDto(repository.saveAndFlush(product));
    }

    public void delete(Long id) throws NotFoundException {
        Product product = repository.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);

        product.delete();

        repository.saveAndFlush(product);
    }

    public List<ProductControllerDTO> listByCategoryName(String categoryName) {
        return repository.findByDeletedFalseAndCategory_Name(categoryName).stream().map(ProductControllerDTO::toDto).toList();
    }
}
